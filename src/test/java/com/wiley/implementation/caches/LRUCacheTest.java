package com.wiley.implementation.caches;

import com.wiley.enums.CacheEvictionStrategyEnum;
import com.wiley.factories.CacheFactory;
import com.wiley.interfaces.caches.Cache;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class LRUCacheTest {
    private Cache lruCache;

    @BeforeClass
    public static void init() {
        TestUtils.initData();
    }

    @Before
    public void initLruCache() {
        lruCache = CacheFactory.createCache(CacheEvictionStrategyEnum.LRU);
        fillCacheData();
    }

    @Test
    public void checkBaseCacheWork() {
        lruCache.put(1, "1");
        lruCache.get(5);
        lruCache.put(2, "2");
        lruCache.get(3);
        lruCache.put(4, "4");

        assertTrue(TestUtils.areHashMapsEqual(TestUtils.getChangedCacheModel(), lruCache.getCacheMap()));
    }

    @Test
    public void checkChangeSize() {
        lruCache.setCacheSize(3);
        lruCache.get(3);
        lruCache.put(1, "1");
        lruCache.put(2, "2");
        assertTrue(TestUtils.areHashMapsEqual(TestUtils.getChangedCacheSizeModel(), lruCache.getCacheMap()));
    }

    private void fillCacheData() {
        lruCache.put(8, "8");
        lruCache.put(10, "10");
        lruCache.put(3, "3");
        lruCache.put(5, "5");
        lruCache.put(6, "6");
    }
}
