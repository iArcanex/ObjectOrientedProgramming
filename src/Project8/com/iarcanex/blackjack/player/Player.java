package Project8.com.iarcanex.blackjack.player;

import Project8.com.iarcanex.blackjack.deck.Card;

import java.util.ArrayList;

/**Blackjack Project
 * CSci 2001-91
 */

/* Player
 * From template, added ID to help with identification during print lns.
 * */

public class Player {

    private ArrayList<Card> hand;
    private String ID;

    public Player(String playerName){

        this.ID = playerName;
        hand=new ArrayList<>();

    }

    public void addCardTohand(Card c){

        hand.add(c);

    }

    public String getID() {

        return ID;

    }

    public void setID(String ID) {

        this.ID = ID;

    }

    public void setHand(ArrayList<Card> hand) {

        this.hand = hand;

    }

    public ArrayList<Card> getHand() {

        return hand;

    }

}
