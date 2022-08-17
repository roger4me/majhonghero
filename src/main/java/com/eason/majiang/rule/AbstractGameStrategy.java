package com.eason.majiang.rule;

import com.eason.majiang.action.*;
import com.eason.majiang.game.GameContext;
import com.eason.majiang.object.MahjongTable;
import com.eason.majiang.object.PlayerLocation;
import com.eason.majiang.object.Tile;

import java.util.*;
import java.util.stream.Stream;

import static com.eason.majiang.action.standard.AutoActionTypes.LIUJU;
import static com.eason.majiang.action.standard.PlayerActionTypes.*;
import static com.eason.majiang.action.standard.PlayerActionTypes.WIN;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public abstract class AbstractGameStrategy implements GameStrategy{
    private Map<String,GameStage> stages;

    @Override
    public boolean checkReady(MahjongTable table)
    {
        return Stream.of(PlayerLocation.values()).map(table::getPlayerByLocation).allMatch(Objects::nonNull);
    }

    @Override
    public List<Tile> getAllTiles() {
        return Tile.all();
    }

    @Override
    public GameStage getStageByName(String stageName) {
        if(stages == null)
        {
            synchronized (this)
            {
                if(stages == null)
                {
                    stages = getAllStages().stream().collect(toMap(GameStage::getName, identity()));
                }
            }
        }
        return stages.get(stageName);
    }

    protected  abstract Collection<GameStage> getAllStages();

    @Override
    public void readyContext(GameContext context) {
        context.setZhuangLocation(nextZhuangLocation(context));
    }

    protected abstract PlayerLocation nextZhuangLocation(GameContext context);

    private static final List<ActionType> ACTION_TYPE_PRIORITY_LIST = Arrays
            .asList(CHI, PENG, ZHIGANG, BUHUA, DRAW_BOTTOM, WIN);

    @Override
    public Comparator<ActionTypeAndLocation> getActionPriorityComparator()
    {
        Comparator<ActionTypeAndLocation> c = Comparator.comparing(
                atl -> ACTION_TYPE_PRIORITY_LIST.indexOf(atl.getActionType()));
        c = c.reversed();
        c = c.thenComparing(a -> {
            PlayerLocation lastLocation = a.getContext()
                    .getLastActionLocation();
            return lastLocation == null || a.getLocation() == null ? PlayerLocation.Relation.SELF
                    : lastLocation.getRelationOf(a.getLocation());
        });
        return c;
    }

    @Override
    public PlayerAction getPlayerDefaultAction(GameContext context,
                                               PlayerLocation location, Set<PlayerActionType> choises) {
        if (choises.contains(DRAW)) {
            return new PlayerAction(location, DRAW);
        }
        if (choises.contains(DRAW_BOTTOM)) {
            return new PlayerAction(location, DRAW_BOTTOM);
        }
        if (choises.contains(DISCARD)) {
            Tile tileToDiscard;
            Action lastAction = context.getLastAction();
            if (context.getLastActionLocation() == location
                    && DRAW.matchBy(lastAction.getType())) {
                tileToDiscard = ((PlayerAction) lastAction).getTile();
            } else {
                tileToDiscard = context.getPlayerInfoByLocation(location)
                        .getAliveTiles().iterator().next();
            }
            return new PlayerAction(location, DISCARD, tileToDiscard);
        }
        return null;
    }

    @Override
    public Action getDefaultAction(GameContext context,
                                   Map<PlayerLocation, Set<PlayerActionType>> choises) {
        if (context.getTable().getTileWallSize() == 0) {
            return new Action(LIUJU);
        } else {
            return null;
        }
    }

    @Override
    public boolean tryEndGame(GameContext context) {
        return context.getGameResult() != null;
    }
}
