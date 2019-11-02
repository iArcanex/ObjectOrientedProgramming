package Project3.Account;

import java.util.Scanner;

/**Week 3
 * CSci 2001-91
 */

/* Class that displays account information and
* sets a deposit. Method used to display balances
* due to the repeated occurrences.
* */

public class AccountTest {

    public static void main(String args[]) {

        Account account1 = new Account("Jane Green", 50.00);
        Account account2 = new Account("John Blue", -7.53);

        // display initial balances (This was updated with the new method)
        displayAccount(account1);
        displayAccount(account2);

        Scanner input = new Scanner(System.in);

        System.out.print("Enter deposit amount for account1: ");
        double depositAmount = input.nextDouble();
        System.out.printf("%nadding %.2f to account1 balance%n%n", depositAmount);
        account1.deposit(depositAmount);

        // display balances again (again updated with new method)
        displayAccount(account1);
        displayAccount(account2);

        System.out.print("Enter deposit amount for account2: ");
        depositAmount = input.nextDouble();
        System.out.printf("%nadding %.2f to account1 balance%n%n", depositAmount);
        account2.deposit(depositAmount);

        // display balances again (again updated with new method)
        displayAccount(account1);
        displayAccount(account2);

    }

    public static void displayAccount(Account accountToDisplay) {

        System.out.printf("%s balance: $%.2f%n", accountToDisplay.getName(), accountToDisplay.getBalance());

    }

}
