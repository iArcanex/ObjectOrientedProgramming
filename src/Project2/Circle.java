package Project2;

import java.util.Scanner;

/**Week 2
 * CSci 2001-91
 */

/* Circle 2.28
 * Class that when run asks for a radius which is
 * then utilized to print a circle's diameter, circumference,
 * and area using the value of pi.
 * */

public class Circle {

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        int radius;

        System.out.println("Enter a radius:");
        radius = scanner.nextInt();

        // Calculations (Text wants everything done in a printf statement)
        System.out.printf("Your circle has a radius of %d." + "%nIts diameter is: %d." + "%nIts circumference is: %.2f" + "%nIts area is: %.2f", radius, (2 * radius), (2 * radius * Math.PI), ((Math.pow(radius, 2)) * Math.PI));

    }

}
