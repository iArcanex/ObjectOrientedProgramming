package Project3.Invoice;

/**Week 3
 * CSci 2001-91
 */

/* Class that creates an Invoice object, where it retrieves
*  information such as the invoiceAmount.
* */

public class InvoiceTest {

    public static void main(String args[]) {

        final String PART_NUMBER = "#01";
        final String PART_DESCRIPTION = "Bronze Bracket";
        final int ITEM_QUANTITY = 10;
        final double ITEM_PRICE = 2.50;

        Invoice invoiceOne = new Invoice(PART_NUMBER, PART_DESCRIPTION, ITEM_QUANTITY, ITEM_PRICE);

        // Verifying constructor works and variables are assigned through toString
        System.out.println(invoiceOne.toString());

        // Calculating invoice based off of item quantity multiplied by item price
        System.out.println(invoiceOne.getInvoiceAmount());

    }


}
