package Project3.HeartRates;

import java.util.Scanner;

/**Week 3
 * CSci 2001-91
 */

/* Class that collects a person's info and display's their age and HR levels.
* */

public class HeartRatesTest {

    public static void main(String args[]) {

        String firstName, lastName;
        int month, day, year;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input your first name:");
        firstName = scanner.next();

        System.out.println("Please input your last name:");
        lastName = scanner.next();

        System.out.println("Please your birth month:");
        month = scanner.nextInt();

        System.out.println("Please your birth day:");
        day = scanner.nextInt();

        System.out.println("Please your birth year:");
        year = scanner.nextInt();

        HeartRates person = new HeartRates(firstName, lastName, month, day, year);

        System.out.printf("You are %s %s. Your date of birth is: %d/%d/%d. %n%nYou are %d years old. " +
                "%n%nYour maximum heartrate is %d. %n%nYour target heartrate is %.2f.", person.getfName(),
                person.getlName(), person.getMonth(), person.getDay(), person.getYear(),
                person.calculateAge(), person.calculateMaximumHeartrate(), person.calculateTargetHeartrate());
    }

}
