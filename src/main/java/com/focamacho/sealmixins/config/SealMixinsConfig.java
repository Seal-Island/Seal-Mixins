package com.focamacho.sealmixins.config;

import blue.endless.jankson.Comment;
import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.SyntaxError;

import java.io.File;
import java.io.IOException;

public class SealMixinsConfig {

    public ConfigObject configObject;

    public void loadConfig() throws IOException, SyntaxError {
        Jankson jankson = Jankson.builder().build();

        File configFile = new File("./config/sealmixins.json");
        JsonObject configJson = jankson.load(configFile);

        configObject = jankson.fromJson(configJson, ConfigObject.class);
    }

    public class ConfigObject {
        @Comment("It makes the tubes of mekanism stop working (Except heat and gas). Server-side only.")
        public boolean disableMekanismTubes = false;

        @Comment("The maximum amount of seeds that Immersive Engineering's Garden Cloche can handle. It works only on the server-side, but it is necessary on the client to avoid visual bugs.")
        public int gardenClocheMaxSeeds = 1;

        @Comment("Disables Tech-Guns explosions. Server-side only.")
        public boolean disableTGExplosions = false;

        @Comment("Connects TechGuns damage to forge events, making it possible to cancel it for protection systems and/or mods, such as draconic armor. Server-side only.")
        public boolean hookTGDamage = true;
    }


}
