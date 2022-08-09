package com.eason.majiang.object;

import java.util.Set;
import static com.eason.majiang.object.StandardTileUnitType.*;
public enum TileGroupType {

    CHI_GROUP(SHUNZI),
    /**
     * 碰
     */
    PENG_GROUP(KEZI),
    /**
     * 直杠
     */
    ZHIGANG_GROUP(GANGZI),
    /**
     * 补杠
     */
    BUGANG_GROUP(GANGZI),
    /**
     * 暗杠
     */
    ANGANG_GROUP(GANGZI),
    /**
     * 补花
     */
    BUHUA_GROUP(HUA_UNIT);

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
