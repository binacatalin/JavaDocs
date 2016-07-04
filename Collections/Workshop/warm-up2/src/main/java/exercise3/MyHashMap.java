package exercise3;

import java.util.*;

/**
 * Exercise 3. Implement a HashMap from scratch. In order to pass all the tests
 * you need to implement all the methods defined below. The key-value pair will
 * be encapsulated in the MyHashMap.MyEntry object defined below.
 * <p>
 * The buckets list in which each MyEntry object will be stored is stored in "buckets" object.
 */
public class MyHashMap {

    //FINAL

    private ArrayList<LinkedList<MyEntry>> buckets;

    private int capacity;

    public MyHashMap(int capacity) {
        this.capacity = capacity;

        // Initialize buckets list
        buckets = new ArrayList<LinkedList<MyEntry>>();
        for (Integer i = 0; i < capacity; i++) {
            buckets.add(new LinkedList<MyEntry>());
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public String get(String key) {
        // TODO Catalin
        int hashcode;

        if (key == null)
            hashcode = 0;
        else
            hashcode = Math.abs(key.hashCode()) % capacity;

        LinkedList<MyEntry> list = buckets.get(hashcode);
        if (list != null) {
            for (MyEntry entry : list) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        // TODO Catalin
        int hashcode;

        if (key == null)
            hashcode = 0;
        else
            hashcode = Math.abs(key.hashCode()) % capacity;

        MyEntry entry = new MyEntry(key, value);

        LinkedList<MyEntry> lst = buckets.get(hashcode);
        int index = 0;
        if (lst != null)
            index = lst.indexOf(entry);
        else
            return;

        if (index < 0) {
            buckets.get(hashcode).add(entry);
        } else {
            buckets.get(hashcode).set(index, entry);
        }
    }

    /**
     *
     * @return
     */
    public Set<String> keySet() {
        // TODO Catalin
        Set<String> ret = new LinkedHashSet<String>();

        for (LinkedList<MyEntry> list : buckets) {
            for (MyEntry entry : list) {
                ret.add(entry.getKey());
            }
        }

        return ret;
    }

    /*
    -------------------------------------------------------------------------------------------------------------
     */
    public Collection<String> values() {
        // TODO Ana
        LinkedList<String> list = new LinkedList<String>();
        for (LinkedList<MyEntry> i : buckets) {
            for (MyEntry entry : i) {
                list.add(entry.getValue());
            }
        }
        return list;
    }

    public String remove(String key) {
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
        //int ceva = Math.abs(key.hashCode());
       // LinkedList<MyEntry> list = buckets.get(ceva);
        for (LinkedList<MyEntry> entry : buckets) {
            for(MyEntry a: entry){
                if (a.getKey().equals(key)){
                    String s = new String(a.getValue());
                    entry.remove(a);
                    System.out.print(s);
                    return s;
                }
            }
        }
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

        @Override
        public int hashCode() {
            return key.hashCode()+value.hashCode();
        }



        @Override
        public boolean equals(Object obj) {
            if(obj==null || this.getClass()!=obj.getClass() || this.getKey()==null || this.getValue()==null) return false;
            return (this.key.equals(((MyEntry)obj).getKey()) && this.value.equals(((MyEntry)obj).getValue()));
        }
    }
}