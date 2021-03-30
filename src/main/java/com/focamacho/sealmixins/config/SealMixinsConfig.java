package com.focamacho.sealmixins.config;

import com.focamacho.sealconfig.SealConfig;

import java.io.File;

public class SealMixinsConfig {

    public ConfigObject configObject;

    public void loadConfig() {
        String configFileName = "./config/sealmixins.json";
        configObject = new SealConfig().getConfig(new File(configFileName), ConfigObject.class);
    }

}
