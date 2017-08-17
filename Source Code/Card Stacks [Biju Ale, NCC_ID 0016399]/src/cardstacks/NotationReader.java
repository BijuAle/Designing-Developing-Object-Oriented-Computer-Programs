/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardstacks;

import javax.swing.JOptionPane;

/**
 *
 * @author Biju Ale
 */
public class NotationReader {

    private int numDices = 0, numFaces = 0, toRemove = 0;

    public int getNumDices() {
        return numDices;
    }

    public int getNumFaces() {
        return numFaces;
    }
    public int getToRemove(){
        return toRemove;
    }

    NotationReader(String diceNotation) {
        parseDiceNotation(diceNotation);
    }

    void parseDiceNotation(String diceNotation) {

        try {
            String[] whole;
            String[] whole2;

            whole = diceNotation.split("d");
            this.numDices = Integer.parseInt(whole[0]);
            String part2 = whole[1];

            if (part2.contains("-")) {
                whole2 = part2.split("-");
                this.numFaces = Integer.parseInt(whole2[0]);
                this.toRemove = Integer.parseInt(whole2[1]);

            } else {
                this.numFaces = Integer.parseInt(whole[1]);
            }
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, "Dice Notation format Incorrect!", "Invalid Dice Notation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
