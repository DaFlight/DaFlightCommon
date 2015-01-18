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

import me.dags.daflight.input.actions.Action;
import me.dags.daflight.input.actions.Dummy;
import org.lwjgl.input.Keyboard;

public class KeyBind
{

    private BindType type;
    private Action action;
    private String name;
    private int keyId;
    private boolean canHold;
    private boolean isToggle;
    private boolean toggleState;
    private boolean press;
    private boolean held;
    private int modX = 0;
    private int modY = 0;
    private int modZ = 0;

    public KeyBind(int i, boolean keyIsToggle)
    {
        name = "";
        type = BindType.GENERIC;
        keyId = i;
        isToggle = keyIsToggle;
        canHold = false;
        action = new Dummy();
    }

    public KeyBind(int i)
    {
        name = "";
        keyId = i;
        isToggle = true;
        press = false;
        action = new Dummy();
    }

    public KeyBind(String controlName, String keyName, BindType bt)
    {
        name = controlName;
        type = bt;
        keyId = Keyboard.getKeyIndex(keyName);
        isToggle = true;
        press = false;
        action = new Dummy();
    }

    public KeyBind(String controlName, String keyName, BindType bt, int x, int y, int z)
    {
        name = controlName;
        type = bt;
        keyId = Keyboard.getKeyIndex(keyName);
        isToggle = true;
        press = false;
        modX = x;
        modY = y;
        modZ = z;
        action = new Dummy();
    }

    public KeyBind setAction(Action a)
    {
        action = a;
        return this;
    }

    public Action getAction()
    {
        return action;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setKey(int i)
    {
        keyId = i;
    }

    public void setKeyFromString(String keyName)
    {
        keyId = Keyboard.getKeyIndex(keyName);
    }

    public void setCanHold(boolean b)
    {
        canHold = b;
    }

    public void setToggle(boolean b)
    {
        isToggle = b;
    }

    public void setState(boolean b)
    {
        toggleState = b;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return keyId;
    }

    public String getKeyName()
    {
        if (keyId < 0)
        {
            return "NONE";
        }
        return Keyboard.getKeyName(keyId);
    }

    public BindType getType()
    {
        return type;
    }

    public boolean canHold()
    {
        return canHold;
    }

    public boolean isToggle()
    {
        return isToggle;
    }

    public boolean getToggleState()
    {
        return toggleState;
    }

    public boolean keyHeld()
    {
        if (isToggle)
        {
            return false;
        }
        if (keyId == 0)
        {
            return false;
        }
        return Keyboard.isCreated() && Keyboard.isKeyDown(keyId);
    }

    public boolean keyPressed()
    {
        if (keyId == 0)
        {
            return false;
        }
        if (Keyboard.isKeyDown(keyId))
        {
            if (press)
            {
                return false;
            }
            if (isToggle)
            {
                toggleState = !toggleState;
            }
            press = true;
            held = true;
            return true;
        }
        press = false;
        return false;
    }

    public boolean keyReleased()
    {
        if (!isToggle)
        {
            if (keyId == 0)
            {
                return false;
            }
            if (held && !keyHeld())
            {
                held = false;
                return true;
            }
        }
        return false;
    }

    public int getModX()
    {
        return modX;
    }

    public int getModY()
    {
        return modY;
    }

    public int getModZ()
    {
        return modZ;
    }

}
