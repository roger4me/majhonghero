package com.eason.majiang.rule;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.PlayerActionType;
import com.eason.majiang.game.GameContext;

import java.util.List;

public class InitStage implements GameStage {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<? extends PlayerActionType> getPlayerActionTYpes() {
        return null;
    }

    @Override
    public List<? extends AutoActionType> getAutoActionTypes() {
        return null;
    }

    @Override
    public Action getPriorAction(GameContext context) {
        return null;
    }

    @Override
    public Action getFinalAction(GameContext context) {
        return null;
    }
}
