package com.focamacho.sealmixins.mixin.techguns;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import techguns.damagesystem.DamageSystem;

@Mixin(value = DamageSystem.class, remap = false)
public class DamageSystemMixin {

    @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
    private static void attackEntityFrom(EntityLivingBase ent, DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        if (!net.minecraftforge.common.ForgeHooks.onLivingAttack(ent, source, amount)) info.setReturnValue(false);
    }

}