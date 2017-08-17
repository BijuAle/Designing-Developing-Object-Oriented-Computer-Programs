/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardstacks;

import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Biju Ale
 */
public class HistoryCardStack extends LinkedList<CardStack> {

    Card moveDealtCard(String diceNotation, CardStackDealtCards csdc) {
        Card dealtCard = new Card();
        for (CardStack eachCardStack : this) {
            if (eachCardStack.getDiceNotation().equals(diceNotation)) {
                dealtCard = eachCardStack.getFirst();
                dealtCard.setCardState("DEALT");
                eachCardStack.removeFirst();
            }
        }
        csdc.add(dealtCard);
        return dealtCard;
    }

    void shuffleStack(String diceNotation) {
        for (CardStack eachCardStack : this) {
            if (eachCardStack.getDiceNotation().equals(diceNotation)) {
                Collections.shuffle(eachCardStack);
            }
        }
    }

    CardStack getFutureCards(String diceNotation) {
        for (CardStack eachCardStack : this) {
            if (eachCardStack.getDiceNotation().equals(diceNotation)) {
                return eachCardStack;
            }
        }
        return null;
    }
//
//    int getMaxFreq(String diceNotation) {
//        int max =0;
//        for (CardStack eachCardStack : this) {
//            if (eachCardStack.getDiceNotation().equals(diceNotation)) {
//                
//            }
//
//        }
//        return -1;
//    }
}
