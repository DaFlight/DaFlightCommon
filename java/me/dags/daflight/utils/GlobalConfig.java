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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.modconfig.ConfigStrategy;
import com.mumfrey.liteloader.modconfig.Exposable;
import com.mumfrey.liteloader.modconfig.ExposableOptions;
import me.dags.daflight.DaFlight;
import me.dags.daflight.player.DaPlayer;

/**
 * @author dags_ <dags@dags.me>
 */

@ExposableOptions(strategy = ConfigStrategy.Unversioned, filename = "global.json")
public class GlobalConfig implements Exposable
{
    private static GlobalConfig instance;

    @Expose
    @SerializedName("Per_server_Configs")
    public boolean perServerConfigs = false;
    @Expose
    @SerializedName("Config_Tooltips")
    public boolean configToolTips = true;
    @Expose
    @SerializedName("Brightness")
    private float brightness = -1.0F;

    public static GlobalConfig getInstance()
    {
        if (instance == null)
        {
            instance = new GlobalConfig();
        }
        return instance;
    }

    private GlobalConfig()
    {
        String fileName = Tools.createGlobalConfig();
        LiteLoader.getInstance().registerExposable(this, fileName);
        LiteLoader.getInstance().writeConfig(this);
    }

    public static void applyDefaults()
    {
        GlobalConfig c = getInstance();
        if (c.brightness < 0F)
        {
            setBrightness(DaFlight.getMC().getGameSettings().gammaSetting);
        }
        DaFlight.getMC().getGameSettings().gammaSetting = c.brightness;
        DaFlight.getMC().getGameSettings().saveOptions();
    }

    public static boolean perServerConfig()
    {
        return getInstance().perServerConfigs;
    }

    public static void setPerServerConfig(boolean b)
    {
        getInstance().perServerConfigs = b;
    }

    public static void saveSettings()
    {
        LiteLoader.getInstance().writeConfig(getInstance());
    }

    public static float getBrightness()
    {
        return getInstance().brightness;
    }

    public static void setBrightness(float f)
    {
        if (f <= 1.0f)
        {
            getInstance().brightness = f;
            return;
        }
        getInstance().brightness = 0.5F;
    }
}
