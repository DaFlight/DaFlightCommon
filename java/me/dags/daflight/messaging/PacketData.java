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

package me.dags.daflight.messaging;

/**
 * @author dags_ <dags@dags.me>
 */

public enum PacketData
{
    CONNECT(new byte[]{1}),
    FULLBRIGHT_OFF(new byte[]{1, 0}),
    FULLBRIGHT_ON(new byte[]{1, 1}),
    MOD_ON(new byte[]{2, 1}),
    MOD_OFF(new byte[]{2, 2}),
    NOCLIP_OFF(new byte[]{3, 0}),
    NOCLIP_ON(new byte[]{3, 1}),
    REFRESH(new byte[]{4, 0}),
    SPEED(new byte[]{100, 0})
    ;

    private final byte[] data;

    PacketData(byte[] b)
    {
        data = b;
    }

    public byte[] getData()
    {
        return data;
    }

    public PacketData setCustomValue(byte b)
    {
        data[1] = b;
        return this;
    }
}
