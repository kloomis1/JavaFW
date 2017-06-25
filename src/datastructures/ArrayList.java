package datastructures;

/*
*Created by: Michael Biggers
*Maintained by: Kyle Loomis
*Details: csci 1933 - lab 004 project3 3/27/17
*/

public class ArrayList<T extends Comparable<T>> implements List<T> {

    //instances the underlying array
    private T[] underArray;

    // constructor sets the underlying array to size 2 as specified
    public ArrayList()  {
        underArray = (T[]) new Comparable[2];
    }

    // getter for underArray
    public T[] getArray() {
        return underArray;
    }

    // turns underArrray into a string
    // used to display underArray easily in the main
    public String toString() {
        String container = "[";
        // parses array and adds its elements onto the string
        for (int i = 0; i < this.size(); i++) {
            if (i != this.size() - 1) {
                container += underArray[i] + ", ";
            }
            else {
                container += underArray[i];
            }
        }
        container += "]";
        return container;
    }

    // adds a new element onto underArray at the end
    public boolean add(T element) {
        // if you try to add a null onto, that doesn't fly. returns false
        if (element == null) {
            return false;
        }
        else {
            //parses through the list checking for the first null to overwrite
            int idx = 0;
            while (underArray[idx] != null) {
                //calls enlarge if there's no space left in the list
                if (idx == underArray.length - 1) {
                    enlarge();
                }
                idx++;
            }
            underArray[idx] = element;
            return true;
        }
    }

    // adds element onto the list at the specified index
    public boolean add(int index, T element) {
        if (element == null || index < 0 || index >= size()) {
            return false;
        }
        else {
            // checks to see if underArray needs to be enlarged
            if (underArray[underArray.length-1] != null) {
                enlarge();
            }
            // constructs a new array, shiftArray, using data from
            // underArray and throwing in the new value at index
            T[] shiftArray = (T[]) new Comparable[underArray.length];
            int j = 0;
            for (int i = 0; i < size(); i++) {
                if (i == index) {
                    shiftArray[j] = element;
                    j++;
                }
                if (j < shiftArray.length) {
                    shiftArray[j] = underArray[i];
                    j++;
                }
            }
            underArray = shiftArray;
            return true;
        }
    }

    // clears all elements from the list by resetting it to its initialization
    public void clear() {
        underArray = (T[]) new Comparable[2];
    }

    // checks to see if element is in the list
    // parses through the list and compares each element individually
    public boolean contains(T element) {
        for (int i = 0; i < underArray.length; i++) {
            if (underArray[i] == element) {
                return true;
            }
        }
        return false;
    }

    // gets the element at the given index
    // if out of underArray's bounds, returns null instead
    public T get(int index) {
        if (index < 0 || index >= underArray.length) {
            return null;
        }
        else {
            return underArray[index];
        }
    }
    // returns the first index of element in underArray by parsing it
    //If null or not found, returns -1
    public int indexOf(T element) {
        if (element == null) {
            return -1;
        }
        else {
            for (int i = 0; i < underArray.length; i++) {
                if (underArray[i] == element) {
                    return i;
                }
            }
        }
        return -1;
    }

    //checks to see if there are any elements in the list
    // parses list, checks to see if element isn't null
    // if everything is null, list is empty
    public boolean isEmpty() {
        for (int i = 0; i < underArray.length; i++) {
            if (underArray[i] != null) {
                return false;
            }
        }
        return true;
    }

    // returns the last index of an element in the list
    // parses underArray from beginning to end looking for element
    // stores the most recent sighting of element in storedIdx
    // reports this at the end if input is valid
    public int lastIndexOf(T element) {
        int storedIdx = -1;
        if (element == null) {
            return -1;
        }
        else {
            for (int i = 0; i < underArray.length; i++) {
                if (underArray[i] == element) {
                    storedIdx = i;
                }
            }
            return storedIdx;
        }
    }

    // sets the thing at index to be element, and returns the previous thing
    // if input is invalid, returns null
    public T set(int index, T element) {
        if (element == null || index < 0 || index >= size()) {
            return null;
        }
        else {
            T removed = null;
            if (underArray[index] != null) {
                removed = underArray[index];
            }
            underArray[index] = element;
            return removed;
        }
    }

    // returns the number of elements in the list that aren't null
    public int size() {
        int size = 0;
        // parses through underArray counting all non-null elements
        for (int i = 0; i < underArray.length; i++) {
            if (underArray[i] != null) {
                size++;
            }
        }
        return size;
    }

