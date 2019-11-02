package Project3.Employee;

/**Week 3
 * CSci 2001-91
 */

/* Object that gets a first and last name, with a monthly salary.
*  It can only set a positive monthly salary.
* */

public class Employee {

    private String fName, lName;
    private double mSalary;

    public Employee(String firstName, String lastName, double monthlySalary) {

        fName = firstName;
        lName = lastName;
        mSalary = monthlySalary;

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

    public double getMSalary() {
        return mSalary;
    }

    public void setmSalary(double mSalary) {

        if(mSalary > 0) {

            this.mSalary = mSalary;

        }

    }

    @Override
    public String toString() {
        return "Employee{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", mSalary=" + mSalary +
                '}';
    }
}
