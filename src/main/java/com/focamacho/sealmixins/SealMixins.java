package com.focamacho.sealmixins;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = SealMixins.MODID, name = SealMixins.NAME, version = SealMixins.VERSION, acceptableRemoteVersions = "*", acceptedMinecraftVersions = "1.12.2")
public class SealMixins {
	
    public static final String MODID = "sealmixins";
    public static final String NAME = "Seal Mixins";
    public static final String VERSION = "1.0.0";

    public static Logger logger = LogManager.getLogger(NAME);

    @EventHandler
    public void init(FMLInitializationEvent event) {
       //Roi, Leticia né?
    }

}
