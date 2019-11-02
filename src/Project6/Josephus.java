package Project6;

import java.util.Arrays;
import java.util.Scanner;

/**Josephus Project
 * CSci 2001-91
 */

/* This class contains the logic for the Josephus problem.
*  It utilizes array traversals in order to set counters and boolean
*  values based on the information provided. Based on the requirements sheet
*  if 10 is entered for numPeople, 4 for killNum, and 3 for startingIndex,
*  the 7th index should be output. Tried to test this with online solvers made through python
*  and C++ to validate that it works, so hopefully there aren't any issues. */

/* I basically tried to make sure that the inputs would equal to what this calculator puts out:
*  http://webspace.ship.edu/deensley/mathdl/Joseph.html
*  It looks like it is working (make sure startingIndex is set to 0), but feel free to elaborate and compare both revisions.*/

public class Josephus {

    int numPeople, killNum, startingIndex, survivorIndex;
    boolean[] people;

    public Josephus(int numPeople, int killNum, int startingIndex) {

        this.numPeople = numPeople;
        this.killNum = killNum;
        this.startingIndex = startingIndex;

    }

    public void beginProblem() {

        System.out.println("Welcome to the circle of death!" +
                "\nLet's collect some information about this circle...");

        // If no tangible data is passed through the object,
        // it will ask the user for the information.
        if(numPeople == 0 && killNum == 0 && startingIndex == 0) {

            // This method collects user input and validates it.
            userInput();

        }

        // This method utilizes the three values required
        // to begin the problem.
        josephusCircle(numPeople, killNum, startingIndex);

    }

    public void userInput() {

        Scanner input = new Scanner(System.in);
        boolean awaitingResponse = true;

        while (awaitingResponse) {

            System.out.println("How many people will be in the circle?");
            numPeople = input.nextInt();

            if (numPeople <= 0) {

                System.out.println("Your answer must be greater than zero. Try again.");

            } else {

                awaitingResponse = false;

            }

        }

        awaitingResponse = true;

        while (awaitingResponse) {

            System.out.println("How many people should be counted, before one is dead? (i.e The kill number)");
            killNum = input.nextInt();

            if (killNum <= 0) {

                System.out.println("Your answer must be greater than zero. Try again.");

            } else {

                awaitingResponse = false;

            }

        }

        awaitingResponse = true;

        while (awaitingResponse) {

            System.out.println("Finally, at what index should the count start at?");
            startingIndex = input.nextInt();

            if (startingIndex < 0) {

                System.out.println("Your answer must be positive. Try again.");

            } else {

                awaitingResponse = false;

            }

        }

            System.out.printf("You have chosen %d people to be within the circle, %nwhere every %d count(s) someone will die. %nThe index begins at %d. %n", numPeople, killNum, startingIndex);
            System.out.println("Calculating. . .");

    }

    public void josephusCircle(int numberOfPeople, int killedNumber, int startingArrayIndex) {

        people = new boolean[numberOfPeople];
        Arrays.fill(people, true);
        int livingCounter = 0;
        int deathCount = 0;

        System.out.println("Running array traversal... Starting at: " + startingArrayIndex);

        for (int i = startingArrayIndex; i <= people.length; i++) {

            // For Debugging
            System.out.println("\nCurrently on index: " + i + "\nOr, person number " + (i + 1) + "\nThis index is: " + people[i]);

            // If the index is true, it'll put the livingCounter up by one, as the index is living.
            // Afterwards if livingCounter is equivalent to killedNumber, index dies and deathCount goes up by one.
            if(people[i] == true) {

                livingCounter++;
                // For Debugging
                System.out.println("People Counter is incremented. Currently at: " + livingCounter);

                if(livingCounter >= killedNumber) {

                    people[i] = false;
                    deathCount++;
                    livingCounter = 0;
                    // For Debugging
                    System.out.println("livingCounter is equal to the killNumber, time to set index " + i + " to false.\n" +
                            "Index " + i + " killed. Resetting livingCounter to 0.\n");

                }

            }

            // If index is false, or dead,  it'll check to see if the amount of dead people
            // lines up with the length of the index - the last living person
            // If it does, loop is stopped as i is set to finish it.
            // Otherwise if i is at the end of the index, it'll reset the loop.
            else if (people[i] == false) {

                // For debugging
                System.out.println("Index " + i + ", or person " + (i+1) + " is already dead.\n");

            }

            if(deathCount == (people.length - 1)) {

                i = people.length;

            }
            //If array has reached its end, this will reset it.
            else if (i == (people.length - 1)) {

                i = -1;

            }


        }

        System.out.printf("You have chosen %d people to be within the circle, %nwhere every %d count(s) someone will die. %nThe index begins at %d. %n", numberOfPeople, killedNumber, startingArrayIndex);

        for (int i = 0; i < people.length; i++) {

            if(people[i] == true) {

                survivorIndex = i;
                System.out.println("The survivor is at index: " + i + ", or person " + (i+1));

            }

        }


    }



}
