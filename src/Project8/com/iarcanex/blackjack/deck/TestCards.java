package Project8.com.iarcanex.blackjack.deck;

import java.util.ArrayList;
import java.util.Arrays;

/**Blackjack Project
 * CSci 2001-91
 */

/* Card Testing class
 * From Template, not much done with it.
 * */

public class TestCards {

    public static void main(String[] args) {

        Card c1 = new Card(Suit.SPADES, Rank.ACE);
        Card c2 = new Card(Suit.DIAMONDS, Rank.EIGHT);

        ArrayList<Card> deck = new ArrayList();

        Suit[] suitValues = Suit.values();

        // For each element in the suit array, we are
        // traversing the rank array. So Diamond 2, Diamond 3, Diamond 4, etc.
        for(Suit s: Suit.values()) {

            for(Rank r: Rank.values()) {

                Card c3 = new Card(s, r);
                deck.add(c3);

            }

        }

        System.out.println(Arrays.toString(deck.toArray()));

        int handTotal = c1.getRank().getRankValue() + c2.getRank().getRankValue();

        System.out.println("Current Hand Total: " + handTotal);

        if(c1.getRank().getRankValue() > c2.getRank().getRankValue()) {

            System.out.println("Card 1 is greater.");

        }
        else {

            System.out.println("Card 2 is greater.");

        }

        System.out.println(c1);

    }

}
