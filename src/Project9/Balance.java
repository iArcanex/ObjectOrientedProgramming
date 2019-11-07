package Project9;

/**Inheritance
 * CSci 2001-91
 */

/* This is the balance class. It will instantiate our account subclasses.
*  By utilizing inheritance I have prevented myself from
*  rewriting similar or the same code. This is an important
*  concept as it saves us time, resources, and allows
*  maintaining software to become easier.*/

public class Balance {

    public static void main(String[] args) {

        CheckingAccount checking1 = new CheckingAccount();
        SavingsAccount savings1 = new SavingsAccount();

        // Inheritance allows a subclass to inherit a superclass's
        // methods. The CheckingAccount class does directly contain the
        // transferFunds() method, but inherits it from Account.
        checking1.transferFunds(savings1);

        // Here a similar thing is being done, but this demonstrates that
        // a subclass may override a superclass's method(s).
        savings1.transferFunds();

    }


}
