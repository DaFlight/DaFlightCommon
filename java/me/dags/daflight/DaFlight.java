package me.dags.daflight;

import me.dags.daflight.gui.hud.HUD;
import me.dags.daflight.messaging.ChannelMessaging;
import me.dags.daflightapi.minecraft.MinecraftGame;
import me.dags.daflight.player.DaPlayer;
import me.dags.daflightapi.DaFlightAPI;
import me.dags.daflightapi.ui.DaFlightUI;
import me.dags.daflightapi.ui.UIHelper;

import java.io.File;

/**
 * @author dags_ <dags@dags.me>
 */

public class DaFlight implements DaFlightAPI
{
    private static DaFlight instance;

    public DaPlayer daPlayer;
    private File configFolder;
    private MinecraftGame minecraftGame;
    private UIHelper uiHelper;
    private ChannelMessaging channelMessaging;
    private HUD hud;

    private DaFlight()
    {}

    public static DaFlight init(MinecraftGame minecraftGame, ChannelMessaging messenging, UIHelper uiHelper, File folder)
    {
        get().minecraftGame = minecraftGame;
        get().channelMessaging = messenging;
        get().uiHelper = uiHelper;
        get().configFolder = folder;
        get().daPlayer = new DaPlayer();
        get().hud = new HUD();
        return get();
    }

    public static DaFlight get()
    {
        if (instance == null)
        {
            instance = new DaFlight();
        }
        return instance;
    }

    public static MinecraftGame getMC()
    {
        return get().minecraftGame;
    }

    public static ChannelMessaging getChannelMessaging()
    {
        return get().channelMessaging;
    }

    public static HUD getHud()
    {
        return get().hud;
    }

    public static UIHelper getUIHelper()
    {
        return get().uiHelper;
    }

    public static File getConfigFolder()
    {
        File folder = get().configFolder;
        if (!folder.exists())
        {
            if (folder.mkdirs())
            {
                System.out.println("Creating new config folder...");
            }
        }
        return folder;
    }

    @Override
    public DaFlightUI getDaFlightUI()
    {
        return hud;
    }
}
