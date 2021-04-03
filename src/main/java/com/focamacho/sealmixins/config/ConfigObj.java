package com.focamacho.sealmixins.config;

import com.focamacho.sealconfig.ConfigObject;
import com.focamacho.sealconfig.relocated.blue.endless.jankson.Comment;

public class ConfigObj {

    @ConfigObject
    @Comment("Modificações para o Immersive Engineering")
    public ImmersiveEngineering immersiveEngineering = new ImmersiveEngineering();

    @ConfigObject
    @Comment("Modificações para o Tech Guns")
    public TechGuns techGuns = new TechGuns();

    @ConfigObject
    @Comment("Modificações para o Patchouli")
    public Patchouli patchouli = new Patchouli();

    @ConfigObject
    @Comment("Modificações para o The Aurorian")
    public TheAurorian theAurorian = new TheAurorian();

    @ConfigObject
    @Comment("Modificações para o EnderIO")
    public EnderIO enderIO = new EnderIO();

    @ConfigObject
    @Comment("Modificações para o Actually Additions")
    public ActuallyAdditions actuallyAdditions = new ActuallyAdditions();

    @ConfigObject
    @Comment("Modificações para o Minecraft")
    public Minecraft minecraft = new Minecraft();

    @ConfigObject
    @Comment("Modificações para o Draconic Evolution")
    public DraconicEvolution draconicEvolution = new DraconicEvolution();

    @ConfigObject
    @Comment("Modificações para o Tinkers' Construct")
    public TinkersConstruct tinkersConstruct = new TinkersConstruct();

    @ConfigObject
    @Comment("Modificações para o Forge")
    public Forge forge = new Forge();

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

    public static class DraconicEvolution {

        @Comment("Desabilita a possibilidade dos Dislocators teleportarem entidades quando um jogador bate nelas com o item.")
        public boolean disableDislocatorEntity = false;

    }

    public static class TinkersConstruct {

        @Comment("Desabilita a habilidade da Crafting Station de se conectar aos containers próximos.")
        public boolean disableStationConnect = false;

    }

    public static class Forge {

        @Comment("Desabilita o ícone de \"Incompatible FML Modded Server\" na lista de servidores.")
        public boolean disableEnhancedServerlist = false;

    }

}