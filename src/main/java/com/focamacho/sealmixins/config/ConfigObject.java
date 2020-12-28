package com.focamacho.sealmixins.config;

import blue.endless.jankson.Comment;

public class ConfigObject {

    @Comment("It makes the tubes of mekanism stop working (Except heat and gas). Server-side only.")
    public boolean disableMekanismTubes = false;

    @Comment("The maximum amount of seeds that Immersive Engineering's Garden Cloche can handle. It works only on the server-side, but it is necessary on the client to avoid visual bugs.")
    public int gardenClocheMaxSeeds = 1;

    @Comment("Disables Tech-Guns explosions. Server-side only.")
    public boolean disableTGExplosions = false;

    @Comment("Adds a mod detection argument to the patchouli entries.")
    public boolean patchouliModCheck = true;

}