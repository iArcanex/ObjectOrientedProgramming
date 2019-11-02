package Project4;

/**Week 4
 * CSci 2001-91
 */

/* 5.21 Pythagorean Triples
* Brute force method that displays a table of the
* Pythagorean triples for side1, side2, and the hypotenuse.
* All no larger than 500.*/

public class PTriples {

    private static final int CAP = 500;

    public static void main(String args[]) {

        int side1, side2, hypo;

        for(side1 = 1; side1 <= CAP; side1++) {

            for(side2 = side1; side2 <= CAP; side2++) {

                for(hypo = side2; hypo <= CAP; hypo++) {

                    if((Math.pow(side1, 2) + Math.pow(side2, 2)) == Math.pow(hypo, 2)) {

                        System.out.printf(" %n%d %d %d", side1, side2, hypo);

                    }

                }

            }

        }

    }

}
