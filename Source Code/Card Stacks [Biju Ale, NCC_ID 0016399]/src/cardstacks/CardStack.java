/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardstacks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Biju Ale
 */
public class CardStack extends LinkedList<Card> {

    private String diceNotation;
    private Integer max;

    CardStack(String diceNotation, Integer[] Combinations, Integer[] frequencies) {
        this.diceNotation = diceNotation;
        populateCardStack(Combinations, frequencies);
    }

    CardStack(String diceNotation) {
        this.diceNotation = diceNotation;
    }

    String getDiceNotation() {
        return diceNotation;
    }

    void populateCardStack(Integer[] Combinations, Integer[] frequencies) {
        for (int i = 0; i < Combinations.length; i++) {
            add(new Card(Combinations[i], frequencies[i], diceNotation, "In Stack"));
        }
    }

    void displayCombinationFrequencies() {
        String a = "Combinations & Frequencies:";
        int sum = 0;
        double percentsum = 0d;

        for (Card eachCard : this) {
            a = a + ("\nCard: " + eachCard.getNumber() + "\tFrequency: " + eachCard.getFrequency() + "\tPercentage: " + ((double) eachCard.getFrequency() / 100d) + "%" + "\tState " + eachCard.getCardState());
            sum += eachCard.getFrequency();
            percentsum += ((double) eachCard.getFrequency() / 100d);
        }
        UserInterface.setTextNotification(a + "\nFrequency Sum: " + sum + "\nPercentage Sum: " + percentsum + "\n\n");
    }

    void removeCard(int toRemove, CardStackRemovedCards rc) {
        Iterator itr = this.iterator();
        ArrayList<Card> temp = new ArrayList();
        while (itr.hasNext()) {
            for (int i = 0; i < toRemove; i++) {
                rc.add((Card) itr.next());
                temp.add((Card) itr.next());
            }
        }
        removeAll(temp);
    }
}
