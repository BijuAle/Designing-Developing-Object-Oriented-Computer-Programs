/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardstacks;

/**
 *
 * @author Biju Ale
 */
public class Card {

    private String diceNotation;
    private String cardState;
    private int number;
    private int frequency;

    Card(int number, int frequency, String diceNotation, String cardState) {
        this.number = number;
        this.frequency = frequency;
        this.cardState = "IN STACK";
        this.diceNotation = diceNotation;
    }

    Card() {
    }

    public String getDiceNotation() {
        return diceNotation;
    }

    public void setDiceNotation(String diceNotation) {
        this.diceNotation = diceNotation;
    }

    public String getCardState() {
        return cardState;
    }

    public void setCardState(String cardState) {
        this.cardState = cardState;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
