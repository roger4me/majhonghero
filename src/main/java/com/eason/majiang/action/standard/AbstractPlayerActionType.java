package com.eason.majiang.action.standard;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.IllegalActionException;
import com.eason.majiang.action.PlayerAction;
import com.eason.majiang.action.PlayerActionType;
import com.eason.majiang.game.GameContext;
import com.eason.majiang.game.GameContextPlayerView;
import com.eason.majiang.object.PlayerInfo;
import com.eason.majiang.object.PlayerLocation;
import com.eason.majiang.object.Tile;
import static com.eason.majiang.utils.MyUtils.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public abstract class AbstractPlayerActionType implements PlayerActionType {
    private static final Logger logger = Logger
            .getLogger(AbstractPlayerActionType.class.getSimpleName());

    @Override
    public boolean canDo(GameContext context, PlayerLocation location) {
        if (!meetPrecondition(context.getPlayerView(location))) {
            return false;
        }
        return canDoWithPrecondition(context, location);
    }

    @Override
    public void doAction(GameContext context, Action action) throws IllegalActionException {
        if (!isLegalAction(context, action)) {
            throw new IllegalActionException(context, action);
        }

        doLegalAction(context, ((PlayerAction)action).getLocation(), ((PlayerAction)action).getTiles());
    }

    @Override
    public boolean isLegalAction(GameContext context, Action action) {
        Objects.requireNonNull(action);
        if (!(action instanceof PlayerAction)) {
            throw new IllegalArgumentException(action + " is not a PlayerAction");
        }
        if (!matchBy(action.getType())) {
            throw new IllegalArgumentException(
                    action.getType().getRealTypeClass().getSimpleName() + " is not " + getRealTypeClass());
        }
        if (!isLegalActionTiles(context.getPlayerView(((PlayerAction) action).getLocation()),
                ((PlayerAction) action).getTiles())) {
            return false;
        }
        return true;
    }

    protected  boolean meetPrecondition(GameContextPlayerView context)
    {
        if (!isAllowedInTing() && context.getMyInfo().isTing()) {
            return false;
        }

        // 验证aliveTiles数量条件
        Predicate<Integer> aliveTileSizeCondition = getAliveTileSizePrecondition();
        if (aliveTileSizeCondition != null) {
            if (!aliveTileSizeCondition
                    .test(context.getMyInfo().getAliveTiles().size())) {
                return false;
            }
        }

        // 验证上一个动作条件
        BiPredicate<Action, PlayerLocation> lastActionPrecondition = getLastActionPrecondition();
        if (lastActionPrecondition != null) {
            Action lastAction = context.getLastAction();
            if (lastAction != null) {
                if (!lastActionPrecondition.test(lastAction,
                        context.getMyLocation())) {
                    return false;
                }
            }
        }

        return true;
    }

    protected  boolean isAllowedInTing()
    {
        return true;
    }

    protected BiPredicate<Action, PlayerLocation> getLastActionPrecondition()
    {
        return (a,p) -> true;
    }

    protected Predicate<Integer> getAliveTileSizePrecondition()
    {
        return s -> true;
    }

    protected boolean canDoWithPrecondition(GameContext context, PlayerLocation location)
    {
        return legalActionTilesStream(context.getPlayerView(location)).findAny()
                .isPresent();
    }

    @Override
    public Collection<Set<Tile>> getLegalActionTiles(GameContextPlayerView context) {
        if (!meetPrecondition(context)) {
            return Collections.emptySet();
        }
        return legalActionTilesStream(context).collect(Collectors.toSet());
    }

    protected Stream<Set<Tile>> legalActionTilesStream(GameContextPlayerView context) {
        PlayerInfo playerInfo = context.getMyInfo();
        if (playerInfo == null) {
            return Stream.empty();
        }
        return combSetStream(
                getActionTilesRange(context, context.getMyLocation()),
                getActionTilesSize())
                .filter(tiles -> isLegalActionTiles(context, tiles));
    }

    protected Set<Tile> getActionTilesRange(GameContextPlayerView context, PlayerLocation location) {
        return context.getMyInfo().getAliveTiles();
    }

    protected abstract int getActionTilesSize();

    protected boolean isLegalActionTiles(GameContextPlayerView context, Set<Tile> tiles) {
        PlayerLocation location = context.getMyLocation();
        if (!meetPrecondition(context)) {
            return false;
        }

        int legalTilesSize = getActionTilesSize();
        if (legalTilesSize > 0
                && (tiles == null || tiles.size() != legalTilesSize)) {
            return false;
        }

        Set<Tile> legalTilesRange = getActionTilesRange(context, location);
        if (tiles != null && legalTilesRange != null
                && !legalTilesRange.containsAll(tiles)) {
            return false;
        }

        boolean legal = isLegalActionWithPreconition(context, tiles);
        return legal;
    }

    protected abstract boolean isLegalActionWithPreconition(GameContextPlayerView context,
                                                            Set<Tile> tiles);

    protected abstract void doLegalAction(GameContext context,
                                          PlayerLocation location, Set<Tile> tiles);

    @Override
    public String toString() {
        return name();
    }

}