    // sorts the underArray based on the boolean input
    // if order is true, sort in ascending (low-high) order
    // if order is false, sort in descending (high-low) order
    // uses standard bubble-sorting algorithm for each case
    // also checks each spot to see if it's null to avoid null pointers
    public void sort(boolean order) {
        int i, j;
        T temp;
        boolean swapped = true;

        if (order) {
        for (i = 0; i < underArray.length && swapped == true; i++) {
            swapped = false;
            for (j = 1; j < underArray.length - i; j++) {
                if (underArray[j] != null && underArray[j-1] != null &&
                        underArray[j].compareTo(underArray[j-1]) < 0) {
                    swapped = true;
                    temp = underArray[j];
                    underArray[j] = underArray[j-1];
                    underArray[j-1] = temp;
                }
            }
        }
    }
        else {
            for (i = 0; i < underArray.length && swapped == true; i++) {
                swapped = false;
                for (j = 1; j < underArray.length - i; j++) {
                    if (underArray[j] != null && underArray[j-1] != null &&
                            underArray[j].compareTo(underArray[j-1]) > 0) {
                        swapped = true;
                        temp = underArray[j];
                        underArray[j] = underArray[j-1];
                        underArray[j-1] = temp;
                    }
                }
            }
        }
    }

    // removes the first instance of element from the list and returns true
    // shifts all elements to the right one over
    // if element isn't found, returns false
    public boolean remove(T element) {
        for (int i = 0; i < underArray.length; i++) {
            //if the element is found, begin moving everything
            if (underArray[i] == element) {
                boolean lastDone = false;
                for (int j = i; j < underArray.length; j++) {
                    // checks to see if it's at the end of the array yet, or has simply found a null inside the array
                    // only stops swapping when it's at the end of the array. Uses lastDone to check
                    if (underArray[j+1] != null) {
                        underArray[j] = underArray[j + 1];
                    }
                    else if (lastDone == false && j != underArray.length -1) {
                        underArray[j] = underArray[j + 1];
                        lastDone = true;
                        j = underArray.length;
                    }
                }
                return true;
            }
        }
        return false;
    }

    // removes the element at the specified index, and shifts list over
    // returns the removed item
    // if index is invalid, returns null
    public T remove(int index) {
        if (index < 0 || index >= underArray.length || underArray[index] == null) {
            return null;
        }
        else {
            T removed = underArray[index];
            boolean lastDone = false;

            for (int i = index; i < this.size(); i++) {
                // checks to see if it's at the end of the array yet, or has simply found a null inside the array
                // only stops swapping when it's at the end of the array. Uses lastDone to check
                if (i != this.size() - 1) {
                    underArray[i] = underArray[i + 1];
                }
                else if (lastDone == false) {
                    underArray[i] = null;
                    lastDone = true;
                    i = this.size();
                }
            }
            return removed;
        }
    }

    // if add finds that the list is too small to be added onto, enlarge is called
    // enlarge doubles the size of underArray so size isn't a problem
    // does this by making a new double-size array, copying all the data
    // onto it, and setting the big array to be underArray
    private void enlarge() {
        T[] bigArray = (T[]) new Comparable[2*underArray.length];
        for (int i = 0; i < underArray.length; i++) {
            bigArray[i] = underArray[i];
        }
        underArray = bigArray;
    }

