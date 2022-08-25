package com.glodblock.github.customprobe.command;

import com.glodblock.github.customprobe.CustomProbe;
import com.google.common.collect.Lists;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.command.CommandTreeBase;

import javax.annotation.Nonnull;
import java.util.List;

public class CustomProbeCommand extends CommandTreeBase {

    public CustomProbeCommand() {
        addSubcommand(new CommandNBT());
        addSubcommand(new CommandTileName());
        addSubcommand(new CommandReload());
    }

    @Nonnull
    @Override
    public String getName() {
        return CustomProbe.MODID;
    }

    @Nonnull
    @Override
    public String getUsage(@Nonnull ICommandSender sender) {
        return "customprobe.command.usage";
    }

    @Nonnull
    @Override
    public List<String> getAliases() {
        return Lists.newArrayList("cp");
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
        super.execute(server, sender, args);
    }
}
