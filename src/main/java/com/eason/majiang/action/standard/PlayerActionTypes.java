package com.eason.majiang.action.standard;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.ActionType;
import com.eason.majiang.action.IllegalActionException;
import com.eason.majiang.action.PlayerActionType;
import com.eason.majiang.game.GameContext;
import com.eason.majiang.game.GameContextPlayerView;
import com.eason.majiang.object.PlayerLocation;
import com.eason.majiang.object.Tile;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static com.eason.majiang.object.PlayerLocation.Relation.PREVIOUS;
import static com.eason.majiang.object.TileGroupType.*;

public enum  PlayerActionTypes implements PlayerActionType {


//    CHI(new CpgActionType(CHI_GROUP, Collections.singleton(PREVIOUS))),
//    /**
//     * 碰
//     */
//    PENG(new CpgActionType(PENG_GROUP)),
//    /**
//     * 直杠
//     */
//    ZHIGANG(new CpgActionType(ZHIGANG_GROUP)),
//    /**
//     * 补杠
//     */
//    BUGANG(new BugangActionType()),
//    /**
//     * 暗杠
//     */
//    ANGANG(new AngangActionType()),
//    /**
//     * 补花
//     */
//    BUHUA(new BuhuaActionType()),
//    /**
//     * 打牌
//     */
//    DISCARD(new DiscardActionType()),
//    /**
//     * 打牌的同时听牌
//     */
//    DISCARD_WITH_TING(new DiscardWithTingActionType()),
//    /**
//     * 摸牌
//     */
   DRAW(new DrawActionType()),
//    /**
//     * 摸底牌
//     */
//    DRAW_BOTTOM(new DrawBottomActionType()),
//    /**
//     * 和牌
//     */
//    WIN(new WinActionType());

    ;

    private final PlayerActionType type;

    private PlayerActionTypes(PlayerActionType type)
    {
        this.type = type;
    }

    @Override
    public boolean canDo(GameContext context, PlayerLocation location) {
        return type.canDo(context, location);
    }

    @Override
    public boolean canPass(GameContext context, PlayerLocation location) {
        return type.canPass(context, location);
    }

    @Override
    public Collection<Set<Tile>> getLegalActionTiles(GameContextPlayerView context) {
        return type.getLegalActionTiles(context);
    }

    @Override
    public boolean isLegalAction(GameContext context, Action action) {
        return type.isLegalAction(context, action);
    }

    @Override
    public void doAction(GameContext context, Action action) throws IllegalActionException {
        type.doAction(context, action);
    }

    @Override
    public boolean matchBy(ActionType actionType) {
        return type.matchBy(actionType);
    }

    @Override
    public Class<? extends ActionType> getRealTypeClass() {
        return type.getRealTypeClass();
    }

    @Override
    public ActionType getRealTypeObject() {
        return type;
    }
}
