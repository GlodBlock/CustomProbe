package com.glodblock.github.customprobe;

import com.glodblock.github.customprobe.command.CustomProbeCommand;
import com.glodblock.github.customprobe.provider.ProviderRegister;
import com.glodblock.github.customprobe.proxy.CommonProxy;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.FMLInjectionData;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = CustomProbe.MODID, version = CustomProbe.VERSION, useMetadata = true)
public class CustomProbe {

    public static final String MODID = "customprobe";
    public static final String VERSION = "0.1.00";

    public static Logger log;

    private static File root;

    @Mod.Instance(MODID)
    public static CustomProbe INSTANCE;

    @SidedProxy(clientSide = "com.glodblock.github.customprobe.proxy.ClientProxy", serverSide = "com.glodblock.github.customprobe.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        log = event.getModLog();
        root = new File((File) FMLInjectionData.data()[6], "customprobes");
        if (!root.exists()) {
            if (!root.mkdirs()) {
                log.error("Fail to create script directory in " + root.getPath() + "!");
            }
        }
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        proxy.init(event);
        ProviderRegister.run();
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void onServerLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CustomProbeCommand());
    }

    public static ResourceLocation resource(String path) {
        return new ResourceLocation(MODID, path);
    }

    public static File getScriptRootFile() {
        return root;
    }

}