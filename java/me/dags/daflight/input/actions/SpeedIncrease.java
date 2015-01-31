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
import me.dags.daflight.gui.hud.HUD;
import me.dags.daflight.player.DaPlayer;
import me.dags.daflight.player.Speed;
import me.dags.daflight.utils.Config;
import me.dags.daflight.utils.Tools;

/**
 * @author dags_ <dags@dags.me>
 */

public class SpeedIncrease extends SpeedAdjust
{
    @Override
    public boolean held(DaPlayer daPlayer)
    {
        if (tick())
        {
            if (daPlayer.flyModOn)
                return incFlySpeed(daPlayer);
            else if (daPlayer.sprintModOn)
                return incSprintSpeed(daPlayer);
        }
        return false;
    }

    private boolean incFlySpeed(DaPlayer daPlayer)
    {
        Speed speed = daPlayer.flySpeed;
        if (speed.isBoosting())
        {
            Config.getInstance().flySpeedMult = speed.incMultiplier();
            DaFlight.getHud().renderTemp("X" + Tools.round1Dp(Config.getInstance().flySpeedMult));
        }
        else
        {
            Config.getInstance().flySpeed = speed.incBaseSpeed();
            DaFlight.getHud().renderTemp("x" + Tools.round1Dp(Config.getInstance().flySpeed * 10F));
        }
        return true;
    }

    private boolean incSprintSpeed(DaPlayer daPlayer)
    {
        Speed speed = daPlayer.sprintSpeed;
        if (speed.isBoosting())
        {
            Config.getInstance().sprintSpeedMult = speed.incMultiplier();
            DaFlight.getHud().renderTemp("X" + Tools.round1Dp(Config.getInstance().sprintSpeedMult));
        }
        else
        {
            Config.getInstance().sprintSpeed = speed.incBaseSpeed();
            DaFlight.getHud().renderTemp("x" + Tools.round1Dp(Config.getInstance().sprintSpeed * 10F));
        }
        return true;
    }
}
