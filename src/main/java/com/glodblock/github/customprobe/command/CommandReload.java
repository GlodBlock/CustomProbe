package com.glodblock.github.customprobe.command;

import com.glodblock.github.customprobe.provider.ProviderRegister;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nonnull;

public class CommandReload extends CommandBase {

    @Nonnull
    @Override
    public String getName() {
        return "reload";
    }

    @Nonnull
    @Override
    public String getUsage(@Nonnull ICommandSender sender) {
        return "/customprobe reload";
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) {
        if (sender instanceof EntityPlayerMP) {
            ProviderRegister.reload();
            sender.sendMessage(new TextComponentTranslation("customprobe.command.reload"));
        } else {
            sender.sendMessage(new TextComponentString("Command cannot be run on the server"));
        }
    }

}
