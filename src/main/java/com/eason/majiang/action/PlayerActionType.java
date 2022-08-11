package com.eason.majiang.action;

import com.eason.majiang.game.GameContext;
import com.eason.majiang.game.GameContextPlayerView;
import com.eason.majiang.object.PlayerLocation;
import com.eason.majiang.object.Tile;

import java.util.Collection;
import java.util.Set;

public interface PlayerActionType extends ActionType {
    public boolean canDo(GameContext context, PlayerLocation location);
    public boolean canPass(GameContext context, PlayerLocation location);
    Collection<Set<Tile>> getLegalActionTiles(GameContextPlayerView context);

}
