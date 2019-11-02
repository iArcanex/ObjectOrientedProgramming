package Project2;

import java.util.Scanner;

/**Week 2
 * CSci 2001-91
 */

/* Smallest And Largest 2.24
 * Class that when run asks for five integers
 * and determines the smallest and largest of them.
 * */

public class SmallestAndLargest {

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        int n1, n2, n3, n4, n5, s, l;

        System.out.println("Please enter an integer:");
        n1 = scanner.nextInt();

        System.out.println("Please enter another integer:");
        n2 = scanner.nextInt();

        System.out.println("Please enter another integer:");
        n3 = scanner.nextInt();

        System.out.println("Please enter another integer:");
        n4 = scanner.nextInt();

        System.out.println("Please enter the final integer:");
        n5 = scanner.nextInt();

        // If tree for largest and smallest. Checks to see how all the other numbers fare starting with the
        // first number. Same if statement repeats, but for the other integers.

        // Smallest
        s = n1;
        if(n2 < s) {

            s = n2;

        }

        if(n3 < s) {

            s = n3;

        }

        if(n4 < s) {

            s = n4;

        }

        if(n5 < s) {

            s = n5;

        }

        // Largest
        l = n1;
        if(n2 > l) {

            l = n2;

        }

        if(n3 > l) {

            l = n3;

        }

        if(n4 > l) {

            l = n4;

        }

        if(n5 > l) {

            l = n5;

        }

        System.out.println("Based on the integers provided the largest is: " + l + "\nThe smallest is: " + s);

    }

}
