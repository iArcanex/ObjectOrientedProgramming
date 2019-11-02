package Project8.com.iarcanex.blackjack.game;

import Project8.com.iarcanex.blackjack.deck.Deck;
import Project8.com.iarcanex.blackjack.player.Player;
import java.util.Scanner;

/**Blackjack Project
 * CSci 2001-91
 */

/* Game - Core of Blackjack
 * This project was very fun to do. This class contains all the essential logic, methods,
 * and game loops to run the console based game. I haven't played Blackjack before so this
 * was a learning experience. With that being said, if any bugs are experienced, it is possible
 * that they are intentional and I did not implement or understand a rule correctly, so please
 * inform me and I can get it adjusted/resubmitted. Biggest challenge was the logic and ace. Ultimately
 * my solution to ace was the change the rank values of the cards depending on if the hand value was
 * greater than 21 or if it was at or less than 11. More details in the comments below.
 * As for the challenges added: Added a way to keep playing and I kept track of wins and losses.
 * Didn't want to add bets as I didn't have enough time to program the logic for that, but
 * could be a good way to fully flesh out this program.
 * */

public class Game {

    private int totalWins = 0, totalLosses = 0;
    private boolean continuePlaying = true, firstGame = true, isBusted = false,
            hasAce = false, firstRound = true, choseStand = false, gameEnd = false;
    private Scanner input;
    private Player player, dealer;
    private Deck deck;

    public Game() {

        input = new Scanner(System.in);
        deck = new Deck();
        player = new Player("player");
        dealer = new Player("dealer");

        startNewGame();

    }

    // Displays game messages which alternate depending on first load or not.
    private void gameMessage(boolean firstGame) {

        if(firstGame) {

            System.out.println("+=-=-=-=-=-=-=-=-=-=-=-=-+");
            System.out.println("Welcome to Blackjack!");
            System.out.println("It appears to be your first");
            System.out.println("game. Would you like to know");
            System.out.println("the info of the game? Y/N");
            System.out.println("    0 = YES     1 = NO    ");
            System.out.println("+=-=-=-=-=-=-=-=-=-=-=-=-+");

            if(acquireAnswer() == 0) {

                System.out.println("+=-=-=-=-=-=-=-=-=-=-=-=-+");
                System.out.println("1) This game of blackjack does");
                System.out.println("does not include betting.");
                System.out.println("Therefore, you will not be");
                System.out.println("able to establish insurances.");
                System.out.println("+=-=-=-=-=-=-=-=-=-=-=-=-+");
                System.out.println("2) The deck numbers 52 cards.");
                System.out.println("At the start, you'll be given");
                System.out.println("two cards. You may stand and");
                System.out.println("allow the dealer to hit and/or");
                System.out.println("flip their hidden card over.");
                System.out.println("If you decide to keep hitting,");
                System.out.println("You may continue so long as");
                System.out.println("You do not go over 21.");
                System.out.println("+=-=-=-=-=-=-=-=-=-=-=-=-+");
                System.out.println("3) If there are two Aces,");
                System.out.println("They are considered in such");
                System.out.println("a manner: if the total hand");
                System.out.println("value is greater than 21, you");
                System.out.println("will have the ace adjusted to 1");
                System.out.println("otherwise if your hand value is 11");
                System.out.println("or less, ace remains at 11.");
                System.out.println("This is dynamic and will account for");
                System.out.println("one or all the aces depending on");
                System.out.println("the hand value. YOU are not able to");
                System.out.println("specify if you'd like to keep the");
                System.out.println("values at 1 or 11, compared to a");
                System.out.println("traditional game. Good luck!");

            }

            else {

                System.out.println("Very well! Skipping the rules...");

            }

            this.firstGame = false;

            System.out.println("+=-=-=-=-=-=-=-=-=-=-=-=-+");
            System.out.println("52 Cards are apart of the deck!");
            System.out.println("The dealer has begun dealing cards!");
            System.out.println("+=-=-=-=-=-=-=-=-=-=-=-=-+");


        }

        else {

            System.out.println("WINS: " + totalWins + "      LOSSES: " + totalLosses);
            System.out.println("+=-=-=-=-=-=-=-=-=-=-=-=-+");
            System.out.println("Would you like to begin a new game?");
            System.out.println("    0 = YES     1 = NO    ");
            System.out.println("+=-=-=-=-=-=-=-=-=-=-=-=-+");

            if(acquireAnswer() == 0) {

                System.out.println("Very well! New game started!");
                System.out.println("+=-=-=-=-=-=-=-=-=-=-=-=-+");

                resetGame();

            }

            else {

                System.out.println("Very well! Come back later!");
                this.continuePlaying = false;

            }

        }


    }

