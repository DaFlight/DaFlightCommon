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

package me.dags.daflight.gui.hud;

import me.dags.daflight.DaFlight;
import me.dags.daflight.minecraft.Colour;
import me.dags.daflight.player.DFController;
import me.dags.daflightapi.ui.DaFlightUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Renders text to screen when certain components of the mod are enabled
 *
 * @author dags_
 */
public class HUD implements DaFlightUI
{
    private static final int FLIGHT = 0;
    private static final int SPRINT = 1;
    private static final int NO_CLIP = 2;
    private static final int FULLBRIGHT = 3;
    private static final int TEMP = 4;

    private List<DFEntry> mods;
    private int counter = 50;

    private String flight = "f";
    private String cine = "c";
    private String run = "r";
    private String clip = "n";
    private String modifier = "*";
    private String fb = "fb";

    public HUD()
    {
        mods = new ArrayList<DFEntry>();
        mods.add(FLIGHT, new DFEntry("", false));
        mods.add(SPRINT, new DFEntry("", false));
        mods.add(NO_CLIP, new DFEntry("", false));
        mods.add(FULLBRIGHT, new DFEntry(Colour.addColour(DaFlight.getConfig().fullBrightStatus), false));
        mods.add(TEMP, new DFEntry("", false));
    }

    public void updateMsg()
    {
        DFController dp = DaFlight.get().DFController;
        boolean flyModifier = false;
        // Flight
        if (dp.flyModOn || DFController.KEY_BINDS.enableFly.bindHeld())
        {
            String s = "";
            flyModifier = DFController.KEY_BINDS.speedModifier.bindHeld();
            if (dp.flyModOn)
            {
                s = flight;
                if (dp.cineFlightOn)
                {
                    s = cine;
                }
            }
            if (DFController.DF_PERMISSIONS.flyEnabled() && (dp.flySpeed.isBoosting() || flyModifier))
            {
                s = s + modifier;
            }
            mods.get(FLIGHT).setTitle(s);
            mods.get(FLIGHT).setShow(true);
        }
        else
        {
            mods.get(FLIGHT).setShow(false);
        }
        // Sprint
        if (DFController.DF_PERMISSIONS.sprintEnabled() && (dp.sprintModOn || DFController.KEY_BINDS.enableSprint.bindHeld()))
        {
            String s = run;
            if (dp.sprintSpeed.isBoosting() || (DFController.KEY_BINDS.speedModifier.bindHeld() && !flyModifier))
            {
                s = s + modifier;
            }
            mods.get(SPRINT).setTitle(s);
            mods.get(SPRINT).setShow(true);
        }
        else
        {
            mods.get(SPRINT).setShow(false);
        }
        // noClip
        mods.get(NO_CLIP).setTitle(clip);
        mods.get(NO_CLIP).setShow(dp.noClipOn);
        // FullBright
        mods.get(FULLBRIGHT).setTitle(fb);
        mods.get(FULLBRIGHT).setShow(dp.fullBrightOn);
    }

    public void refreshStatuses()
    {
        flight = Colour.getColouredString(DaFlight.getConfig().flightStatus);
        cine = Colour.getColouredString(DaFlight.getConfig().cineFlightStatus);
        run = Colour.getColouredString(DaFlight.getConfig().runStatus);
        modifier = Colour.getColouredString(DaFlight.getConfig().speedStatus);
        clip = Colour.getColouredString(DaFlight.getConfig().noClipStatus);
        fb = Colour.getColouredString(DaFlight.getConfig().fullBrightStatus);
    }

    public void renderTemp(String s)
    {
        counter = 50;
        mods.get(TEMP).setTitle(s);
        mods.get(TEMP).setShow(true);
    }

    public void setTemp(Boolean b)
    {
        mods.get(TEMP).setShow(b);
    }

    @Override
    public void draw()
    {
        if (DaFlight.getConfig().disabled)
        {
            return;
        }
        counter--;
        if (DaFlight.getConfig().showHud && DaFlight.getMC().getMinecraft().inGameHasFocus && !DaFlight.getMC().getGameSettings().showDebugInfo)
        {
            int slot = 5;
            for (DFEntry d : mods)
            {
                if (d.isShown())
                {
                    if (DaFlight.getConfig().textShadow)
                    {
                        DaFlight.getMC().getMinecraft().fontRendererObj.drawStringWithShadow(d.getTitle(), 5, slot, 0xFFFFFF);
                    }
                    else
                    {
                        DaFlight.getMC().getMinecraft().fontRendererObj.drawString(d.getTitle(), 5, slot, 0xFFFFFF);
                    }
                    slot += 10;
                }
            }
        }
        if (counter <= 0)
        {
            setTemp(false);
        }
    }

    @SuppressWarnings("unused")
    @Override
    public int addModStatus(String s)
    {
        int i = mods.size();
        mods.add(i, new DFEntry(s, false));
        return i;
    }

    @SuppressWarnings("unused")
    @Override
    public void removeModStatus(int id)
    {
        if (id < mods.size() && id > 3)
        {
            mods.remove(id);
        }
    }

    @SuppressWarnings("unused")
    @Override
    public void setStatusVisibility(int id, boolean b)
    {
        if (id < mods.size() && id > 3)
        {
            mods.get(id).setShow(b);
        }
    }

    @SuppressWarnings("unused")
    @Override
    public void setStatus(int id, String s)
    {
        if (id < mods.size() && id > 3)
        {
            mods.get(id).setTitle(s);
        }
    }

}
