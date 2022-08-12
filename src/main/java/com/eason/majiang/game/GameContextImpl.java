package com.eason.majiang.game;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.PlayerAction;
import com.eason.majiang.object.MahjongTable;
import com.eason.majiang.object.PlayerInfo;
import com.eason.majiang.object.PlayerLocation;
import com.eason.majiang.rule.GameStage;
import com.eason.majiang.rule.GameStrategy;
import com.eason.majiang.rule.TimeLimitStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class GameContextImpl implements  GameContext {

    private static final Logger logger = Logger
            .getLogger(GameContextImpl.class.getSimpleName());

    private MahjongTable table;
    private GameStrategy gameStrategy;
    private TimeLimitStrategy timeLimitStrategy;

    private PlayerLocation zhuangLoaction;
    private GameStage gameStage;
    private List<Action> doneActions = new ArrayList<>();
    private GameResult gameResult;
    public GameContextImpl(MahjongTable table, GameStrategy gameStrategy, TimeLimitStrategy timeLimitStrategy) {
        this.table = table;
        this.gameStrategy = gameStrategy;
        this.timeLimitStrategy = timeLimitStrategy;
    }

    @Override
    public MahjongTable getTable() {
        return table;
    }

    @Override
    public GameStrategy getGameStrategy() {
        return gameStrategy;
    }

    @Override
    public TimeLimitStrategy getTimeLimitStrategy() {
        return timeLimitStrategy;
    }

    @Override
    public PlayerInfo getPlayerInfoByLocation(PlayerLocation location) {
        return table.getPlayerInfos().get(location);
    }

    @Override
    public PlayerLocation getZhuangLocation() {
        return zhuangLoaction;
    }

    @Override
    public void setZhuangLocation(PlayerLocation zhuangLocation) {
        this.zhuangLoaction = zhuangLocation;
    }

    @Override
    public GameStage getStage() {
        return gameStage;
    }

    @Override
    public void setStage(GameStage stage) {
       this.gameStage = stage;
    }

    @Override
    public void actionDone(Action action) {
       doneActions.add(action);
    }

    @Override
    public Action getLastAction() {
       if(doneActions.isEmpty())
       {
         return null;
       }

       return doneActions.get(doneActions.size()-1);
    }

    @Override
    public PlayerLocation getLastActionLocation() {
        Action lastAction = getLastAction();
        if (lastAction == null || !(lastAction instanceof PlayerAction)) {
            return null;
        }
        return ((PlayerAction) lastAction).getLocation();
    }

    @Override
    public List<Action> getDoneActions() {
         return doneActions;
    }

    @Override
    public GameResult getGameResult() {
        return  gameResult;
    }

    @Override
    public void setGameResult(GameResult gameResult) {
       this.gameResult = gameResult;
    }
    private final Map<PlayerLocation, GameContextPlayerView> playerViews = new HashMap<>();
    @Override
    public GameContextPlayerView getPlayerView(PlayerLocation location) {
        GameContextPlayerView view = playerViews.get(location);
        if (view == null) { // 不需要加锁，因为多创建了也没事
            view = newPlayerView(location);
            playerViews.put(location, view);
        }
        return view;
    }

    protected  GameContextPlayerView newPlayerView(PlayerLocation location)
    {
        return new GameContextPlayerViewImpl(this, location);
    }
}
