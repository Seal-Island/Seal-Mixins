package com.focamacho.sealmixins.mixin.draconicevolution;

import com.brandon3055.draconicevolution.items.tools.DislocatorAdvanced;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = DislocatorAdvanced.class, remap = false)
public class DislocatorAdvancedMixin {

    @Inject(method = "onLeftClickEntity", at = @At("HEAD"), cancellable = true)
    public void onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

}
