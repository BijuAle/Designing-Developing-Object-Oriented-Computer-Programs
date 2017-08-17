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
public class CardStackDealtCards extends ArrayList<Card> {

    ArrayList<Card> getDealtCards(String diceNotation) {
        ArrayList<Card> dealtCards = new ArrayList();
        for (Card eachCard : this) {
            if (eachCard.getDiceNotation().equals(diceNotation)) {
                dealtCards.add(eachCard);
            }
        }
        return dealtCards;
    }
}
