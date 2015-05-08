/*
 * Copyright (c) 2014, dags_ <dags@dags.me>
 *
 *  Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby
 *  granted, provided that the above copyright notice and this permission notice appear in all copies.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING
 *  ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL,
 *  DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS,
 *  WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 *  USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package me.dags.daflight.utils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author dags_ <dags@dags.me>
 */

public class SpeedDefaults
{
    @Expose
    private float maxBaseSpeed;
    @Expose
    private float maxMultiplier;

    private SpeedDefaults(float f1, float f2)
    {
        maxBaseSpeed = f1;
        maxMultiplier = f2;
    }

    public float getDefaultMaxBaseSpeed()
    {
        if (maxBaseSpeed <= 0F)
        {
            return 5F;
        }
        return maxBaseSpeed;
    }

    public float getDefaultMaxMultiplier()
    {
        if (maxMultiplier <= 0F)
        {
            return 10F;
        }
        return maxMultiplier;
    }

    public boolean usingCustomSpeeds()
    {
        return maxBaseSpeed * maxMultiplier > 50.0F;
    }

    public static SpeedDefaults loadDefaults()
    {
        Gson gson = new Gson();
        InputStream in = SpeedDefaults.class.getResourceAsStream("/defaults.json");
        SpeedDefaults speedDefaults = gson.fromJson(new InputStreamReader(in), SpeedDefaults.class);
        if (speedDefaults == null)
        {
            return new SpeedDefaults(5F, 10F);
        }
        return speedDefaults;
    }
}
