package com.focamacho.sealmixins.mixin.techguns;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import techguns.damagesystem.TGExplosion;

@Mixin(value = TGExplosion.class, remap = false)
public class TGExplosionMixin {

    /**
     * @author Seal Mixins
     * @reason Bloquear a quebra de blocos em explosões
     */
    @Overwrite
    private void breakBlocks() {}

}
