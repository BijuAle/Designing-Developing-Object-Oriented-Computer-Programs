/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardstacks;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Biju Ale
 */
public class UserInterface extends JFrame implements ActionListener {

    private JLabel lblEnterNotation;
    private JTextField txtDiceNotation;
    private static JTextArea txtNotification;
    private JButton btnSubmit, btnFutureCards, btnRemoved, btnDealt, btnShuffle, btnGetCard;
    private JPanel pnlUserInput, pnlCommands, pnlNotification;

    NotationReader nreader;
    HistoryDice historyDice;
    CardStack cardStack;
    HistoryCardStack historyCardStack;
    CardStackRemovedCards cardStackRemovedCards;
    CardStackDealtCards cardStackDealtCards;
    Graphics g;
    static DrawActionListener drawActionListener;
    static Canvas canvas;

    private UserInterface() {

        //Setting up Frame Properties
        setSize(800, 850);
        setTitle("Card Stacks - authored by Biju Ale");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Stting up Container
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.setBackground(Color.decode("#F7E3CB"));

        //Instantiating components at NORTH (for input)
        lblEnterNotation = new JLabel("Enter dice notation");
        lblEnterNotation.setFont(new Font("Times New Roman", Font.BOLD, 20));

        txtDiceNotation = new JTextField(8);
        txtDiceNotation.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.BLUE), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        txtDiceNotation.setPreferredSize(new Dimension(30, 30));
        txtDiceNotation.setFont(new Font("Times New Roman", Font.BOLD, 20));

        btnSubmit = new JButton("SUBMIT");
        btnSubmit.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnSubmit.setBackground(Color.DARK_GRAY);
        btnSubmit.setForeground(Color.LIGHT_GRAY);

        //Instantiating components at WEST (for commands)
        btnFutureCards = new MyButton("PEEK FUTURE CARDS");
        btnRemoved = new MyButton("PEEK REMOVED CARD");
        btnDealt = new MyButton("PEEK DEALT CARDS");
        btnShuffle = new MyButton("SHUFFLE");
        btnGetCard = new MyButton("GET CARD");

        //Instantiating panels for input, commands & notifciation
        pnlUserInput = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlUserInput.setBackground(Color.decode("#1DE9B6"));
        pnlCommands = new JPanel(new GridLayout(5, 1));
        pnlNotification = new JPanel(new GridLayout(1, 1));

        //Adding all components to input panel
        pnlUserInput.add(lblEnterNotation);
        pnlUserInput.add(txtDiceNotation);
        pnlUserInput.add(btnSubmit);

        //Adding all components to commands panel
        pnlCommands.add(btnGetCard);
        pnlCommands.add(btnShuffle);
        pnlCommands.add(btnDealt);
        pnlCommands.add(btnRemoved);
        pnlCommands.add(btnFutureCards);

        //Adding notification area
        txtNotification = new JTextArea(7, 100);
        txtNotification.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtNotification.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        txtNotification.setBackground(Color.decode("#1DE9B6"));
        JScrollPane txtNotificationScroll = new JScrollPane(txtNotification);

        c.add(pnlUserInput, BorderLayout.PAGE_START);
        c.add(pnlCommands, BorderLayout.LINE_START);
        c.add(txtNotificationScroll, BorderLayout.PAGE_END);

        //Adding action listener to all buttons
        btnSubmit.addActionListener(this);
        btnShuffle.addActionListener(this);
        btnRemoved.addActionListener(this);
        btnFutureCards.addActionListener(this);
        btnGetCard.addActionListener(this);
        btnDealt.addActionListener(this);

        //Initialize all stacks, dice & cardstack history
        historyDice = new HistoryDice();
        cardStackRemovedCards = new CardStackRemovedCards();
        cardStackDealtCards = new CardStackDealtCards();
        historyCardStack = new HistoryCardStack();

        //Instantiating canvas
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(2000, 2000));
        canvas.setBackground(Color.decode("#F7E3CB"));
        JScrollPane canvasScroll = new JScrollPane(canvas);
        c.add(canvasScroll, BorderLayout.CENTER);

        drawActionListener = canvas;
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object Source = (Object) e.getSource();

        if (Source.equals(btnSubmit)) {
            String diceNotation = txtDiceNotation.getText();
            nreader = new NotationReader(diceNotation);
            Dice dice = new Dice(diceNotation, nreader.getNumDices(), nreader.getNumDices() * nreader.getNumFaces());
            if (historyDice.addToDiceHistory(dice)) {
                cardStack = new CardStack(diceNotation, dice.getCombinations(), dice.getFrequencies());
                Collections.shuffle(cardStack);
                if (nreader.getToRemove() > 0) {
                    cardStack.removeCard(nreader.getToRemove(), cardStackRemovedCards);
                }
                historyCardStack.add(cardStack);
                txtNotification.setText("\n" + "Rolling...\n" + diceNotation + "'s combinations & frequencies added to card stack.");
                btnGetCard.doClick();
            } else {
                txtNotification.setText("\n" + "Already rolled dice - " + diceNotation + "\nGetting Card...");
                btnGetCard.doClick();
            }
        } else if (Source.equals(btnGetCard)) {
            canvas.sendSingleDealtCard(historyCardStack.moveDealtCard(txtDiceNotation.getText(), cardStackDealtCards));
            canvas.ACTION_DRAW = Canvas.DRAW_FOR_GET_CARD;
        } else if (Source.equals(btnDealt)) {
            canvas.sendAllDealtCards(cardStackDealtCards.getDealtCards(txtDiceNotation.getText()));
            canvas.ACTION_DRAW = Canvas.DRAW_FOR_DEALT_CARD;
        } else if (Source.equals(btnShuffle)) {
            historyCardStack.shuffleStack(txtDiceNotation.getText());
        } else if (Source.equals(btnRemoved)) {
            canvas.sendRemovedCards(cardStackRemovedCards.getRemovedCards(txtDiceNotation.getText()));
            canvas.ACTION_DRAW = Canvas.DRAW_FOR_REMOVED;
        } else if (Source.equals(btnFutureCards)) {
            canvas.sendFutureCards(historyCardStack.getFutureCards(txtDiceNotation.getText()));
            canvas.ACTION_DRAW = Canvas.DRAW_FOR_FUTURE;
        }
    }

    public class MyButton extends JButton {
        MyButton(String text) {
            setText(text);
            setFont(new Font("Century Gothic", Font.BOLD, 14));
            setBackground(Color.decode("#B38B6D"));
            setFocusPainted(false);
            setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        }
    }

    static void setTextNotification(String text) {
        txtNotification.setText("\n" + text);
    }

    static String getTextNotification() {
        return txtNotification.getText();
    }

    public static void main(String[] args) {
        new UserInterface().setVisible(true);
    }
}
