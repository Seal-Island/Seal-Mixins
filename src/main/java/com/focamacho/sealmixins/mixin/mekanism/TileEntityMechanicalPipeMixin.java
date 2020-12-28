package com.focamacho.sealmixins.mixin.mekanism;

import mekanism.common.tile.transmitter.TileEntityMechanicalPipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = TileEntityMechanicalPipe.class, remap = false)
public class TileEntityMechanicalPipeMixin {

    /**
     * @author Seal Mixins
     * @reason Desabilitar os tubos do Mekanism
     */
    @Overwrite
    public void func_73660_a() {}

}