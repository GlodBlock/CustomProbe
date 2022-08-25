package com.glodblock.github.customprobe.provider.wrap;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityInfo implements IValidCheckable {

    protected final Class<? extends TileEntity> entityClazz;

    @SuppressWarnings("unchecked")
    public TileEntityInfo(Class<?> entity) {
        if (TileEntity.class.isAssignableFrom(entity)) {
            this.entityClazz = (Class<? extends TileEntity>) entity;
        }
        else {
            this.entityClazz = null;
        }
    }

    @Override
    public boolean isValidTileEntity(World world, BlockPos pos, EnumFacing face) {
        TileEntity te = world.getTileEntity(pos);
        return entityClazz.isInstance(te);
    }

}
