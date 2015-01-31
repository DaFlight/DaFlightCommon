package me.dags.daflight;

import me.dags.daflight.input.KeybindHandler;
import me.dags.daflight.messaging.ChannelMessaging;
import me.dags.daflightapi.minecraft.MinecraftGame;
import me.dags.daflight.utils.Config;
import me.dags.daflight.utils.GlobalConfig;
import me.dags.daflightapi.IDaFlightMod;
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
        return "2.2r1";
    }

    @Override
    public void onInit(MinecraftGame mcGame, ChannelMessaging channelMessaging, UIHelper uiHelper, File configFolder)
    {
        DaFlight.init(mcGame, channelMessaging, uiHelper, configFolder);
        Config.getInstance();
        Config.applySettings();
        GlobalConfig.applyDefaults();
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
                Config.reloadConfig();
                Config.applySettings();
            }
            if (Config.getInstance().disabled)
                DaFlight.get().daPlayer.disableAll();
        }
        if (inGame && !Config.getInstance().disabled)
        {
            DaFlight.get().daPlayer.update();
            wasInGame = true;
            if (clock)
                DaFlight.get().daPlayer.tickUpdate();
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
        DaFlight.get().daPlayer.onGameJoin();
        if (GlobalConfig.perServerConfig() && !DaFlight.getMC().getMinecraft().isSingleplayer())
        {
            Config.loadServerConfig();
            Config.applySettings();
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
