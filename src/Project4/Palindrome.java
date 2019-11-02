package Project4;

import java.util.Scanner;

/**Week 4
 * CSci 2001-91
 */

/* 4.30 - Palindrome
* Program that reads a five digit int and sees
* if it can be read backwards or not. Displays an error
* if the int is too large or small.*/

public class Palindrome {

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        int n1 = 0, d1, d2, d4, d5, errorBounds = 0;

        while(errorBounds != 5) {

            System.out.println("Please enter a five digit integer:");
            n1 = scanner.nextInt();

            if(n1 >= 10000) {
                errorBounds = 5;
            }
            else {

                System.out.println("The number provided is not a five digit integer.");

            }

        }

        // Calculating the numbers
        // You can create a loop and find string length to make it easier, but I think the exercise wants us to do it mathematically.

        d1 = n1 / 10000;
        d2 = (n1 % 10000) / 1000;
        d4 = (n1 % 100) / 10;
        d5 = n1 % 10;

        // Checks first digit with fifth, then the second with the fourth.

        if(d1 == d5) {

            if(d2 == d4) {

                System.out.println("Your number was: " + n1 + "\nIt is a palindrome.");

            }

        }

        else {

            System.out.println("Your number was: " + n1 + "\nIt is not a palindrome.");

        }



    }

}
