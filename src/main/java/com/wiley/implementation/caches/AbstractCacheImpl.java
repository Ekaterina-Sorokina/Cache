package com.wiley.implementation.caches;

import com.wiley.interfaces.caches.Cache;

public abstract class AbstractCacheImpl implements Cache {
    private int cacheSize = 5;

    public int getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize) {
        if (cacheSize < MINIMUM_CACHE_SIZE) {
            System.out.println("The cache size should be not less than " + MINIMUM_CACHE_SIZE);
            return;
        }
        int oldCacheSize = this.cacheSize;
        int newCacheSize = cacheSize;
        this.cacheSize = cacheSize;
        if (oldCacheSize > newCacheSize) {
            updateCacheMap();
        }
    }

    protected void updateCacheMap() {
    }
}
