package com.eason.majiang.action.standard;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.AutoActionType;
import com.eason.majiang.action.PlayerActionType;
import com.eason.majiang.game.GameContext;
import com.eason.majiang.rule.GameStage;

import java.util.List;

public class FinishedStage implements GameStage {
    public static final String NAME = "FINISHED";
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
        return null;
    }

    @Override
    public Action getFinalAction(GameContext context) {
        // TODO Auto-generated method stub
        return null;
    }
}
