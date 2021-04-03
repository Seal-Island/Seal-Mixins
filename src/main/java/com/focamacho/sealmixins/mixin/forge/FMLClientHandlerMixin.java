package com.focamacho.sealmixins.mixin.forge;

import net.minecraft.client.gui.ServerListEntryNormal;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nullable;

@Mixin(value = FMLClientHandler.class, remap = false)
public class FMLClientHandlerMixin {

    /**
     * @reason Desativar a mensagem de Incompatible
     * FML Modded Server do Forge na lista de servidores.
     * @author Seal Mixins
     */
    @Overwrite
    @Nullable
    public String enhanceServerListEntry(ServerListEntryNormal serverListEntry, ServerData serverEntry, int x, int width, int y, int relativeMouseX, int relativeMouseY) {
        return null;
    }

}
