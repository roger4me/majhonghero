package com.eason.majiang.object;

import java.util.Set;

public interface TileGroupPlayerView {
    public TileGroupType getType();

    public Set<Tile> getTiles();

    public PlayerLocation.Relation getFromRelation();

    public Tile getGotTile() ;
}
