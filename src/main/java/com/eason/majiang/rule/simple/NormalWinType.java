package com.eason.majiang.rule.simple;

import com.eason.majiang.object.PlayerInfo;
import com.eason.majiang.object.Tile;
import com.eason.majiang.object.TileUnit;
import com.eason.majiang.rule.win.WinInfo;
import com.eason.majiang.rule.win.WinType;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class NormalWinType implements WinType {

    private static final NormalWinType INSTANCE = new NormalWinType();

    public static NormalWinType get() {
        return INSTANCE;
    }

    private NormalWinType() {
    }


    @Override
    public List<List<TileUnit>> parseWinTileUnits(WinInfo winInfo) {
        return null;
    }

    @Override
    public List<Tile> getDiscardCandidates(Set<Tile> aliveTiles, Collection<Tile> candidates) {
        return null;
    }

    @Override
    public Stream<ChangingForWin> changingsForWin(PlayerInfo playerInfo, int changeCount, Collection<Tile> candidates) {
        return null;
    }
}
