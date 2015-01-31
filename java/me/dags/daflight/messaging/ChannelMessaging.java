package me.dags.daflight.messaging;

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
                case 1:
                    handler.fullBright(value == 1);
                    break;
                case 2:
                    handler.flyMod(value == 1);
                    break;
                case 3:
                    handler.softFall(value == 1);
                    break;
                case 4:
                    handler.refresh(value);
                    break;
                case 100:
                    handler.setSpeed(value);
                    break;
            }
        }
    }

    public void dispatchMessage(PacketData packetData)
    {
        dispatcher.dispatchMessage(packetData);
    }
}
