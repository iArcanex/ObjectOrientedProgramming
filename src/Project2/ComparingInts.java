package Project2;

import java.util.Scanner;

/**Week 2
 * CSci 2001-91
 */

/* Comparing Ints 2.16
 * Class that when run asks for two integers, then
 * compares both of them outputting which is larger.
 * */

public class ComparingInts {

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        int n1, n2;

        System.out.println("Enter an integer:");
        n1 = scanner.nextInt();

        System.out.println("Enter another integer:");
        n2 = scanner.nextInt();

        if(n1 > n2){

            System.out.println("The integers you have entered are: " + n1 + " and " + n2);
            System.out.println("\nOf both of them, " + n1 + " is the largest, whereas " + n2 + " is the smallest.");

        }
        else if(n2 > n1) {

            System.out.println("The integers you have entered are: " + n1 + " and " + n2);
            System.out.println("\nOf both of them, " + n2 + " is the largest, whereas " + n1 + " is the smallest.");

        }
        else if(n1 == n2) {

            System.out.println("The integers you have entered are: " + n1 + " and " + n2);
            System.out.println("\nThey are both equal to one another.");

        }

    }

}
