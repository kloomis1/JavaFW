package dataops;

/*
*Created by: Kyle Loomis
*Maintained by: Kyle Loomis
*Details: csci 1933 - lab 004
*/

import java.util.Comparator;

public class ReverseStringComparator implements Comparator<String> {


    public int compare(String str1, String str2) {
        String strArray1[] = str1.split("");
        String strArray2[] = str2.split("");


        int tracker1 = strArray1.length;
        int tracker2 = strArray2.length;

        int i;


        if (tracker1 >= tracker2) {
            i = tracker2;
        }
        else {
            i = tracker1;
        }

        while (i > 0) {
            tracker2--;
            tracker1--;
            if (strArray1[tracker1].equals(strArray2[tracker2]) == false) {
                i = 0;
            }
            else { i--; }
        }
        if (i == -1) {
            return strArray1[tracker1].compareTo(strArray2[tracker2]);
        }
        else {
            return 0;
        }
    }

    public static void main(String[] args) {
        ReverseStringComparator compared = new ReverseStringComparator();
        System.out.println("Comparing strings: "+ compared.compare("bbb","aaa"));
    }
}
