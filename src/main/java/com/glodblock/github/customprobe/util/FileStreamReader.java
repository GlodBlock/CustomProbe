package com.glodblock.github.customprobe.util;

import com.glodblock.github.customprobe.CustomProbe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileStreamReader {

    public static String readFromFile(File file)  {
        try {
            FileInputStream stream = new FileInputStream(file);
            int c;
            StringBuilder sb = new StringBuilder();
            while ((c = stream.read()) != -1) {
                sb.append((char) c);
            }
            return sb.toString();
        } catch (IOException exception) {
            exception.printStackTrace();
            CustomProbe.log.error("File to read file in " + file.getPath());
        }
        return null;
    }

}
