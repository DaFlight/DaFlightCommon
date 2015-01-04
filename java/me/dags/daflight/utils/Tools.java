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

import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.util.log.LiteLoaderLogger;
import me.dags.daflight.minecraft.MCGame;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Tools extends MCGame
{

    public static DecimalFormat df1;
    public static DecimalFormat df2;
    public static DecimalFormat df3;

    static
    {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.ENGLISH);
        dfs.setDecimalSeparator(".".charAt(0));
        df1 = new DecimalFormat("#.#", dfs);
        df2 = new DecimalFormat("#.##", dfs);
        df3 = new DecimalFormat("#.###", dfs);
    }

    public static void log(String msg)
    {
        LiteLoaderLogger.info("[DaFlight] " + msg);
    }

    public static double round(double d)
    {
        return Double.valueOf(df3.format(d));
    }

    public static void tellPlayer(String msg)
    {
        getPlayer().addChatMessage(getMessage(msg));
    }

    private static File getDaFlightFolder()
    {
        File folder = new File(LiteLoader.getCommonConfigFolder(), "daflight");
        if (!folder.exists())
        {
            folder.mkdirs();
        }
        return folder;
    }

    public static String getOrCreateConfig(String folder, String server)
    {
        String fileName = server + ".json";
        File serversFolder = new File(getDaFlightFolder(), folder);
        if (!serversFolder.exists())
        {
            serversFolder.mkdirs();
        }
        File configFile = new File(serversFolder, fileName);
        if (!configFile.exists())
        {
            try
            {
                configFile.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return "daflight/servers/" + fileName;
    }

    public static String createGlobalConfig()
    {
        String fileName = "global.json";
        File configFile = new File(getDaFlightFolder(), fileName);
        if (!configFile.exists())
        {
            try
            {
                configFile.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return "daflight/" + fileName;
    }

}
