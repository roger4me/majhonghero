package com.eason.majiang.game;

import com.eason.majiang.action.Action;
import com.eason.majiang.object.MahjongTable;
import com.eason.majiang.object.PlayerInfo;
import com.eason.majiang.object.PlayerLocation;

import java.util.List;

public interface GameContext {
    public MahjongTable getTable();

    public GameStrategy getGameStrategy();

    public TimeLimitStrategy getTimeLimitStrategy();

    public PlayerInfo getPlayerInfoByLocation(PlayerLocation location);

    public PlayerLocation getZhuangLocation();

    public void setZhuangLocation(PlayerLocation zhuangLocation);

    public GameStage getStage();

    public void setStage(GameStage stage);

    public void actionDone(Action action);

    public Action getLastAction();

    public PlayerLocation getLastActionLocation();

    public List<Action> getDoneActions();

    public GameResult getGameResult();

    public void setGameResult(GameResult gameResult);

    public GameContextPlayerView getPlayerView(PlayerLocation location);
}
