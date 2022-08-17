package com.eason.majiang.action.standard;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.AutoActionType;
import com.eason.majiang.action.IllegalActionException;
import com.eason.majiang.game.GameContext;
import com.eason.majiang.game.GameResult;

public class LiujuActionType implements AutoActionType {
    @Override
    public boolean isLegalAction(GameContext context, Action action) {
        return context.getStage().getName().equals(PlayingStage.NAME);
    }

    @Override
    public void doAction(GameContext context, Action action) throws IllegalActionException {
        GameResult result = new GameResult(context.getTable().getPlayerInfos(),
                context.getZhuangLocation());
        context.setGameResult(result);
    }
}
