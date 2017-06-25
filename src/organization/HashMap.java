package organization;

/*
*Created by: csci 1933 TAs
*Maintained by: Kyle Loomis
*Details: csci 1933 - lab 004
*/

/*
*References:
*  - https://www.quora.com/How-is-Hashmap-in-Java-implemented-internally-What-are-the-pros-and-cons-to-use-it-What-are-the-complexities-it-provides-for-insert-delete-and-lookup/answer/Ankit-Mittal-59?srid=uaOc7
*  - Used Quora article to understand how HashMaps work in a general sense
*  - https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#hashCode()
*  - Used reference on public int hashCode() method to understand exactly how that method works
*/

public class HashMap<K,V> implements Map<K,V> {

    // the actual array everything is stored in
    private Entry<K,V>[] hashMap;
    // size of array
    private int bucketLength = 128;
    // total number of key-value mappings
    private int size = 0;

    // constructors
    // default array set to length 128
    public HashMap() { hashMap = new Entry[bucketLength]; }

    // if you want to set your own array length, you can
    public HashMap(int bucketLength) {
        this.bucketLength = bucketLength;
        hashMap = new Entry[bucketLength];
    }

    public Entry indexHash(int i) {
        return hashMap[i];
    }

    public int getBucketLength() {
        return bucketLength;
    }

    // getters for private variables
    public Entry<K, V>[] getHashMap() { return hashMap; }
    public int size() {
        return size;
    }

    // removes all mappings by resetting hashMap
    public void clear() {
        hashMap = new Entry[bucketLength];
        size = 0;
    }

    // returns true if the Map contains no
    // key-value mappings, else false
    public boolean isEmpty() {
        if (size != 0)
            return false;
        return true;
    }

    // determines which bucket (array index) the
    // Entry should be placed into. Calculated by
    // taking remainder of key (with .hashCode method)
    // and modulating it by the number of buckets
    private int hash(K key) {
        // returns -1 if key is null to avoid
        // null pointer error with .hashCode method
        if (key == null)
            return 0;
        int hashed = key.hashCode() % bucketLength;
        // accounts for any possible negative hash values
        if (hashed < 0)
            hashed = 0 - hashed;
        return hashed;
    }

    // returns true if key is mapped
    // to value, else
    public boolean containsKey(K key) {
        int index = hash(key);
        Entry<K,V> ptr = (hashMap[index]);

        // iterates through keys stored at the hashMap
        // index and returns true
        // also added a loopTracker to track the efficiency of the method
        int loopTracker = 0;
        while (ptr != null) {
            loopTracker++;
            if (ptr.getKey() != null && ptr.getKey().equals(key)) {
                return true;
            }
            else if (ptr.getKey() == key) {
                return true;
            }
            ptr = ptr.getNext();
        }
        return false;
    }

    // returns true if the Map contains a mapping
    // for the specified value, and false otherwise.
    public boolean containsValue(V value) {
        // loops through each position in array
        // and looks to each linked list to find value
        int loopTracker = 0;
        for (int i = 0; i < bucketLength; i++) {
            loopTracker++;
            Entry<K, V> pointer = hashMap[i];
            // only enters into inner loop if
            // pointer was not assigned to null
            while (pointer != null) {
                loopTracker++;
                if (pointer.getValue() != null && pointer.getValue().equals(value)) {
                    return true;
                }
                else if (pointer.getValue() == value) {
                    return true;
                }
                pointer = pointer.getNext();
            }
        }
        return false;
    }

    // returns the value to which the specified
    // key is mapped, else null
    public V get(K key) {
        if (size == 0) return null;

        int index = hash(key);
        // added loopTracker to track time complexity
        int loopTracker = 0;
        // only executed if index = 0
        if (key == null && hashMap[index] != null) {
            Entry<K, V> pointer = hashMap[index];
            while (pointer.getNext() != null) {
                loopTracker++;
                pointer = pointer.getNext();
            }
            // checks for null key input
            if (pointer.getKey() == null) {
                return pointer.getValue();
            }
            else {
                return null;
            }
        }

        // executed if key doesn't equal null
        else if (hashMap[index] != null) {
            Entry<K, V> pointer = hashMap[index];
            // iterates till hash value of pointer key is less than key hash value
            while (pointer != null && pointer.getKey().hashCode() < key.hashCode()) {
                loopTracker++;
                pointer = pointer.getNext();
            }
            // in the event that there are multiple Entries with the same hash code as the
            // input key, cycles through them to find the right one
            if (pointer != null && pointer.getKey().hashCode() == key.hashCode()
                    && !pointer.getKey().equals(key)) {
                while (pointer != null && !pointer.getKey().equals(key)) {
                    loopTracker++;
                    pointer = pointer.getNext();
                }
            }
            if (pointer != null && pointer.getKey().equals(key)) {
                return pointer.getValue();
            }
        }
        return null;
    }

