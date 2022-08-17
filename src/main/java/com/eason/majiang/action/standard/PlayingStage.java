package com.eason.majiang.action.standard;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.AutoActionType;
import com.eason.majiang.action.PlayerActionType;
import com.eason.majiang.action.StageSwitchAction;
import com.eason.majiang.game.GameContext;
import com.eason.majiang.rule.GameStage;

import java.util.List;

import static com.eason.majiang.action.standard.PlayerActionTypes.WIN;

public class PlayingStage implements GameStage {
    public static final String NAME ="PLAYING";


    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public List<? extends PlayerActionType> getPlayerActionTypes() {
       return List.of(PlayerActionTypes.values());
    }

    @Override
    public List<? extends AutoActionType> getAutoActionTypes() {
        return List.of();
    }

    @Override
    public Action getPriorAction(GameContext context) {
        if(context.getDoneActions().stream().map(Action::getType).anyMatch(type->type == WIN))
        {
            return new StageSwitchAction(FinishedStage.NAME);
        }
        return null;
    }

    @Override
    public Action getFinalAction(GameContext context) {
        return new Action(AutoActionTypes.LIUJU);
    }
}
