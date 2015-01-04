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

public class Vector
{

    private volatile double x;
    private volatile double y;
    private volatile double z;
    private volatile double speed;

    private volatile boolean hasInput;
    private volatile boolean hasLateralInput;

    public Vector()
    {
        this.x = 0D;
        this.y = 0D;
        this.z = 0D;
        this.speed = 1D;
        this.hasInput = false;
        this.hasLateralInput = false;
    }

    public Vector(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.speed = 1D;
        this.hasInput = false;
        this.hasLateralInput = false;
    }

    public double getX()
    {
        return this.x;
    }

    public double getY()
    {
        return this.y;
    }

    public double getZ()
    {
        return this.z;
    }

    public double getSpeed()
    {
        return this.speed;
    }

    public boolean hasInput()
    {
        return this.hasInput;
    }

    public boolean hasLateralInput()
    {
        return this.hasLateralInput;
    }

    public void add(double newX, double newY, double newZ)
    {
        x += newX;
        y += newY;
        z += newZ;
    }

    public void set(double newX, double newY, double newZ)
    {
        x = newX;
        y = newY;
        z = newZ;
    }

    public void multiply(double factor)
    {
        x *= factor;
        y *= factor;
        z *= factor;
    }

    public void setSpeed(double d)
    {
        this.speed = d;
    }

    public void setHasInput(boolean b)
    {
        this.hasInput = b;
    }

    public void setHasLateralInput(boolean b)
    {
        this.hasLateralInput = b;
    }

    public void reset()
    {
        x = 0;
        y = 0;
        z = 0;
    }

}
