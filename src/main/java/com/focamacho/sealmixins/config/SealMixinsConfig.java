package com.focamacho.sealmixins.config;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.SyntaxError;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SealMixinsConfig {

    public ConfigObject configObject;
    private final String configFileName = "./config/sealmixins.json";

    public void loadConfig() throws IOException, SyntaxError {
        Jankson jankson = Jankson.builder().build();

        File configFile = new File(configFileName);
        if(!configFile.exists()) saveDefaultConfig();
        JsonObject configJson = jankson.load(configFile);

        configObject = jankson.fromJson(configJson, ConfigObject.class);
    }

    public void saveDefaultConfig() throws IOException {
        File configFile = new File(configFileName);
        Jankson jankson = Jankson.builder().build();
        String result = jankson
                .toJson(new ConfigObject())
                .toJson(true, true, 0);

        if (!configFile.exists()) configFile.createNewFile();
        FileOutputStream out = new FileOutputStream(configFile, false);

        out.write(result.getBytes());
        out.flush();
        out.close();
    }

}
