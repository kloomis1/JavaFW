//package dataops;
//
///*
//*Created by: Kyle Loomis
//*Maintained by: Kyle Loomis
//*Details: csci 1933 - lab 004
//*/
//
//import static org.junit.Assert.*;
//
//public class MergesortTest {
//    @org.junit.Test
//    public void merge1() throws Exception {
//        Object[] result;
//
//        String[] tString1 = {"Annie","Bob"};
//        String[] tString2 = {"Annie","Bill"};
//        String[] correctStr1 = {"Annie","Annie","Bill","Bob"};
//        result = Mergesort.merge(tString1,tString2);
//
//        assertArrayEquals(result,correctStr1);
//
//        Integer[] tInt1 = {2,4};
//        Integer[] tInt2 = {1,3,5};
//        Integer[] correctInt1 = {1,2,3,4,5};
//        result = Mergesort.merge(tInt1,tInt2);
//
//        assertArrayEquals(result,correctInt1);
//
//        Integer[] tInt3 = {1,1};
//        Integer[] tInt4 = {2,3,6};
//        Integer[] correctInt2 = {1,1,2,3,6};
//        result = Mergesort.merge(tInt3,tInt4);
//
//        assertArrayEquals(result,correctInt2);
//    }
//
//    @org.junit.Test
//    public void merge2() throws Exception {
//        String[] tString1 = {"Annie","Bob","Charlie","Dylan","Kyle"};
//        String[] tString2 = {"Annie","Bill","Charles","Dee","Zi"};
//        Object[] correct = {"Annie", "Annie", "Bill", "Bob", "Charles", "Charlie", "Dee", "Dylan", "Kyle", "Zi"};
//        Object[] result = Mergesort.merge(tString1,tString2);
//        assertArrayEquals(result, correct);
//    }
//
//    @org.junit.Test
//    public void merge3() throws Exception {
//        Double[] tDub1 = {1.0,2.0,4.0,6.0,80.0}; //
//        Double[] tDub2 = {1.0,30.0,60.0,70.0,90.0}; //
//        Object[] correct = {1.0,1.0,2.0,4.0,6.0,30.0,60.0,70.0,80.0,90.0};
//        Object[] result = Mergesort.merge(tDub1, tDub2);
//        assertArrayEquals(correct,result);
//    }
//
//    @org.junit.Test
//    public void mergeTest4() throws Exception {
//        Integer[] tInt1 = {1,2,4,5,6,8,8}; //
//        Integer[] tInt2 = {1,2,3,6,7,9}; //
//        Object[] correct = {1,1,2,2,3,4,5,6,6,7,8,8,9};
//        Object[] result = Mergesort.merge(tInt1, tInt2);
//        assertArrayEquals(correct,result);
//    }
//
//    @org.junit.Test
//    public void mergesort1() throws Exception {
//        Object[] sorted;
//
//
//        Integer[] tInt1 = {3,1,2};
//        Integer[] correctInt1 = {1,2,3};
//        sorted = Mergesort.mergesort(tInt1);
//        assertArrayEquals(sorted,correctInt1);
//
//        Integer[] tInt2 = {1,2,3};
//        Integer[] correctInt2 = {1,2,3};
//        sorted = Mergesort.mergesort(tInt2);
//        assertArrayEquals(sorted,correctInt2);
//
//
//        String[] tString1 = {"Bob","Annie","Mark"};
//        String[] correctStr1 = {"Annie","Bob","Mark"};
//        sorted = Mergesort.mergesort(tString1);
//        assertArrayEquals(sorted,correctStr1);
//    }
//
//    @org.junit.Test
//    public void mergesort2() throws Exception {
//        Integer[] test = {1,6,5,3,8,9,2,4,0};
//        Object[] result = Mergesort.mergesort(test);
//        Object[] correct = {0,1,2,3,4,5,6,8,9};
//        assertArrayEquals(correct, result);
//    }
//
//    @org.junit.Test
//    public void mergesort3() throws Exception {
//        String[] test = {"abby", "abbie", "abe", "zyklon", "zero", "michael", "joe"};
//        Object[] result = Mergesort.mergesort(test);
//        Object[] correct = {"abbie", "abby", "abe", "joe", "michael", "zero", "zyklon"};
//        assertArrayEquals(result, correct);
//    }
//
//    @org.junit.Test
//    public void mergesort4() throws Exception {
//        Double[] test = {80.0,40.0,30.0,3.0,1.23,56.8,34.7};
//        Object[] result = Mergesort.mergesort(test);
//        Object[] correct = {1.23,3.0,30.0,34.7,40.0,56.8,80.0};
//        assertArrayEquals(result, correct);
//    }
//
//    @org.junit.Test
//    public void ReverseStringComparatorTest() throws Exception {
//        String s1 = "abc";
//        String s2 = "xyz";
//        ReverseStringComparator compTest = new ReverseStringComparator();
//        int result = compTest.compare(s1,s2);
//        int correct = -23;
//        assertEquals(result, correct);
//    }
//
//    @org.junit.Test
//    public void CompMergesortTest() throws Exception {
//        ReverseStringComparator compTest = new ReverseStringComparator();
//        String[] test = {"abby", "abbie", "abe", "zyklon", "zero", "michael", "joe"};
//        Object[] result = Mergesort.mergesort(test, compTest);
//        Object[] correct = {"abe", "abbie", "joe", "michael", "zyklon", "zero", "abby"};
//        assertArrayEquals(correct, result);
//    }
//}