    // Resets and clears deck/hands + flags.
    private void resetGame() {

      // Reset Deck on new game
        deck.getDeck().clear();
        deck.loadDeck();
        deck.shuffleDeck();
        player.getHand().clear();
        dealer.getHand().clear();

      // Resetting boolean flags
        this.continuePlaying = true;
        this.firstRound = true;
        this.choseStand = false;
        this.gameEnd = false;
        this.isBusted = false;
        this.hasAce = false;

    }

    // game method with nested while loops. The inner one
    // looks at the current game and whether it has ended or not.
    // The outer one looks at if the players wishes to keep playing.
    // Most logic in this method is called from other methods.
    private void startNewGame() {

        while(continuePlaying) {

            gameMessage(firstGame);

            while (!gameEnd) {

                if (firstRound) {

                    firstDeal();

                    gameEndLogic();
                    if(!gameEnd) {

                        printPlayersHand();
                        playerHit();

                    }

                    firstRound = false;

                }

                // After first deal, player will be directed to continue hitting (if not standing).
                else {

                    gameEndLogic();
                    if(!gameEnd) {

                        printPlayersHand();
                        playerHit();

                    }

                }

            }

        }

    }

    private void firstDeal() {

        // This is supposed to mimic how a dealer would deal their cards.
        // Player gets the first one, second to the dealer faced down, third to the player
        // and a fourth one to the player.

        // Dealer
        dealer.addCardTohand(deck.getDeck().get(1));
        System.out.println("Dealer deals their self a card, it has been placed face down.");
        dealer.addCardTohand(deck.getDeck().get(3));
        System.out.println("Dealer deals their self another card, it is a " + deck.getDeck().get(3).getRank().getRankName() + " of " + deck.getDeck().get(3).getSuit().getSuitName());

        // Player
        System.out.println("The cards that are dealt to you are:");
        System.out.println("FIRST CARD: " + deck.getDeck().get(0).getRank().getRankName() + " of " + deck.getDeck().get(0).getSuit().getSuitName());
        System.out.println("SECOND CARD: " + deck.getDeck().get(2).getRank().getRankName() + " of " + deck.getDeck().get(2).getSuit().getSuitName());
        System.out.println("+=-=-=-=-=-=-=-=-=-=-=-=-+");
        player.addCardTohand(deck.getDeck().get(0));
        player.addCardTohand(deck.getDeck().get(2));

        // Remove cards from deck (removes first four)
        // Will always be zero as the array re-adjusts.
        deck.getDeck().remove(0);
        deck.getDeck().remove(0);
        deck.getDeck().remove(0);
        deck.getDeck().remove(0);

    }

