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

package me.dags.daflight.player.mode;

import me.dags.daflight.DaFlight;
import me.dags.daflight.player.DFController;
import me.dags.daflight.player.Vector;
import me.dags.daflightapi.minecraft.MinecraftGame;

public class FlightMode implements IMode
{
    @Override
    public void input(DFController DFController)
    {
        MinecraftGame mc = DaFlight.getMC();
        Vector v = DFController.movementVector;
        if (v.hasInput())
        {

            mc.getPlayer().setVelocity(v.getX(), v.getY(), v.getZ());
        }
        else
        {
            double smoothing = DaFlight.getConfig().flySmoothing;
            mc.getPlayer().setVelocity(mc.getPlayer().motionX * smoothing, 0, mc.getPlayer().motionZ * smoothing);
        }
    }

    @Override
    public void unFocused()
    {
        MinecraftGame mc = DaFlight.getMC();
        double smoothing = DaFlight.getConfig().flySmoothing;
        double xMotion = mc.getPlayer().motionX;
        double yMotion = mc.getPlayer().motionY;
        double zMotion = mc.getPlayer().motionZ;
        mc.getPlayer().setVelocity(xMotion * smoothing, yMotion * smoothing, zMotion * smoothing);
    }
}
