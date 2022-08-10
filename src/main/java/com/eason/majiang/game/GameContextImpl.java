package com.eason.majiang.game;

import com.eason.majiang.action.Action;
import com.eason.majiang.object.MahjongTable;
import com.eason.majiang.object.PlayerInfo;
import com.eason.majiang.object.PlayerLocation;

import java.util.List;
import java.util.logging.Logger;

public class GameContextImpl implements  GameContext {


    private static final Logger logger = Logger.getLogger(GameContextImpl.class.getSimpleName());

    private MahjongTable table;
    private



    @Override
    public MahjongTable getTable() {
        return null;
    }

    @Override
    public GameStrategy getGameStrategy() {
        return null;
    }

    @Override
    public TimeLimitStrategy getTimeLimitStrategy() {
        return null;
    }

    @Override
    public PlayerInfo getPlayerInfoByLocation(PlayerLocation location) {
        return null;
    }

    @Override
    public PlayerLocation getZhuangLocation() {
        return null;
    }

    @Override
    public void setZhuangLocation(PlayerLocation zhuangLocation) {

    }

    @Override
    public GameStage getStage() {
        return null;
    }

    @Override
    public void setStage(GameStage stage) {

    }

    @Override
    public void actionDone(Action action) {

    }

    @Override
    public Action getLastAction() {
        return null;
    }

    @Override
    public PlayerLocation getLastActionLocation() {
        return null;
    }

    @Override
    public List<Action> getDoneActions() {
        return null;
    }

    @Override
    public GameResult getGameResult() {
        return null;
    }

    @Override
    public void setGameResult(GameResult gameResult) {

    }

    @Override
    public GameContextPlayerView getPlayerView(PlayerLocation location) {
        return null;
    }
}