    private void gameEndLogic() {

        // Logic checks for the game. These are where a lot of information
        // is determined as best aligned to the rules of blackjack.
        // (or that I know of, before this I didn't know anything about the game :] )

        // IF the dealer busts, it will first check if the player also did.
        // If the player will also bust, they will lose. Otherwise, the player wins.
        if(didPlayerBust(dealer)) {

            // If dealer busts and you do, it is your loss.
            if(didPlayerBust(player)) {

                System.out.println("+=-=-=-=-=-=-=-=-=-=-=-=-+");
                System.out.println("You bit off more than you could chew!");
                System.out.println("Better luck next time.");
                System.out.println();

                totalLosses += 1;
                System.out.println("+-=-=- Final Card Stats -=-=-+");

            }

            else {

                System.out.println("+=-=-=-=-=-=-=-=-=-=-=-=-+");
                System.out.println("You've won! The dealer has busted.");
                System.out.println();

                totalWins += 1;
                System.out.println("+-=-=- Final Card Stats -=-=-+");

            }

            gameEnd = true;

        }

        // IF the dealer doesn't bust, it will then check the player.
        if(didPlayerBust(player)) {

            System.out.println("You've lost. Player has busted.");

            totalLosses += 1;
            System.out.println("+-=-=- Final Card Stats -=-=-+");
            gameEnd = true;

        }

        // Here if both haven't busted, then it'll check the values each holds.
        // If both have the same, it'll result in a tie.
        if(totalHandValue(dealer) == 21 && choseStand)  {

            if(totalHandValue(player) == 21) {

                System.out.println("It's a tie... Leaving all scott free...");

                System.out.println("+-=-=- Final Card Stats -=-=-+");

            }

            else {

                System.out.println("You've lost! The dealer's hand value is 21!");

                totalLosses += 1;
                System.out.println("+-=-=- Final Card Stats -=-=-+");

            }

            isBusted = false;
            gameEnd = true;

        }

        // If the player's hand value is 21, they win. The dealer is
        // favored in certain cases within this game, so I usually have the
        // dealer checked first.
        if(totalHandValue(player) == 21) {

            System.out.println("BLACKJACK: You've won! Your hand value is 21!");

            totalWins += 1;
            System.out.println("+-=-=- Final Card Stats -=-=-+");
            isBusted = false;
            gameEnd = true;

        }

        // This is a specific check that keeps in mind if a player has an ACE.
        // Basically does the same thing as before, but it validates that the values of
        // ACE are adjusted before determining if a player has busted or not.
        if(playerHasAce(dealer) && totalHandValue(dealer) > 21) {

            if(playerHasAce(player) && totalHandValue(player) > 21) {

                System.out.println("Both dealer and player have busted, therefore it is your loss.");

                totalLosses += 1;
                System.out.println("+-=-=- Final Card Stats -=-=-+");
                isBusted = true;
                gameEnd = true;
            }

            else {

                System.out.println("You've won! The dealer has busted.");

                totalWins += 1;
                System.out.println("+-=-=- Final Card Stats -=-=-+");
                isBusted = true;
                gameEnd = true;

            }

        }

        if(playerHasAce(player) && totalHandValue(player) > 21) {

            System.out.println("You've lost. Player has busted.");

            totalLosses += 1;
            System.out.println("+-=-=- Final Card Stats -=-=-+");
            isBusted = true;
            gameEnd = true;
        }

        // If both haven't busted, let's check to see who has the greater hand...
        if((totalHandValue(dealer) > totalHandValue(player)) && !firstRound && !isBusted && choseStand) {

            if(totalHandValue(dealer) <= 21) {

                System.out.println("Dealer has greater hand value...");
                System.out.println("+-=-=- Final Card Stats -=-=-+");
                totalLosses += 1;
                gameEnd = true;

            }

        }

        else if((totalHandValue(player) > totalHandValue(dealer)) && !firstRound && !isBusted && choseStand) {

            if(totalHandValue(player) <= 21 && !(totalHandValue(dealer) <= 21)) {

                System.out.println("Player has greater hand value...");
                System.out.println("+-=-=- Final Card Stats -=-=-+");
                totalWins += 1;
                gameEnd = true;

            }


        }

        if(gameEnd) {

            printPlayersHand();

        }


    }

    private void printPlayersHand() {

        // Dealer starts at one card shown as we cannot see their full hand.
        // Unless the players have standed, busted, or game has ended then all info will be shown.

       // Dealer's Hand
        if(choseStand || gameEnd) {

            System.out.println("THE DEALER'S HAND: - VALUE: [" + totalHandValue(dealer) + "]");
            System.out.println(" - " + dealer.getHand().get(0).getRank().getRankName() + " of " +  dealer.getHand().get(0).getSuit().getSuitName());

        }

        else {

            System.out.println("THE DEALER'S HAND: - APPARENT VALUE: [" + (totalHandValue(dealer) - dealer.getHand().get(0).getRank().getRankValue()) + "]");
            System.out.println(" - A Faced Down Card");

        }

        for (int i = 1; i < dealer.getHand().size(); i++) {

            System.out.println(" - " + dealer.getHand().get(i).getRank().getRankName() + " of " +  dealer.getHand().get(i).getSuit().getSuitName());

        }

       // Player's Hand
        System.out.println("YOUR CURRENT HAND: - VALUE: [" + totalHandValue(player) + "]");

        for (int i = 0; i < player.getHand().size(); i++) {

            System.out.println(" - " + player.getHand().get(i).getRank().getRankName() + " of " +  player.getHand().get(i).getSuit().getSuitName());

        }

        System.out.println("+=-=-=-=-=-=-=-=-=-=-=-=-+");


    }

