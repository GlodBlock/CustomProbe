package com.glodblock.github.customprobe.provider;

import com.glodblock.github.customprobe.CustomProbe;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;

import java.io.File;
import java.util.Objects;

public class ProviderRegister {

    public static void run() {
        ITheOneProbe oneProbe = TheOneProbe.theOneProbeImp;
        if (CustomProbe.getScriptRootFile().listFiles() != null) {
            for (File script : Objects.requireNonNull(CustomProbe.getScriptRootFile().listFiles())) {
                if (script != null && script.isFile()) {
                    oneProbe.registerProvider(new GenericProvider(script));
                }
            }
        }
    }

}
