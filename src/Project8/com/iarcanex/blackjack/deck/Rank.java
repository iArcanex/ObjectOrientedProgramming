package Project8.com.iarcanex.blackjack.deck;

/**Blackjack Project
 * CSci 2001-91
 */

/* Rank - enum
 * Values of ranks are here.
 * */

public enum Rank {

    TWO(2, "two"), THREE(3, "three"), FOUR(4, "four"), FIVE(5,"five"),
    SIX(6, "six"), SEVEN(7, "seven"), EIGHT(8,"eight"), NINE(9, "nine"),
    TEN(10, "ten"), JACK(10, "jack"), QUEEN(10, "queen"), KING(10, "king"), ACE(11, "ace");

    private int rankValue;
    private final String rankName;

    Rank(int value, String name) {

        rankValue = value;
        rankName = name;

    }

    public void setRankValue(int rankValue) {

        this.rankValue = rankValue;

    }

    public int getRankValue() {

        return rankValue;

    }

    public String getRankName() {

        return rankName;

    }
}
