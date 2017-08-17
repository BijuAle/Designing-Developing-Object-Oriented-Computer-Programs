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
public interface DrawActionListener {

    public void sendDrawCommand(int ACTION_DRAW);

    public void sendAllDealtCards(ArrayList allDealtCards);

    public void sendSingleDealtCard(Card singleDealtCard);

    public void sendRemovedCards(ArrayList allRemovedCards);

    public void sendFutureCards(CardStack futureCards);
}
