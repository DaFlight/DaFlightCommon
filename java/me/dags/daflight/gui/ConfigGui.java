/*
 * Copyright (c) 2014, dags_ <dags@dags.me>
 *
 *  Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby
 *  granted, provided that the above copyright notice and this permission notice appear in all copies.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING
 *  ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL,
 *  DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS,
 *  WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 *  USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package me.dags.daflight.gui;

import me.dags.daflight.DaFlight;
import me.dags.daflight.gui.uielements.*;
import me.dags.daflight.input.Binds;
import me.dags.daflight.player.DFController;
import me.dags.daflight.utils.Config;
import me.dags.daflightapi.ui.UIHelper;
import me.dags.daflightapi.ui.element.IEntryBox;
import me.dags.daflightapi.ui.element.UIElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dags_ <dags@dags.me>
 */

public abstract class ConfigGui extends GuiScreen
{
    protected final GuiScreen parent;
    protected final Config config = DaFlight.getConfig();

    protected final List<UIElement> uiElements;
    protected boolean singleColumnMode;
    protected int columnHeight = 365;

    private ToggleButton disable;
    private ToggleButton flight3D;
    private ToggleButton showHud;
    private ToggleButton showToolTips;
    private ToggleButton perServer;
    private ToggleButton textShadow;
    private ToggleButton disableFov;

    private Slider flySpeed;
    private Slider flyMultiplier;
    private Slider flySmoothing;
    private Slider sprintSpeed;
    private Slider sprintMultiplier;
    private Slider jumpMultiplier;
    private Slider leftRightMultiplier;

    private BindButton flyKey;
    private BindButton cineKey;
    private BindButton flyUpKey;
    private BindButton flyDownKey;
    private BindButton sprintKey;
    private BindButton speedKey;
    private BindButton speedUpKey;
    private BindButton speedDownKey;
    private BindButton fullBrightKey;
    private BindButton noClipKey;

    private ToggleButton flyHold;
    private ToggleButton sprintHold;
    private ToggleButton speedHold;
    private ToggleButton fbHold;
    private ToggleButton noClipHold;

    private IEntryBox flyStatus;
    private IEntryBox cineStatus;
    private IEntryBox sprintStatus;
    private IEntryBox noClipStatus;
    private IEntryBox speedStatus;
    private IEntryBox fullbrightStatus;

    protected ScrollBar scrollBar;

    protected boolean isScrollable;
    protected int maxYOffset = 0;
    protected int yOffset = 0;
    private boolean mouseHeld = false;

    public ConfigGui()
    {
        uiElements = new ArrayList<UIElement>();
        init(DaFlight.getMC().getScaledResolution().getScaledWidth(), DaFlight.getMC().getScaledResolution().getScaledHeight());
        this.parent = null;
    }

    public void init(int displayWidth, int displayHeight)
    {
        init(displayWidth, displayHeight, false);
    }

