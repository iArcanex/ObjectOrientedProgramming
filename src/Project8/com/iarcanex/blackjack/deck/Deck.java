package Project8.com.iarcanex.blackjack.deck;

import java.util.ArrayList;

/**Blackjack Project
 * CSci 2001-91
 */

/* Deck
* From template/class work. Contains methods to load and shuffle the deck.
* */

public class Deck {

    ArrayList<Card> deck;

    public Deck() {

        deck = new ArrayList<>();
        loadDeck();
        shuffleDeck();

        // For Debugging
        //System.out.println(Arrays.toString(deck.toArray()));

    }

    public void loadDeck() {

        // For each element in the suit array, we are
        // traversing the rank array. So Diamond 2, Diamond 3, Diamond 4, etc.
        for(Suit s: Suit.values()) {

            for(Rank r: Rank.values()) {

                deck.add(new Card(s, r));

            }

        }

    }

    public void shuffleDeck() {

        for(int i = 0; i < deck.size() - 1; i++) {

            swapCardLocation(i);

        }

    }

    // Fisher-Yates Algorithm
    public void swapCardLocation(int index) {

        Card tempCard = deck.get(index);
        // this min will ensure that previously switched cards, are not apart of the random
        // numbers chosen.
        int max = deck.size();
        int min = index + 1;

        int rand = (int)(Math.random()*(max-min) + min);
        deck.set(index, deck.get(rand));
        deck.set(rand, tempCard);



    }

    public ArrayList<Card> getDeck() {

        return deck;

    }

    public void setDeck(ArrayList<Card> deck) {

        this.deck = deck;

    }


}
