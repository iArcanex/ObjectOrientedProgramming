package Project3.Date;

/**Week 3
 * CSci 2001-91
 */

/* Class that creates a Date object.
* Displays date based on info.
* */

public class DateTest {

    public static void main(String args[]) {

        final int MONTH = 8;
        final int DAY = 30;
        final int YEAR = 1999;

        Date birthday = new Date(MONTH, DAY, YEAR);

        birthday.displayDate();

    }

}