    // Method takes an input. If yes, it will ask if you wish to hit or stand.
    // If hit, it will pick the top of the deck, in this case it is index of 0.
    // If stand, flag will be triggered to keep stand ongoing and dealer will
    // keep drawing until bust or win.
    private void playerHit() {

        if(!choseStand) {

            System.out.println("Would you like to hit?");
            System.out.println("  0 = YES     1 = NO  ");
            System.out.println("+=-=-=-=-=-=-=-=-=-=-=-=-+");

            if(acquireAnswer() == 0) {

                System.out.println("You have chosen to hit.");
                System.out.println("The card that was dealt is:");
                System.out.println("CARD: " + deck.getDeck().get(0).getRank().getRankName() + " of " + deck.getDeck().get(0).getSuit().getSuitName());
                System.out.println("+=-=-=-=-=-=-=-=-=-=-=-=-+");

                // Add card to player hand and remove from dealer's deck.
                player.addCardTohand(deck.getDeck().get(0));
                deck.getDeck().remove(0);
                deck.getDeck().remove(0);

            }

            else {

                choseStand = true;
                System.out.println("You have chosen to stand.");

                //System.out.println("The dealer reveals their hidden card.");
                //System.out.println("It is a " + dealer.getHand().get(0).getRank().getRankName() + " of " +  dealer.getHand().get(0).getSuit().getSuitName());

            }

        }

        else {

            System.out.println("Dealer deals their self a card, it is a " + deck.getDeck().get(0).getRank().getRankName() + " of " +  deck.getDeck().get(0).getSuit().getSuitName());
            dealer.addCardTohand(deck.getDeck().get(0));
            deck.getDeck().remove(0);

        }

    }

    // Simple method to validate the answer.
    private int acquireAnswer() {

        int answer = input.nextInt();
        boolean awaitingAnswer = true;

        while(awaitingAnswer) {

            if (answer == 0 || answer == 1) {

                awaitingAnswer = false;

            }

            else {

                System.out.println("The answer you've entered is not correct.");
                System.out.println("Please re-enter a 0 OR 1.");

            }

        }

        return answer;

    }

    // Solution to Ace problem that I came to was to change the value
    // of the card's rankValue. This will modify depending on the situation.
    private boolean playerHasAce(Player player) {

        if(player.getHand().size() > 0) {

            // How this loop will work is it will traverse through all the player's
            // cards in their deck. If it finds an ace it'll check to see if their
            // total hand value is greater than 11. If it is, then it will adjust the
            // value of the rank to 1. IF there is another ACE in the hand it'll will
            // also check to see if the value is above 11. In (all?) cases if a player
            // has two ACEs they can qualify to have an ace valued at 1 and another at 11
            // or have both valued at 1. Here the default outcome is dependent on the value
            // of the hand the player has. IF there are two aces, one will be valued at one
            // and the other at eleven. In the case you have two aces PLUS other cards (that cause it go over 21)
            // both can be valued at one. If I was to continue building this project I would give the player
            // the option to have aces valued together at 11 or/and 2.
            for (int i = 0; i < player.getHand().size(); i++) {

                // Feel free to comment out debugs if they're annoying.
                if(player.getHand().get(i).getRank().getRankName().equals("ace")) {

                    hasAce = true;

                    if(totalHandValue(player) > 21) {

                        player.getHand().get(i).getRank().setRankValue(1);
                        System.out.println("DEBUG: ACE is in hand of " + player.getID() + " and is over 21! Keeping/Changing value to one.");

                    }

                    else if (totalHandValue(player) <= 11 /*&& !(totalHandValue(player) + 10 > 21)*/){

                        player.getHand().get(i).getRank().setRankValue(11);
                        System.out.println("DEBUG: ACE is in hand of " + player.getID() + " and is less than 11! Keeping/Changing value to eleven.");

                    }

                }

                else {

                    System.out.println("DEBUG: " + player.getHand().get(i).getRank().getRankName() + " is not an ACE in hand of " + player.getID() + ".");

                }

            }

        }

        else {

            System.out.println("ACE CHECK: There are no cards in hand of " + player.getID() + ".");

        }

        return hasAce;

    }

    // Checks to see if player hand value would lead player to bust.
    // Also utilizes ace check to understand the information from that as well.
    // isBusted flag will be used in gameEnd Logic
    private boolean didPlayerBust(Player player) {

        int totalHandValue = 0;

        // Checks to see what all rank values are in players
        // hand and if they exceed 21, the player has busted.
        if(player.getHand().size() > 0) {

            for (int i = 0; i < player.getHand().size(); i++) {

                totalHandValue += player.getHand().get(i).getRank().getRankValue();

            }

            if(totalHandValue > 21) {

                if(playerHasAce(player)) {

                    System.out.println("DEBUG: ACE check passed.");

                }

                else {

                    isBusted = true;

                }

            }

            else {

                isBusted = false;

            }

        }

        else {

            System.out.println("BUST CHECK: There are no cards in this player's hand!");

        }

        return isBusted;

    }

    // Simple method to calculate total hand value.
    private int totalHandValue(Player player) {

        int handValue = 0;

        for (int i = 0; i < player.getHand().size(); i++) {

            handValue += player.getHand().get(i).getRank().getRankValue();

        }

        return handValue;

    }

}