    // associates value with key in the HashMap
    public V put(K key, V value) {
        int index = hash(key);
        int hasher = 0;
        // this is used to track time complexity
        int putLoopTracker = 0;
        if (key != null) { hasher = key.hashCode(); }

        // checks to see if there's any data at the index
        // if not, then value is associated with key in position
        // else, iterates through Entry linked list to suitable
        // position for key-value pair to reside
        if (hashMap[index] != null) {
            Entry<K, V> pointer = hashMap[index];
            Entry<K, V> trailer = null;
            // if the pointer's key is null, it either returns its value if input key is null,
            // or adds the new element just before it, because nulls go at end
            if (pointer.getKey() == null) {
                if (key == null) {
                    return pointer.getValue();
                }
                else {
                    hashMap[index] = new Entry<K, V>(key, value, pointer);
                    size++;
                    return value;
                }
            }
            // since null keys go at the end, I know any null keys added to the
            // list must go at the end of the list. So I parse it
            else if (key == null) {
                while (pointer.getNext() != null) {
                    putLoopTracker++;
                    pointer = pointer.getNext();
                }
                // then, I check to see if I can put down a new Entry with a null key or not
                if (pointer.getKey() == null) {
                    return pointer.getValue();
                }
                else {
                    // since key will always be null here, we save a little
                    // space by just assigning the key value to null
                    pointer.setNext(new Entry<K, V>(null, value));
                    size++;
                    return value;
                }
            }

            while (pointer != null && pointer.getKey().hashCode() < hasher) {
                putLoopTracker++;
                trailer = pointer;
                pointer = pointer.getNext();
                if (pointer != null && pointer.getKey() == null) {
                    trailer.setNext(new Entry<K, V>(key, value, pointer));
                    size++;
                    return value;
                }
            }
            // in the event that there are multiple Entries with the same hash code as the
            // input key, cycles through them to find the right one
            if (pointer != null && pointer.getKey().hashCode() == key.hashCode()
                    && !pointer.getKey().equals(key)) {
                while (pointer != null && !pointer.getKey().equals(key)
                        && pointer.getKey().hashCode() == key.hashCode()) {
                    putLoopTracker++;
                    trailer = pointer;
                    pointer = pointer.getNext();
                }
            }
            if (pointer != null && pointer.getKey().equals(key)) {
                return pointer.getValue();
            }
            else {
                if (trailer == null) {
                    hashMap[index] = new Entry<K, V>(key, value, pointer);
                    size++;
                    return value;
                }
                else {
                    trailer.setNext(new Entry<K, V>(key, value, pointer));
                    size++;
                    return value;
                }
            }
        }
        else {
            hashMap[index] = new Entry<K, V>(key, value);
            size++;
            return value;
        }
    }


