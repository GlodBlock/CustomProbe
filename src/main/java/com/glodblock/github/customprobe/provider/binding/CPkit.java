package com.glodblock.github.customprobe.provider.binding;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.util.text.TextFormatting;

public class CPkit {

    public static String setColor(String color) {
        TextFormatting realColor = TextFormatting.getValueByName(color);
        return realColor == null ? TextFormatting.RESET.toString() : realColor.toString();
    }

    public static boolean existNBTData(NBTTagCompound nbt, String args) {
        return nbt.hasKey(args);
    }

    public static Object extractNBTData(NBTTagCompound nbt, String args) {
        NBTBase obj = nbt.getTag(args);
        if (obj instanceof NBTTagByte) {
            return ((NBTTagByte) obj).getByte();
        }
        else if (obj instanceof NBTTagShort) {
            return ((NBTTagShort) obj).getShort();
        }
        else if (obj instanceof NBTTagInt) {
            return ((NBTTagInt) obj).getInt();
        }
        else if (obj instanceof NBTTagLong) {
            return ((NBTTagLong) obj).getLong();
        }
        else if (obj instanceof NBTTagFloat) {
            return ((NBTTagFloat) obj).getFloat();
        }
        else if (obj instanceof NBTTagDouble) {
            return ((NBTTagDouble) obj).getDouble();
        }
        else if (obj instanceof NBTTagString) {
            return ((NBTTagString) obj).getString();
        }
        else if (obj instanceof NBTTagByteArray) {
            return ((NBTTagByteArray) obj).getByteArray();
        }
        else if (obj instanceof NBTTagIntArray) {
            return ((NBTTagIntArray) obj).getIntArray();
        }
        else {
            return obj;
        }
    }

    public static Object getItemFromNBT(NBTTagCompound nbt) {
        return new ItemStack(nbt);
    }

    public static Object getNBTListElementAt(NBTTagList list, int pos) {
        return list.get(pos);
    }

}
