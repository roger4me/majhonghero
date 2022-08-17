package com.eason.majiang.rule.simple;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.ActionTypeAndLocation;
import com.eason.majiang.action.PlayerAction;
import com.eason.majiang.action.PlayerActionType;
import com.eason.majiang.game.GameContext;
import com.eason.majiang.object.PlayerLocation;
import com.eason.majiang.rule.AbstractGameStrategy;
import com.eason.majiang.rule.GameStage;
import com.eason.majiang.rule.win.FanType;
import com.eason.majiang.rule.win.WinType;

import java.util.*;

public class SimpleGameStrategy extends AbstractGameStrategy {
    private static final List<WinType> WIN_TYPES = Collections.singletonList(NormalWinType.get());

    @Override
    protected Collection<GameStage> getAllStages() {
        return null;
    }

    @Override
    protected PlayerLocation nextZhuangLocation(GameContext context) {
        return PlayerLocation.EAST;
    }

    @Override
    public GameStage getFirstStage() {
        return null;
    }

    @Override
    public Comparator<ActionTypeAndLocation> getActionPriorityComparator() {
        return null;
    }

    @Override
    public PlayerAction getPlayerDefaultAction(GameContext context, PlayerLocation location, Set<PlayerActionType> choises) {
        return null;
    }

    @Override
    public Action getDefaultAction(GameContext context, Map<PlayerLocation, Set<PlayerActionType>> choises) {
        return null;
    }

    @Override
    public List<WinType> getAllWinTypes() {
        return null;
    }

    @Override
    public List<FanType> getAllFanTypes() {
        return null;
    }

    @Override
    public boolean tryEndGame(GameContext context) {
        return false;
    }
}
