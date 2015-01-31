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

package me.dags.daflight.input.binds;

import me.dags.daflight.DaFlight;
import me.dags.daflight.input.actions.*;
import me.dags.daflight.utils.Config;
import org.lwjgl.input.Keyboard;

public class KeyBinds
{
    public static final MenuBind MENU_BINDING = new MenuBind("Quick Menu", Keyboard.KEY_F10, "DaFlight");

    public KeyBind[] binds;
    public KeyBind[] movementBinds;
    public KeyBind fullBright;
    public KeyBind speedUp;
    public KeyBind speedDown;
    public KeyBind flyUp;
    public KeyBind flyDown;
    public KeyBind enableFly;
    public KeyBind enableSprint;
    public KeyBind speedModifier;
    public KeyBind cineFlight;

    public KeyBind forward;
    public KeyBind backward;
    public KeyBind left;
    public KeyBind right;
    public KeyBind jump;

    public KeyBinds()
    {
        initSettings();
    }

    public void updateMovementKeys()
    {
        forward.setKey(DaFlight.getMC().getGameSettings().keyBindForward.getKeyCode());
        backward.setKey(DaFlight.getMC().getGameSettings().keyBindBack.getKeyCode());
        left.setKey(DaFlight.getMC().getGameSettings().keyBindLeft.getKeyCode());
        right.setKey(DaFlight.getMC().getGameSettings().keyBindRight.getKeyCode());
        jump.setKey(DaFlight.getMC().getGameSettings().keyBindJump.getKeyCode());
    }

    public void initSettings()
    {
        Config c = Config.getInstance();
        fullBright = new KeyBind("FullBright", c.fullBrightKey, BindType.FULLBRIGHT).setAction(new ToggleFullbright());
        fullBright.setToggle(c.fullbrightIsToggle);
        fullBright.setCanHold(true);
        speedUp = new KeyBind("Speed++", c.speedUpKey, BindType.SPEEDUP).setAction(new SpeedIncrease());
        speedUp.setToggle(false);
        speedDown = new KeyBind("Speed--", c.speedDownKey, BindType.SPEEDDOWN).setAction(new SpeedDecrease());
        speedDown.setToggle(false);

        flyUp = new KeyBind("FlyUp", c.upKey, BindType.UP, 0, 1, 0);
        flyUp.setToggle(false);
        flyDown = new KeyBind("FlyDown", c.downKey, BindType.DOWN, 0, -1, 0);
        flyDown.setToggle(false);

        enableFly = new KeyBind("Fly", c.flyKey, BindType.FLY).setAction(new ToggleFlight());
        enableFly.setCanHold(true);
        enableFly.setToggle(c.flyIsToggle);
        enableSprint = new KeyBind("Sprint", c.sprintKey, BindType.SPRINT).setAction(new ToggleSprint());
        enableSprint.setCanHold(true);
        enableSprint.setToggle(c.sprintIsToggle);
        speedModifier = new KeyBind("SpeedMod", c.speedKey, BindType.MODIFIER).setAction(new ToggleSpeed());
        speedModifier.setCanHold(true);
        speedModifier.setToggle(c.speedIsToggle);
        cineFlight = new KeyBind("CineFlight", c.cineFlyKey, BindType.CINEFLIGHT).setAction(new ToggleCineFlight());

        forward = new KeyBind("Forward", "W", BindType.MOVE, 1, 0, 1);
        forward.setCanHold(true);
        forward.setToggle(false);
        backward = new KeyBind("Backward", "S", BindType.MOVE, -1, 0, -1);
        backward.setCanHold(true);
        backward.setToggle(false);
        left = new KeyBind("Left", "A", BindType.STRAFE, 1, 0, -1);
        left.setCanHold(true);
        left.setToggle(false);
        right = new KeyBind("Right", "D", BindType.STRAFE, -1, 0, 1);
        right.setCanHold(true);
        right.setToggle(false);
        jump = new KeyBind("Jump", "SPACE", BindType.GENERIC);
        jump.setCanHold(true);
        jump.setToggle(false);
        updateMovementKeys();

        binds = new KeyBind[]{speedModifier, enableFly, enableSprint, fullBright, cineFlight, flyUp, flyDown, speedUp, speedDown};
        movementBinds = new KeyBind[]{forward, backward, left, right, flyUp, flyDown};
    }
}
