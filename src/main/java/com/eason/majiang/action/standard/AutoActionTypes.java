package com.eason.majiang.action.standard;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.ActionType;
import com.eason.majiang.action.AutoActionType;
import com.eason.majiang.action.IllegalActionException;
import com.eason.majiang.game.GameContext;

public enum  AutoActionTypes  implements AutoActionType {

    DEAL(new DealActionType()),
    /**
     * 流局
     */
    LIUJU(new LiujuActionType());

    private final ActionType type;

    private AutoActionTypes(ActionType type) {
        this.type = type;
    }

    @Override
    public void doAction(GameContext context, Action action) throws IllegalActionException {
        type.doAction(context, action);
    }

    @Override
    public boolean matchBy(ActionType testType) {
        return type.matchBy(testType);
    }

    @Override
    public Class<? extends ActionType> getRealTypeClass() {
        return type.getRealTypeClass();
    }

    @Override
    public ActionType getRealTypeObject() {
        return type;
    }

    @Override
    public boolean isLegalAction(GameContext context, Action action) {
        return type.isLegalAction(context, action);
    }
}
