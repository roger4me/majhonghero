package com.eason.majiang.rule;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.AutoActionType;
import com.eason.majiang.action.PlayerActionType;
import com.eason.majiang.action.StageSwitchAction;
import com.eason.majiang.game.GameContext;

import java.util.List;

public class InitStage implements GameStage {
    public static final String NAME = "INIT";
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public List<? extends PlayerActionType> getPlayerActionTypes() {
        return List.of();
    }

    @Override
    public List<? extends AutoActionType> getAutoActionTypes() {
       return List.of();
    }

    @Override
    public Action getPriorAction(GameContext context) {
        return new StageSwitchAction(context.getGameStrategy().getFirstStage().getName());
    }

    @Override
    public Action getFinalAction(GameContext context) {
        return null;
    }
}
