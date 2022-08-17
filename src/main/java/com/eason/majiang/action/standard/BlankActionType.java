package com.eason.majiang.action.standard;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.IllegalActionException;
import com.eason.majiang.action.PlayerActionType;
import com.eason.majiang.game.GameContext;
import com.eason.majiang.game.GameContextPlayerView;
import com.eason.majiang.object.PlayerLocation;
import com.eason.majiang.object.Tile;

import java.util.Collection;
import java.util.Set;

public class BlankActionType implements PlayerActionType {
    @Override
    public boolean canDo(GameContext context, PlayerLocation location) {
        return false;
    }

    @Override
    public boolean canPass(GameContext context, PlayerLocation location) {
        return false;
    }

    @Override
    public Collection<Set<Tile>> getLegalActionTiles(GameContextPlayerView context) {
        return null;
    }

    @Override
    public boolean isLegalAction(GameContext context, Action action) {
        return false;
    }

    @Override
    public void doAction(GameContext context, Action action) throws IllegalActionException {

    }
}
