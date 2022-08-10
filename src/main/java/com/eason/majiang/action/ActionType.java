package com.eason.majiang.action;

import com.eason.majiang.game.GameContext;

public interface ActionType {

    public default String name()
    {
        return this.getClass().getSimpleName();
    }

    public boolean isLegalAction(GameContext context, Action action);

    public void doAction(GameContext context, Action action) throws IllegalActionException;

    public default boolean matchBy(ActionType actionType)
    {
      return getRealTypeClass().isAssignableFrom(actionType.getRealTypeClass());
    }

    public default Class<? extends ActionType> getRealTypeClass()
    {
        return this.getClass();
    }

    public default ActionType getRealTypeObject()
    {
        return this;
    }

}
