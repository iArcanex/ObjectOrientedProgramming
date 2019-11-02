package Project3.HeartRates;

/**Week 3
 * CSci 2001-91
 */

/* Object that collects first and last name, alongside date of birth.
*  It provides calculations for age, maximum and target HR.
* */

public class HeartRates {

    private String fName, lName;
    private int month, day, year, age, maxHR;

    public HeartRates(String firstName, String lastName, int month, int day, int year) {

        fName = firstName;
        lName = lastName;
        this.month = month;
        this.day = day;
        this.year = year;

    }

    // Calculates rough age based on year.
    public int calculateAge() {

        if(year < 2019) {

            age = 2019 - year;

        }
        else {

            age = 0;

        }

        return age;

    }

    public int calculateMaximumHeartrate() {

        maxHR = 220 - age;

        return maxHR;

    }

    public double calculateTargetHeartrate() {

        double targetHR = (.85)*(maxHR);

        return targetHR;

    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
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
        return "HeartRates{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", month=" + month +
                ", day=" + day +
                ", year=" + year +
                '}';
    }

}
