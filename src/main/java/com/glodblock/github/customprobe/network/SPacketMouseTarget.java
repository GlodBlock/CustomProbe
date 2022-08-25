package com.glodblock.github.customprobe.network;

import com.glodblock.github.customprobe.CustomProbe;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SPacketMouseTarget implements IMessage {

    private int mode;

    public SPacketMouseTarget() {
    }

    public SPacketMouseTarget(int mode) {
        this.mode = mode;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.mode = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.mode);
    }

    public static class Handler implements IMessageHandler<SPacketMouseTarget, IMessage> {

        @Override
        public IMessage onMessage(SPacketMouseTarget message, MessageContext ctx) {
            EntityPlayerSP player = Minecraft.getMinecraft().player;
            RayTraceResult pos = Minecraft.getMinecraft().objectMouseOver;
            if (pos != null && pos.typeOfHit == RayTraceResult.Type.BLOCK) {
                CPacketTileRequest packet = new CPacketTileRequest(pos.getBlockPos(), message.mode);
                CustomProbe.proxy.netHandler.sendToServer(packet);
            }
            else {
                player.sendMessage(new TextComponentString("No blocks selected!"));
            }
            return null;
        }
    }
}