    // removes mapping for the specified key from hashMap
    // returns the value mapped to that key
    // if key is not present, returns null
    public V remove(K key) {
        int index = hash(key);
        int hasher = 0;
        // this is used to track time complexity
        int removeLoopTracker = 0;
        if (key != null) { hasher = key.hashCode(); }
        // checks the proper index to see if anything's there
        if (hashMap[index] == null) {
            return null;
        } else {
            Entry<K, V> pointer = hashMap[index];
            Entry<K, V> trailer = null;
            // if the given key is null and the first element has a null key, we remove it
            if (pointer.getKey() == null && key == null) {
                V returned = pointer.getValue();
                hashMap[index] = pointer.getNext();
                size--;
                return returned;
            }
            // otherwise, we need to parse the list to find our null key Entry
            else if (key == null) {
                while (pointer.getNext() != null) {
                    removeLoopTracker++;
                    trailer = pointer;
                    pointer = pointer.getNext();
                }
                // if our Entry is there, at the end of the list, we remove it
                if (pointer.getKey() == null) {
                    V returned = pointer.getValue();
                    trailer.setNext(null);
                    size--;
                    return returned;
                }
                else {
                    return null;
                }
            }
            // parses the index's linked lists to look for the properly numbered key
            while (pointer != null && pointer.getKey().hashCode() < hasher) {
                removeLoopTracker++;
                trailer = pointer;
                pointer = pointer.getNext();
            }
            // in the event that there are multiple Entries with the same hash code as the
            // input key, cycles through them to find the right one
            if (pointer != null && pointer.getKey().hashCode() == key.hashCode()
                    && !pointer.getKey().equals(key)) {
                while (pointer != null && !pointer.getKey().equals(key)
                        && pointer.getKey().hashCode() == key.hashCode()) {
                    removeLoopTracker++;
                    trailer = pointer;
                    pointer = pointer.getNext();
                }
            }
            // if key's found, returns value
            if (trailer != null && pointer != null && pointer.getKey().equals(key)) {
                V returned = pointer.getValue();
                trailer.setNext(pointer.getNext());
                size--;
                return returned;
            } else {
                // checks to see if it was maybe the first thing in the linked list that needs
                // to be removed
                if (pointer != null && pointer.getKey().equals(key)) {
                    V returned = pointer.getValue();
                    if (pointer.getNext() != null) { hashMap[index] = pointer.getNext(); }
                    else { hashMap[index] = null; }
                    size--;
                    return returned;
                }
                // if everything fails, it must not exist
                else {
                    return null;
                }
            }
        }
    }

    // replaces and returns the mapping for the specified key only
    // if it is currently mapped to some value, and returns null otherwise.
    public V replace(K key, V value) {
        int index = hash(key);
        // this is used to track time complexity
        int loopTracker = 0;
        Entry<K,V> ptr = (hashMap[index]);
        // initial check for null ptr value
        if (ptr == null) {
            return null;
        }

        else if (key == null) {
            while (ptr.getNext() != null) {
                loopTracker++;
                ptr = ptr.getNext();
            }
            // swaps value
            if (ptr.getKey() == null) {
                V returned = ptr.getValue();
                ptr.setValue(value);
                return returned;
            }
            else {
                return null;
            }
        }
        V returned = null;
        // locates key similarly implemented
        // to containsKey and get methods
        while (ptr.getNext() != null && !ptr.getKey().equals(key)) {
            loopTracker++;
            ptr = ptr.getNext();
        }
        // stores then updates ptr value
        if (ptr.getKey().equals(key)) {
            returned = ptr.getValue();
            ptr.setValue(value);
        }
        return returned;
    }

    public String toString() {
        String storage = "\n{";
        if (size == 0) {
            return storage + "}";
        }

        // used in initial construction of key-value paid
        // during inner loop
        boolean firstVal = true;

        // loops through each position in array
        // and looks to each linked list to find value
        for (int i = 0; i < bucketLength; i++) {
            Entry<K, V> pointer = hashMap[i];
            // only enters into inner loop if
            // pointer was not assigned to null
            while (pointer != null) {
                if (firstVal) {
                    storage += "\n" + pointer.getKey() + "  :" + pointer.getValue();
                    firstVal = false;
                }
//                        "\n     |" +
//                        "\n     |" +
//                        "\n     V\n" +
                else storage += ", \n" + pointer.getKey() + "   :" + pointer.getValue();
                pointer = pointer.getNext();
            }
        }
        return storage + "\n}";
    }



    public static void main(String[] args) {
        HashMap<Task, Task> taskMap = new HashMap<>();
        Task t1 = new Task("washer", false, null);
        Task[] tasks1 = {t1};
        Task t2 = new Task("dryer", false, tasks1);
        Task[] tasks2 = {t2};
        Task t3 = new Task("fold", false, tasks2);
        Task[] tasks3 = {t3};
        Task t4 = new Task("stash",false, tasks3);
        Task[] tasks4 = {t4};
        Task t5 = new Task("smoke dope",false, tasks4);

        taskMap.put(t1,t2);
        taskMap.put(t2,t3);
        taskMap.put(t3,t4);
        taskMap.put(t4,t5);

        System.out.println("HashMap: " + taskMap.toString());
        System.out.println(tasks1.hashCode());
    }
}
