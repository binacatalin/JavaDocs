package exercise3;

import java.util.*;

/**
 * Exercise 3. Implement a HashMap from scratch. In order to pass all the tests
 * you need to implement all the methods defined below. The key-value pair will
 * be encapsulated in the MyHashMap.MyEntry object defined below.
 *
 * The buckets list in which each MyEntry object will be stored is stored in "buckets" object.
 */
public class MyHashMap {

    private ArrayList<LinkedList<MyEntry>> buckets;

    private int capacity;

    public MyHashMap(int capacity) {
        this.capacity = capacity;

        // Initialize buckets list
        buckets = new ArrayList<LinkedList<MyEntry>>();
        for(Integer i = 0; i < capacity; i++) {
            buckets.add(new LinkedList<MyEntry>());
        }
    }

    public String get(String key) {
        // TODO Catalin
        return null;
    }

    public void put(String key, String value) {
        // TODO Catalin
    }

    public Set<String> keySet() {
        // TODO Catalin
        return null;
    }

    public Collection<String> values() {
        // TODO Ana
        return null;
    }

    public String remove(String key) {
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
//        Ana
        return null;
    }

    public boolean containsKey(String key) {
        // TODO Jeni
        for (LinkedList<MyEntry> i : buckets) {
            for (MyEntry entry : i) {
                if (entry.getKey().equals(key))
                    return true;
            }
        }
        return false;
    }

    public boolean containsValue(String value) {
        // TODO Jeni
        for (LinkedList<MyEntry> i : buckets) {
            for (MyEntry entry : i) {
                if (entry.getValue().equals(value))
                    return true;
            }
        }
        return false;
    }

    public int size() {
        // TODO Return the number of the Entry objects stored in all the buckets
//        Nicoleta
        int size = 0;
        for (LinkedList bucket : buckets) {
            size += bucket.size();
        }
        System.out.println(size);
        return size;
    }

    public void clear() {
        // TODO Remove all the Entry objects from the bucket list
//        Nicoleta
        for (LinkedList bucket : buckets) {
            bucket.clear();
        }
    }

    public Set<MyEntry> entrySet() {
        // TODO Return a Set containing all the Entry objects
//        Nicoleta
        Set<MyEntry> set = new LinkedHashSet<MyEntry>();
        for (LinkedList bucket : buckets) {
            set.addAll(bucket);
        }
        return set;
    }

    public boolean isEmpty() {
        int cnt = buckets.size();
        for (LinkedList<MyEntry> i : buckets) {
            if (i.isEmpty()) {
                cnt--;
            }
        }
        if (cnt == 0)
            return true;
        else
            return false;
    }

    public static class MyEntry {
        private String key;
        private String value;

        public MyEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
