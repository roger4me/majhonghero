package com.eason.majiang.game;

import com.eason.majiang.action.Action;
import com.eason.majiang.object.MahjongTablePlayerView;
import com.eason.majiang.object.PlayerInfo;
import com.eason.majiang.object.PlayerLocation;
import com.eason.majiang.object.Tile;
import com.eason.majiang.rule.GameStrategy;
import com.eason.majiang.rule.TimeLimitStrategy;

import java.util.List;

public interface GameContextPlayerView {
    public MahjongTablePlayerView getTableView();

    public GameStrategy getGameStrategy();

    public TimeLimitStrategy getTimeLimitStrategy();

    public PlayerLocation getMyLocation();

    public PlayerInfo getMyInfo();
    public PlayerLocation getZhuangLocation();

    /**
     * 返回当前阶段名称。
     */
    public String getStageName();

    /**
     * 返回到目前为止做出的最后一个动作。
     */
    public Action getLastAction();

    /**
     * 返回到目前为止做出的最后一个动作的玩家位置。
     */
    public PlayerLocation getLastActionLocation();

    /**
     * 如果刚刚摸牌，则返回刚摸的牌，否则返回null。
     */
    public Tile getJustDrawedTile();

    /**
     * 返回已经做完的动作。
     */
    public List<Action> getDoneActions();

    /**
     * 如果已结束则返回游戏结果，否则返回null。
     */
    public GameResult getGameResult();



}
