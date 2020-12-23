package com.focamacho.sealmixins.mixin.immersiveengineering;

import blusunrize.immersiveengineering.common.gui.IESlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = IESlot.Belljar.class, remap = false)
public class IESlotBelljarMixin {

    @Shadow private int type;

    @Inject(method = "func_75219_a", at = @At("HEAD"), cancellable = true)
    private void getSlotStackLimit(CallbackInfoReturnable<Integer> info) {
        if(type == 1) info.setReturnValue(32);
    }

}
