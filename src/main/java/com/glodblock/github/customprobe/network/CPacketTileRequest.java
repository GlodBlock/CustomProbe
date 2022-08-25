package com.glodblock.github.customprobe.network;

import com.glodblock.github.customprobe.util.NBTDisplay;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.ArrayList;
import java.util.List;

public class CPacketTileRequest implements IMessage {

    private BlockPos pos;

    public CPacketTileRequest() {
    }

    public CPacketTileRequest(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
    }

    public static class Handler implements IMessageHandler<CPacketTileRequest, IMessage> {

        @Override
        public IMessage onMessage(CPacketTileRequest message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            TileEntity te = player.world.getTileEntity(message.pos);
            if (te != null) {
                NBTTagCompound tag = new NBTTagCompound();
                te.writeToNBT(tag);
                List<String> rec = new ArrayList<>();
                NBTDisplay.formatNBTString(tag, rec, "");
                player.sendMessage(new TextComponentTranslation("customprobe.command.nbtdisplay"));
                for (String line : rec) {
                    player.sendMessage(new TextComponentString(line));
                }
            }
            else {
                player.sendMessage(new TextComponentString("No tile entity selected!"));
            }
            return null;
        }
    }

}
