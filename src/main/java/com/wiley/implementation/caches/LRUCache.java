package com.wiley.implementation.caches;

import com.wiley.enums.CacheEvictionStrategyEnum;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends AbstractCacheImpl {
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private LinkedHashMap<Integer, Object> cacheMap;

    public LRUCache() {
        createCacheHashMap();
    }

    private void createCacheHashMap() {
        int capacity = (int) Math.ceil(DEFAULT_LOAD_FACTOR * getCacheSize()) + 1;
        cacheMap = new LinkedHashMap<Integer, Object>(capacity, DEFAULT_LOAD_FACTOR, true) {
            protected boolean removeEldestEntry(Map.Entry<Integer, Object> eldest) {
                return size() > getCacheSize();
            }
        };
    }


    @Override
    public CacheEvictionStrategyEnum getCacheType() {
        return CacheEvictionStrategyEnum.LRU;
    }

    @Override
    public Object get(Integer key) {
        return cacheMap.get(key);
    }

    @Override
    public void put(Integer key, Object value) {
        cacheMap.put(key, value);
    }

    @Override
    public void updateCacheMap() {
        int index = 0;
        Iterator<Map.Entry<Integer, Object>> entrySetIterator = cacheMap.entrySet().iterator();
        while (entrySetIterator.hasNext()) {
            index++;
            entrySetIterator.next();
            if (index < getCacheSize()) {
                entrySetIterator.remove();
            }
        }
    }

    @Override
    public Map<Integer, Object> getCacheMap() {
        return cacheMap;
    }
}
