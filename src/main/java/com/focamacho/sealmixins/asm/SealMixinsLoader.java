package com.focamacho.sealmixins.asm;

import com.focamacho.sealmixins.SealMixins;
import com.focamacho.sealmixins.config.ConfigObj;
import com.focamacho.sealmixins.config.SealMixinsConfig;
import com.focamacho.sealmixins.util.ModHandler;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import javax.annotation.Nullable;
import java.util.Map;

@IFMLLoadingPlugin.SortingIndex(732)
@IFMLLoadingPlugin.MCVersion("1.12.2")
public class SealMixinsLoader implements IFMLLoadingPlugin {

    public SealMixinsLoader() {
        SealMixinsConfig.loadConfig();

        ConfigObj config = SealMixinsConfig.configObject;

        //Minecraft
        if(config.minecraft.chorusTeleportEvent) loadMixin("minecraft", "chorusfruit");

        //Immersive Engineering
        if(config.immersiveEngineering.gardenClocheMaxSeeds > 1) loadMixin("immersiveengineering", "gardencloche");

        //Tech Guns
        if(config.techGuns.disableTGExplosions) loadMixin("techguns", "explosion");

        //Patchouli
        if(config.patchouli.patchouliModCheck) loadMixin("patchouli", "modcheck");

        //Actually Additions
        if(config.actuallyAdditions.staffTeleportEvent) loadMixin("actuallyadditions", "teleportstaff");

        //EnderIO
        if(config.enderIO.staffTeleportEvent) loadMixin("enderio", "teleport");

        //Draconic Evolution
        if(config.draconicEvolution.disableDislocatorEntity) loadMixin("draconicevolution", "dislocator");

        //Tinkers' Construct
        if(config.tinkersConstruct.disableStationConnect) loadMixin("tconstruct", "stationcontainer");

        //Forge
        if(config.forge.disableEnhancedServerlist) loadMixin("forge", "serverlist");

        MixinBootstrap.init();
        ModHandler.clear();
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    private void loadMixin(String modid, String mixin){
        if(modid.equalsIgnoreCase("forge") || modid.equalsIgnoreCase("minecraft") || ModHandler.load(modid)) {
            Mixins.addConfiguration("mixins/sealmixins/" + modid + "/mixins." +  mixin + ".json");
            SealMixins.logger.info("Carregando mixin \"" + mixin + "\" para o mod \"" + modid + "\".");
        }
    }

}
