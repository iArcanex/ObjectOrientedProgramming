package Project3.Employee;

/**Week 3
 * CSci 2001-91
 */

/* Class that creates two Employee objects and prints salary info.
*  Employees also get a 10% raise!
* */

public class EmployeeTest {

    public static void main(String args[]) {

        final String FIRST_NAME_1 = "Robert";
        final String LAST_NAME_1 = "Anderson";
        final double MONTHLY_SALARY_1 = 5000;

        final String FIRST_NAME_2 = "Anna";
        final String LAST_NAME_2 = "Johnson";
        final double MONTHLY_SALARY_2 = 5000;

        Employee employeeOne = new Employee(FIRST_NAME_1, LAST_NAME_1, MONTHLY_SALARY_1);
        Employee employeeTwo = new Employee(FIRST_NAME_2, LAST_NAME_2, MONTHLY_SALARY_2);

        System.out.println(employeeOne.getMSalary());
        System.out.println(employeeTwo.getMSalary());

        // 10% Raise
        employeeOne.setmSalary(((MONTHLY_SALARY_1)*(.1)) + MONTHLY_SALARY_1);
        employeeTwo.setmSalary(((MONTHLY_SALARY_2)*(.1)) + MONTHLY_SALARY_2);

        // Verifying the raise
        System.out.println(employeeOne.getMSalary());
        System.out.println(employeeTwo.getMSalary());

    }

}
