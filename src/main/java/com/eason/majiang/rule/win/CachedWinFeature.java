package com.eason.majiang.rule.win;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public abstract class CachedWinFeature {
    private final Map<Integer,Boolean> cache = Collections.synchronizedMap(new WeakHashMap<>());

}
