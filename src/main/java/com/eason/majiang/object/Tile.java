package com.eason.majiang.object;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tile implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final List<Tile> all;
    static
    {
        List<Tile> allTiles = TileType.all().stream()
                .flatMap(type -> IntStream
                        .range(0, type.suit().getTileCountByType())
                        .mapToObj(id -> new Tile(type, id)))
                .collect(Collectors.toList());
        all = Collections.unmodifiableList(allTiles);
    }
    private final TileType type;
    private final int id;
    private Tile(TileType type, int id)
    {
        this.type = type;
        this.id = id;
    }

    public static List<Tile> all()
    {
        return all;
    }
    public static Set<Tile> allOfType(TileType type)
    {
        return all.stream().filter(tile->tile.type == type).collect(Collectors.toSet());
    }

    public static Tile of(TileType type, int id)
    {
        return all.stream().filter(tile->tile.type == type && tile.id == id).findAny().orElse(null);
    }


    public TileType type() {
        return type;
    }

    public int id()
    {
        return id;
    }
}
