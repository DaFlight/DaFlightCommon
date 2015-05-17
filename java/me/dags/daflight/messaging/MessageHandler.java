package me.dags.daflight.messaging;

import me.dags.daflight.DaFlight;
import me.dags.daflight.player.DFController;
import me.dags.daflight.player.DFPermissions;
import me.dags.daflightapi.messaging.DFData;
import me.dags.daflightapi.messaging.PluginMessageHandler;

/**
 * @author dags_ <dags@dags.me>
 */

public class MessageHandler implements PluginMessageHandler
{
    private DFPermissions getPerms()
    {
        return DFController.DF_PERMISSIONS;
    }

    @Override
    public void fullBright(boolean enable)
    {
        String message = enable ? "Fullbright allowed!" : "Fullbright not allowed!";
        if (getPerms().fbEnabled() != enable)
        {
            DaFlight.getMC().tellPlayer(message);
        }
        DFController.DF_PERMISSIONS.setFullbrightEnabled(enable);
        if (!enable && DaFlight.get().DFController.fullBrightOn)
            DaFlight.get().DFController.toggleFullbright();
    }

    @Override
    public void flyMod(boolean enable)
    {
        String message = enable ? "Fly/Sprint mod allowed!" : "Fly/Sprint mod not allowed!";
        if (getPerms().flyEnabled() != enable)
        {
            DaFlight.getMC().tellPlayer(message);
        }
        DFController.DF_PERMISSIONS.setMovementModsEnabled(enable);
        if (!enable)
            DaFlight.get().DFController.disableMovementMods();
    }

    @Override
    public void softFall(boolean enable)
    {
        String message = enable ? "Survival SoftFall allowed!" : "Survival SoftFall not allowed!";
        if (getPerms().noFallDamageEnabled() != enable)
        {
            DaFlight.getMC().tellPlayer(message);
        }
        DFController.DF_PERMISSIONS.setNoFallDamage(enable);
    }

    @Override
    public void noClip(boolean enable)
    {
        String message = enable ? "NoClip allowed!" : "NoClip not allowed!";
        if (getPerms().noClipEnabled() != enable)
        {
            DaFlight.getMC().tellPlayer(message);
        }
        DFController.DF_PERMISSIONS.setNoClipEnabled(enable);
        if (!enable && DaFlight.get().DFController.noClipOn)
            DaFlight.get().DFController.toggleNoClip();
    }

    @Override
    public void refresh(byte value)
    {
        if (DaFlight.get().DFController.flyModOn || DaFlight.get().DFController.sprintModOn)
        {
            DaFlight.getChannelMessaging().dispatchMessage(DFData.getBooleanData(DFData.FLY_MOD, true));
        }
        if (DaFlight.get().DFController.noClipOn)
        {
            DaFlight.getChannelMessaging().dispatchMessage(DFData.getBooleanData(DFData.NOCLIP, true));
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
