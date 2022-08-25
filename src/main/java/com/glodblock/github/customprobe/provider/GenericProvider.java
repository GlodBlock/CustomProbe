package com.glodblock.github.customprobe.provider;

import com.glodblock.github.customprobe.CustomProbe;
import com.glodblock.github.customprobe.provider.wrap.TileEntityInfo;
import com.glodblock.github.customprobe.util.FileStreamReader;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.codehaus.groovy.control.CompilationFailedException;

import javax.script.*;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

public class GenericProvider implements IProbeInfoProvider {

    private static final HashMap<String, Object> allScriptsEngine = new HashMap<>();
    private static final HashSet<String> brokenScripts = new HashSet<>();

    private TileEntityInfo info;
    private final String script;
    private final String id;

    public GenericProvider(File scriptFile) {
        this.script = FileStreamReader.readFromFile(scriptFile);
        this.id = scriptFile.getName();
    }

    @Override
    public String getID() {
        return CustomProbe.MODID + ":" + this.id;
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        if (brokenScripts.contains(this.id)) {
            return;
        }

        NBTTagCompound nbt = new NBTTagCompound();

        //Init
        if (blockState.getBlock().hasTileEntity(blockState)) {
            TileEntity tileEntity = world.getTileEntity(data.getPos());
            if (tileEntity == null) {
                return;
            }
            nbt = tileEntity.writeToNBT(nbt);
        }
        else {
            return;
        }

        if (!allScriptsEngine.containsKey(this.id)) {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("groovy");
            Bindings bindings = engine.createBindings();
            try {
                engine.eval(script, bindings);
                allScriptsEngine.put(this.id, engine);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }

        Invocable invocable = (Invocable) allScriptsEngine.get(this.id);
        try {
            if (this.info == null) {
                Class<?> blockType = (Class<?>) invocable.invokeFunction("getTileClass");
                this.info = new TileEntityInfo(blockType);
            }
            if (this.info.isValidTileEntity(world.getTileEntity(data.getPos()))) {
                invocable.invokeFunction("addInfo", probeInfo, player, data, nbt);
            }
        } catch (NoSuchMethodException | ScriptException | CompilationFailedException e) {
            e.printStackTrace();
            CustomProbe.log.error("Fail to load script: " + id);
            CustomProbe.log.error("It won't be reload until a manual reload.");
            brokenScripts.add(this.id);
        }
    }

}
