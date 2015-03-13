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

import me.dags.daflight.DaFlight;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Tools
{

    private static DecimalFormat df1;
    private static DecimalFormat df2;

    static
    {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.ENGLISH);
        dfs.setDecimalSeparator(".".charAt(0));
        df1 = new DecimalFormat("0.0", dfs);
        df2 = new DecimalFormat("0.00", dfs);
    }

    public static float round(float f)
    {
        return Float.valueOf(df2.format(f));
    }

    public static String round1Dp(float f)
    {
        return df1.format(f);
    }

    public static String round2Dp(float f)
    {
        return df2.format(f);
    }

    public static File getOrCreateFile(File folder, String fileName)
    {
        folder.mkdirs();
        File file = new File(folder, fileName);
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static File getOrCreateFolder(File parent, String name)
    {
        File folder = new File(parent, name);
        folder.mkdirs();
        return folder;
    }

    public static String createGlobalConfig()
    {
        String fileName = "global.json";
        File configFile = new File(DaFlight.getConfigFolder(), fileName);
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
