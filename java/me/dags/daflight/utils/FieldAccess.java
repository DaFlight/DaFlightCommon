package me.dags.daflight.utils;

import java.lang.reflect.Field;

/**
 * @author dags_ <dags@dags.me>
 */

public class FieldAccess<T>
{
    private Field field;
    private String[] fieldNames;
    private int attempt = 0;

    public FieldAccess(Class owner, String[] obfNames)
    {
        this.fieldNames = obfNames;
        this.field = getField(owner);
    }

    private Field getField(Class owner)
    {
        if (this.attempt >= this.fieldNames.length)
            return null;
        Field f;
        try
        {
            f = owner.getDeclaredField(this.fieldNames[this.attempt]);
            f.setAccessible(true);
        }
        catch (NoSuchFieldException e)
        {
            this.attempt++;
            f = getField(owner);
        }
        return f;
    }

    @SuppressWarnings("unchecked")
    public T get(Object owner)
    {
        try
        {
            return (T) field.get(owner);
        }
        catch (IllegalAccessException e)
        {
            return null;
        }
    }

    public void set(Object owner, T t)
    {
        try
        {
            field.set(owner, t);
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}
