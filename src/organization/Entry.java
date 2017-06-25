package organization;

/*
*Created by: Chris Dovolis
*Maintained by: Kyle Loomis
*Details: csci 1933 - lab 004
*/

// Entry<K, V> makes up the links in each linked list in the HashMap
// Contains a key, used to determine placement, and a value storing data
// Also has a link to the next Entry, next
public class Entry<K, V> {

    // private variables stored by class
    private K key;
    private V value;
    private Entry<K, V> next;

    // Four different constructors
    // Allow you to make the Entry with any viable combination of data

    public Entry(K k, V v, Entry<K, V> n) {
        key = k;
        value = v;
        next = n;
    }

    public Entry(K k, V v) {
        key = k;
        value = v;
        next = null;
    }

    public Entry(K k) {
        key = k;
        value = null;
        next = null;
    }

    public Entry(K k, Entry<K, V> n) {
        key = k;
        next = n;
    }

    // getters for private variables
    public K getKey() { return key; }
    public V getValue() { return value; }
    public Entry<K, V> getNext() { return next; }

    // setters for private variables
    public void setKey(K k) { key = k; }
    public void setValue(V v) { value = v; }
    public void setNext(Entry<K, V> n) { next = n; }
}
