package me.dags.daflight;

import me.dags.daflight.input.KeybindHandler;
import me.dags.daflight.messaging.ChannelMessaging;
import me.dags.daflightapi.IDaFlightMod;
import me.dags.daflightapi.minecraft.MinecraftGame;
import me.dags.daflightapi.ui.UIHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dags_ <dags@dags.me>
 */

public class DaFlightMod implements IDaFlightMod
{
    private boolean wasInGame;

    @Override
    public String getName()
    {
        return "DaFlight";
    }

    @Override
    public String getVersion()
    {
        return DaFlight.VERSION;
    }

    @Override
    public void onInit(MinecraftGame mcGame, ChannelMessaging channelMessaging, UIHelper uiHelper, File configFolder)
    {
        DaFlight.init(mcGame, channelMessaging, uiHelper, configFolder);
        DaFlight.getConfig().applySettings();
        DaFlight.getGlobalConfig().applyDefaults();
    }

    @Override
    public void onTick(boolean clock, boolean inGame)
    {
        if (clock)
        {
            KeybindHandler.checkMenuKey();
            if (!inGame && wasInGame)
            {
                wasInGame = false;
                DaFlight.reloadConfig();
                DaFlight.getConfig().applySettings();
            }
            if (DaFlight.getConfig().disabled)
                DaFlight.get().DFController.disableAll();
        }
        if (inGame && !DaFlight.getConfig().disabled)
        {
            DaFlight.get().DFController.update();
            wasInGame = true;
            if (clock)
                DaFlight.get().DFController.tickUpdate();
        }
    }

    @Override
    public void onRender()
    {
        DaFlight.getHud().draw();
    }

    @Override
    public void onJoinGame()
    {
        DaFlight.get().DFController.onGameJoin();
        if (DaFlight.getGlobalConfig().perServerConfig() && !DaFlight.getMC().getMinecraft().isSingleplayer())
        {
            DaFlight.getServerConfig();
            DaFlight.getConfig().applySettings();
            DaFlight.getMC().tellPlayer("Server config loaded for: " + DaFlight.getMC().getServerData().serverIP);
        }
    }

    @Override
    public void onPluginMessage(String channel, byte[] data)
    {
        DaFlight.getChannelMessaging().onPacketReceived(channel, data);
    }

    @Override
    public List<String> getPluginChannels()
    {
        List<String> channel = new ArrayList<String>();
        channel.add("DaFlight");
        return channel;
    }
}
