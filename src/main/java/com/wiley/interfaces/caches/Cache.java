package com.wiley.interfaces.caches;

import com.wiley.enums.CacheEvictionStrategyEnum;

import java.util.Map;

public interface Cache {
    int MINIMUM_CACHE_SIZE = 3;

    int getCacheSize();

    void setCacheSize(int size);

    CacheEvictionStrategyEnum getCacheType();

    Object get(Integer key);

    void put(Integer key, Object value);

    Map<Integer, Object> getCacheMap();
}
