package com.eason.majiang.rule;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.AutoActionType;
import com.eason.majiang.action.PlayerActionType;
import com.eason.majiang.game.GameContext;

import java.util.List;

public interface GameStage {
    public String getName();
    public List<? extends PlayerActionType> getPlayerActionTypes();
    public List<? extends AutoActionType> getAutoActionTypes();
    public Action getPriorAction(GameContext context);
    public Action getFinalAction(GameContext context);

}
