package gametheory;

/**
 * Created by kloomis on 4/8/17.
 */

import java.util.Arrays;

public class Roll implements RollInterface {
    private int[] storageArray;

    // instantiates storargeArray of length 5
    public Roll() { storageArray = new int[5]; }

    // instantiates storargeArray of length n
    public Roll(int n) { storageArray = new int[n]; }

    // rolls and returns dice in range [1,6]
    public int roll() {
        return 1 + (int) ((Math.random() * 6));
    }

    // rolls and returns dice in specified
    // range [min,max]
    public int rollRange(int min, int max) {
        return min + (int) ((Math.random() * max));
    }

    // used for error checking distribution
    // of rolled dice in range [1,6]
    public int reverseRoll() {
        // inverses the possible values used
        // to evaluate authenticity of RNG
        int[] checkerArray = {6,5,4,3,2,1};
        return checkerArray[(int) ((Math.random() * 6))];
    }

    // organizes elements in storageArray
    public void sortStorage() {
        Arrays.sort(storageArray);
        int[] temp = new int[storageArray.length];
        int j = 0;
        for (int i = storageArray.length; i > 0; i--) { temp[j++] = storageArray[i-1]; }
        storageArray = temp;
    }

    // rolls dice with roll() to fill storageArray
    // length with values in range [1,6]
    public void fillStorageArray() {
        for (int i = 0; i < storageArray.length; i++) { this.storageArray[i] = roll(); }
    }

    // iterates through storageArray and returns
    // true if storageArray contains number inputted,
    // else false
    public boolean contains(int number) {
        for (int i = 0; i < storageArray.length; i++) { if (storageArray[i] == number) return true; }
        return false;
    }

    // returns true if hand contains at least
    // one of each value within the set {4,5,6},
    // else false
    public boolean boolAnalyze() {
        if (!contains(6)) { return false; }
        if (!contains(5)) { return false; }
        if (!contains(4)) { return false; }
        return true;
    }

    // returns the total value contained in a hand
    // 0 is returned if the hand doesn't contain
    // {4,5,6}, else returns the total contained in
    // range [2,12]
    public int intAnalyze() {
        if (boolAnalyze() != true) { return 0; }
        else {
            int[] copy = storageArray;
            int[] removalArray = {4,5,6};
            int total = 0;
            // sets the required values in removalArray to 0 in copy array
            for (int i = 0; i < removalArray.length; i++) {
                for (int k = 0; k < copy.length; k++) {
                    if (removalArray[i] == copy[k]) {
                        copy[k] = 0;
                        // breaks inner loop to avoid deleting duplicate
                        // values contained in removalArray
                        break;
                    }
                }
            }
            // sums up the 2 remaining digits and returns total
            for (int k = 0; k < copy.length; k++) { total += copy[k]; }
            return total;
        }
    }

    // returns private storageArray
    public int[] getStorage() { return storageArray; }


    public static void main(String[] args) {
        // number of iterations for each loop
        int iterations = 1000000;
        // number distribution array
        int[] numDist = new int[6];
        // contains values 0, and 2-12
        double[] scoreArray = new double[12];
        // holds true/false value
        double[] analysisArray = new double[2];

        // instantiates hand object
        Roll hand = new Roll();

        for (int i = 0; i < iterations; i++) { numDist[hand.roll() - 1]++; }
        System.out.println();
        for (int i = 0; i < numDist.length; i++) { System.out.println("Number " + (i+1) + " total: " + numDist[i]); }

        hand.fillStorageArray();
        System.out.println();
//        for (int i = 0; i < hand.getStorage().length; i++) { System.out.println("Position " + (i+1) + " number: " + hand.getStorage()[i]);}
//        person1.organizeStorage();
//        System.out.println();
//        for (int i = 0; i < person1.getStorage().length; i++) { System.out.println("Position " + (i+1) + " number: " + person1.getStorage()[i]); }

        for (int i = 0; i < iterations; i++) {
            hand.fillStorageArray();
            if (hand.boolAnalyze()) {
                analysisArray[0]++;
                scoreArray[hand.intAnalyze() - 1]++;
            }
            else {
                analysisArray[1]++;
                scoreArray[0]++;
            }
        }
        System.out.println("Percent of Ship, Captain, and Crew: " + ((analysisArray[0] / (analysisArray[0] + analysisArray[1])) * 100));
        System.out.println();
        System.out.println("Percent of of all days: " + (scoreArray[0] / iterations) * 100);
        for (int i = 1; i < scoreArray.length; i++) {
            System.out.println("Percent of " + (i+1) + "'s: " + (scoreArray[i] / iterations) * 100);
        }
    } // main
} // com.algora.gametheory.Roll.java