    public void init(int displayWidth, int displayHeight, boolean singleColumn)
    {
        uiElements.clear();

        int columnWidth = 200;
        int middleSpacer = 10;
        int settingsWidth = singleColumn ? columnWidth : (columnWidth * 2) + middleSpacer;

        isScrollable = displayHeight < getContentHeight();
        maxYOffset = isScrollable ? displayHeight - getContentHeight() - 10 : 0;

        Logo logo = new Logo();

        int xLeft = (displayWidth - settingsWidth) / 2;
        int xRight = singleColumn ? xLeft : displayWidth - xLeft - columnWidth;
        int yTop = (isScrollable ? 5 : ((displayHeight - columnHeight) / 2)) + 35;

        // Left column
        int y = yTop;
        uiElements.add(logo.setXY((displayWidth / 2) - (logo.getWidth() / 2), yTop - 35));
        uiElements.add(new Label(xLeft, y, "Preferences").setColour(EnumChatFormatting.DARK_AQUA));
        uiElements.add(disable = new ToggleButton(1, xLeft, y += 11, 100, 20, "Disabled", config.disabled));
        uiElements.add(flight3D = new ToggleButton(1, xLeft + 102, y, 99, 20, "3DFlight", config.threeDFlight));
        uiElements.add(showHud = new ToggleButton(1, xLeft, y += 21, 100, 20, "ShowHUD", config.showHud));
        uiElements.add(showToolTips = new ToggleButton(1, xLeft + 102, y, 99, 20, "ToolTips", DaFlight.getGlobalConfig().configToolTips));
        uiElements.add(perServer = new ToggleButton(1, xLeft, y += 21, 100, 20, "PerServer", DaFlight.getGlobalConfig().perServerConfig()));
        uiElements.add(textShadow = new ToggleButton(1, xLeft + 102, y, 99, 20, "TextShadow", config.textShadow));
        uiElements.add(disableFov = new ToggleButton(1, xLeft, y += 21, 100, 20, "DisableFOV", config.disableFov));

        int w1 = 156;
        int w2 = columnWidth - w1 - 1;
        uiElements.add(new Label(xLeft, y += 31, "KeyBinds").setColour(EnumChatFormatting.DARK_AQUA));
        uiElements.add(flyKey = new BindButton(xLeft, y += 11, w1, 20, false, "Fly", config.flyKey, "F"));
        uiElements.add(flyHold = new ToggleButton(1, xLeft + w1 + 1, y, w2, 20, "Hold", config.flyIsToggle, new String[]{"Hold", "Toggle"}));
        uiElements.add(sprintKey = new BindButton(xLeft, y += 21, w1, 20, false, "Sprint", config.sprintKey, "R"));
        uiElements.add(sprintHold = new ToggleButton(1, xLeft + w1 + 1, y, w2, 20, "Hold", config.sprintIsToggle, new String[]{"Hold", "Toggle"}));
        uiElements.add(speedKey = new BindButton(xLeft, y += 21, w1, 20, false, "Speed", config.speedKey, "X"));
        uiElements.add(speedHold = new ToggleButton(1, xLeft + w1 + 1, y, w2, 20, "Hold", config.speedIsToggle, new String[]{"Hold", "Toggle"}));
        uiElements.add(fullBrightKey = new BindButton(xLeft, y += 21, w1, 20, false, "FullBright", config.fullBrightKey, "MINUS"));
        uiElements.add(fbHold = new ToggleButton(1, xLeft + w1 + 1, y, w2, 20, "Hold", config.fullbrightIsToggle, new String[]{"Hold", "Toggle"}));
        uiElements.add(noClipKey = new BindButton(xLeft, y += 21, w1, 20, false, "NoClip", config.noClipKey, "N"));
        uiElements.add(noClipHold = new ToggleButton(1, xLeft + w1 + 1, y, w2, 20, "Hold", config.noCLipIsToggle, new String[]{"Hold", "Toggle"}));

        uiElements.add(cineKey = new BindButton(xLeft, y += 21, w1, 20, false, "CineFly", config.cineFlyKey, "C"));
        uiElements.add(flyUpKey = new BindButton(xLeft, y += 21, w1, 20, false, "FlyUp", config.upKey, "SPACE"));
        uiElements.add(flyDownKey = new BindButton(xLeft, y += 21, w1, 20, false, "FlyDown", config.downKey, "LSHIFT"));
        uiElements.add(speedUpKey = new BindButton(xLeft, y += 21, w1, 20, false, "Speed++", config.speedUpKey, "RBRACKET"));
        uiElements.add(speedDownKey = new BindButton(xLeft, y += 21, w1, 20, false, "Speed--", config.speedDownKey, "LBRACKET"));

        // Right column
        y = singleColumn ? y + 31 : yTop;
        DFController DFController = DaFlight.get().DFController;
        uiElements.add(new Label(xRight, y, "Modifiers").setColour(EnumChatFormatting.DARK_AQUA));
        uiElements.add(flySpeed = new Slider(1, xRight, y += 11, 0F, DFController.flySpeed.getMaxBaseSpeed(), 1F, 200).setDisplayString("FlySpeed").setDefaultValue(config.flySpeed * 10));
        uiElements.add(flyMultiplier = new Slider(1, xRight, y += 21, 0F, DFController.flySpeed.getMaxMultiplier(), 5F, 200).setDisplayString("FlySpeedMultiplier").setDefaultValue(config.flySpeedMult));
        uiElements.add(flySmoothing = new Slider(1, xRight, y += 21, 0F, 1F, 0.7F, 200).setDisplayString("FlySmoothing").setDefaultValue(config.flySmoothing));
        uiElements.add(sprintSpeed = new Slider(1, xRight, y += 21, 0F, DFController.sprintSpeed.getMaxBaseSpeed(), 1F, 200).setDisplayString("SprintSpeed").setDefaultValue(config.sprintSpeed * 10));
        uiElements.add(sprintMultiplier = new Slider(1, xRight, y += 21, 0F, DFController.sprintSpeed.getMaxMultiplier(), 5F, 200).setDisplayString("SprintSpeedMultiplier").setDefaultValue(config.sprintSpeedMult));
        uiElements.add(jumpMultiplier = new Slider(1, xRight, y += 21, 0F, 1F, 0.75F, 200).setDisplayString("JumpMultiplier").setDefaultValue(config.jumpModifier));
        uiElements.add(leftRightMultiplier = new Slider(1, xRight, y += 21, 0F, 1F, 0.85F, 200).setDisplayString("Left/RightMultiplier").setDefaultValue(config.lrModifier));

        uiElements.add(new Label(xRight, y += 31, "Statuses").setColour(EnumChatFormatting.DARK_AQUA));
        UIHelper helper = DaFlight.getUIHelper();
        uiElements.add(flyStatus = helper.getEntryBox(xRight, y += 11, 200, 17, "Flight", "f", true).setString(config.flightStatus));
        uiElements.add(cineStatus = helper.getEntryBox(xRight, y += 21, 200, 17, "CineFlight", "c",true).setString(config.cineFlightStatus));
        uiElements.add(sprintStatus = helper.getEntryBox(xRight, y += 21, 200, 17, "Sprint", "r",true).setString(config.runStatus));
        uiElements.add(noClipStatus = helper.getEntryBox(xRight, y += 21, 200, 17, "NoClip", "n", true).setString(config.noClipStatus));
        uiElements.add(speedStatus = helper.getEntryBox(xRight, y += 21, 200, 17, "Speed", "*",true).setString(config.speedStatus));
        uiElements.add(fullbrightStatus = helper.getEntryBox(xRight, y + 21, 200, 17, "Fullbright", "fb",true).setString(config.fullBrightStatus));

        uiElements.add(scrollBar = new ScrollBar(displayWidth - 4, 0, displayHeight, maxYOffset).setVisible(isScrollable));
        setToolTips();
    }

