package me.dags.daflight.messaging;

import me.dags.daflight.DaFlight;
import me.dags.daflight.player.DFController;
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
            if (!DFController.DF_PERMISSIONS.fbEnabled())
            {
                DaFlight.getMC().tellPlayer("Fullbright enabled!");
            }
            DFController.DF_PERMISSIONS.setFullbrightEnabled(true);
        }
        else
        {
            if (DFController.DF_PERMISSIONS.fbEnabled())
            {
                DaFlight.getMC().tellPlayer("Fullbright disabled!");
            }
            DFController.DF_PERMISSIONS.setFullbrightEnabled(false);
            DaFlight.get().DFController.toggleFullbright();
        }
    }

    @Override
    public void flyMod(boolean enable)
    {
        if (enable)
        {
            if (!DFController.DF_PERMISSIONS.flyEnabled())
            {
                DaFlight.getMC().tellPlayer("Fly/Sprint mod enabled!");
            }
            DFController.DF_PERMISSIONS.setMovementModsEnabled(true);
        }
        else
        {
            if (DFController.DF_PERMISSIONS.flyEnabled())
            {
                DaFlight.getMC().tellPlayer("Fly/Sprint mod disabled!");
            }
            DFController.DF_PERMISSIONS.setMovementModsEnabled(false);
            DaFlight.get().DFController.disableMovementMods();
        }
    }

    @Override
    public void softFall(boolean enable)
    {
        if (enable)
        {
            if (!DFController.DF_PERMISSIONS.noFallDamageEnabled())
            {
                DaFlight.getMC().tellPlayer("Survival SoftFall enabled!");
            }
            DFController.DF_PERMISSIONS.setNoFallDamage(true);
        }
        else
        {
            if (DFController.DF_PERMISSIONS.noFallDamageEnabled())
            {
                DaFlight.getMC().tellPlayer("Survival SoftFall disabled!");
            }
            DFController.DF_PERMISSIONS.setNoFallDamage(false);
        }
    }

    @Override
    public void refresh(byte value)
    {
        if (DaFlight.get().DFController.flyModOn || DaFlight.get().DFController.sprintModOn)
        {
            DaFlight.getChannelMessaging().dispatchMessage(PacketData.MOD_ON);
        }
    }

    @Override
    public void setSpeed(int value)
    {
        DaFlight.getMC().tellPlayer("Max speed set by server! " + value);
        DaFlight.get().DFController.flySpeed.setMaxSpeed(value);
        DaFlight.get().DFController.sprintSpeed.setMaxSpeed(value);
        DaFlight.getHud().updateMsg();
    }
}
