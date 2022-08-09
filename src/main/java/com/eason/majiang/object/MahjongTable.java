package com.eason.majiang.object;

import java.util.*;
import java.util.stream.Collectors;

public class MahjongTable {
    private List<Tile> tileWall;

    private int initBottomSize;

    private int drawedBottomSize;

    private Map<PlayerLocation, PlayerInfo> playerInfos;

    public void init()
    {
        tileWall = new ArrayList<>();
         playerInfos = new EnumMap<PlayerLocation, PlayerInfo>(PlayerLocation.class);
         for(PlayerLocation location: PlayerLocation.values())
         {
             playerInfos.put(location,new PlayerInfo());
         }
    }

    public void readyForGame(Collection<Tile> allTiles)
    {
        playerInfos.values().forEach(PlayerInfo::clear);
        tileWall.clear();
        tileWall.addAll(allTiles);
        Collections.shuffle(tileWall);
        initBottomSize = 0;
        drawedBottomSize = 0;
    }

    public int getTileWallSize()
    {
        return tileWall.size();
    }

    public int getInitBottomSize()
    {
        return initBottomSize;
    }

    public void setInitBottomSize(int initBottomSize)
    {
        this.initBottomSize = initBottomSize;
    }

    public int getDrawedBottomSize() {
        return drawedBottomSize;
    }

    public void setDrawedBottomSize(int drawedBottomSize) {
        this.drawedBottomSize = drawedBottomSize;
    }

    public List<Tile> drawBottom(int count)
    {
        if(count<=0 || count>tileWall.size())
        {
            return Collections.emptyList();
        }
        List<Tile> toBeDrawed = tileWall.subList(tileWall.size() - count, tileWall.size());
        List<Tile> drawed = new ArrayList<>(toBeDrawed);
        toBeDrawed.clear();
        drawedBottomSize += drawed.size();
        return drawed;
    }
    public Map<PlayerLocation, PlayerInfo> getPlayerInfos() {
        return playerInfos;
    }

    protected void setPlayerInfos(Map<PlayerLocation, PlayerInfo> playerInfos) {
        this.playerInfos = playerInfos;
    }

    public Player getPlayerByLocation(PlayerLocation location) {
        PlayerInfo info = playerInfos.get(location);
        return info == null ? null : info.getPlayer();
    }

    public void setPlayer(PlayerLocation location, Player player) {
        PlayerInfo playerInfo = playerInfos.get(location);
        if (playerInfo == null) {
            playerInfo = new PlayerInfo();
            playerInfos.put(location, playerInfo);
        }
        playerInfo.setPlayer(player);
    }

    private final Map<PlayerLocation, PlayerView> playerViews = new HashMap<>();

    public MahjongTablePlayerView getPlayerView(PlayerLocation location) {
        PlayerView view = playerViews.get(location);
        if (view == null) {
            view = new PlayerView(location);
            playerViews.put(location, view);
        }
        return view;
    }

    private class PlayerView implements MahjongTablePlayerView {

        private final PlayerLocation myLocation;

        private PlayerView(PlayerLocation myLocation) {
            this.myLocation = myLocation;
        }

        @Override
        public PlayerLocation getMyLocation() {
            return myLocation;
        }

        @Override
        public String getPlayerName(PlayerLocation location) {
            Player player = getPlayerByLocation(location);
            return player != null ? player.getName() : null;
        }

        @Override
        public int getTileWallSize() {
            return MahjongTable.this.getTileWallSize();
        }

        @Override
        public int getInitBottomSize() {
            return MahjongTable.this.getInitBottomSize();
        }

        @Override
        public int getDrawedBottomSize() {
            return MahjongTable.this.getDrawedBottomSize();
        }

        @Override
        public Map<PlayerLocation, PlayerInfoPlayerView> getPlayerInfoView() {
            return playerInfos.entrySet().stream()
                    .collect(Collectors.toMap(entry -> entry.getKey()
                            , entry -> entry.getValue().getOtherPlayerView()));
        }

    }

}
