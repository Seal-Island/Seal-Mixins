package com.focamacho.sealmixins.event;

import com.elseytd.theaurorian.TAConfig;
import com.focamacho.sealmixins.config.SealMixinsConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TheAuroriaEvent {

    @SubscribeEvent
    public void onTeleport(EnderTeleportEvent event) {
        if(event.getEntity() instanceof EntityPlayer) {
            if(event.getEntity().dimension == TAConfig.Config_AurorianDimID && SealMixinsConfig.configObject.theAurorian.blockTheAurorianTeleport) {
                event.setCanceled(true);
                event.getEntity().sendMessage(new TextComponentString(SealMixinsConfig.configObject.theAurorian.blockTheAurorianTeleportMessage));
            }
        }
    }

}
