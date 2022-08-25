package com.glodblock.github.customprobe.provider.wrap;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IValidCheckable {

    boolean isValidTileEntity(World world, BlockPos pos, EnumFacing face);

}
