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

package me.dags.daflight.player.controller;

import me.dags.daflight.minecraft.MCGame;
import me.dags.daflight.player.DaPlayer;
import me.dags.daflight.player.Vector;
import me.dags.daflight.utils.Config;

public class FlightController extends MCGame implements IController
{
    @Override
    public void input(Vector v)
    {
        if (v.hasInput())
        {
            getPlayer().setVelocity(v.getX(), v.getY(), v.getZ());
        }
        else
        {
            double smoothing = Config.getInstance().flySmoothing;
            getPlayer().setVelocity(getPlayer().motionX * smoothing, 0, getPlayer().motionZ * smoothing);
        }
        if (getPlayer().movementInput.jump && !jumpyKeyIsFlyUp())
            getPlayer().motionY -= 0.15D;
        if (getPlayer().movementInput.sneak && !sneakKeyIsFlyDown())
            getPlayer().motionY += 0.15D;
    }

    @Override
    public void unFocused()
    {
        double smoothing = Config.getInstance().flySmoothing;
        getPlayer().setVelocity(getPlayer().motionX * smoothing, getPlayer().motionY * smoothing, getPlayer().motionZ * smoothing);
    }

    private boolean jumpyKeyIsFlyUp()
    {
        return DaPlayer.KEY_BINDS.flyUp.getId() == getGameSettings().keyBindJump.getKeyCode();
    }

    private boolean sneakKeyIsFlyDown()
    {
        return DaPlayer.KEY_BINDS.flyDown.getId() == getGameSettings().keyBindSneak.getKeyCode();
    }
}
