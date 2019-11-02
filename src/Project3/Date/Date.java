package Project3.Date;

/**Week 3
 * CSci 2001-91
 */

/* Object that gets a month, day, and year.
* It has a method that displays the date as well.
* */

public class Date {

    private int month, day, year;

    public Date(int month, int day, int year) {

        this.month = month;
        this.day = day;
        this.year = year;

    }

    public void displayDate() {

        System.out.println("The date is: " + month + "/" + day + "/" + year);

    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Date{" +
                "month=" + month +
                ", day=" + day +
                ", year=" + year +
                '}';
    }
}
