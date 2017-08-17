/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardstacks;

import java.util.Random;

/**
 *
 * @author Biju Ale
 */
public class Dice {

    private String diceName;
    public  static final int ROLL_TIMES = 10000;
    private Integer[] Combinations;
    private Integer[] frequencies;
    private int minCombination, maxCombination;

    Dice(String diceName, int minCombination, int maxCombination) {
        this.diceName = diceName;
        setMinMax(minCombination, maxCombination);
    }

    public Integer[] getCombinations() {
        return Combinations;
    }

    public Integer[] getFrequencies() {
        return frequencies;
    }

    //set - min & max Combination
    private void setMinMax(int minCombination, int maxCombination) {
        this.minCombination = minCombination;
        this.maxCombination = maxCombination;
        populateCombinations();
    }

    //Populate array with all possible combinations
    private void populateCombinations() {
        Combinations = new Integer[maxCombination - minCombination + 1];
        int index = 0;
        for (int eachCombination = minCombination; eachCombination < maxCombination + 1; eachCombination++) {
            Combinations[index] = eachCombination;
            index++;
        }
        roll(Combinations);
    }

    //Roll the dice & record combinations' frequecnies
    private void roll(Integer[] Combinations) {
        int randomIndex;

        //Reset all indexes
        frequencies = new Integer[Combinations.length];
        for (int i = 0; i < frequencies.length; i++) {
            frequencies[i] = 0;
        }

        //Save index frequencies
        Random rdmGenerator = new Random();
        for (int i = 0; i < ROLL_TIMES; i++) {
            randomIndex = rdmGenerator.nextInt(Combinations.length);
            frequencies[randomIndex] += 1;

        }
    }

    public String getDiceName() {
        return diceName;
    }

}
