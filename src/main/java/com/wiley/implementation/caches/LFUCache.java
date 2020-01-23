package com.wiley.implementation.caches;

import com.wiley.enums.CacheEvictionStrategyEnum;

import java.lang.reflect.Array;
import java.util.*;

public class LFUCache extends AbstractCacheImpl {

    private int minCount = 0;
    private int originCountValue = 1;
    private HashMap<Integer, Object> valuesMap;
    private HashMap<Integer, Integer> countsMap;
    private HashMap<Integer, LinkedHashSet<Integer>> listOfKeyMap;

    public LFUCache() {
        valuesMap = new HashMap<>();
        countsMap = new HashMap<>();
        listOfKeyMap = new HashMap<>();
        listOfKeyMap.put(originCountValue, new LinkedHashSet<>());
    }

    @Override
    public CacheEvictionStrategyEnum getCacheType() {
        return CacheEvictionStrategyEnum.LFU;
    }

    @Override
    public Object get(Integer key) {
        Object value = valuesMap.get(key);
        if (value == null) {
            return null;
        }
        int count = countsMap.get(key);
        int nextCount = count + 1;
        countsMap.put(key, nextCount);
        LinkedHashSet<Integer> cachedObjectList = listOfKeyMap.get(count);
        cachedObjectList.remove(key);

        if (count == minCount && cachedObjectList.size() == 0)
            minCount++;
        if (!listOfKeyMap.containsKey(nextCount)) {
            listOfKeyMap.put(nextCount, new LinkedHashSet<>());
        }

        listOfKeyMap.get(nextCount).add(key);
        return valuesMap.get(key);
    }

    @Override
    public void put(Integer key, Object value) {
        if (valuesMap.containsKey(key)) {
            valuesMap.put(key, value);
            get(key);
            return;
        }
        //To allow add new element into cache
        updateCacheMap(getCacheSize() - 1);
        valuesMap.put(key, value);
        countsMap.put(key, originCountValue);
        minCount = originCountValue;
        listOfKeyMap.get(originCountValue).add(key);
    }

    @Override
    public void updateCacheMap() {
        updateCacheMap(getCacheSize());
    }


    public void updateCacheMap(int maxValue) {
        List<Integer> deleteObjectKeys = new ArrayList<>();
        int currentSize = valuesMap.size();
        while (currentSize > maxValue) {
            LinkedHashSet<Integer> cachedObjectList = listOfKeyMap.get(minCount);
            Iterator<Integer> cachedObjectIterator = cachedObjectList.iterator();
            while (cachedObjectIterator.hasNext() && currentSize > maxValue) {
                deleteObjectKeys.add(cachedObjectIterator.next());
                cachedObjectIterator.remove();
                currentSize--;
            }
            if (cachedObjectList.isEmpty()) {
                minCount++;
            }
        }
        deleteObjectKeys.forEach(key -> {
            valuesMap.remove(key);
            countsMap.remove(key);
        });
    }

    @Override
    public Map<Integer, Object> getCacheMap() {
        return valuesMap;
    }
}
