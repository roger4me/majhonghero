package com.eason.majiang.object;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerTiles {

    protected Set<Tile> aliveTiles = new HashSet<>();

    protected List<TileGroup> tileGroups = new ArrayList<>();

    public Set<Tile> getAliveTiles()
    {
        return aliveTiles;
    }

    public void setAliveTiles(Set<Tile> aliveTiles)
    {
        this.aliveTiles = aliveTiles;
    }

    public List<TileGroup> getTileGroups()
    {
        return tileGroups;
    }

    public void setTileGroups(List<TileGroup> tileGroups)
    {
        this.tileGroups = tileGroups;
    }
}
