package gametheory;

/**
 * Created by kloomis on 4/9/17.
 */


public interface RollInterface {

    // rolls and returns dice in range [1,6]
    public int roll();

    // rolls and returns dice in specified
    // range [min,max]
    public int rollRange(int min, int max);

    // used for error checking distribution
    // of rolled dice in range [1,6]
    public int reverseRoll();

    // organizes elements in storageArray
    public void sortStorage();

    // rolls dice with roll() to fill storageArray
    // length with values in range [1,6]
    public void fillStorageArray();

    // iterates through storageArray and returns
    // true if storageArray contains number inputted,
    // else false
    public boolean contains(int number);

    // returns true if hand contains at least
    // one of each value within the set {4,5,6},
    // else false
    public boolean boolAnalyze();

    // returns the total value contained in a hand
    // 0 is returned if the hand doesn't contain
    // {4,5,6}, else returns the total contained in
    // range [2,12]
    public int intAnalyze();

    // returns private storageArray
    public int[] getStorage();
}
