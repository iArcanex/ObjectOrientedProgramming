package Project4;

import java.util.Scanner;

/**Week 4
 * CSci 2001-91
 */

/* 4.23 - Two Largest Numbers
* Class that asks for 10 ints then evaluates
* the top two largest ints */

public class TwoLargestNumbers {

    public static void main(String args[]) {

        int counter, number, large1 = 0, large2 = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter an integer when prompted:");

        for(counter = 0; counter <= 10 ; counter++) {

            System.out.println("Enter an integer:");
            number = scanner.nextInt();

            // Check and assign largest ints
            if(number > large1) {

                large1 = number;

            }
            else if(number > large2) {

                large2 = number;

            }
        }

        System.out.println("Largest int was: " + large1 + "\nNext largest int was: " + large2);


    }


}
