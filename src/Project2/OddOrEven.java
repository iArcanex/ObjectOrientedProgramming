package Project2;

import java.util.Scanner;

/**Week 2
 * CSci 2001-91
 */

/* Odd or Even 2.25
 * Class that when run asks for an integer and
 * determines whether it is an even or odd number.
 * */

public class OddOrEven {

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        int n1;

        System.out.println("Please enter an integer:");
        n1 = scanner.nextInt();

        // Calculate if odd or even
        if((n1 % 2) == 0){

            System.out.println("The integer " + n1 + " is even.");

        }
        else{

            System.out.println("The integer " + n1 + " is odd.");

        }

    }
}
