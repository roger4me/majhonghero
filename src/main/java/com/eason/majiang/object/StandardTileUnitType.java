package com.eason.majiang.object;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import static com.eason.majiang.object.TileRank.*;

public enum StandardTileUnitType implements TileUnitType{

    JIANG(2)
            {
                @Override
                protected boolean isLegalTilesWithCorrectSize(Collection<Tile> tiles) {
                   return tiles.stream().map(Tile::type).distinct().count() == 1;
                }

                @Override
                protected boolean isLegalTileTypesWithCorrectSize(Collection<TileType> types) {
                    return types.stream().distinct().count() == 1;
                }

                @Override
                protected List<List<TileType>> getLackedTypesForLessTiles(Collection<Tile> tiles) {
                    TileType type = tiles instanceof List ? ((List<Tile>) tiles).get(0).type() : tiles.iterator().next().type();
                    return singletonList(singletonList(type));
                }
            },
    SHUNZI(3)
            {
                @Override
                protected boolean isLegalTilesWithCorrectSize(Collection<Tile> tiles) {
                    if (tiles.iterator().next().type().suit().getRankClass() != TileRank.NumberRank.class)
                    {
                        return false;
                    }
                    if(tiles.stream().map(tile->tile.type().suit()).distinct().count()>1)
                    {
                        return false;
                    }

                    int[] numbers = tiles.stream().mapToInt(tile->((NumberRank) tile.type().rank()).number()).sorted()
                                     .toArray();
                    var crtNumber = 0;
                    for (int number : numbers) {
                        if (crtNumber == 0 || number == crtNumber + 1)
                            crtNumber = number;
                        else
                            return false;
                    }

                    return true;
                }

                @Override
                protected boolean isLegalTileTypesWithCorrectSize(Collection<TileType> types) {
                   if(types.iterator().next().suit().getRankClass() != NumberRank.class)
                   {
                       return false;
                   }

                   if(types.stream().map(tile->tile.suit()).distinct().count()>1)
                   {
                       return false;
                   }// rank不连续的，非法
                    int[] numbers = types.stream().mapToInt(type -> ((NumberRank) type.rank()).number()).sorted().toArray();
                    int crtNumber = 0;
                    for (int number : numbers) {
                        if (crtNumber == 0 || number == crtNumber + 1)
                            crtNumber = number;
                        else
                            return false;
                    }

                    return true;
                }

                @Override
                protected List<List<TileType>> getLackedTypesForLessTiles(Collection<Tile> tiles) {
                    return null;
                }
            },


    private static final Logger logger = Logger.getLogger(StandardTileUnitType.class.getSimpleName());

    private final int size;

    private StandardTileUnitType(int tileCount)
    {
        this.size = tileCount;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isLegalTiles(Collection<Tile> tiles) {
        if (tiles.size() != size())
            return false;
        return isLegalTilesWithCorrectSize(tiles);
    }

    @Override
    public boolean isLegalTileTypes(Collection<TileType> types) {
        if (types.size() != size())
            return false;
        return isLegalTileTypesWithCorrectSize(types);
    }

    public List<List<TileType>> getLackedTypesForTiles(Collection<Tile> tiles) {
        if (tiles.size() > size())
            return emptyList();
        if (tiles.size() == size())
            return isLegalTilesWithCorrectSize(tiles) ? singletonList(emptyList()) : emptyList();
        if (tiles.isEmpty())
            throw new IllegalArgumentException("tiles cannot be empty.");
        return getLackedTypesForLessTiles(tiles);
    }


    protected abstract boolean isLegalTilesWithCorrectSize(Collection<Tile> tiles);
    protected abstract boolean isLegalTileTypesWithCorrectSize(Collection<TileType> types);
    protected abstract List<List<TileType>> getLackedTypesForLessTiles(Collection<Tile> tiles);
}
