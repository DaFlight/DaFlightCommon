/*
 * Copyright (c) 2014, dags_ <dags@dags.me>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.dags.daflight.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.dags.daflight.DaFlight;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author dags_ <dags@dags.me>
 */

public class GlobalConfig
{
    private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    private static final String FILE_NAME = "global.json";

    @Expose
    @SerializedName("Per_server_Configs")
    public boolean perServerConfigs = false;
    @Expose
    @SerializedName("Config_Tooltips")
    public boolean configToolTips = true;
    @Expose
    @SerializedName("Brightness")
    private float brightness = -1.0F;

    private File saveFile;

    private GlobalConfig()
    {
    }

    public GlobalConfig setSaveFile(File file)
    {
        saveFile = file;
        return this;
    }

    public boolean perServerConfig()
    {
        return perServerConfigs;
    }

    public float getBrightness()
    {
        return brightness;
    }

    public void setPerServerConfig(boolean b)
    {
        perServerConfigs = b;
    }

    public void setBrightness(float f)
    {
        if (f <= 1.0f)
        {
            brightness = f;
            return;
        }
        brightness = 0.5F;
    }

    public void applyDefaults()
    {
        if (brightness < 0F)
        {
            setBrightness(DaFlight.getMC().getGameSettings().gammaSetting);
        }
        DaFlight.getMC().getGameSettings().gammaSetting = brightness;
        DaFlight.getMC().getGameSettings().saveOptions();
    }

    public GlobalConfig saveConfig()
    {
        try
        {
            if (!saveFile.exists())
                saveFile.createNewFile();
            FileWriter writer = new FileWriter(saveFile);
            writer.write(GSON.toJson(this));
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return this;
    }

    public static GlobalConfig loadGlobalConfig()
    {
        File file = new File(DaFlight.getConfigFolder(), GlobalConfig.FILE_NAME);
        try
        {
            GlobalConfig config =  GSON.fromJson(new FileReader(file), GlobalConfig.class);
            if (config != null)
                return config.setSaveFile(file);
        }
        catch (IOException e)
        {}
        return new GlobalConfig().setSaveFile(file).saveConfig();
    }
}
