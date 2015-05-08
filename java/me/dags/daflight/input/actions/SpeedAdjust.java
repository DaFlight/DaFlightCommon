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

package me.dags.daflight.input.actions;

import me.dags.daflight.player.DFController;

/**
 * @author dags_ <dags@dags.me>
 */

public class SpeedAdjust implements Action
{
    private static long ticker;

    protected boolean tick()
    {
        if (System.currentTimeMillis() - ticker >= 50)
        {
            ticker = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    @Override
    public boolean pressed(DFController DFController)
    {
        return false;
    }

    @Override
    public boolean held(DFController DFController)
    {
        return false;
    }

    @Override
    public boolean released(DFController DFController)
    {
        return false;
    }
}
