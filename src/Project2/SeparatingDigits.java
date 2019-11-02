package Project2;

import java.util.Scanner;

/**Week 2
 * CSci 2001-91
 */

/* Separating Digits 2.30
 * Class that when run asks for a five digit integer.
 * It will be then separate the integer into its individual
 * digits three spaces apart.
 * */

public class SeparatingDigits {

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        int n1, d1, d2, d3, d4, d5;

        System.out.println("Please enter a five digit integer:");
        n1 = scanner.nextInt();

        // Calculating the numbers (Assuming it WILL be five digits)
        // You can create a loop and find string length to make it easier, but I think the exercise wants us to do it mathematically.

        d1 = n1 / 10000;
        d2 = (n1 % 10000) / 1000;
        d3 = (n1 % 1000) / 100;
        d4 = (n1 % 100) / 10;
        d5 = n1 % 10;

        System.out.println("Your separated digits are as follows:\n" + d1 + "   " + d2 + "   " + d3 + "   " + d4 + "   " + d5 + "   ");



    }

}
