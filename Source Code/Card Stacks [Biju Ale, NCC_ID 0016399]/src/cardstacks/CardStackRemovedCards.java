/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardstacks;

import java.util.ArrayList;

/**
 *
 * @author Biju Ale
 */
public class CardStackRemovedCards extends ArrayList<Card> {

    void displayRemovedCards(String diceNotation) {
        for (Card eachCard : this) {
            if (eachCard.getDiceNotation().equals(diceNotation)) {
                System.out.println("" + eachCard.getDiceNotation() + " " + eachCard.getNumber());
            }
        }

    }

    ArrayList<Card> getRemovedCards(String diceNotation) {
        ArrayList<Card> removedCards = new ArrayList();
        for (Card eachCard : this) {
            if (eachCard.getDiceNotation().equals(diceNotation)) {
                removedCards.add(eachCard);
            }
        }
        return removedCards;
    }

}
