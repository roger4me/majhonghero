package com.eason.majiang.rule;

import com.eason.majiang.action.PlayerActionType;
import com.eason.majiang.game.GameContext;
import com.eason.majiang.object.PlayerLocation;

import java.util.Map;
import java.util.Set;

@FunctionalInterface
public interface TimeLimitStrategy {
    public static final TimeLimitStrategy NO_LIMIT =(xx,yy)-> null;

    Integer getLimit(GameContext context, Map<PlayerLocation, Set<PlayerActionType>> choices);

}
