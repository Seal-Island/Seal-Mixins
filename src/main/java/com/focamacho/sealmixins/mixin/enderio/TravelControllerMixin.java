package com.focamacho.sealmixins.mixin.enderio;

import crazypants.enderio.api.teleport.TravelSource;
import crazypants.enderio.base.teleport.TravelController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TravelController.class, remap = false)
public class TravelControllerMixin {

    @Inject(method = "travelToLocation", at = @At("HEAD"), cancellable = true)
    private static void travelToLocation(EntityPlayer player, ItemStack equipped, EnumHand hand, TravelSource source, BlockPos coord, boolean conserveMomentum, CallbackInfoReturnable<Boolean> cir) {
        if(!(source == TravelSource.STAFF || source == TravelSource.STAFF_BLINK)) return;

        net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(player, coord.getX(), coord.getY(), coord.getZ(), 0.0F);
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) cir.cancel();
    }

}
