package me.dags.daflight.messaging;

import me.dags.daflight.DaFlight;
import me.dags.daflight.player.DaPlayer;
import me.dags.daflightapi.messaging.PluginMessageHandler;

/**
 * @author dags_ <dags@dags.me>
 */

public class MessageHandler implements PluginMessageHandler
{
    @Override
    public void fullBright(boolean enable)
    {
        if (enable)
        {
            if (!DaPlayer.DF_PERMISSIONS.fbEnabled())
            {
                DaFlight.getMC().tellPlayer("Fullbright enabled!");
            }
            DaPlayer.DF_PERMISSIONS.setFullbrightEnabled(true);
        }
        else
        {
            if (DaPlayer.DF_PERMISSIONS.fbEnabled())
            {
                DaFlight.getMC().tellPlayer("Fullbright disabled!");
            }
            DaPlayer.DF_PERMISSIONS.setFullbrightEnabled(false);
            DaFlight.get().daPlayer.toggleFullbright();
        }
    }

    @Override
    public void flyMod(boolean enable)
    {
        if (enable)
        {
            if (!DaPlayer.DF_PERMISSIONS.flyEnabled())
            {
                DaFlight.getMC().tellPlayer("Fly/Sprint mod enabled!");
            }
            DaPlayer.DF_PERMISSIONS.setMovementModsEnabled(true);
        }
        else
        {
            if (DaPlayer.DF_PERMISSIONS.flyEnabled())
            {
                DaFlight.getMC().tellPlayer("Fly/Sprint mod disabled!");
            }
            DaPlayer.DF_PERMISSIONS.setMovementModsEnabled(false);
            DaFlight.get().daPlayer.disableMovementMods();
        }
    }

    @Override
    public void softFall(boolean enable)
    {
        if (enable)
        {
            if (!DaPlayer.DF_PERMISSIONS.noFallDamageEnabled())
            {
                DaFlight.getMC().tellPlayer("Survival SoftFall enabled!");
            }
            DaPlayer.DF_PERMISSIONS.setNoFallDamage(true);
        }
        else
        {
            if (DaPlayer.DF_PERMISSIONS.noFallDamageEnabled())
            {
                DaFlight.getMC().tellPlayer("Survival SoftFall disabled!");
            }
            DaPlayer.DF_PERMISSIONS.setNoFallDamage(false);
        }
    }

    @Override
    public void refresh(byte value)
    {
        if (DaFlight.get().daPlayer.flyModOn || DaFlight.get().daPlayer.sprintModOn)
        {
            DaFlight.getChannelMessaging().dispatchMessage(PacketData.MOD_ON);
        }
    }

    @Override
    public void setSpeed(int value)
    {
        DaFlight.getMC().tellPlayer("Max speed set by server! " + value);
        DaFlight.get().daPlayer.flySpeed.setMaxSpeed(value);
        DaFlight.get().daPlayer.sprintSpeed.setMaxSpeed(value);
        DaFlight.getHud().updateMsg();
    }
}
