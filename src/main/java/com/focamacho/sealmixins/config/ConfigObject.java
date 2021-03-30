package com.focamacho.sealmixins.config;

import com.focamacho.sealconfig.relocated.blue.endless.jankson.Comment;

public class ConfigObject {

    @Comment("A quantidade máxima de sementes que um Garden-Cloche consegue suportar.\n" +
            "A geração de itens nele será multiplicada pela quantidade de sementes.\n" +
            "Funciona somente no Server-Side, mas vai causar bugs visuais se não estiver no client.")
    public int gardenClocheMaxSeeds = 1;

    @Comment("Desabilita as explosões do Tech Guns. (Server-Side).")
    public boolean disableTGExplosions = false;

    @Comment("Adiciona uma detecção de mod como argumento para as páginas do Patchouli.")
    public boolean patchouliModCheck = true;

}