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

package me.dags.daflight.input;

import me.dags.daflight.LiteModDaFlight;
import me.dags.daflight.input.binds.KeyBind;
import me.dags.daflight.input.binds.KeyBinds;
import me.dags.daflight.player.DaPlayer;

/**
 * @author dags_ <dags@dags.me>
 */

public class KeybindHandler
{
    public static void checkMenuKey()
    {
        if (KeyBinds.MENU_BINDING.isKeyPressed())
            KeyBinds.MENU_BINDING.displayGui();
    }

    public static void handleInput(DaPlayer daPlayer)
    {
        boolean result = false;
        for (KeyBind kb : DaPlayer.KEY_BINDS.binds)
        {
            if (kb.keyPressed())
                result = kb.getAction().pressed(daPlayer) || result;
            else if (kb.keyHeld())
                result = kb.getAction().held(daPlayer) || result;
            else if (kb.keyReleased())
                result = kb.getAction().released(daPlayer) || result;
        }
        if (result)
            LiteModDaFlight.getHud().updateMsg();
    }
}
