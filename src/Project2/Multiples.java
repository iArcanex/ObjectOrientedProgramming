package Project2;

import java.util.Scanner;

/**Week 2
 * CSci 2001-91
 */

/* Multiples 2.26
 * Class that when run asks for two integers and
 * determines if the first int is a multiple of the second
 * */

public class Multiples {

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        int n1, n2;

        System.out.println("Please enter an integer:");
        n1 = scanner.nextInt();

        System.out.println("Please enter another integer:");
        n2 = scanner.nextInt();

        // Calculate if first is multiple of second
        if((n1 % n2) == 0){

            System.out.println("The integer " + n1 + " is a multiple of " + n2 + ".");

        }
        else{

            System.out.println("The integer " + n1 + " is NOT a multiple of " + n2 + ".");

        }

    }

}
