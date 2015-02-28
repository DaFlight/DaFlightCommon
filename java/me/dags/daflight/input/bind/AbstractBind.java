package me.dags.daflight.input.bind;

import me.dags.daflight.input.actions.Action;
import me.dags.daflight.input.actions.Dummy;

/**
 * @author dags_ <dags@dags.me>
 */

public abstract class AbstractBind
{
    protected BindType type;
    protected Action action;
    protected String name;
    protected int bindId;

    protected boolean canHold;
    protected boolean isToggle;
    protected boolean toggleState;
    protected boolean press;
    protected boolean held;

    private int modX = 0;
    private int modY = 0;
    private int modZ = 0;

    public AbstractBind(String controlName, int bindIdName)
    {
        name = controlName;
        bindId = bindIdName;
        isToggle = true;
        press = false;
        action = new Dummy();
    }

    public AbstractBind setAction(Action a)
    {
        action = a;
        return this;
    }

    public AbstractBind setName(String s)
    {
        name = s;
        return this;
    }

    public AbstractBind setCanHold(boolean b)
    {
        canHold = b;
        return this;
    }

    public AbstractBind setToggle(boolean b)
    {
        isToggle = b;
        return this;
    }

    public AbstractBind setState(boolean b)
    {
        toggleState = b;
        return this;
    }

    public AbstractBind setMods(int x, int y, int z)
    {
        modX = x;
        modY = y;
        modZ = z;
        return this;
    }

    public AbstractBind setType(BindType bindType)
    {
        type = bindType;
        return this;
    }

    public Action getAction()
    {
        return action;
    }

    public String getName()
    {
        return name;
    }

    public int getBindId()
    {
        return bindId;
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

    public boolean bindPressed()
    {
        if (isBindDown())
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

    public boolean bindHeld()
    {
        return !isToggle && bindId >= 0 && isBindDown();
    }

    public boolean bindReleased()
    {
        if (!isToggle)
        {
            if (bindId < 0)
            {
                return false;
            }
            if (held && !bindHeld())
            {
                held = false;
                return true;
            }
        }
        return false;
    }

    public abstract AbstractBind setFromId(int id);

    public abstract AbstractBind setFromString(String stringName);

    public abstract String getBindName();

    public abstract boolean isBindDown();
}
