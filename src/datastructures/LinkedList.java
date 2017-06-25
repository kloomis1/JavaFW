package datastructures;

/*
*Created by: Kyle Loomis
*Maintained by: Kyle Loomis
*Details: csci 1933 - lab 004 project3 3/27/17
*/

public class LinkedList<T extends Comparable<T>> implements List<T> {
    private Node<T> node;

    // constructor heads the Node with null
    public LinkedList() {
        this.node = new Node<>(null);
    }

    // turns a list into string in order
    // to visualize the elements contained
    public String toString() {
        String str = "[";
        Node<T> current = node;

        if (current.getNext() != null) {
            current = current.getNext();
            if (current.getData() != null) {
                str += current.getData();
                while (current.getNext() != null) {
                    current = current.getNext();
                    if (current.getData() != null) {
                        str += ", " + current.getData();
                    }
                }
            }
        }
        str += "]";
        return str;
    }

    // add element of type T to end of list. If element is
    // null, it will not add it and return false, else it will
    // add and return true
    public boolean add(T element) {
        if (element == null) { return false; }

        // gets next object in Node, since
        // the first is headed with null
        Node<T> current =  node;
        while (current.getNext() != null && current.getNext().getData() != null) {
            current = current.getNext();
        }
        current.setNext(new Node<T>(element));
        return true;
    }

    // adds element at a specific index. If
    // element is in position of index it will
    // shift that element and all other succeeding
    // elements to the right
    public boolean add(int index, T element) {
        Node<T> current = node;
        int inner = size();
        int outer = size();
        int i = 0;

        // checks for invalid input (null) or
        // if index is out of range. If true,
        // false is returned. Calls size()
        // method to check size of list
        if (element == null || index < 0 || index >= size()) {
            return false;
        }

        // iterates to last element,
        // then duplicates it. repeats
        // this process, except index of
        // last element decreases (converges)
        // to the index specified
        while (outer > index) {
            // reassigns current to head of list
            // each iteration
            current = node;
            i = 0;
            while (i < inner) {
                current = current.getNext();
                i++;
            }
            // if statement only executes
            // at very end of list once
            if (i == size()) {
                current.setNext(new Node<T>(current.getData()));
            }
            else {
                current.getNext().setData(current.getData());
            }
            inner--;
            outer--;
        }
        // finally sets the element into the current
        // (index) position in list
        current.setData(element);
        return true;
    }

    // removes all elements from the list
    // except for the head of null
    public void clear() {
        Node<T> current = node;

        current = current.getNext();
        // removes each element from list
        // until size == 0
        while (size() != 0) {
            remove(current.getData());
        }
    }

    // returns true if element contained
    // in list, else otherwise
    public boolean contains(T element) {
        Node<T> current = node;

        while (current.getNext() != null) {
            current = current.getNext();
            if (current.getData() != null && current.getData().equals(element)) {
                return true;
            }
        }
        return false;
    }

    // returns object at a specified
    // index. if out of bounds, returns
    // null
    public T get(int index) {
        Node<T> current = node;
        int i = 0;
//        // error checking to convert input
//        // of double to int
//        index = (int) index;
        if (index < 0 || index >= size()) { return null; }

        while (i <= index) {
            current = current.getNext();
            i++;
        }
        return current.getData();
    }

