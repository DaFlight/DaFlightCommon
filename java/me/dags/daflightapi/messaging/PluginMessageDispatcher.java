package me.dags.daflightapi.messaging;

import me.dags.daflight.messaging.PacketData;

/**
 * @author dags_ <dags@dags.me>
 */

public interface PluginMessageDispatcher
{
    public void dispatchMessage(PacketData packetData);
}
