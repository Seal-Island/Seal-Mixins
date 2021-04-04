package com.focamacho.sealmixins.mixin.minecraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityFishHook.class)
public class EntityFishHookMixin {

    @Shadow private EntityPlayer angler;

    @Shadow public Entity caughtEntity;

    @Inject(method = "bringInHookedEntity", at = @At("HEAD"), cancellable = true)
    public void bringInHookedEntity(CallbackInfo ci) {
        if(MinecraftForge.EVENT_BUS.post(new AttackEntityEvent(this.angler, this.caughtEntity))) ci.cancel();
    }

}
