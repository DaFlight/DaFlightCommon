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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.dags.daflight.DaFlight;
import me.dags.daflight.player.DFController;
import me.dags.daflight.player.Speed;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author dags_ <dags@dags.me>
 */

public class Config
{
    private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    private static final String FILE_NAME = "daflight.json";

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
    @SerializedName("NoClip_Key")
    public String noClipKey = "N";
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
    @Expose
    @SerializedName("NoClip_Is_Toggle")
    public boolean noCLipIsToggle = true;

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
    @Expose
    @SerializedName("Disable_FOV")
    public boolean disableFov = true;

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
    @SerializedName("NoClip_Status")
    public String noClipStatus = "n";
    @Expose
    @SerializedName("Status_Shadow")
    public boolean textShadow = true;

    private File saveFile;

    private Config()
    {
    }

    public Config setSaveFile(File file)
    {
        saveFile = file;
        return this;
    }

    public void setSpeeds(Speed speed)
    {
        switch (speed.getType())
        {
            case FLY:
                flySpeed = speed.getBaseSpeed();
                flySpeedMult = speed.getMultiplier();
                saveConfig();
                break;
            case SPRINT:
                sprintSpeed = speed.getBaseSpeed();
                sprintSpeedMult = speed.getMultiplier();
                saveConfig();
                break;
        }
    }

    public void applySettings()
    {
        DFController dFController = DaFlight.get().DFController;
        DFController.KEY_BINDS.initSettings();
        dFController.flySpeed.setSpeedValues(flySpeed, flySpeedMult);
        dFController.sprintSpeed.setSpeedValues(sprintSpeed, sprintSpeedMult);
        DaFlight.getHud().refreshStatuses();
        if (!speedIsToggle)
        {
            dFController.flySpeed.setBoost(false);
            dFController.sprintSpeed.setBoost(false);
            DaFlight.getHud().updateMsg();
        }
    }

    public Config saveConfig()
    {
        try
        {
            if (!saveFile.exists())
                saveFile.createNewFile();
            FileWriter writer = new FileWriter(saveFile);
            writer.write(GSON.toJson(this));
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return this;
    }

    public static Config getDefaultConfig()
    {
        File file = new File(DaFlight.getConfigFolder(), Config.FILE_NAME);
        try
        {
            Config config = GSON.fromJson(new FileReader(file), Config.class);
            if (config != null)
                return config.setSaveFile(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return new Config().setSaveFile(file).saveConfig();
    }

    public static Config getServerConfig()
    {
        File file = new File(Tools.getOrCreateFolder(DaFlight.getConfigFolder(), "servers"), DaFlight.getMC().getServerData().serverIP.replace(":", "-") + ".json");
        try
        {
            return GSON.fromJson(new FileReader(file), Config.class).setSaveFile(file);
        }
        catch (IOException e)
        {
            return new Config().setSaveFile(file).saveConfig();
        }
    }
}
