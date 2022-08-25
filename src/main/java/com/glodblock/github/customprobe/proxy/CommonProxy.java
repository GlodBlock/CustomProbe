package com.glodblock.github.customprobe.proxy;

import com.glodblock.github.customprobe.CustomProbe;
import com.glodblock.github.customprobe.network.ChannelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class CommonProxy {

    public final SimpleNetworkWrapper netHandler = NetworkRegistry.INSTANCE.newSimpleChannel(CustomProbe.MODID);

    public void preInit(FMLPreInitializationEvent event) {
        ChannelLoader.run();
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }

}
