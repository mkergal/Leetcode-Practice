package com.mitaTestApp.DataStructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashMapImpl {

    class MyHashMap {
        private int size;
        private List<Bucket> hashtable;

        public MyHashMap() {
            this.size = 769;
            this.hashtable = new ArrayList<Bucket>();
            for (int i = 0; i < this.size; ++i) {
                this.hashtable.add(new Bucket());
            }
        }

        public void put(int key, int value) {
            int hash_key = key % size;
            this.hashtable.get(hash_key).update(key, value);
        }

        public int get(int key) {
            int hash_key = key % size;
            return this.hashtable.get(hash_key).get(key);
        }

        public void remove(int key) {
            int hash_key = key % size;
            this.hashtable.get(hash_key).remove(key);
        }
    }

    class Entry<U,V>{
        public U key;
        public V val;
        Entry(U key, V val){
            this.key = key;
            this.val =val;
        }
    }

    class Bucket{
        private List<Entry<Integer,Integer>> bucket;

        Bucket(){
            bucket = new LinkedList<Entry<Integer,Integer>>();
        }

        public Integer get(Integer key){
            for(Entry<Integer,Integer> pair : this.bucket){
                if(pair.key.equals(key))
                    return pair.val;
            }
            return -1;
        }

        public void update (Integer key, Integer val){
            boolean found = false;
            for(Entry<Integer,Integer> pair : this.bucket){
                if(pair.key.equals(key)){
                    pair.val = val;
                    found  = true;
                }

            }
            if(!found){
                this.bucket.add(new Entry<Integer,Integer>(key,val));
            }
        }

        public void remove(Integer key) {
            for (Entry<Integer, Integer> pair : this.bucket) {
                if (pair.key.equals(key)) {
                    this.bucket.remove(pair);
                    break;
                }
            }
        }

    }

}
