package Project4;

import java.util.Scanner;

/**Week 4
 * CSci 2001-91
 */

/* 5.24/5.25 Diamonds
* Prints a diamond utilizing repetition with print
* statements that contain an asterisk.
* (5.25) Reads an odd number from 1 to 19
* to specify the number rows in the diamond. */

public class Diamonds {

    public static void main(String args[]) {

        int star, space, rowNum = 0, row = 0, errorBounds = 0;
        Scanner scanner = new Scanner(System.in);

        while (errorBounds != 1) {

            System.out.println("Please enter an odd number in the range of 1 to 19: ");
            rowNum = scanner.nextInt();

            if (rowNum < 1 || rowNum > 19) {

                System.out.println("That is not an expected answer.");

            } else if ((rowNum % 2) == 0) {

                System.out.println("That is not an expected answer.");

            } else {

                errorBounds = 1;

            }

        }

        // Utilizes rowNum within the nested loops to establish how many
        // asterisks and spaces to print

        for(row = 0; row <= rowNum; row++) {

            // This loop looks at how many spaces should be printed before the asterisk
            for (space = 1; space <= (rowNum - row); space++)

                System.out.print(" ");

            // This loop calculates the amount of asterisks based off of the current row
            for(star = 1; star <= (2 * row) - 1; star++)

                System.out.print("*");
                System.out.println("");


        }

        for(row = rowNum - 1; row >= 1; row--) {

            for(space = 1; space <= (rowNum - row); space++)

                System.out.print(" ");

            for(star = 1; star <= (2 * row) -1; star++)

                System.out.print("*");
                System.out.println("");

        }

    }
}
