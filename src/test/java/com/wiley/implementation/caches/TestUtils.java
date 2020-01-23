package com.wiley.implementation.caches;

import java.util.HashMap;
import java.util.Map;

public class TestUtils {
    private static Map<Integer, Object> changedCacheModel;
    private static Map<Integer, Object> changedCacheSizeModel;

    public static Map<Integer, Object> getChangedCacheModel() {
        return changedCacheModel;
    }

    public static Map<Integer, Object> getChangedCacheSizeModel() {
        return changedCacheSizeModel;
    }

    public static void initData() {
        changedCacheModel = new HashMap<>();
        changedCacheModel.put(1, "1");
        changedCacheModel.put(2, "2");
        changedCacheModel.put(3, "3");
        changedCacheModel.put(4, "4");
        changedCacheModel.put(5, "5");

        changedCacheSizeModel = new HashMap<>();
        changedCacheSizeModel.put(1, "1");
        changedCacheSizeModel.put(2, "2");
        changedCacheSizeModel.put(3, "3");
    }

    public static boolean areHashMapsEqual(Map<Integer, Object> first, Map<Integer, Object> second) {
        if (first.size() != second.size()) {
            return false;
        }

        return first.entrySet().stream()
                .allMatch(e -> e.getValue().equals(second.get(e.getKey())));
    }
}
