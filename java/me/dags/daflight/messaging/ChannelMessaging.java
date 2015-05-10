package me.dags.daflight.messaging;

import me.dags.daflightapi.messaging.DFData;
import me.dags.daflightapi.messaging.PluginMessageDispatcher;
import me.dags.daflightapi.messaging.PluginMessageHandler;

/**
 * @author dags_ <dags@dags.me>
 */

public class ChannelMessaging
{
    private PluginMessageHandler handler;
    private PluginMessageDispatcher dispatcher;

    public ChannelMessaging(PluginMessageHandler handler, PluginMessageDispatcher dispatcher)
    {
        this.handler = handler;
        this.dispatcher = dispatcher;
    }

    public void onPacketReceived(String channel, byte[] data)
    {
        if (channel.equals("DaFlight") && data.length == 2)
        {
            final byte type = data[0];
            final byte value = data[1];
            switch (type)
            {
                case DFData.NOCLIP:
                    handler.noClip(DFData.getBoolFor(value));
                    break;
                case DFData.FULL_BRIGHT:
                    handler.fullBright(DFData.getBoolFor(value));
                    break;
                case DFData.FLY_MOD:
                    handler.flyMod(DFData.getBoolFor(value));
                    break;
                case DFData.NO_FALL_DAMAGE:
                    handler.softFall(DFData.getBoolFor(value));
                    break;
                case DFData.REFRESH:
                    handler.refresh(value);
                    break;
                case DFData.SPEED:
                    handler.setSpeed(value);
                    break;
            }
        }
    }

    public void dispatchMessage(byte[] data)
    {
        dispatcher.dispatchMessage(data);
    }
}
