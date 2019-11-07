package Project9;

/**Inheritance
 * CSci 2001-91
 */

/* This is the superclass Account.
* It contains all the methods that would
* be inherited by the subclasses CheckingAccount
* and SavingsAccount.
* */

public class Account {

    public void transferFunds(Object account) {

        System.out.println("I transferred funds to this account: " + account.toString());

    }

}
