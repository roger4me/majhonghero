package com.eason.majiang.rule;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.ActionTypeAndLocation;
import com.eason.majiang.action.PlayerAction;
import com.eason.majiang.action.PlayerActionType;
import com.eason.majiang.game.GameContext;
import com.eason.majiang.object.MahjongTable;
import com.eason.majiang.object.PlayerLocation;
import com.eason.majiang.object.Tile;
import com.eason.majiang.rule.win.FanType;
import com.eason.majiang.rule.win.WinInfo;
import com.eason.majiang.rule.win.WinType;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GameStrategy {
    public  boolean checkReady(MahjongTable table);

    public List<Tile> getAllTiles();

    public GameStage getStageByName(String stageName);

    public GameStage getFirstStage();

    public void readyContext(GameContext context);

    public Comparator<ActionTypeAndLocation> getActionPriorityComparator();

    public PlayerAction getPlayerDefaultAction(GameContext context, PlayerLocation location,
                                               Set<PlayerActionType> choises);

    public Action getDefaultAction(GameContext context, Map<PlayerLocation, Set<PlayerActionType>> choises);

    public List<WinType> getAllWinTypes();

    public default boolean canWin(WinInfo winInfo)
    {
        return getAllWinTypes().stream().anyMatch(winType -> winType.match(winInfo));
    }

    public List<FanType> getAllFanTypes();

    public default  Map<FanType,Integer> getFans(WinInfo winInfo)
    {
        return FanType.getFans(winInfo, getAllFanTypes(), getAllWinTypes());
    }

    public boolean tryEndGame(GameContext context);


}
