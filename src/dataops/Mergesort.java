package dataops;

/*
*Created by: Kyle Loomis
*Maintained by: Kyle Loomis
*Details: csci 1933 - lab 004
*/

import java.util.Comparator;
import java.util.Objects;

public class Mergesort {

    public static <T extends Comparable<T>> T[] merge(T[] arr1, T[] arr2) {
        int arr1Tracker = 0;
        int arr2Tracker = 0;
        int i = 0;

        T[] result = (T[]) new Comparable[arr1.length + arr2.length];

        while (i < result.length) {
            if (arr1Tracker == arr1.length) {
                result[i] = arr2[arr2Tracker];
                arr2Tracker++;
                i++;
            }
            else if (arr2Tracker == arr2.length) {
                result[i] = arr1[arr1Tracker];
                arr1Tracker++;
                i++;
            }
            else if (arr1[arr1Tracker].compareTo(arr2[arr2Tracker]) < 0) {
                result[i] = arr1[arr1Tracker];
                arr1Tracker++;
                i++;
            }
            else if (arr1[arr1Tracker].compareTo(arr2[arr2Tracker]) == 0) {
                result[i] = arr1[arr1Tracker];
                arr1Tracker++;
                i++;

                result[i] = arr2[arr2Tracker];
                arr2Tracker++;
                i++;
            }
            else {
                result[i] = arr2[arr2Tracker];
                arr2Tracker++;
                i++;
            }
        }
        return result;
    }


    public static <T extends Comparable<T>> T[] mergesort(T[] arr) {
        T[] result;
        if (arr.length == 1) {
            return arr;
        }
        else {
            T[] arr1;
            T[] arr2;
            if ((arr.length % 2) == 0) {
                arr1 = (T[]) new Comparable[arr.length / 2];
                arr2 = (T[]) new Comparable[arr.length / 2];
                int i = 0;
                int j = 0;
                while (i < arr.length/2) {
                    arr1[i] = arr[i];
                    i++;
                }
                while (i < arr.length) {
                    arr2[j] = arr[i];
                    i++;
                    j++;
                }
            }
            else {
                arr1 = (T[]) new Comparable[(arr.length / 2) + 1];
                arr2 = (T[]) new Comparable[arr.length / 2];
                int i = 0;
                int j = 0;
                while (i < arr.length/2 + 1) {
                    arr1[i] = arr[i];
                    i++;
                }
                while (i < arr.length) {
                    arr2[j] = arr[i];
                    i++;
                    j++;
                }

            }
            return merge(mergesort(arr1), mergesort(arr2));
        }
    }


    public static <T> T[] merge(T[] arr1, T[] arr2, Comparator<T> comp) {
        int arr1Tracker = 0;
        int arr2Tracker = 0;
        int i = 0;

        T[] result = (T[]) new Comparable[arr1.length + arr2.length];

        while (i < result.length) {
            if (arr1Tracker == arr1.length) {
                result[i] = arr2[arr2Tracker];
                arr2Tracker++;
                i++;
            }
            else if (arr2Tracker == arr2.length) {
                result[i] = arr1[arr1Tracker];
                arr1Tracker++;
                i++;
            }
            else if (comp.compare(arr1[arr1Tracker],arr2[arr2Tracker]) < 0) {
                result[i] = arr1[arr1Tracker];
                arr1Tracker++;
                i++;
            }
            else if (comp.compare(arr1[arr1Tracker],arr2[arr2Tracker]) == 0) {
                result[i] = arr1[arr1Tracker];
                arr1Tracker++;
                i++;

                result[i] = arr2[arr2Tracker];
                arr2Tracker++;
                i++;
            }
            else {
                result[i] = arr2[arr2Tracker];
                arr2Tracker++;
                i++;
            }
        }
        return result;
    }



    public static <T> T[] mergesort(T[] arr, Comparator<T> comp) {
        T[] result;
        if (arr.length == 1) {
            return arr;
        }
        else {
            T[] arr1;
            T[] arr2;
            if ((arr.length % 2) == 0) {
                arr1 = (T[]) new Comparable[arr.length / 2];
                arr2 = (T[]) new Comparable[arr.length / 2];
                int i = 0;
                int j = 0;
                while (i < arr.length/2) {
                    arr1[i] = arr[i];
                    i++;
                }
                while (i < arr.length) {
                    arr2[j] = arr[i];
                    i++;
                    j++;
                }
            }
            else {
                arr1 = (T[]) new Comparable[(arr.length / 2) + 1];
                arr2 = (T[]) new Comparable[arr.length / 2];
                int i = 0;
                int j = 0;
                while (i < arr.length/2 + 1) {
                    arr1[i] = arr[i];
                    i++;
                }
                while (i < arr.length) {
                    arr2[j] = arr[i];
                    i++;
                    j++;
                }

            }
            return merge(mergesort(arr1,comp), mergesort(arr2,comp), comp);
        }
    }

    public static void main(String[] args) {
        String[] tString1 = {"Annie","Bob","Charlie","Dylan","Kyle"};
        String[] tString2 = {"Annie","Bill","Charles","Dee","Zi"};

        Integer[] tInt1 = {1,2,4,5,6,8,8};
        Integer[] tInt2 = {1,2,3,6,7,9};


        Object[] result = merge(tInt1,tInt2);
        for (int i = 0; i < result.length; i++) {
            System.out.println("Result " + i + ": " + result[i]);
        }

        System.out.println();

        result = merge(tString1,tString2);
        for (int i = 0; i < result.length; i++) {
            System.out.println("Result " + i + ": " + result[i]);
        }

        System.out.println();
        Object[] sorted;


        Integer[] tInt3 = {8,9,7,6,1,3,2,5,0};
        sorted = mergesort(tInt3);
        for (int i = 0; i < sorted.length; i++) {
            System.out.println("Result " + i + ": " + sorted[i]);
        }


        System.out.println();

        String[] tString3 = {"Bob","Annie","Mark","John","Dylan","Frank","Mary","Greg","Kyle"};
        sorted = mergesort(tString3);
        for (int i = 0; i < sorted.length; i++) {
            System.out.println("Result " + i + ": " + sorted[i]);
        }

        System.out.println();
        System.out.println("Time for the compMergesort test");
        ReverseStringComparator revTest = new ReverseStringComparator();
        Object[] revResult = mergesort(tString3, revTest);
        for (int i = 0; i < revResult.length; i++) {
            System.out.println(revResult[i]);
        }
    }

}
