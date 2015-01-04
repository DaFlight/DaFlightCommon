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

package me.dags.daflight.gui.uielements;

import me.dags.daflight.gui.UIElement;
import me.dags.daflight.minecraft.MCGame;
import me.dags.daflight.utils.GLHelper;
import net.minecraft.util.ResourceLocation;

/**
 * @author dags_ <dags@dags.me>
 */

public class Logo implements UIElement
{
    private final ResourceLocation LOGO = new ResourceLocation("daflight", "daflight-logo.png");
    private int xPos;
    private int yPos;
    private int defaultY;
    private int imageWidth;
    private int imageHeight;
    private int width;
    private int height;

    public Logo()
    {
        imageWidth = 512;
        imageHeight = 512;
        width = 128;
        height = 128;
    }

    ;

    public Logo(int x, int y)
    {
        xPos = x;
        yPos = y;
        defaultY = y;
        imageWidth = 512;
        imageHeight = 512;
        width = 128;
        height = 128;
    }

    public Logo setXY(int x, int y)
    {
        xPos = x;
        yPos = y;
        defaultY = y;
        return this;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void draw()
    {
        MCGame.getMinecraft().getTextureManager().bindTexture(LOGO);
        GLHelper.glDrawTexturedRect(xPos, yPos, width, height, 1, 1, imageWidth, imageHeight);
    }

    @Override
    public void drawElement(int mouseX, int mouseY)
    {
        draw();
    }

    @Override
    public void renderToolTips(int mouseX, int mouseY)
    {

    }

    @Override
    public void addToolTip(ToolTip t)
    {

    }

    @Override
    public boolean mouseInput(int mouseX, int mouseY)
    {
        return false;
    }

    @Override
    public boolean shiftClicked()
    {
        return false;
    }

    @Override
    public void mouseUnpressed(int mouseX, int mouseY)
    {

    }

    @Override
    public boolean keyInput(char keyChar, int keyId)
    {
        return false;
    }

    @Override
    public void setYOffset(int offset)
    {
        this.yPos += offset;
    }

    @Override
    public void setYPos(int pos)
    {
        this.yPos = this.defaultY + pos;
    }

    @Override
    public void resetYOffset()
    {
        this.yPos = defaultY;
    }
}
