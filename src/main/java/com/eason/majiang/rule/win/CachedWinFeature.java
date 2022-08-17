package com.eason.majiang.rule.win;

import com.eason.majiang.object.PlayerInfo;
import com.eason.majiang.object.Tile;

import java.util.*;
import java.util.function.Function;

public abstract class CachedWinFeature {
    private final Map<Integer,Boolean> cache = Collections.synchronizedMap(new WeakHashMap<>());
    private boolean useAliveTiles = true;
    private final List<Function<PlayerInfo,?>> otherCacheKeys = new ArrayList<>();

    protected  void setUseAliveTiles(boolean useAliveTiles)
    {
        this.useAliveTiles = useAliveTiles;
    }
    protected void addCacheKey(Function<PlayerInfo,?> value)
    {
        otherCacheKeys.add(value);
    }

    public boolean match(PlayerInfo playerInfo, Set<Tile> aliveTiles)
    {
        Set<Tile> realAliveTiles = aliveTiles != null ? aliveTiles : playerInfo.getAliveTiles();

        int hash = hash(playerInfo, realAliveTiles);
        Boolean result = cache.get(hash);
        if (result == null) {
            result = matchWithoutCache(playerInfo, realAliveTiles);
            cache.put(hash, result);
        }
        return result;
    }
    private int hash(PlayerInfo playerInfo, Set<Tile> realAliveTiles) {
        final int prime = 31;
        int result = 1;
        if (useAliveTiles) {
            result = prime * result + ((realAliveTiles == null) ? 0 : realAliveTiles.hashCode());
        }
        for (Function<PlayerInfo, ?> function : otherCacheKeys) {
            Object value = function.apply(playerInfo);
            result = prime * result + ((value == null) ? 0 : value.hashCode());
        }
        return result;
    }

    public abstract boolean matchWithoutCache(PlayerInfo playerInfo, Set<Tile> realAliveTiles);


}
