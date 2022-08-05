package com.eason.majiang.object;

import java.util.Collection;

public interface TileUnitType {
    int size();
    boolean isLegalTiles(Collection<Tile> tiles);
    boolean isLegalTileTypes(Collection<TileType> types);
}