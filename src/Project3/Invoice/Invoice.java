package Project3.Invoice;

/**Week 3
 * CSci 2001-91
 */

/* Object that gets a part #, description, item quantity, and price.
* It is able to provide only a positive invoice amount.
* */

public class Invoice {

    private String partNumber, partDesc;
    private int itemQuantity;
    private double itemPrice;

    public Invoice(String partNumber, String partDesc, int itemQuantity, double itemPrice) {

        this.partNumber = partNumber;
        this.partDesc = partDesc;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;

    }

    public double getInvoiceAmount() {

        if(itemQuantity < 0) {

            itemQuantity = 0;

        }

        if(itemPrice < 0.0) {

            itemPrice = 0.0;

        }

        return (itemQuantity*itemPrice);

    }

    public String getPartNumber() {

        return partNumber;

    }

    public void setPartNumber(String partNumber) {

        this.partNumber = partNumber;

    }

    public String getPartDesc() {

        return partDesc;

    }

    public void setPartDesc(String partDesc) {

        this.partDesc = partDesc;

    }

    public int getItemQuantity() {

        return itemQuantity;

    }

    public void setItemQuantity(int itemQuantity) {

        this.itemQuantity = itemQuantity;

    }

    public double getItemPrice() {

        return itemPrice;

    }

    public void setItemPrice(double itemPrice) {

        this.itemPrice = itemPrice;

    }

    @Override
    public String toString() {
        return "Invoice{" +
                "partNumber='" + partNumber + '\'' +
                ", partDesc='" + partDesc + '\'' +
                ", itemQuantity=" + itemQuantity +
                ", itemPrice=" + itemPrice +
                '}';
    }

}
