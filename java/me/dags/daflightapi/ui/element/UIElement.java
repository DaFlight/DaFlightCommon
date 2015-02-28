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

package me.dags.daflightapi.ui.element;

import me.dags.daflight.gui.uielements.ToolTip;

/**
 * @author dags_ <dags@dags.me>
 */

public interface UIElement
{
    public void drawElement(int mouseX, int mouseY);

    public void renderToolTips(int mouseX, int mouseY);

    public void addToolTip(ToolTip t);

    public boolean mouseInput(int mouseX, int mouseY, int mouseButton);

    public boolean shiftClicked();

    public void mouseUnpressed(int mouseX, int mouseY);

    public boolean keyInput(char keyChar, int keyId);

    public void setYOffset(int offset);

    public void setYPos(int pos);

    public void resetYOffset();
}
