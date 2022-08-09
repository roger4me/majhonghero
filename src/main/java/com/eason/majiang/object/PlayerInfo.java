package com.eason.majiang.object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerInfo extends PlayerTiles implements Cloneable {
    private Player player = null;

    private Tile lastDrawedTile = null;

    private List<Tile> discardedTiles = new ArrayList<>();

    private boolean isTing = false;

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public void setLastDrawedTile(Tile lastDrawedTile)
    {
        this.lastDrawedTile = lastDrawedTile;
    }

    public Tile getLastDrawedTile()
    {
        return lastDrawedTile;
    }

    public List<Tile> getDiscardedTiles() {
        return discardedTiles;
    }

    public void setDiscardedTiles(List<Tile> discardedTiles) {
        this.discardedTiles = discardedTiles;
    }

    public boolean isTing() {
        return isTing;
    }

    public void setTing(boolean isTing) {
        this.isTing = isTing;
    }
    public void clear() {
        aliveTiles.clear();
        lastDrawedTile = null;
        discardedTiles.clear();
        tileGroups.clear();
        isTing = false;
    }

    @Override
    public PlayerInfo clone() {
        PlayerInfo c;
        try {
            c = (PlayerInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        c.aliveTiles = new HashSet<>(aliveTiles);
        c.discardedTiles = new ArrayList<>(discardedTiles);
        c.tileGroups = new ArrayList<>(tileGroups);
        return c;
    }
    private PlayerView otherPlayerView;
    public PlayerInfoPlayerView getOtherPlayerView() {
        if (otherPlayerView == null) { // 不需要加锁，因为多创建了也没事
            otherPlayerView = new PlayerView();
        }
        return otherPlayerView;
    }
    private class PlayerView implements PlayerInfoPlayerView {

        @Override
        public String getPlayerName() {
            Player player = getPlayer();
            return player != null ? getPlayer().getName() : null;
        }

        @Override
        public int getAliveTileSize() {
            return getAliveTiles().size();
        }

        @Override
        public List<Tile> getDiscardedTiles() {
            return Collections.unmodifiableList(discardedTiles);
        }

        @Override
        public List<TileGroupPlayerView> getTileGroups() {
            return tileGroups.stream().map(TileGroup::getOtherPlayerView).collect(Collectors.toList());
        }

        @Override
        public boolean isTing() {
            return isTing;
        }

    }

}
