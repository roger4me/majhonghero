package com.eason.majiang.game;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.PlayerAction;
import com.eason.majiang.object.MahjongTablePlayerView;
import com.eason.majiang.object.PlayerInfo;
import com.eason.majiang.object.PlayerLocation;
import com.eason.majiang.object.Tile;
import com.eason.majiang.rule.GameStrategy;
import com.eason.majiang.rule.TimeLimitStrategy;

import java.util.List;

import static com.eason.majiang.action.standard.PlayerActionTypes.*;

public class GameContextPlayerViewImpl implements GameContextPlayerView {

    private final GameContext gameContext;
    private final PlayerLocation myLocation;

    public GameContextPlayerViewImpl(GameContextImpl gameContext, PlayerLocation location) {
        this.gameContext = gameContext;
        this.myLocation = location;
    }

    @Override
    public MahjongTablePlayerView getTableView() {
        return gameContext.getTable().getPlayerView(myLocation);
    }

    @Override
    public GameStrategy getGameStrategy() {
        return gameContext.getGameStrategy();
    }

    @Override
    public TimeLimitStrategy getTimeLimitStrategy() {
        return gameContext.getTimeLimitStrategy();
    }

    @Override
    public PlayerLocation getMyLocation() {
        return myLocation;
    }

    @Override
    public PlayerInfo getMyInfo() {
        return gameContext.getPlayerInfoByLocation(myLocation);
    }

    @Override
    public PlayerLocation getZhuangLocation() {
        return gameContext.getZhuangLocation();
    }

    @Override
    public String getStageName() {
        return gameContext.getStage().getName();
    }

    @Override
    public Action getLastAction() {
        return gameContext.getLastAction();
    }

    @Override
    public PlayerLocation getLastActionLocation() {
        return gameContext.getLastActionLocation();
    }

    @Override
    public Tile getJustDrawedTile() {
        Action la = getLastAction();
        if (!(la instanceof PlayerAction)) {
            return null;
        }
        if (((PlayerAction) la).getLocation() != myLocation) {
            return null;
        }
        if (!DRAW.matchBy(la.getType())) {
            return null;
        }
        return getMyInfo().getLastDrawedTile();
    }

    @Override
    public List<Action> getDoneActions() {
        return gameContext.getDoneActions();
    }

    @Override
    public GameResult getGameResult() {
        return gameContext.getGameResult();
    }
}
