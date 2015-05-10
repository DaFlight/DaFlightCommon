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

package me.dags.daflight.gui;

import me.dags.daflight.DaFlight;
import me.dags.daflightapi.ui.element.UIElement;
import org.lwjgl.input.Mouse;

/**
 * @author dags_ <dags@dags.me>
 */

public class QuickMenu extends ConfigGui
{
    public QuickMenu()
    {
        super();
    }

    @Override
    public void drawScreen(int x, int y, float f)
    {
        checkSizeChange();
        drawDefaultBackground();
        handleScrolling();
        super.drawScreen(x, y, f);
        super.handleScrollbar(x, y);
    }

    @Override
    public void keyTyped(char keyChar, int keyId)
    {
        boolean exit = keyInput(keyChar, keyId);
        if (exit)
            close();
    }

    private void checkSizeChange()
    {
        if (DaFlight.getMC().screenSizeChanged())
        {
            save();
            super.maxYOffset = 0;
            super.yOffset = 0;
            applySizeChange(DaFlight.getMC().getScaledResolution().getScaledWidth(), DaFlight.getMC().getScaledResolution().getScaledHeight());
        }
    }

    private int scale()
    {
        return DaFlight.getMC().getScaledResolution().getScaleFactor();
    }

    public void handleScrolling()
    {
        if (Mouse.hasWheel() && super.isScrollable)
        {
            int offset = 0;
            int i = Mouse.getDWheel();
            Mouse.getDWheel();
            if (i > 0)
                offset = 5 * scale();
            else if (i < 0)
                offset = -5 * scale();
            if (offset != 0)
            {
                if (super.yOffset + offset > 0 || super.yOffset + offset < super.maxYOffset)
                    offset = 0;
                super.yOffset += offset;
                scroll(offset);
            }
        }
    }

    private void scroll(int offset)
    {
        for (UIElement e : super.uiElements)
            e.setYOffset(offset);
    }

    private void close()
    {
        save();
        DaFlight.getMC().getMinecraft().displayGuiScreen(super.parent);
        DaFlight.getMC().getMinecraft().setIngameFocus();
    }
}
