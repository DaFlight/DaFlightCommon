package me.dags.daflightapi.messaging;

/**
 * @author dags_ <dags@dags.me>
 */

public interface PluginMessageHandler
{
    public void fullBright(boolean enable);

    public void flyMod(boolean enable);

    public void softFall(boolean enable);

    public void noClip(boolean enable);

    public void refresh(byte value);

    public void setSpeed(int value);
}
