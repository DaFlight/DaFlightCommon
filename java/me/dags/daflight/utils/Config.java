/*
 * Copyright (c) 2014, dags_ <dags@dags.me>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.dags.daflight.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.modconfig.ConfigStrategy;
import com.mumfrey.liteloader.modconfig.Exposable;
import com.mumfrey.liteloader.modconfig.ExposableOptions;
import me.dags.daflight.DaFlight;
import me.dags.daflight.player.DaPlayer;
import me.dags.daflight.player.Speed;

/**
 * @author dags_ <dags@dags.me>
 */

@ExposableOptions(strategy = ConfigStrategy.Unversioned, filename = "daflight.json")
public class Config implements Exposable
{
    /**
     * KeyBinds
     */
    @Expose
    @SerializedName("Fly_Up_Key")
    public String upKey = "SPACE";
    @Expose
    @SerializedName("Fly_Down_Key")
    public String downKey = "LSHIFT";
    @Expose
    @SerializedName("FullBright_Key")
    public String fullBrightKey = "MINUS";
    @Expose
    @SerializedName("Flight_Key")
    public String flyKey = "F";
    @Expose
    @SerializedName("Sprint_Key")
    public String sprintKey = "R";
    @Expose
    @SerializedName("SpeedMod_Key")
    public String speedKey = "X";
    @Expose
    @SerializedName("CineFlight_Key")
    public String cineFlyKey = "C";
    @Expose
    @SerializedName("SpeedUp_Key")
    public String speedUpKey = "RBRACKET";
    @Expose
    @SerializedName("SpeedDown_Key")
    public String speedDownKey = "LBRACKET";

    /**
     * ControlToggles
     */
    @Expose
    @SerializedName("Fly_Is_Toggle")
    public boolean flyIsToggle = true;
    @Expose
    @SerializedName("Sprint_Is_Toggle")
    public boolean sprintIsToggle = true;
    @Expose
    @SerializedName("SpeedMod_Is_Toggle")
    public boolean speedIsToggle = true;
    @Expose
    @SerializedName("FullBright_Is_Toggle")
    public boolean fullbrightIsToggle = true;

    /**
     * Preferences
     */
    @Expose
    @SerializedName("Disable_Mod")
    public boolean disabled = false;
    @Expose
    @SerializedName("3D_Flight")
    public boolean threeDFlight = false;
    @Expose
    @SerializedName("Show_Hud")
    public boolean showHud = true;

    /**
     * Parameters
     */
    @Expose
    @SerializedName("Fly_Speed")
    public float flySpeed = 0.1F;
    @Expose
    @SerializedName("Fly_Speed_Multiplier")
    public float flySpeedMult = 5.0F;
    @Expose
    @SerializedName("Fly_Smoothing_Factor")
    public float flySmoothing = 0.7F;
    @Expose
    @SerializedName("Sprint_Speed")
    public float sprintSpeed = 0.1F;
    @Expose
    @SerializedName("Sprint_Speed_Multiplier")
    public float sprintSpeedMult = 5.0F;
    @Expose
    @SerializedName("Jump_Multiplier")
    public float jumpModifier = 0.9F;
    @Expose
    @SerializedName("Left-Right_Modifier")
    public float lrModifier = 0.85F;

    /**
     * HudElements
     */
    @Expose
    @SerializedName("Flight_Status")
    public String flightStatus = "f";
    @Expose
    @SerializedName("Cine_Flight_Status")
    public String cineFlightStatus = "c";
    @Expose
    @SerializedName("Sprint_Status")
    public String runStatus = "r";
    @Expose
    @SerializedName("FullBright_Status")
    public String fullBrightStatus = "fb";
    @Expose
    @SerializedName("Speed_Status")
    public String speedStatus = "*";
    @Expose
    @SerializedName("Status_Shadow")
    public boolean textShadow = true;

    private static Config instance;

    private Config()
    {
        LiteLoader.getInstance().registerExposable(this, "daflight.json");
    }

    private Config(String server)
    {
        String fileName = Tools.getOrCreateConfig("servers", server);
        LiteLoader.getInstance().registerExposable(this, fileName);
        saveSettings();
    }

    public static Config getInstance()
    {
        if (instance == null)
        {
            instance = new Config();
        }
        return instance;
    }

    public static void loadServerConfig()
    {
        instance = new Config(DaFlight.getMC().getServerData().serverIP.replace(":", "-"));
    }

    public static void reloadConfig()
    {
        instance = null;
        getInstance();
    }

    public static void setSpeeds(Speed speed)
    {
        switch (speed.getType())
        {
            case FLY:
                getInstance().flySpeed = speed.getBaseSpeed();
                getInstance().flySpeedMult = speed.getMultiplier();
                saveSettings();
                break;
            case SPRINT:
                getInstance().sprintSpeed = speed.getBaseSpeed();
                getInstance().sprintSpeedMult = speed.getMultiplier();
                saveSettings();
                break;
        }
    }

    public static void saveSettings()
    {
        LiteLoader.getInstance().writeConfig(getInstance());
    }

    public static void applySettings()
    {
        DaPlayer daPlayer = DaFlight.get().daPlayer;
        Config c = getInstance();
        DaPlayer.KEY_BINDS.initSettings();
        daPlayer.flySpeed.setSpeedValues(c.flySpeed, c.flySpeedMult);
        daPlayer.sprintSpeed.setSpeedValues(c.sprintSpeed, c.sprintSpeedMult);
        DaFlight.getHud().refreshStatuses();
        if (!c.speedIsToggle)
        {
            daPlayer.flySpeed.setBoost(false);
            daPlayer.sprintSpeed.setBoost(false);
            DaPlayer.KEY_BINDS.speedModifier.setState(false);
            DaFlight.getHud().updateMsg();
        }
    }
}
