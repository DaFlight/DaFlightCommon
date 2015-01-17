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

public class Speed
{

    private boolean boost;
    private Double baseSpeed;
    private Double multiplier;
    private Double totalSpeed;
    private Double maxSpeed;

    public Speed()
    {
        baseSpeed = 0.11;
        multiplier = 1.0;
        totalSpeed = 1.0;
        maxSpeed = 50D;
        boost = false;
    }

    public Double getTotalSpeed()
    {
        return totalSpeed;
    }

    public Double getBaseSpeed()
    {
        return baseSpeed;
    }

    public Double getMultiplier()
    {
        return multiplier;
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

    public void incBaseSpeed()
    {
        if (10 * (baseSpeed + 0.01) * multiplier <= maxSpeed && baseSpeed * 10 < 5D)
        {
            baseSpeed += 0.01;
            if (baseSpeed > 5D)
            {
                baseSpeed = 5D;
            }
        }
        update();
    }

    public void decBaseSpeed()
    {
        if (baseSpeed > 0.01)
        {
            baseSpeed -= 0.01;
        }
        update();
    }

    public void incMultiplier()
    {
        if (10 * baseSpeed * (multiplier + 0.1) <= maxSpeed && multiplier <= 9.9D)
        {
            multiplier += 0.1;
        }
        update();
    }

    public void decMultiplier()
    {
        if (multiplier > 1.0)
        {
            multiplier -= 0.1;
        }
        update();
    }

    public void setBaseSpeed(Double d)
    {
        if (10 * d * multiplier <= maxSpeed)
        {
            baseSpeed = d;
        }
        else
        {
            baseSpeed = 0.11;
        }
        update();
    }

    public void setMultiplier(Double d)
    {
        if (10 * baseSpeed * d <= maxSpeed)
        {
            multiplier = d;
        }
        else
        {
            multiplier = 1.1;
        }
        update();
    }

    public void setMaxSpeed(Double d)
    {
        maxSpeed = d;
        update();
    }

    private void update()
    {
        if (boost)
        {
            totalSpeed = baseSpeed * multiplier * 5;
        }
        else
        {
            totalSpeed = baseSpeed * 5;
        }

        if (totalSpeed * 2 > maxSpeed)
        {
            baseSpeed = 0.11;
            multiplier = 1.0;
            totalSpeed = baseSpeed * 5;
        }
    }

}
