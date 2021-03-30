package com.focamacho.sealmixins.config;

import com.focamacho.sealconfig.ConfigObject;
import com.focamacho.sealconfig.relocated.blue.endless.jankson.Comment;

public class ConfigObj {

    @ConfigObject
    public ImmersiveEngineering immersiveEngineering = new ImmersiveEngineering();

    @ConfigObject
    public TechGuns techGuns = new TechGuns();

    @ConfigObject
    public Patchouli patchouli = new Patchouli();

    @ConfigObject
    public TheAurorian theAurorian = new TheAurorian();

    @ConfigObject
    public EnderIO enderIO = new EnderIO();

    @ConfigObject
    public ActuallyAdditions actuallyAdditions = new ActuallyAdditions();

    @ConfigObject
    public Minecraft minecraft = new Minecraft();

    public static class ImmersiveEngineering {

        @Comment("A quantidade máxima de sementes que um Garden-Cloche consegue suportar.\n" +
                "A geração de itens nele será multiplicada pela quantidade de sementes.\n" +
                "Funciona somente no Server-Side, mas vai causar bugs visuais se não estiver no client.")
        public int gardenClocheMaxSeeds = 1;

    }

    public static class TechGuns {

        @Comment("Desabilita as explosões do Tech Guns. (Server-Side).")
        public boolean disableTGExplosions = false;

    }

    public static class Patchouli {

        @Comment("Adiciona uma detecção de mod como argumento para as páginas do Patchouli.")
        public boolean patchouliModCheck = true;

    }

    public static class TheAurorian {

        @Comment("Bloqueia qualquer teleporte na dimensão The Aurorian.")
        public boolean blockTheAurorianTeleport = false;
        public String blockTheAurorianTeleportMessage = "§bUma força mágica impede que você se mova.";

    }

    public static class ActuallyAdditions {

        @Comment("Dispara um evento \"EnderTeleportEvent\" ao usar a Teleport Staff. Permitindo o cancelamento.")
        public boolean staffTeleportEvent = true;

    }

    public static class EnderIO {

        @Comment("Dispara um evento \"EnderTeleportEvent\" ao usar a The Ender e a Staff of Traveling. Permitindo o cancelamento.")
        public boolean staffTeleportEvent = true;

    }

    public static class Minecraft {

        @Comment("Dispara um evento \"EnderTeleportEvent\" ao comer uma Chorus Fruit. Permitindo o cancelamento.")
        public boolean chorusTeleportEvent = true;

    }

}