package com.focamacho.sealmixins.config;

import com.focamacho.sealconfig.SealConfig;

import java.io.File;

public class SealMixinsConfig {

    public static ConfigObj configObject;

    public static void loadConfig() {
        String configFileName = "./config/sealmixins.json";
        configObject = new SealConfig().getConfig(new File(configFileName), ConfigObj.class);
    }

}
