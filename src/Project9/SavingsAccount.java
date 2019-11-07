package Project9;

/**Inheritance
 * CSci 2001-91
 */

/* This is a subclass of Account. */

public class SavingsAccount extends Account {

    // Here we are overriding transferFunds that is inherited from Account
    public void transferFunds() {

        System.out.println("This bank does not let you transfer funds from a savings account...");

    }

    @Override
    public String toString() {

        return "SavingsAccount{}";

    }
}
