package com.wiley.factories;

import com.wiley.enums.CacheEvictionStrategyEnum;
import com.wiley.implementation.caches.LFUCache;
import com.wiley.implementation.caches.LRUCache;
import com.wiley.interfaces.caches.Cache;

public class CacheFactory {
    public static Cache createCache(CacheEvictionStrategyEnum cacheType) {
        switch (cacheType) {
            case LFU:
                return new LFUCache();
            case LRU:
            default:
                return new LRUCache();
        }
    }
}
