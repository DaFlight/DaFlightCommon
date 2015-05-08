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

package me.dags.daflight.input.actions;

import me.dags.daflight.DaFlight;
import me.dags.daflight.player.DFController;
import me.dags.daflight.player.Speed;
import me.dags.daflight.utils.Tools;

/**
 * @author dags_ <dags@dags.me>
 */

public class SpeedDecrease extends SpeedAdjust
{
    @Override
    public boolean pressed(DFController DFController)
    {
        return false;
    }

    @Override
    public boolean held(DFController DFController)
    {
        if (tick())
        {
            if (DFController.flyModOn)
                return decreaseFlySpeed(DFController);
            else if (DFController.sprintModOn)
                return decreaseSprintSpeed(DFController);
        }
        return false;
    }

    private boolean decreaseFlySpeed(DFController DFController)
    {
        Speed speed = DFController.flySpeed;
        if (speed.isBoosting())
        {
            DaFlight.getConfig().flySpeedMult = speed.decMultiplier();
            DaFlight.getHud().renderTemp("X" + Tools.round1Dp(DaFlight.getConfig().flySpeedMult));
        }
        else
        {
            DaFlight.getConfig().flySpeed = speed.decBaseSpeed();
            DaFlight.getHud().renderTemp("x" + Tools.round1Dp(DaFlight.getConfig().flySpeed * 10F));
        }
        return true;
    }

    private boolean decreaseSprintSpeed(DFController DFController)
    {
        Speed speed = DFController.sprintSpeed;
        if (speed.isBoosting())
        {
            DaFlight.getConfig().sprintSpeedMult = speed.decMultiplier();
            DaFlight.getHud().renderTemp("X" + Tools.round1Dp(DaFlight.getConfig().sprintSpeedMult));
        }
        else
        {
            DaFlight.getConfig().sprintSpeed = speed.decBaseSpeed();
            DaFlight.getHud().renderTemp("x" + Tools.round1Dp(DaFlight.getConfig().sprintSpeed * 10F));
        }
        return true;
    }
}
