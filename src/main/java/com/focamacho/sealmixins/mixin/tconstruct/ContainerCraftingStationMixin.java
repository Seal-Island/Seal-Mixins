package com.focamacho.sealmixins.mixin.tconstruct;

import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.tools.common.inventory.ContainerCraftingStation;

@Mixin(value = ContainerCraftingStation.class, remap = false)
public class ContainerCraftingStationMixin {

    @Inject(method = "blacklisted", at = @At("HEAD"), cancellable = true)
    public void blacklisted(Class<? extends TileEntity> clazz, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

}
