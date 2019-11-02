package Project2;

import java.util.Scanner;

/**Week 2
 * CSci 2001-91
 */

/* Arithmetic 2.15
 * Class that when run asks for two integers, then prints
 * their sum, product, difference, and quotient.
 * */

public class Arithmetic {

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        int n1, n2, sum, product, difference;
        double quotient;

        System.out.println("Please enter an integer:");
        n1 = scanner.nextInt();

        System.out.println("Please enter another integer:");
        n2 = scanner.nextInt();

     // Calculations

        // SUM
        sum = n1 + n2;

        // PRODUCT
        product = n1 * n2;

        // DIFFERENCE (Subtracts bigger from smaller)

            // Initialization of variable
            difference = n1 - n2;

        if(n1 >= n2) {

            difference = n1 - n2;

        }
        else if(n2 >= n1) {

            difference = n2 - n1;

        }

        // QUOTIENT (Divides from the larger)

            // Initialization of variable
            quotient = n1 / n2;

        if(n1 >= n2) {

            quotient = ((double)n1)/ n2;

        }
        else if(n2 >= n1) {

            quotient = ((double)n2) / n1;

        }

        System.out.println("Based on the integers provided their operations are detailed below:\n");
        System.out.println("Sum: " + sum + "\nProduct: " + product + "\nDifference: " + difference + "\nQuotient: " + quotient);

    }

}
