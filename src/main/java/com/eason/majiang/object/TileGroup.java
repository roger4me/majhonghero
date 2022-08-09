package com.eason.majiang.object;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TileGroup implements Serializable {

    private static final long  serialVersionUID = 1L;

    private TileGroupType type;
    private Set<Tile> tiles;
    private PlayerLocation.Relation fromRelation;
    private  Tile gotTile;
    public TileGroup(TileGroupType type, Tile gotTile, PlayerLocation.Relation fromRelation,
                     Set<Tile> tiles) {
        if (!type.isLegalTiles(tiles)) {
            throw new IllegalArgumentException("Illegal group tiles：[" + type
                    + "]" + Arrays.asList(tiles));
        }

        this.type = type;
        this.gotTile = gotTile;
        this.fromRelation = fromRelation;
        this.tiles = new HashSet<>(tiles);
    }

    public TileGroupType getType() {
        return type;
    }

    public TileGroup(TileGroupType type, Set<Tile> tiles)
    {
        this(type,null,null,tiles);
    }

    public Set<Tile> getTiles()
    {
        return tiles;
    }

    public PlayerLocation.Relation getFromRelation()
    {
        return fromRelation;
    }

    public Tile getGotTile()
    {
        return gotTile;
    }

    private PlayerView view;

    public TileGroupPlayerView getOtherPlayerView()
    {
        if(view == null)
        {
            view = new PlayerView();
        }
        return view;
    }

    private class PlayerView implements TileGroupPlayerView
    {
        @Override
        public TileGroupType getType() {
            return TileGroup.this.getType();
        }

        @Override
        public Set<Tile> getTiles() {
            if (getType() == TileGroupType.ANGANG_GROUP) {
                return null;
            }
            return TileGroup.this.getTiles();
        }

        @Override
        public PlayerLocation.Relation getFromRelation() {
            return TileGroup.this.getFromRelation();
        }

        @Override
        public Tile getGotTile() {
            if (getType() == TileGroupType.ANGANG_GROUP) {
                return null;
            }
            return TileGroup.this.getGotTile();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((gotTile == null) ? 0 : gotTile.hashCode());
        result = prime * result
                + ((fromRelation == null) ? 0 : fromRelation.hashCode());
        result = prime * result + ((tiles == null) ? 0 : tiles.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TileGroup)) {
            return false;
        }
        TileGroup other = (TileGroup) obj;
        if (gotTile == null) {
            if (other.gotTile != null) {
                return false;
            }
        } else if (!gotTile.equals(other.gotTile)) {
            return false;
        }
        if (fromRelation == null) {
            if (other.fromRelation != null) {
                return false;
            }
            if (tiles == null) {
                if (other.tiles != null) {
                    return false;
                }
            } else if (!tiles.equals(other.tiles)) {
                return false;
            }
        } else {
            if (!fromRelation.equals(other.fromRelation)) {
                return false;
            }
            if (tiles == null) {
                if (other.tiles != null) {
                    return false;
                }
            } else if (!tiles.equals(other.tiles)) {
                return false;
            }
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }
}
