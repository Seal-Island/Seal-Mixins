package com.focamacho.sealmixins.mixin.actuallyadditions;

import de.ellpeck.actuallyadditions.mod.items.ItemTeleStaff;
import de.ellpeck.actuallyadditions.mod.items.base.ItemEnergy;
import de.ellpeck.actuallyadditions.mod.util.WorldUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ItemTeleStaff.class, remap = false)
public abstract class ItemTeleStaffMixin extends ItemEnergy {

    public ItemTeleStaffMixin(int maxPower, int transfer, String name) {
        super(maxPower, transfer, name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!world.isRemote) {
            RayTraceResult pos = WorldUtil.getNearestPositionWithAir(world, player, 100);
            if (pos != null && (pos.typeOfHit == RayTraceResult.Type.BLOCK || player.rotationPitch >= -5)) {
                int side = pos.sideHit.ordinal();
                if (side != -1) {
                    double x = pos.hitVec.x - (side == 4 ? 0.5 : 0) + (side == 5 ? 0.5 : 0);
                    double y = pos.hitVec.y - (side == 0 ? 2.0 : 0) + (side == 1 ? 0.5 : 0);
                    double z = pos.hitVec.z - (side == 2 ? 0.5 : 0) + (side == 3 ? 0.5 : 0);
                    int baseUse = 200;
                    int use = baseUse + (int) (baseUse * pos.hitVec.distanceTo(new Vec3d(player.posX, player.posY + (player.getEyeHeight() - player.getDefaultEyeHeight()), player.posZ)));
                    if (this.getEnergyStored(stack) >= use) {
                        net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(player, x, y, z, 0.0F);
                        if (!net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) {
                            ((EntityPlayerMP) player).connection.setPlayerLocation(x, y, z, player.rotationYaw, player.rotationPitch);
                            player.dismountRidingEntity();
                            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                            if (!player.capabilities.isCreativeMode) {
                                this.extractEnergyInternal(stack, use, false);
                                player.getCooldownTracker().setCooldown(this, 50);
                            }
                            return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
                        }
                    }
                }
            }
        }

        player.swingArm(hand);
        return ActionResult.newResult(EnumActionResult.FAIL, stack);
    }

}
