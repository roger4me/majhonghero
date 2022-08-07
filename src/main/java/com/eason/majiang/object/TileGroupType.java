package com.eason.majiang.object;

import java.util.Set;

public enum TileGroupType {



    private final TileUnitType unitType;

    private TileGroupType(TileUnitType unitType) {
        this.unitType = unitType;
    }

    public TileUnitType getUnitType() {
        return unitType;
    }

    public int size() {
        return unitType.size();
    }

    public boolean isLegalTiles(Set<Tile> tiles) {
        return unitType.isLegalTiles(tiles);
    }
}
