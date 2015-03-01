package me.dags.daflight.input.bind;

import org.lwjgl.input.Mouse;

/**
 * @author dags_ <dags@dags.me>
 */

public class MouseBind extends AbstractBind
{
    public MouseBind(String controlName, String keyName)
    {
        super(controlName, getBindFromString(keyName));
    }

    @Override
    public AbstractBind setFromId(int id)
    {
        if (id > -1 && id < Mouse.getButtonCount())
        {
            bindId = id;
        }
        return this;
    }

    @Override
    public AbstractBind setFromString(String stringName)
    {
        return setFromId(getBindFromString(stringName));
    }

    @Override
    public String getBindName()
    {
        if (bindId < 0)
        {
            return "NONE";
        }
        return "MOUSE_" + bindId;
    }

    public boolean isBindDown()
    {
        return bindId > -1 && Mouse.isButtonDown(bindId);
    }

    private static int getBindFromString(String s)
    {
        return Integer.parseInt(s.split("_")[1]);
    }
}
