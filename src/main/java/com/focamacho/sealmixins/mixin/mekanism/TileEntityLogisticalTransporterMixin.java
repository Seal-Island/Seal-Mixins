package com.focamacho.sealmixins.mixin.mekanism;

import mekanism.common.tile.transmitter.TileEntityLogisticalTransporter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = TileEntityLogisticalTransporter.class, remap = false)
public class TileEntityLogisticalTransporterMixin {

    /**
     * @author Seal Mixins
     * @reason Desabilitar os tubos do Mekanism
     */
    @Overwrite
    public void func_73660_a() {}

    /**
     * @author Seal Mixins
     * @reason Desabilitar os tubos do Mekanism
     */
    @Overwrite
    public void onWorldJoin() {}

}