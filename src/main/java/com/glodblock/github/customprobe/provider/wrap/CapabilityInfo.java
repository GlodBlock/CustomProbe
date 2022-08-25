package com.glodblock.github.customprobe.provider.wrap;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

public class CapabilityInfo implements IValidCheckable {

    protected final Capability<?> capability;

    public CapabilityInfo(Capability<?> capability) {
        this.capability = capability;
    }

    @Override
    public boolean isValidTileEntity(World world, BlockPos pos, EnumFacing face) {
        TileEntity te = world.getTileEntity(pos);
        if (te != null) {
            return te.hasCapability(capability, face);
        }
        return false;
    }

    public Object getCapability(TileEntity te, EnumFacing face) {
        if (te != null) {
            return te.getCapability(capability, face);
        }
        return null;
    }
}
