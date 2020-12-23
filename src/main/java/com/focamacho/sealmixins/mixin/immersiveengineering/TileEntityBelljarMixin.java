package com.focamacho.sealmixins.mixin.immersiveengineering;

import blusunrize.immersiveengineering.ImmersiveEngineering;
import blusunrize.immersiveengineering.api.ApiUtils;
import blusunrize.immersiveengineering.api.energy.immersiveflux.FluxStorage;
import blusunrize.immersiveengineering.api.tool.BelljarHandler;
import blusunrize.immersiveengineering.common.Config;
import blusunrize.immersiveengineering.common.blocks.TileEntityIEBase;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityBelljar;
import blusunrize.immersiveengineering.common.util.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.ItemHandlerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static blusunrize.immersiveengineering.common.Config.IEConfig.Machines.belljar_growth_mod;
import static blusunrize.immersiveengineering.common.blocks.metal.TileEntityBelljar.*;

@Mixin(value = TileEntityBelljar.class, remap = false)
public abstract class TileEntityBelljarMixin extends TileEntityIEBase implements ITickable {

    @Shadow public int dummy;

    @Shadow private NonNullList<ItemStack> inventory;

    @Shadow public int fertilizerAmount;

    @Shadow public boolean renderActive;

    @Shadow public FluxStorage energyStorage;

    @Shadow public abstract BelljarHandler.IPlantHandler getCurrentPlantHandler();

    @Shadow public float renderGrowth;

    @Shadow public float fertilizerMod;

    @Shadow private float growth;

    @Shadow protected abstract void sendSyncPacket(int type);

    @Shadow public FluidTank tank;

    @Shadow public EnumFacing facing;

