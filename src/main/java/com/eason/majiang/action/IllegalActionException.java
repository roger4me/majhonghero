package com.eason.majiang.action;

import com.eason.majiang.game.GameContext;

public class IllegalActionException extends Exception {
    private Action action;

    public IllegalActionException(GameContext context, Action action)
    {
        super(action.toString() +" context:" + context);
    }
}
