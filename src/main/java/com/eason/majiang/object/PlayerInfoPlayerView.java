package com.eason.majiang.object;

import java.util.List;

public interface PlayerInfoPlayerView {

    public String getPlayerName();

    public int getAliveTileSize();


    public List<Tile> getDiscardedTiles();


    public List<TileGroupPlayerView> getTileGroups();

    public boolean isTing();
}
