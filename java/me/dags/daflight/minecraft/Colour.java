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

package me.dags.daflight.minecraft;

import net.minecraft.util.EnumChatFormatting;

/**
 * @author dags_ <dags@dags.me>
 */

public class Colour
{
    private static final char SECTION = 167;

    public static String stripColour(String s)
    {
        return s.replaceAll("(?i)" + SECTION + "([a-f0-9k-or])", "&" + "$1");
    }

    public static String addColour(String s)
    {
        return s.replaceAll("(?i)&([a-f0-9k-or])", SECTION + "$1");
    }

    public static String getColouredString(String s)
    {
        String value = addColour(s);
        if (value.equals(s))
        {
            return EnumChatFormatting.RESET + s;
        }
        return value;
    }
}
