package com.eason.majiang.rule.win;

import com.eason.majiang.game.GameContextPlayerView;
import com.eason.majiang.object.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinInfo extends PlayerTiles {


    private Tile winTile;
    private boolean ziMo;
    private GameContextPlayerView contextView;

    private List<TileType> tileTypes;

    private final Map<WinType, List<List<TileUnit>>> units = new HashMap<>();
    private final Map<FanType, Integer> fans = new HashMap<>();

    public static WinInfo fromPlayerTiles(PlayerTiles playerTiles, Tile winTile, boolean ziMo)
    {
        WinInfo winInfo = new WinInfo();
        winInfo.setAliveTiles(playerTiles.getAliveTiles());
        winInfo.setTileGroups(playerTiles.getTileGroups());
        winInfo.setWinTile(winTile);
        winInfo.setZiMo(ziMo);
        return winInfo;
    }
    private final Set<TileUnit> noYaoJiuKeUnits = new HashSet<>();

    public Tile getWinTile() {
        return winTile;
    }

    public void setWinTile(Tile winTile) {
        this.winTile = winTile;
    }

    public Boolean getZiMo() {
        return ziMo;
    }

    public void setZiMo(Boolean ziMo) {
        this.ziMo = ziMo;
    }

    public void setContextView(GameContextPlayerView contextView) {
        this.contextView = contextView;
    }

    public GameContextPlayerView getContextView() {
        return contextView;
    }

    public List<TileType> getTileTypes() {
        if (tileTypes == null) {
            Stream<Tile> tiles = getAliveTiles().stream();
            for (TileGroup group : getTileGroups()) {
                tiles = Stream.concat(tiles, group.getTiles().stream());
            }
            tileTypes = tiles.map(Tile::type).sorted().collect(Collectors.toList());
        }
        return tileTypes;
    }

    public void setTileTypes(List<TileType> tileTypes) {
        this.tileTypes = tileTypes;
    }

    public Map<WinType, List<List<TileUnit>>> getUnits() {
        return units;
    }

    public void setUnits(WinType winType, List<List<TileUnit>> units) {
        this.units.put(winType, units);
    }

    public Map<FanType, Integer> getFans() {
        return fans;
    }

    public void setFans(FanType fanType, Integer fans) {
        this.fans.put(fanType, fans);
    }

    public Set<TileUnit> getNoYaoJiuKeUnits() {
        return noYaoJiuKeUnits;
    }

    public void addNoYaoJiuKeUnits(Collection<TileUnit> units) {
        noYaoJiuKeUnits.addAll(units);
    }

    @Override
    public String toString() {
        return "WinInfo [\nwinTile=" + winTile + ",\nziMo=" + ziMo + ",\ncontextView=" + contextView + ",\naliveTiles="
                + aliveTiles + ",\ntileGroups=" + tileGroups + "\n]\n";
    }



}
