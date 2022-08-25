package com.glodblock.github.customprobe.command;

import com.glodblock.github.customprobe.CustomProbe;
import com.glodblock.github.customprobe.network.SPacketMouseTarget;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nonnull;

public class CommandNBT extends CommandBase {

    @Nonnull
    @Override
    public String getName() {
        return "nbt";
    }

    @Nonnull
    @Override
    public String getUsage(@Nonnull ICommandSender sender) {
        return "/customprobe nbt";
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) {
        if (sender instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) sender;
            CustomProbe.proxy.netHandler.sendTo(new SPacketMouseTarget(1), player);
        } else {
            sender.sendMessage(new TextComponentString("Command cannot be run on the server"));
        }
    }
}