    // return the 1st index of element
    // in the list. if element is null
    // or not found in the list, return -1
    public int indexOf(T element) {
        Node<T> current = node;
        int index = 0;

        while (current.getNext() != null) {
            current = current.getNext();
            if (current.getData() != null && current.getData().equals(element)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    // same as indexOf method, except
    // returns index of last element
    public int lastIndexOf(T element) {
        Node<T> current = node;
        int i = 0;
        // initially sets index to -1.
        // will be changed in if statement
        // in while loop
        int index = -1;

        while (current.getNext() != null && current.getNext().getData() != null) {
            current = current.getNext();
            if (current.getData().equals(element)) {
                index = i;
            }
            i++;
        }
        return index;
    }

    // returns true if the list is
    // empty, else false
    public boolean isEmpty() {
        Node<T> current = node;
        if (current.getNext() != null) { return false; }
        return true;
    }

    // replaces element at specified index
    // and returns element previously at
    // that index. If index out of bounds/
    // element is null, null is returned
    public T set(int index, T element) {
        Node<T> current = node;
        int i = 0;

        if (index < 0 || index >= size() || element == null) {
            return null;
        }
        T returned = get(index);

        while (i <= index) {
            current = current.getNext();
            i++;
        }
        current.setData(element);

        return returned;
    }

    // checks and returns size of list
    public int size() {
        Node<T> current = node;
        int size = 0;
        while (current.getNext() != null) {
            current = current.getNext();
            if (current.getData() != null) {
                size++;
            }
        }
        return size;
    }

    // sorts elements in the list - if order
    // true, list is sorted in increasing
    // (alphabetical) order, else sorted in
    // decreasing order.
    // Uses  compareTo(T o) since T extends
    // Comparable
    public void sort(boolean order) {
        Node<T> current = node;
        T holder = null;
        int i = 0;
        int ptr = 0;
        // represents dynamic size beccause
        // variable decreases every iteration
        // through inner loop
        int dynSize = size() -1;

        while (ptr < size()) {
            i = 0;
            current = node.getNext();
            while (current.getNext() != null && current.getNext().getData() != null && i < dynSize) {
                // checks if order is true to sort
                // in increasing order
                if (order == true && current.getData().compareTo(current.getNext().getData()) > 0) {
                    holder = current.getData();
                    current.setData(current.getNext().getData());
                    current.getNext().setData(holder);
                }
                // sorts in decreasing order
                else if (order == false && current.getData().compareTo(current.getNext().getData()) < 0) {
                    holder = current.getData();
                    current.setData(current.getNext().getData());
                    current.getNext().setData(holder);
                }
                current = current.getNext();
                i++;
            }
            dynSize--;
            ptr++;
        }
    }

    // removes first instance of element
    // list, and shift any subsequent
    // elements to the left. If successful,
    // return true, else return false
    public boolean remove(T element) {
        Node<T> current = node;
        int i = 0;
        int index = indexOf(element);

        // initially checks if element isn't
        // in the list
        if (index == -1) { return false; }

        // 1st loop updates current to equal
        // the object before element
        while (i < index) {
            current = current.getNext();
            i++;
        }

        while (current.getNext() != null && current.getNext().getData() != null) {
            // checks if current is 1 before
            // end of list, if true then next
            // node is set to null
            if (i == size() - 1) {
                current.setNext(new Node<T>(null));
                return true;
            }
            // current is updated to next and set to
            // next data
            else {
                current = current.getNext();
                current.setData(current.getNext().getData());
                i++;
            }
        }
        return false;
    }

    // removes data at specified index and returns
    // it. If index is out-of-bounds, returns null.
    // Shift the indices as in the other remove.
    // Functionally exactly the same as form remove
    // method, except returns data it just removed,
    // or null if no data is found
    public T remove(int index) {
        Node<T> current = node;
        int i = 0;

        if (index < 0 || index >= size()) { return null; }

        T returned = get(index);
        while (i < index) {
            current = current.getNext();
            i++;
        }
        while (current.getNext() != null && current.getNext().getData() != null) {
            if (i == size() - 1) {
                current.setNext(new Node<T>(null));
                return returned;
            }
            else {
                current = current.getNext();
                current.setData(current.getNext().getData());
                i++;
            }
        }
        return null;
    }

    // added unnecessary comment
    public static void main(String[] args) {
        LinkedList<String> strNode = new LinkedList<>();
        LinkedList<Integer> intNode = new LinkedList<>();
        LinkedList<Double> doubleNode = new LinkedList<>();
        LinkedList<String> emptyNode = new LinkedList<>();

        System.out.println("Checking methods");


        // adding values to strNode, intNode and doubleNode
        strNode.add("1");
        strNode.add("2");
        strNode.add("3");
        intNode.add(1);
        intNode.add(3);
        intNode.add(2);
        intNode.add(4);
        doubleNode.add(2.1);
        doubleNode.add(1.9);
        doubleNode.add(1.0);

        System.out.println();

        // strNode method checks
        System.out.println("Method checks on strNode");
        System.out.println("size() - expecting size = 3: " + strNode.size());
        System.out.println("toString() - expecting [1,2,3]: " + strNode.toString());
        System.out.println("add(null) - expecting false: " + strNode.add(null));
        System.out.println("contains('3') - expecting true: " + strNode.contains("3"));
        System.out.println("contains('4') - expecting false: " + strNode.contains("4"));
        System.out.println("get(-10) - expecting null: " + strNode.get(-10));
        System.out.println("get(1) - expecting 2: " + strNode.get(1));
        System.out.println("isEmpty() - expecting false: " + strNode.isEmpty());
        System.out.println("indexOf('1') - expecting 0: " + strNode.indexOf("1"));
        System.out.println("indexOf('0') - expecting -1: " + strNode.indexOf("0"));
        System.out.println("lastIndexOf('1') - expecting 0: " + strNode.lastIndexOf("1"));
        System.out.println("lastIndexOf('0') - expecting -1: " + strNode.lastIndexOf("0"));
        System.out.println("add('1') - expecting true: " + strNode.add("1"));
        System.out.println("toString() - expecting [1,2,3,1]: " + strNode.toString());
        System.out.println("lastIndexOf('1') - expecting 3: " + strNode.lastIndexOf("1"));
        System.out.println("remove('1') - expecting true: " + strNode.remove("1"));
        System.out.println("remove('-1') - expecting false: " + strNode.remove("-1"));
        System.out.println("toString() - expecting [2,3,1]: " + strNode.toString());
        System.out.println("remove(2) - expecting 1: " + strNode.remove(2));
        System.out.println("remove(2) - expecting null: " + strNode.remove(2));
        System.out.println("toString() - expecting [2,3]: " + strNode.toString());
        System.out.println("set(0,'4') - expecting 2: " + strNode.set(0,"4"));
        System.out.println("set(-1,'4') - expecting null: " + strNode.set(-1,"4"));
        System.out.println("set(2,'4') - expecting null: " + strNode.set(2,"4"));
        System.out.println("set(1,null) - expecting null: " + strNode.set(1,null));
        System.out.println("toString() - expecting [4,3]: " + strNode.toString());
        strNode.clear();
        System.out.println("clear() -> toString() - expecting []: " + strNode.toString());
        System.out.println("adding variables with add(element) method");
        strNode.add("zebra");
        strNode.add("mary");
        strNode.add("mom");
        strNode.add("annie");
        System.out.println("toString() - expecting [zebra,mary,mom,annie]: " + strNode.toString());
        strNode.sort(true);
        System.out.println("sort(true) - toString() expecting [annie,mary,mom,zebra]: " + strNode.toString());
        System.out.println("add(1,'luke') - expecting true: " + strNode.add(1,"luke"));
        System.out.println("toString() - expecting [annie,luke,mary,mom,zebra]: " + strNode.toString());
        System.out.println("add(1, null) - expecting false: " + strNode.add(1,null));
        System.out.println("add(-1, '4') - expecting false: " + strNode.add(-1,"4"));
        System.out.println("add(3, '4') - expecting false: " + strNode.add(5,"4"));
        System.out.println("toString() repeat - expecting [annie,luke,mary,mom,zebra]: " + strNode.toString());

        System.out.println();

        // intNode method checks
        System.out.println("Method checks on intNode");
        System.out.println("size() - expecting size = 4: " + intNode.size());
        System.out.println("toString() - expecting [1,3,2,4]: " + intNode.toString());
        System.out.println("add(null) - expecting false: " + intNode.add(null));
        System.out.println("contains(1) - expecting true: " + intNode.contains(1));
        System.out.println("contains(0) - expecting false: " + intNode.contains(0));
        System.out.println("get(4) - expecting null: " + intNode.get(4));
        System.out.println("get(3) - expecting 4: " + intNode.get(3));
        System.out.println("isEmpty() - expecting false: " + intNode.isEmpty());
        System.out.println("indexOf(4) - expecting 3: " + intNode.indexOf(4));
        System.out.println("indexOf(-5) - expecting -1: " + intNode.indexOf(-5));
        System.out.println("lastIndexOf(4) - expecting 3: " + intNode.lastIndexOf(4));
        System.out.println("lastIndexOf(-5) - expecting -1: " + intNode.lastIndexOf(-5));
        System.out.println("remove(1) - expecting true: " + intNode.remove((Integer)1));
        System.out.println("remove(1) - expecting false: " + intNode.remove((Integer)1));
        System.out.println("toString() repeat - expecting [3,2,4]: " + intNode.toString());
        System.out.println("remove(1) - expecting 2: " + intNode.remove(1));
        System.out.println("toString() repeat - expecting [3,4]: " + intNode.toString());
        System.out.println("remove(2) - expecting null: " + intNode.remove(2));
        System.out.println("remove(-1) - expecting null: " + intNode.remove(-1));
        System.out.println("remove(0) - expecting 3: " + intNode.remove(0));
        System.out.println("toString() repeat - expecting [3,4]: " + intNode.toString());
        System.out.println("add(10) - expecting true: " + intNode.add(10));
        System.out.println("remove(11) - expecting null: " + intNode.remove(11));
        System.out.println("remove(0) - expecting 4: " + intNode.remove(0));
        System.out.println("toString() repeat - expecting [10]: " + intNode.toString());
        System.out.println("set(0,-1) - expecting 10: " + intNode.set(0,-1));
        System.out.println("toString() - expecting [-1]: " + intNode.toString());
        intNode.clear();
        System.out.println("clear() -> toString() - expecting []: " + intNode.toString());
        System.out.println("adding variables with add(element) method");
        intNode.add(1);
        intNode.add(4);
        intNode.add(101);
        intNode.add(0);
        System.out.println("toString() - expecting [1,4,101,0]: " + intNode.toString());
        intNode.sort(true);
        System.out.println("sort(true) - toString() expecting [0,4,101,0]: " + intNode.toString());
        System.out.println("add(0,-51) - expecting true: " + intNode.add(0,-51));
        System.out.println("toString() - expecting [-51,0,4,101,0]: " + intNode.toString());
        System.out.println("adding 1000 elements to test robustness");
        for (int i = 0; i < 1000; i++) {
            intNode.add(i);
        }
        System.out.println("removing 1000 elements");
        for (int i = 0; i < 1000; i++) {
            intNode.remove(5);
        }
        System.out.println("toString() - expecting [-51,1,4,101,0]: " + intNode.toString());


        System.out.println();

        // doubleNode method checks
        System.out.println("Method checks on doubleNode");
        System.out.println("size() - expecting size = 3: " + doubleNode.size());
        System.out.println("toString() - expecting [2.1,1.9,1.0]: " + doubleNode.toString());
        System.out.println("add(null) - expecting false: " + doubleNode.add(null));
        System.out.println("toString() repeat - expecting [2.1,1.9,1.0]: " + doubleNode.toString());
        System.out.println("contains(1.0) - expecting true: " + doubleNode.contains(1.0));
        System.out.println("contains(1.91) - expecting false: " + doubleNode.contains(1.91));
        System.out.println("get(-1) - expecting null: " + doubleNode.get(-1));
        System.out.println("get(1) - expecting 1.9: " + doubleNode.get(1));
        System.out.println("isEmpty() - expecting false: " + doubleNode.isEmpty());
        System.out.println("indexOf(1.9) - expecting 1: " + doubleNode.indexOf(1.9));
        System.out.println("indexOf(1.91) - expecting -1: " + doubleNode.indexOf(1.91));
        System.out.println("lastIndexOf(1.9) - expecting 1: " + doubleNode.lastIndexOf(1.9));
        System.out.println("lastIndexOf(1.91) - expecting -1: " + doubleNode.lastIndexOf(1.91));
        System.out.println("toString() repeat - expecting [2.1,1.9,1.0]: " + doubleNode.toString());
        System.out.println("remove(1.0) - expecting true: " + doubleNode.remove(1.0));
        System.out.println("toString() repeat - expecting [2.1,1.9]: " + doubleNode.toString());
        System.out.println("remove(1) - expecting 1.9: " + doubleNode.remove(1));
        System.out.println("toString() repeat - expecting [2.1]: " + doubleNode.toString());
        System.out.println("set(0, 1.67) - expecting 2.1: " + doubleNode.set(0,1.67));
        System.out.println("toString() - expecting [1.67]: " + doubleNode.toString());
        doubleNode.clear();
        System.out.println("clear() -> toString() - expecting []: " + doubleNode.toString());
        System.out.println("adding variables with add(element) method");
        doubleNode.add(-10.1);
        doubleNode.add(0.01);
        doubleNode.add(0.001);
        doubleNode.add(1.1);
        System.out.println("toString() - expecting [-10.1,0.01,0.001,1.1]: " + doubleNode.toString());
        doubleNode.sort(false);
        System.out.println("sort(false) - toString() expecting [1.1,0.01,0.001,-10.1]: " + doubleNode.toString());
        System.out.println("add(3,-1.067) - expecting true: " + doubleNode.add(3,-1.067));
        System.out.println("toString() - expecting [1.1,0.01,0.001,-1.067,-10.1]: " + doubleNode.toString());

        System.out.println();

        System.out.println("Method checks on emptyNode");
        System.out.println("toString() - expecting []: " + emptyNode.toString());
        System.out.println("isEmpty() - expecting true: " + emptyNode.isEmpty());
        System.out.println("size() - expecting size = 0: " + emptyNode.size());
        System.out.println("indexOf('1') - expecting -1: " + emptyNode.indexOf("1"));
        System.out.println("lastIndexOf('1') - expecting -1: " + emptyNode.lastIndexOf("1"));
        System.out.println("remove('1') - expecting false: " + emptyNode.remove("1"));
        System.out.println("remove(0) - expecting null: " + emptyNode.remove(0));
        System.out.println("toString() repeat - expecting []: " + emptyNode.toString());
        System.out.println("set(0,'167') - expecting null: " + emptyNode.set(0,"167"));
        System.out.println("toString() - expecting []: " + emptyNode.toString());
        emptyNode.clear();
        System.out.println("clear() -> toString() - expecting []: " + emptyNode.toString());
        emptyNode.sort(false);
        System.out.println("sort(false) - toString() expecting []: " + emptyNode.toString());
        emptyNode.sort(true);
        System.out.println("sort(true) - toString() expecting []: " + emptyNode.toString());
        System.out.println("add(0,'106') - expecting false: " + emptyNode.add(3,"106"));
        System.out.println("toString() - expecting []: " + emptyNode.toString());

        System.out.println();

//        System.out.println("toString() - expecting [1.1,0.01,0.001,-1.067,-10.1]: " + reverse(doubleNode));
        System.out.println("toString() - expecting [1.1,0.01,0.001,-1.067,-10.1]: " + doubleNode.toString());

    }
}
