package Project3.Account;

/**Week 3
 * CSci 2001-91
 */

/* Object very similar to figure in text book.
* Requires a name and balance. Balance/deposit are only set if positive.
* */

public class Account {

    private String name;
    private double balance;

    public Account(String name, double balance) {

        this.name = name;

        if(balance > 0.0){

            this.balance = balance;

        }

    }

    public void deposit(double depositAmount) {

        if(depositAmount > 0.0) {

            balance = balance + depositAmount;

        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

}
