package com.eason.majiang.action.standard;

import com.eason.majiang.action.Action;
import com.eason.majiang.action.AutoActionType;
import com.eason.majiang.action.IllegalActionException;
import com.eason.majiang.game.GameContext;
import com.eason.majiang.object.MahjongTable;
import com.eason.majiang.object.PlayerInfo;
import com.eason.majiang.object.PlayerLocation;

import java.util.stream.Stream;

public class DealActionType implements AutoActionType {

    protected DealActionType() {
    }

    @Override
    public boolean isLegalAction(GameContext context, Action action) {
        return context.getDoneActions().stream().map(Action::getType).noneMatch(this::matchBy);
    }

    @Override
    public void doAction(GameContext context, Action action) throws IllegalActionException {
        MahjongTable table = context.getTable();
        PlayerLocation zhuang = context.getZhuangLocation();
        for (int i = 0; i < 4; i++) {
            int drawCount = i < 3 ? 4 : 1;
            Stream.of(PlayerLocation.Relation.values()).map(zhuang::getLocationOf)
                    .map(context::getPlayerInfoByLocation)
                    .map(PlayerInfo::getAliveTiles)
                    .forEach(aliveTiles -> aliveTiles
                            .addAll(table.draw(drawCount)));
        }
        context.getPlayerInfoByLocation(zhuang).getAliveTiles()
                .addAll(table.draw(1));
    }
}
