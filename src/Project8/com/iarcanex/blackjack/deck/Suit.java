package Project8.com.iarcanex.blackjack.deck;

/**Blackjack Project
 * CSci 2001-91
 */

/* Suit - enum
 * Values for suits are here.
 * */

public enum Suit {

    HEARTS("hearts"), DIAMONDS("diamonds"), SPADES("spades"), CLUBS("clubs");

    Suit(String name) {

        suitName = name;

    }

    private final String suitName;


    public String getSuitName() {

        return suitName;

    }

}
