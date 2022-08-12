package com.eason.majiang.action;

import com.eason.majiang.object.Player;
import com.eason.majiang.object.PlayerLocation;
import com.eason.majiang.object.Tile;

import java.util.Set;

public class PlayerAction extends Action {
   private PlayerLocation location;
   private Set<Tile> tiles;

   public PlayerAction(PlayerLocation location, ActionType type)
   {
       this(location, type, Set.of());
   }

   public PlayerAction(PlayerLocation location, ActionType type, Tile tile)
   {
       this(location, type, Set.of(tile));
   }

    public PlayerAction(PlayerLocation location, ActionType type, Set<Tile> tiles) {
        super(type);
        this.location = location;
        this.tiles = tiles;
    }

    public Set<Tile> getTiles()
    {
        return tiles;
    }
    public Tile getTile() {
        Set<Tile> tiles = getTiles();
        if (tiles.isEmpty()) {
            return null;
        }
        if (tiles.size() > 1) {
            throw new IllegalStateException("Tile count is more than 1: " + tiles.size());
        }
        return tiles.iterator().next();
    }

    public PlayerLocation getLocation() {
        return location;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((tiles == null) ? 0 : tiles.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof PlayerAction)) {
            return false;
        }
        PlayerAction other = (PlayerAction) obj;
        if (location != other.location) {
            return false;
        }
        if (tiles == null) {
            if (other.tiles != null) {
                return false;
            }
        } else if (!tiles.equals(other.tiles)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + getLocation() + ", " + getType() + ", " + tiles + "]";
    }


}
