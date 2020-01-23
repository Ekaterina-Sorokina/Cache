package com.wiley.implementation.caches;

import com.wiley.enums.CacheEvictionStrategyEnum;
import com.wiley.factories.CacheFactory;
import com.wiley.interfaces.caches.Cache;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class LFUCacheTest {
    private Cache lfuCache;
    private static Map<Integer, Object> changedCacheModel;
    private static Map<Integer, Object> changedCacheSizeModel;

    @BeforeClass
    public static void init() {
        TestUtils.initData();
    }


    @Before
    public void initLruCache() {
        lfuCache = CacheFactory.createCache(CacheEvictionStrategyEnum.LFU);
        fillCacheData();
    }

    @Test
    public void checkBaseCacheWork() {
        lfuCache.get(5);
        lfuCache.get(5);
        lfuCache.get(3);
        lfuCache.get(3);
        lfuCache.get(8);
        lfuCache.get(10);
        lfuCache.put(1, "1");
        lfuCache.get(1);
        lfuCache.get(1);
        lfuCache.put(2, "2");
        lfuCache.get(2);
        lfuCache.get(2);
        lfuCache.get(3);
        lfuCache.put(4, "4");
        lfuCache.get(4);
        lfuCache.get(4);

        assertTrue(TestUtils.areHashMapsEqual(TestUtils.getChangedCacheModel(), lfuCache.getCacheMap()));
    }

    @Test
    public void checkChangeSize() {
        lfuCache.setCacheSize(3);
        lfuCache.get(3);
        lfuCache.get(8);
        lfuCache.get(3);
        lfuCache.put(1, "1");
        lfuCache.put(2, "2");
        assertTrue(TestUtils.areHashMapsEqual(TestUtils.getChangedCacheSizeModel(), lfuCache.getCacheMap()));
    }

    private void fillCacheData() {
        lfuCache.put(8, "8");
        lfuCache.put(10, "10");
        lfuCache.put(3, "3");
        lfuCache.put(5, "5");
        lfuCache.put(6, "6");
    }
}
