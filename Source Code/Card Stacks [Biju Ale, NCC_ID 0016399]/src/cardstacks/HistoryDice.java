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
public class HistoryDice extends ArrayList<Dice> {

    boolean addToDiceHistory(Dice dice) {
        for (Dice eachDice : this) {
            if (eachDice.getDiceName().equals(dice.getDiceName())) {
                return false;
            }
        }
        return add(dice);
    }
}
