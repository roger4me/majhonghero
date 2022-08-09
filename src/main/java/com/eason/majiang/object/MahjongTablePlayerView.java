package com.eason.majiang.object;

import java.util.Map;

public interface MahjongTablePlayerView {
    /**
     * 返回当前玩家位置。
     */
    public PlayerLocation getMyLocation();

    /**
     * 返回指定位置的玩家名称。
     */
    public String getPlayerName(PlayerLocation location);

    /**
     * 返回牌墙中的剩余牌数。
     */
    public int getTileWallSize();

    /**
     * 返回此局开始时的底牌数量。
     */
    public int getInitBottomSize();

    /**
     * 返回已经从底部摸牌的数量。
     */
    public int getDrawedBottomSize();

    /**
     * 返回PlayerInfo视图。
     */
    public Map<PlayerLocation, PlayerInfoPlayerView> getPlayerInfoView();
}
