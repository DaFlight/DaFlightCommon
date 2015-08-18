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

import me.dags.daflight.DaFlight;
import me.dags.daflight.utils.Tools;

public class Speed
{
    private final SpeedType speedType;

    private boolean boost;
    private float baseSpeed;
    private float multiplier;
    private float totalSpeed;

    private float maxBaseSpeed;
    private float maxMultiplier;
    private float maxSpeed;

    public Speed(SpeedType type, float maxBase, float maxMult)
    {
        speedType = type;
        maxBaseSpeed = maxBase;
        maxMultiplier = maxMult;
        maxSpeed = maxBaseSpeed * maxMultiplier;
    }

    public Speed resetMaxSpeed()
    {
        maxSpeed = maxBaseSpeed * maxMultiplier;
        return this;
    }

    public Speed setBoost(boolean b)
    {
        boost = b;
        updateSpeed();
        return this;
    }

    public Speed setMaxSpeed(float f)
    {
        maxSpeed = f;
        if (10F * baseSpeed * multiplier > maxSpeed)
        {
            multiplier = 1.0F;
        }
        if (10F * baseSpeed * multiplier > maxSpeed)
        {
            baseSpeed = 0.1F;
        }
        updateSpeed();
        return this;
    }

    public Speed setSpeedValues(float base, float mult)
    {
        baseSpeed = base;
        multiplier = mult;
        setMaxSpeed(maxSpeed);
        return this;
    }

    public Speed toggleBoost()
    {
        return setBoost(!boost);
    }

    public SpeedType getType()
    {
        return speedType;
    }

    public boolean isBoosting()
    {
        return boost;
    }

    public float getBaseSpeed()
    {
        return baseSpeed;
    }

    public float getMultiplier()
    {
        return multiplier;
    }

    public float getTotalSpeed()
    {
        return totalSpeed;
    }

    public float getMaxBaseSpeed()
    {
        return maxBaseSpeed;
    }

    public float getMaxMultiplier()
    {
        return maxMultiplier;
    }

    public float incBaseSpeed()
    {
        float temp = maxSpeed / multiplier;
        float max = maxBaseSpeed < maxSpeed ? maxBaseSpeed : maxSpeed;
        temp = temp > max ? max / 10F : temp / 10F;

        if (baseSpeed + 0.01F <= temp)
        {
            baseSpeed += 0.01F;
            updateSpeed();
        }
        return baseSpeed;
    }

    public float decBaseSpeed()
    {
        baseSpeed = baseSpeed - 0.01F > 0 ? baseSpeed - 0.01F : baseSpeed;
        updateSpeed();
        return baseSpeed;
    }

    public float incMultiplier()
    {
        float temp = maxSpeed / (10F * baseSpeed);
        temp = temp > maxMultiplier ? maxMultiplier : temp;

        if (multiplier + 0.1F <= temp)
        {
            multiplier += 0.1F;
            updateSpeed();
        }
        return multiplier;
    }

    public float decMultiplier()
    {
        multiplier = multiplier - 0.1F > 0 ? multiplier - 0.1F : multiplier;
        updateSpeed();
        return multiplier;
    }

    private void updateSpeed()
    {
        totalSpeed = boost ? 5.0F * baseSpeed * multiplier : 5.0F * baseSpeed;
        baseSpeed = Tools.round(baseSpeed);
        multiplier = Tools.round(multiplier);
        DaFlight.getConfig().setSpeeds(this);
    }

    public enum SpeedType
    {
        FLY,
        SPRINT,
        ;
    }
}
