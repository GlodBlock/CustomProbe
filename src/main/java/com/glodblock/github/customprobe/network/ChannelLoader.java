package com.glodblock.github.customprobe.network;

import com.glodblock.github.customprobe.CustomProbe;
import net.minecraftforge.fml.relauncher.Side;

public class ChannelLoader {

    @SuppressWarnings("all")
    public static void run() {
        int id = 0;
        CustomProbe.proxy.netHandler.registerMessage(new CPacketTileRequest.Handler(), CPacketTileRequest.class, id ++, Side.SERVER);
        CustomProbe.proxy.netHandler.registerMessage(new SPacketMouseTarget.Handler(), SPacketMouseTarget.class, id ++, Side.CLIENT);
    }

}
