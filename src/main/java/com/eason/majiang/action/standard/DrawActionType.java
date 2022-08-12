package com.eason.majiang.action.standard;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.IllegalActionException;
import com.eason.majiang.game.GameContext;
import com.eason.majiang.game.GameContextPlayerView;
import com.eason.majiang.object.PlayerLocation;
import com.eason.majiang.object.Tile;

import java.util.Set;

public class DrawActionType extends AbstractPlayerActionType {
    @Override
    protected int getActionTilesSize() {
        return 0;
    }

    @Override
    protected boolean isLegalActionWithPreconition(GameContextPlayerView context, Set<Tile> tiles) {
        return context.getTableView().getTileWallSize()>0;
    }

    @Override
    protected void doLegalAction(GameContext context, PlayerLocation location, Set<Tile> tiles) {
        Tile tile = context.getTable().draw(1).get(0);
        context.getPlayerInfoByLocation(location).getAliveTiles().add(tile);
        context.getPlayerInfoByLocation(location).setLastDrawedTile(tile);
    }

    @Override
    public boolean canPass(GameContext context, PlayerLocation location) {
        return false;
    }

}
