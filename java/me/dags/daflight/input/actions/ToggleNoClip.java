package me.dags.daflight.input.actions;

import me.dags.daflight.player.DFController;

/**
 * @author dags_ <dags@dags.me>
 */

public class ToggleNoClip implements Action
{
    @Override
    public boolean pressed(DFController DFController)
    {
        DFController.toggleNoClip();
        return true;
    }

    @Override
    public boolean held(DFController DFController)
    {
        if (!DFController.noClipOn)
        {
            DFController.toggleNoClip();
            return true;
        }
        return false;
    }

    @Override
    public boolean released(DFController DFController)
    {
        if (DFController.noClipOn)
        {
            DFController.toggleNoClip();
            return true;
        }
        return false;
    }
}