    @Override
    public void update()
    {
        ApiUtils.checkForNeedlessTicking((TileEntityBelljar)(Object)this);
        if(dummy!=0||world.isBlockPowered(getPos()))
            return;
        ItemStack soil = inventory.get(SLOT_SOIL);
        ItemStack seed = inventory.get(SLOT_SEED);
        if(getWorld().isRemote)
        {
            if(energyStorage.getEnergyStored() > Config.IEConfig.Machines.belljar_consumption&&fertilizerAmount > 0&&renderActive)
            {
                BelljarHandler.IPlantHandler handler = getCurrentPlantHandler();
                if(handler!=null&&handler.isCorrectSoil(seed, soil)&&fertilizerAmount > 0)
                {
                    if(renderGrowth < 1)
                    {
                        renderGrowth += belljar_growth_mod*handler.getGrowthStep(seed, soil, renderGrowth, this, fertilizerMod, true);
                        fertilizerAmount--;
                    }
                    else
                        renderGrowth = handler.resetGrowth(seed, soil, renderGrowth, this, true);
                    if(Utils.RAND.nextInt(8)==0)
                    {
                        double partX = getPos().getX()+.5;
                        double partY = getPos().getY()+2.6875;
                        double partZ = getPos().getZ()+.5;
                        ImmersiveEngineering.proxy.spawnRedstoneFX(getWorld(), partX, partY, partZ, .25, .25, .25, 1f, .55f, .1f, .1f);
                    }
                }
            }
        }
        else
        {
            if(!seed.isEmpty())
            {
                BelljarHandler.IPlantHandler handler = getCurrentPlantHandler();
                if(handler!=null&&handler.isCorrectSoil(seed, soil)&&fertilizerAmount > 0&&energyStorage.extractEnergy(Config.IEConfig.Machines.belljar_consumption, true)== Config.IEConfig.Machines.belljar_consumption)
                {
                    boolean consume = false;
                    if(growth >= 1)
                    {
                        ItemStack[] oldOutputs = handler.getOutput(seed, soil, this);
                        ItemStack[] newOutputs = new ItemStack[oldOutputs.length];

                        if(seed.getCount() > 1) {
                            for (int i = 0; i < oldOutputs.length; i++) {
                                ItemStack item = oldOutputs[i].copy();
                                int newSize = item.getCount() * seed.getCount();
                                if(newSize > 64) newSize = 64;
                                item.setCount(newSize);
                                newOutputs[i] = item;
                            }
                        } else {
                            newOutputs = oldOutputs;
                        }

                        int canFit = 0;
                        boolean[] emptySlotsUsed = new boolean[4];
                        for(ItemStack output : newOutputs)
                            if(!output.isEmpty())
                                for(int j = 3; j < 7; j++)
                                {
                                    ItemStack existing = inventory.get(j);
                                    if((existing.isEmpty()&&!emptySlotsUsed[j-3])||(ItemHandlerHelper.canItemStacksStack(existing, output)&&existing.getCount()+output.getCount() <= existing.getMaxStackSize()))
                                    {
                                        canFit++;
                                        if(existing.isEmpty())
                                            emptySlotsUsed[j-3] = true;
                                        break;
                                    }
                                }
                        if(canFit >= newOutputs.length)
                        {
                            for(ItemStack output : newOutputs)
                                for(int j = 3; j < 7; j++)
                                {
                                    ItemStack existing = inventory.get(j);
                                    if(existing.isEmpty())
                                    {
                                        inventory.set(j, output.copy());
                                        break;
                                    }
                                    else if(ItemHandlerHelper.canItemStacksStack(existing, output)&&existing.getCount()+output.getCount() <= existing.getMaxStackSize())
                                    {
                                        existing.grow(output.getCount());
                                        break;
                                    }
                                }
                            growth = handler.resetGrowth(seed, soil, growth, this, false);
                            consume = true;
                        }
                    }
                    else if(growth < 1)
                    {
                        growth += belljar_growth_mod*handler.getGrowthStep(seed, soil, growth, this, fertilizerMod, false);
                        consume = true;
                        if(world.getTotalWorldTime()%32==((getPos().getX()^getPos().getZ())&31))
                            sendSyncPacket(0);
                    }
                    if(consume)
                    {
                        energyStorage.extractEnergy(Config.IEConfig.Machines.belljar_consumption, false);
                        fertilizerAmount--;
                        if(!renderActive)
                        {
                            renderActive = true;
                            sendSyncPacket(0);
                        }
                    }
                    else if(renderActive)
                    {
                        renderActive = false;
                        sendSyncPacket(0);
                    }
                }
                else
                    growth = 0;

                if(fertilizerAmount <= 0&&tank.getFluidAmount() >= Config.IEConfig.Machines.belljar_fluid)
                {
                    BelljarHandler.FluidFertilizerHandler fluidFert = BelljarHandler.getFluidFertilizerHandler(tank.getFluid());
                    if(fluidFert!=null)
                    {
                        fertilizerMod = fluidFert.getGrowthMultiplier(tank.getFluid(), seed, soil, this);
                        tank.drain(Config.IEConfig.Machines.belljar_fluid, true);
                        ItemStack fertilizer = inventory.get(SLOT_FERTILIZER);
                        if(!fertilizer.isEmpty())
                        {
                            BelljarHandler.ItemFertilizerHandler itemFert = BelljarHandler.getItemFertilizerHandler(fertilizer);
                            if(itemFert!=null)
                                fertilizerMod *= itemFert.getGrowthMultiplier(fertilizer, seed, soil, this);
                            fertilizer.shrink(1);
                            if(fertilizer.getCount() <= 0)
                                inventory.set(2, ItemStack.EMPTY);
                        }
                        fertilizerAmount = Config.IEConfig.Machines.belljar_fertilizer;
                        sendSyncPacket(1);
                    }
                }
            }
            else
                growth = 0;

            if(world.getTotalWorldTime()%8==0)
            {
                BlockPos outputPos = getPos().up().offset(facing.getOpposite());
                TileEntity outputTile = Utils.getExistingTileEntity(world, outputPos);
                if(outputTile!=null)
                    for(int j = 3; j < 7; j++)
                    {
                        ItemStack outStack = inventory.get(j);
                        if(!outStack.isEmpty())
                        {
                            int outCount = Math.min(outStack.getCount(), 16);
                            ItemStack stack = Utils.copyStackWithAmount(outStack, outCount);
                            stack = Utils.insertStackIntoInventory(outputTile, stack, facing);
                            if(!stack.isEmpty())
                                outCount -= stack.getCount();
                            outStack.shrink(outCount);
                            if(outStack.getCount() <= 0)
                                this.inventory.set(j, ItemStack.EMPTY);
                        }
                    }
            }
        }
    }

}
