package com.glodblock.github.customprobe.provider.wrap;

import net.minecraft.tileentity.TileEntity;

public class TileEntityInfo {

    private final Class<? extends TileEntity> entityClazz;

    @SuppressWarnings("unchecked")
    public TileEntityInfo(Class<?> entity) {
        if (TileEntity.class.isAssignableFrom(entity)) {
            this.entityClazz = (Class<? extends TileEntity>) entity;
        }
        else {
            this.entityClazz = null;
        }
    }

    public boolean isValidTileEntity(TileEntity te) {
        return entityClazz.isInstance(te);
    }

}
