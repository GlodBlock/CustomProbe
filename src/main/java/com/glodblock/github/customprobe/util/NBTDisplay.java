package com.glodblock.github.customprobe.util;

import net.minecraft.nbt.*;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class NBTDisplay {

    public static void formatNBTString(NBTTagCompound tag, List<String> output, String whiteSpace) {
        StringBuilder whiteSpaceBuilder = new StringBuilder(whiteSpace);
        for (String key : tag.getKeySet()) {
            NBTBase obj = tag.getTag(key);
            if (obj instanceof NBTTagByte) {
                output.add(whiteSpaceBuilder + key + ": " + TextFormatting.BLUE + ((NBTTagByte) obj).getByte() + " (Byte)");
            }
            else if (obj instanceof NBTTagShort) {
                output.add(whiteSpaceBuilder + key + ": " + TextFormatting.AQUA + ((NBTTagShort) obj).getShort() + " (Short)");
            }
            else if (obj instanceof NBTTagInt) {
                output.add(whiteSpaceBuilder + key + ": " + TextFormatting.DARK_BLUE + ((NBTTagInt) obj).getInt() + " (Integer)");
            }
            else if (obj instanceof NBTTagLong) {
                output.add(whiteSpaceBuilder + key + ": " + TextFormatting.DARK_AQUA + ((NBTTagLong) obj).getLong() + " (Long)");
            }
            else if (obj instanceof NBTTagFloat) {
                output.add(whiteSpaceBuilder + key + ": " + TextFormatting.GREEN + ((NBTTagFloat) obj).getFloat() + " (Flout)");
            }
            else if (obj instanceof NBTTagDouble) {
                output.add(whiteSpaceBuilder + key + ": " + TextFormatting.GOLD + ((NBTTagDouble) obj).getDouble() + " (Double)");
            }
            else if (obj instanceof NBTTagString) {
                output.add(whiteSpaceBuilder + key + ": " + TextFormatting.RED + ((NBTTagString) obj).getString());
            }
            else if (obj instanceof NBTTagByteArray) {
                output.add(whiteSpaceBuilder + key + ": [");
                whiteSpaceBuilder.append(" ");
                for (byte data : ((NBTTagByteArray) obj).getByteArray()) {
                    output.add(whiteSpaceBuilder.toString() + TextFormatting.BLUE + data);
                }
                whiteSpaceBuilder.deleteCharAt(whiteSpaceBuilder.length() - 1);
                output.add(whiteSpaceBuilder + "] (Byte Array)");
            }
            else if (obj instanceof NBTTagIntArray) {
                output.add(whiteSpaceBuilder + key + ": [");
                whiteSpaceBuilder.append(" ");
                for (int data : ((NBTTagIntArray) obj).getIntArray()) {
                    output.add(whiteSpaceBuilder.toString() + TextFormatting.DARK_BLUE + data);
                }
                whiteSpaceBuilder.deleteCharAt(whiteSpaceBuilder.length() - 1);
                output.add(whiteSpaceBuilder + "] (Integer Array)");
            }
            else if (obj instanceof NBTTagCompound) {
                output.add(whiteSpaceBuilder + key + ": {");
                formatNBTString((NBTTagCompound) obj, output, whiteSpace + " ");
                output.add(whiteSpaceBuilder + "} (NBT Tag)");
            }
            else if (obj instanceof NBTTagList) {
                output.add(whiteSpaceBuilder + key + ": [");
                int cnt = 0;
                for (NBTBase data : (NBTTagList) obj) {
                    cnt ++;
                    NBTTagCompound wrap = new NBTTagCompound();
                    wrap.setTag("#" + cnt, data);
                    formatNBTString(wrap, output, whiteSpace + " ");
                }
                output.add(whiteSpaceBuilder + "] (NBT Tag List)");
            }
        }
    }

}
