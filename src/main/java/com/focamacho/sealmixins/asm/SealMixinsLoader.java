package com.focamacho.sealmixins.asm;

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
        loadMixin("immersiveengineering");
        loadMixin("techguns");

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

    private void loadMixin(String modid){
        if(ModHandler.load(modid)) {
            Mixins.addConfiguration("mixins/mixins.sealmixins." + modid + ".json");
        }
    }

}
