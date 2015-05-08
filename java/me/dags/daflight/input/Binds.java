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

package me.dags.daflight.input;

import me.dags.daflight.DaFlight;
import me.dags.daflight.input.actions.*;
import me.dags.daflight.input.bind.*;
import me.dags.daflight.utils.Config;
import org.lwjgl.input.Keyboard;

public class Binds
{
    public static final MenuBind MENU_BINDING = new MenuBind("Quick Menu", Keyboard.KEY_F10, "DaFlight");

    public AbstractBind[] binds;
    public AbstractBind[] movementBinds;
    public AbstractBind fullBright;
    public AbstractBind speedUp;
    public AbstractBind speedDown;
    public AbstractBind flyUp;
    public AbstractBind flyDown;
    public AbstractBind enableFly;
    public AbstractBind enableSprint;
    public AbstractBind speedModifier;
    public AbstractBind cineFlight;
    public AbstractBind noClip;

    public AbstractBind forward;
    public AbstractBind backward;
    public AbstractBind left;
    public AbstractBind right;
    public AbstractBind jump;

    public Binds()
    {
        initSettings();
    }

    public void updateMovementKeys()
    {
        forward.setFromId(DaFlight.getMC().getGameSettings().keyBindForward.getKeyCode());
        backward.setFromId(DaFlight.getMC().getGameSettings().keyBindBack.getKeyCode());
        left.setFromId(DaFlight.getMC().getGameSettings().keyBindLeft.getKeyCode());
        right.setFromId(DaFlight.getMC().getGameSettings().keyBindRight.getKeyCode());
        jump.setFromId(DaFlight.getMC().getGameSettings().keyBindJump.getKeyCode());
    }

    public void initSettings()
    {
        Config c = DaFlight.getConfig();
        fullBright = getBind("FullBright", c.fullBrightKey).setType(BindType.FULLBRIGHT).setAction(new ToggleFullbright());
        fullBright.setToggle(c.fullbrightIsToggle);
        fullBright.setCanHold(true);
        speedUp = getBind("Speed++", c.speedUpKey).setType(BindType.SPEEDUP).setAction(new SpeedIncrease());
        speedUp.setToggle(false);
        speedDown = getBind("Speed--", c.speedDownKey).setType(BindType.SPEEDDOWN).setAction(new SpeedDecrease());
        speedDown.setToggle(false);

        flyUp = getBind("FlyUp", c.upKey).setType(BindType.UP).setMods(0, 1, 0).setToggle(false);
        flyDown = getBind("FlyDown", c.downKey).setType(BindType.DOWN).setMods(0, -1, 0).setToggle(false);

        enableFly = getBind("Fly", c.flyKey).setType(BindType.FLY).setAction(new ToggleFlight()).setCanHold(true).setToggle(c.flyIsToggle);
        enableSprint = getBind("Sprint", c.sprintKey).setType(BindType.SPRINT).setAction(new ToggleSprint()).setCanHold(true).setToggle(c.sprintIsToggle);
        speedModifier = getBind("SpeedMod", c.speedKey).setType(BindType.MODIFIER).setAction(new ToggleSpeed()).setCanHold(true).setToggle(c.speedIsToggle);
        noClip = getBind("NoClip", c.noClipKey).setType(BindType.NO_CLIP).setAction(new ToggleNoClip()).setToggle(c.noCLipIsToggle);
        cineFlight = getBind("CineFlight", c.cineFlyKey).setType(BindType.CINEFLIGHT).setAction(new ToggleCineFlight());

        forward = getBind("Forward", "W").setType(BindType.MOVE).setMods(1, 0, 1).setCanHold(true).setToggle(false);
        backward = getBind("Backward", "S").setType(BindType.MOVE).setMods(-1, 0, -1).setCanHold(true).setToggle(false);
        left = getBind("Left", "A").setType(BindType.STRAFE).setMods(1, 0, -1).setCanHold(true).setToggle(false);
        right = getBind("Right", "D").setType(BindType.STRAFE).setMods(-1, 0, 1).setCanHold(true).setToggle(false);
        jump = getBind("Jump", "SPACE").setType(BindType.GENERIC).setCanHold(true).setToggle(false);
        updateMovementKeys();

        binds = new AbstractBind[]{enableFly, enableSprint, speedModifier, fullBright, cineFlight, noClip, flyUp, flyDown, speedUp, speedDown};
        movementBinds = new AbstractBind[]{forward, backward, left, right, flyUp, flyDown};
    }

    private static AbstractBind getBind(String name, String bindName)
    {
        if (bindName.startsWith("MOUSE_"))
            return new MouseBind(name, bindName);
        return new KeyBind(name, bindName);
     }
}
