package core;

import java.time.LocalDateTime;
import java.time.YearMonth;

/**
 * Created by Jakub Gawrylkowicz a1326002
 * Finallized by David Bakic a1148885
 *
 * Represents the model of invoice.
 */
public class Invoice {

  static public final int TYPE_UNPAID = 0;
  static public final int TYPE_PAID = 1;

  static public int ID_MIN = 700000;
  static public int ID_MAX = 799999;

  private int invoiceID;
  private static int counter = ID_MIN;
  private PaymentMethod paymentMethod;
  private boolean isPaid;
  private LocalDateTime dateCreated;


  private YearMonth yearMonth;
  private int subscriptionID;
  //private int productID;
  //private int planType;
  private int customerID;
  private double chargedAmount;
  private LocalDateTime paymentReceived;

  public Invoice(int customerID, int subscriptionID, double chargedAmount, YearMonth yearMonth,
      PaymentMethod paymentMethod) {

    this.dateCreated = LocalDateTime.now();
    this.invoiceID = counter++;
    System.out.println(invoiceID);
    this.customerID = customerID;
    this.subscriptionID = subscriptionID;
    //this.productID = productID;
    //this.planType = planType;
    this.paymentMethod = paymentMethod;
    this.chargedAmount = chargedAmount;
    this.yearMonth = yearMonth;
    if (chargedAmount == 0) {
      this.isPaid = true;
    } else {
      this.isPaid = false;
    }
  }



  public int getInvoiceID() {
    return invoiceID;
  }

  public boolean isPaid() {
    return this.isPaid;
  }

  public void addPayment(PaymentMethod paymentMethod) {

    if (!this.isPaid()) {
      this.paymentMethod = paymentMethod;
      this.isPaid = true;
      this.paymentReceived = LocalDateTime.now();
    }

  }

  public int getSubscriptionID() {
    return subscriptionID;
  }

  public void setPaid(boolean value) {
    this.isPaid = value;
    this.paymentReceived = LocalDateTime.now();
  }

  public LocalDateTime getDateCreated() {
    return dateCreated;
  }

  public void printToCMD() {

    System.out.println("ID: " + this.invoiceID);
    System.out.println("CustomerID: " + customerID);
    System.out.println("SubscriptionID : " + subscriptionID);
    System.out.println("chargedAmount: " + chargedAmount);
    //System.out.println("ProductID: " + productID);
    //System.out.println("planType: " + planType);
    System.out.println("isPaid: " + isPaid);
    System.out.println("paidMonth: " + yearMonth.toString());

  }


  public String toString(){
    String str;

    str = "";
    str += "InvoiceID: " + this.invoiceID + "\n" + "CustomerID: " + customerID + "\n";
    str += "SubscriptionID : " + subscriptionID + "\n" + "chargedAmount: " + chargedAmount + "\n";
    str += "isPaid: " + isPaid + "\n" + "paidMonth: " + yearMonth.toString();


    return str;
  }
}
