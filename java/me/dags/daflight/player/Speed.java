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

package me.dags.daflight.player;

import me.dags.daflight.utils.Tools;

public class Speed
{
    private boolean boost;
    private float baseSpeed;
    private float multiplier;
    private float totalSpeed;

    private float maxBaseSpeed;
    private float maxMultiplier;
    private float maxSpeed;

    public Speed()
    {
        baseSpeed = 0.11F;
        multiplier = 1.0F;
        totalSpeed = 1.0F;
        maxBaseSpeed = 5F;
        maxMultiplier = 10F;
        maxSpeed = 50F;
        boost = false;
    }

    public Speed setMaxBaseSpeed(float f)
    {
        maxBaseSpeed = f * 0.1F;
        return this;
    }

    public Speed setMaxMultiplier(float f)
    {
        maxMultiplier = f;
        return this;
    }

    public Speed setMaxSpeed(float f)
    {
        maxSpeed = f;
        update();
        return this;
    }

    public float getTotalSpeed()
    {
        return totalSpeed;
    }

    public float getMaxBaseSpeed()
    {
        return maxBaseSpeed * 10;
    }

    public float getMaxMultiplier()
    {
        return maxMultiplier;
    }

    public boolean isBoost()
    {
        return boost;
    }

    public void toggleBoost()
    {
        boost = !boost;
        update();
    }

    public void setBoost(boolean b)
    {
        boost = b;
        update();
    }

    public float incBaseSpeed()
    {
        float oldBase = baseSpeed;
        baseSpeed = baseSpeed + 0.01F < maxBaseSpeed ? baseSpeed + 0.01F : maxBaseSpeed;
        if (10F * baseSpeed * multiplier <= maxSpeed)
            update();
        else
            baseSpeed = oldBase;
        return Tools.round(baseSpeed);
    }

    public float decBaseSpeed()
    {
        baseSpeed = baseSpeed - 0.01F > 0.01F ? baseSpeed - 0.01F : baseSpeed;
        update();
        return Tools.round(baseSpeed);
    }

    public float incMultiplier()
    {
        float oldMult = multiplier;
        multiplier = multiplier + 0.1F < maxMultiplier ? multiplier + 0.1F : maxMultiplier;
        if (10F * baseSpeed * multiplier <= maxSpeed)
            update();
        else
            multiplier = oldMult;
        return Tools.round(multiplier);
    }

    public float decMultiplier()
    {
        multiplier = multiplier - 0.1F > 0.1F ? multiplier - 0.1F : multiplier;
        update();
        return Tools.round(multiplier);
    }

    public void setBaseSpeed(float f)
    {
        baseSpeed = 10F * f * multiplier <= maxSpeed ? f : 0.11F;
        update();
    }

    public void setMultiplier(float f)
    {
        multiplier = 10F * baseSpeed * f <= maxSpeed ? f : 1.1F;
        update();
    }

    private void update()
    {
        totalSpeed = boost ? baseSpeed * multiplier * 5F : baseSpeed * 5F;
    }
}