    public void setToolTips()
    {
        disable.addToolTip(new ToolTip("Enable/Disable", "Quickly disable all features of the mod."));
        flight3D.addToolTip(new ToolTip("3DFlight", "Control your vertical movement by looking", "up/down whilst flying forwards."));
        showHud.addToolTip(new ToolTip("ShowHud", "Shows the status messages in the top left", "of the screen when mods are enabled."));
        showToolTips.addToolTip(new ToolTip("ShowToolTips", "Show tooltips like this in the settings menu."));
        perServer.addToolTip(new ToolTip("PerServerConfigs", "Creates a unique settings 'profile' for each", "server that you visit."));
        textShadow.addToolTip(new ToolTip("StatusTextShadow", "Draw the mod statuses with or", "without text shadow."));
        disableFov.addToolTip(new ToolTip("DisableFov", "Prevents the FOV from changing", "when flight is toggled on/off."));

        flyKey.addToolTip(new ToolTip("Fly", "Enable/Disable FlyMod with this key."));
        sprintKey.addToolTip(new ToolTip("Sprint", "Enable/Disable SprintMod with this key. Is only", "active whilst FlyMod is off."));
        speedKey.addToolTip(new ToolTip("Speed", "Enable/Disable the speed boost for FlyMod or", "SprintMod depending on which is active."));
        cineKey.addToolTip(new ToolTip("CineFly", "Enable/Disable cinematic flight mode. Is only", "active whilst FlyMod is on."));
        noClipKey.addToolTip(new ToolTip("NoClip", "Enable/Disable NoClip mode while flying (allows", "you to fly through solid blocks). Currently", "only works in single-player creative mode."));
        fullBrightKey.addToolTip(new ToolTip("FullBright", "Enable/Disable: Lights the entire world to full brightness."));
        flyUpKey.addToolTip(new ToolTip("FlyUp", "The key you hold to fly upwards."));
        flyDownKey.addToolTip(new ToolTip("FlyDown", "The key you hold to fly downwards."));
        speedUpKey.addToolTip(new ToolTip("SpeedUp", "Hold this to increase your speed whilst in-game.", "If speed boost is enabled, it will increase the multiplier.", "FlyMod takes priority over SprintMod if both are enabled."));
        speedDownKey.addToolTip(new ToolTip("SpeedDown", "Hold this to decrease your speed whilst in-game.", "If speed boost is enabled, it will increase the multiplier.", "FlyMod takes priority over SprintMod if both are enabled."));

        ToolTip hold = new ToolTip("Hold/Toggle", "Select whether this key should act as a", "toggle or if it should only be active whilst", "held down.");
        flyHold.addToolTip(hold);
        sprintHold.addToolTip(hold);
        speedHold.addToolTip(hold);
        fbHold.addToolTip(hold);

        flySpeed.addToolTip(new ToolTip("FlySpeed", "Set the base fly speed."));
        flyMultiplier.addToolTip(new ToolTip("FlySpeedMultiplier", "Set the boosted fly speed (toggled", "by the speed key)"));
        flySmoothing.addToolTip(new ToolTip("FlySmoothing", "Set the amount of momentum to be applied", "when flying."));
        sprintSpeed.addToolTip(new ToolTip("SprintSpeed", "Set the base sprint speed."));
        sprintMultiplier.addToolTip(new ToolTip("SprintSpeedMultiplier", "Set the boosted sprint speed (toggled by", "the speed key)."));
        jumpMultiplier.addToolTip(new ToolTip("JumpMultiplier", "Adjust the amount of vertical speed to", "be applied when jumping with sprint mod", "on."));
        leftRightMultiplier.addToolTip(new ToolTip("Left/RightMultiplier", "Adjust the amount of side-to-side speed", "to be applied when strafing with sprint or", "fly mod on."));
    }

