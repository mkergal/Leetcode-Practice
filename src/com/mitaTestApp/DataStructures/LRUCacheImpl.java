package com.mitaTestApp.DataStructures;

import java.util.LinkedHashMap;
import java.util.Set;

public class LRUCacheImpl {

    class LRUCache {
        int capacity;
        LinkedHashMap<Integer, Node> lhm;

        class Node {
            int key;
            int value;

            Node(int key, int value){
                this.key = key;
                this.value = value;
            }

        }

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.lhm = new LinkedHashMap<>();
        }

        public int get(int key) {

            int val = -1;
            if(lhm.containsKey(key)){
                val = lhm.get(key).value;
                // removing this recently accessed key and
                lhm.remove(key);
                // adding it back again to LHM as this node is latest accessed
                lhm.put(key, new Node(key, val));
            }

            return val;

        }

        public void put(int key, int value) {
            if(lhm.containsKey(key)){
                // removing this recently accessed key and
                lhm.remove(key);
                // adding it back again to LHM as this node is latest accessed (also maybe updating value)
                lhm.put(key, new Node(key, value));
            } else {
                int size = lhm.size();
                if(size<capacity){
                    lhm.put(key, new Node(key, value));
                } else {
                    Set<Integer> keys = lhm.keySet();
                    int oldestKey = 0;
                    for (int keyInLHM : keys) {
                        oldestKey = keyInLHM;
                        break;  // first node in LHM will be oldest, so breaking it as we got key of first one
                    }
                    // removing the oldest key and
                    lhm.remove(oldestKey);
                    // adding the one for which this method is called
                    lhm.put(key, new Node(key, value));
                }
            }

        }
    }

}
