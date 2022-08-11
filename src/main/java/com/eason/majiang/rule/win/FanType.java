package com.eason.majiang.rule.win;

import java.util.*;

public interface FanType extends FanTypeMatcher {

    public String name();

    public int score();

    public Set<FanType> covered();

    public static Map<FanType,Integer> getFans(WinInfo winInfo, Collection<FanType> fanTypes,Collection<WinType> winTypes)
    {
        winTypes.forEach(winType -> winType.parseWinTileUnits(winInfo));
        // 如果没parse出来，说明不和牌，直接返回空map
        if (winInfo.getUnits() == null || winInfo.getUnits().isEmpty()) {
            return Collections.emptyMap();
        }

        // 算番
        Map<FanType, Integer> fans = new HashMap<>();
        Set<FanType> coveredFanTypes = new HashSet<>();
        fanTypes.forEach(crtFanType -> {
            if (coveredFanTypes.contains(crtFanType)) {
                return;
            }

            Integer matchCount;

            matchCount = winInfo.getFans().get(crtFanType);
            if (matchCount == null) {
                matchCount = crtFanType.matchCount(winInfo);
                if (matchCount > 0) {
                    winInfo.getFans().put(crtFanType, matchCount);
                }
            }
            if (matchCount > 0) {
                fans.put(crtFanType, crtFanType.score() * matchCount);
                Set<? extends FanType> covered = crtFanType.covered();
                if (covered != null && !covered.isEmpty()) {
                    coveredFanTypes.addAll(covered);
                }
            }
        });

        return fans;
    }

}