    @Override
    public void drawScreen(int x, int y, float f)
    {
        for (UIElement e : uiElements)
            e.drawElement(x, y);
        if (showToolTips.getToggleState() && !mouseHeld)
            for (UIElement e : uiElements)
                e.renderToolTips(x, y);
    }

    @Override
    public void mouseClicked(int x, int y, int id)
    {
        mouseHeld = true;
        if (isShiftKeyDown())
        {
            for (UIElement e : uiElements)
                e.shiftClicked();
        }
        else
            for (UIElement e : uiElements)
                e.mouseInput(x, y, id);
    }

    @Override
    public void mouseReleased(int x, int y, int f)
    {
        mouseHeld = false;
        for (UIElement e : uiElements)
            e.mouseUnpressed(x, y);
    }

    public boolean keyInput(char keyChar, int keyId)
    {
        boolean exit = keyId == Keyboard.KEY_ESCAPE;
        for (UIElement e : uiElements)
            if (e.keyInput(keyChar, keyId))
                exit = false;
        return exit || Binds.MENU_BINDING.isKeyPressed();
    }

    public void handleScrollbar(int mouseX, int mouseY)
    {
        if (this.isScrollable && this.scrollBar.isActive())
        {
            int offset = scrollBar.getYOffset();
            if (offset != yOffset)
            {
                this.yOffset = offset;
                setScrollPos(this.yOffset);
            }
        }
    }

    public void setScrollBarVisibility(boolean visible)
    {
        this.scrollBar.setVisible(visible);
    }

    private void setScrollPos(int pos)
    {
        for (UIElement e : this.uiElements)
            e.setYPos(pos);
    }

    public void applySizeChange(int width, int height)
    {
        if (singleColumnMode && width > 420)
        {
            init(width, height, singleColumnMode = false);
        }
        else if (!singleColumnMode && width < 420)
        {
            init(width, height, singleColumnMode = true);
        }
        else
        {
            init(width, height, singleColumnMode);
        }
    }

    public int getContentHeight()
    {
        return singleColumnMode ? columnHeight * 2 : columnHeight;
    }

    public void save()
    {
        DaFlight.getGlobalConfig().perServerConfigs = perServer.getToggleState();
        DaFlight.getGlobalConfig().configToolTips = showToolTips.getToggleState();

        config.disabled = disable.getToggleState();
        config.threeDFlight = flight3D.getToggleState();
        config.showHud = showHud.getToggleState();
        config.textShadow = textShadow.getToggleState();
        config.disableFov = disableFov.getToggleState();

        config.flySpeed = flySpeed.getValue() / 10F;
        config.flySpeedMult = flyMultiplier.getValue();
        config.flySmoothing = flySmoothing.getValue();
        config.sprintSpeed = sprintSpeed.getValue() / 10F;
        config.sprintSpeedMult = sprintMultiplier.getValue();
        config.jumpModifier = jumpMultiplier.getValue();
        config.lrModifier = leftRightMultiplier.getValue();

        config.flyKey = flyKey.getValue();
        config.flyIsToggle = flyHold.getToggleState();
        config.sprintKey = sprintKey.getValue();
        config.sprintIsToggle = sprintHold.getToggleState();
        config.speedKey = speedKey.getValue();
        config.noClipKey = noClipKey.getValue();
        config.noCLipIsToggle = noClipHold.getToggleState();
        config.speedIsToggle = speedHold.getToggleState();
        config.fullBrightKey = fullBrightKey.getValue();
        config.fullbrightIsToggle = fbHold.getToggleState();
        config.cineFlyKey = cineKey.getValue();
        config.upKey = flyUpKey.getValue();
        config.downKey = flyDownKey.getValue();
        config.speedUpKey = speedUpKey.getValue();
        config.speedDownKey = speedDownKey.getValue();

        config.flightStatus = flyStatus.getValue();
        config.cineFlightStatus = cineStatus.getValue();
        config.runStatus = sprintStatus.getValue();
        config.noClipStatus = noClipStatus.getValue();
        config.speedStatus = speedStatus.getValue();
        config.fullBrightStatus = fullbrightStatus.getValue();

        DaFlight.getConfig().saveConfig();
        DaFlight.getConfig().applySettings();
        DaFlight.getGlobalConfig().saveConfig();
        DaFlight.getHud().updateMsg();
    }
}
