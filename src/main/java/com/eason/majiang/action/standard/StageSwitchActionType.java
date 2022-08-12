package com.eason.majiang.action.standard;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.ActionType;
import com.eason.majiang.action.IllegalActionException;
import com.eason.majiang.action.StageSwitchAction;
import com.eason.majiang.game.GameContext;
import com.eason.majiang.rule.GameStage;

public class StageSwitchActionType implements ActionType {
    public static final StageSwitchActionType INSTANCE = new StageSwitchActionType();

    @Override
    public String name() {
        return "STAGE";
    }

    @Override
    public boolean isLegalAction(GameContext context, Action action) {
        if (!(action instanceof StageSwitchAction)) {
            return false;
        }
        return context.getGameStrategy().getStageByName(((StageSwitchAction) action).getNextStageName()) != null;
    }

    @Override
    public void doAction(GameContext context, Action action) throws IllegalActionException {
        GameStage nextStage = context.getGameStrategy().getStageByName(((StageSwitchAction) action).getNextStageName());
        if (nextStage == null) {
            throw new IllegalActionException(context, action);
        }
        context.setStage(nextStage);
    }

    @Override
    public String toString() {
        return name();
    }
}