    // main tests all the methods implemented
    // what the tests hope to accomplish are said in the print statements
    public static void main(String[] args) {

        // initializing arrays
        ArrayList testlist = new ArrayList();
        ArrayList emptyList = new ArrayList();
        ArrayList smallList = new ArrayList();
        ArrayList breaklist = new ArrayList();

        System.out.println("Now testing the first add method: should return true,true,true,false,false");
        System.out.println(testlist.add(2));
        System.out.println(testlist.add(4));
        System.out.println(testlist.add(7));
        System.out.println(testlist.add(null));
        System.out.println(emptyList.add(null));
        System.out.println("Now displaying testlist");
        System.out.println(testlist.toString());

        System.out.println();
        System.out.println("Now adding 1000 elements to an array, to see if it breaks. Should return 0, 500, 999");
        for (int i = 0; i < 1000; i++) {
            breaklist.add(i);
        }
        System.out.println(breaklist.get(0));
        System.out.println(breaklist.get(500));
        System.out.println(breaklist.get(999));

        System.out.println();
        System.out.println("Now testing the second add method: should return false, true, true, false, false, false");
        System.out.println(testlist.add(-1, 34));
        System.out.println(testlist.add(0, 34));
        System.out.println(testlist.add(3,9));
        System.out.println(testlist.add(6, 30));
        System.out.println(testlist.add(3,null));
        System.out.println(emptyList.add(0,1));
        System.out.println("Now displaying testlist");
        System.out.println(testlist.toString());

        System.out.println();
        System.out.println("Now testing contains method: should return true, false, true, false, false");
        System.out.println(testlist.contains(34));
        System.out.println(testlist.contains("cat"));
        System.out.println(testlist.contains(7));
        System.out.println(testlist.contains(27));
        System.out.println(emptyList.contains(100));

        System.out.println();
        System.out.println("Now testing get method. Should return 34, null, null, 9, null");
        System.out.println(testlist.get(0));
        System.out.println(testlist.get(5));
        System.out.println(testlist.get(20));
        System.out.println(testlist.get(3));
        System.out.println(emptyList.get(3));

        System.out.println();
        System.out.println("Now testing indexOf method. Should return 3, -1, 4, -1, -1");
        System.out.println(testlist.indexOf(9));
        System.out.println(testlist.indexOf(null));
        System.out.println(testlist.indexOf(7));
        System.out.println(testlist.indexOf(20));
        System.out.println(emptyList.indexOf(3.0));

        System.out.println();
        System.out.println("Now testing isEmpty method. Should return true, false, false");
        smallList.add("cats");
        System.out.println(emptyList.isEmpty());
        System.out.println(testlist.isEmpty());
        System.out.println(smallList.isEmpty());

        System.out.println();
        smallList.add("dogs");
        smallList.add(7);
        smallList.add("dogs");
        smallList.add(7);
        smallList.add("cats");
        smallList.add("dogs");
        System.out.println("Now displaying smallList");
        System.out.println(smallList.toString());
        System.out.println("Now testing lastIndexOf method. Should return 4, 6, 5, -1, -1");
        System.out.println(smallList.lastIndexOf(7));
        System.out.println(smallList.lastIndexOf("dogs"));
        System.out.println(smallList.lastIndexOf("cats"));
        System.out.println(smallList.lastIndexOf(3.0));
        System.out.println(emptyList.lastIndexOf(3.0));

        System.out.println();
        System.out.println("Now testing the set method. Should return cats, null, null, dogs, null");
        System.out.println(smallList.set(0, "reset1"));
        System.out.println(smallList.set(7, "reset2"));
        System.out.println(smallList.set(8, 1337));
        System.out.println(smallList.set(3, 1337));
        System.out.println(emptyList.set(0, 1337));
        System.out.println("Now displaying smallList");
        System.out.println(smallList.toString());

        System.out.println();
        System.out.println("Now testing the sort method forwards");
        testlist.sort(true);
        System.out.println(testlist.toString());
        System.out.println("Now testing the method backwards");
        testlist.sort(false);
        System.out.println(testlist.toString());
        System.out.println("Now testing the method backwards on emptyList");
        emptyList.sort(false);
        System.out.println(emptyList.toString());

        System.out.println();
        System.out.println("Now testing remove function. Should return true, false, true, false, false");
        System.out.println(testlist.remove((Integer) 34));
        System.out.println(testlist.remove((Integer) 35));
        System.out.println(testlist.remove((Integer) 2));
        System.out.println(testlist.remove((Integer) 8));
        System.out.println(emptyList.remove((Integer) 34));
        System.out.println("Now displaying testlist");
        System.out.println(testlist.toString());

        System.out.println();
        System.out.println("Now testing remove at index function. Should return 9, null, null, 4, null");
        System.out.println(testlist.remove(0));
        System.out.println(testlist.remove(10));
        System.out.println(testlist.remove(2));
        System.out.println(testlist.remove(1));
        System.out.println(emptyList.remove(-1));
        System.out.println("Now displaying testlist");
        System.out.println(testlist.toString());
        System.out.println(emptyList.toString());

        System.out.println();
        System.out.println("Now removing 1000 elements from an array, to see if it breaks. Should return []");
        for (int i = 0; i < 1000; i++) {
            breaklist.remove(0);
        }
        System.out.println(breaklist.toString());

        System.out.println();
        System.out.println("Now clearing testlist, and displaying");
        testlist.clear();
        emptyList.clear();
        System.out.println(testlist.toString());
        System.out.println(emptyList.toString());
    }
}