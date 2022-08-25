package com.glodblock.github.customprobe.provider;

import com.glodblock.github.customprobe.CustomProbe;
import com.glodblock.github.customprobe.util.FileStreamReader;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class ProviderRegister {

    private static final HashMap<String, GenericProvider> allProviders = new HashMap<>();

    public static void run() {
        ITheOneProbe oneProbe = TheOneProbe.theOneProbeImp;
        if (CustomProbe.getScriptRootFile().listFiles() != null) {
            for (File script : Objects.requireNonNull(CustomProbe.getScriptRootFile().listFiles())) {
                if (script != null && script.isFile()) {
                    GenericProvider provider = new GenericProvider(script);
                    oneProbe.registerProvider(provider);
                    allProviders.put(provider.getID(), provider);
                }
            }
        }
    }

    public static void reload() {
        ITheOneProbe oneProbe = TheOneProbe.theOneProbeImp;
        GenericProvider.reload();
        if (CustomProbe.getScriptRootFile().listFiles() != null) {
            for (File script : Objects.requireNonNull(CustomProbe.getScriptRootFile().listFiles())) {
                if (script != null && script.isFile()) {
                    GenericProvider newProvider = new GenericProvider(script);
                    if (allProviders.containsKey(newProvider.getID())) {
                        GenericProvider oldProvider = allProviders.get(newProvider.getID());
                        oldProvider.reSet(FileStreamReader.readFromFile(script));
                    } else {
                        oneProbe.registerProvider(newProvider);
                        allProviders.put(newProvider.getID(), newProvider);
                    }
                }
            }
        }
    }

}
