package core;

import java.time.LocalDateTime;

/**
 * Created by
 * Jakub Gawrylkowicz 1326002
 * Konrad Laaber 1301118
 *
 */
public class Subscription {

  public static int ID_MIN = 400000;
  public static int ID_MAX = 499999;
  private static int counter = ID_MIN;
  private int subscriptionID;
  private int productID;
  private int customerID;
  private int planType;
  private SubscriptionState subscriptionState;
  private LocalDateTime validUntil;
  //private boolean isNewCustomer;


  public Subscription(int customerID, int productID, int planType, Boolean isNewCustomer) {

    if (counter == ID_MAX) {
      ID_MIN *= 10;
      ID_MAX *= 10;
      counter = ID_MIN;
    }
    this.subscriptionID = counter++;
    this.customerID = customerID;
    this.productID = productID;
    this.planType = planType;
    //this.isNewCustomer = isNewCustomer;

    this.subscriptionState = new InactiveSubscriptionState();

    if (isNewCustomer) {
      this.startSubscription();
      this.paymentReceived();
    }
  }


  public int getProductID() {
    return productID;
  }

  public int getCustomerID() {
    return customerID;
  }

  public int getPlanType() {
    return planType;
  }

  public int getSubscriptionID() {
    return subscriptionID;
  }

  public String getSubscriptionState() {

    return this.subscriptionState.getDescription();
  }

  public LocalDateTime getValidUntil() {
    return validUntil;
  }

  public void startSubscription() {
    if (this.subscriptionState instanceof InactiveSubscriptionState) {
      this.validUntil = LocalDateTime.now();
      this.subscriptionState = new ActiveSubscriptionState();
    }
  }

  public void cancelSubscription() {
    if (this.subscriptionState instanceof ActiveSubscriptionState
        || this.subscriptionState instanceof SuspendedSubscriptionState) {
      this.subscriptionState = new InactiveSubscriptionState();
    }
  }

  public void paymentReceived() {
    if (this.subscriptionState instanceof ActiveSubscriptionState
        || this.subscriptionState instanceof SuspendedSubscriptionState) {
      this.subscriptionState = new ActiveSubscriptionState();
      this.validUntil = this.validUntil.plusMonths(1);
    }
  }

  public void paymentPending() {
    if (this.subscriptionState instanceof ActiveSubscriptionState) {
      this.subscriptionState = new SuspendedSubscriptionState();
    }
  }


  public void printToCMD() {
    System.out.println("Customer: " + this.customerID);
    System.out.println("productID: " + this.productID);
    System.out.println("planType: " + this.planType);
    System.out.println("subscriptionID: " + this.subscriptionID);
    System.out.println("state: " + this.getSubscriptionState());

    if (this.validUntil == null) {
      System.out.println("Valid to: not started yet, payment necessary");
    } else {
      System.out.println("Valid to: " + this.validUntil);
    }


  }

  @Override
  public String toString() {
    String str = "";

    str += "Customer: " + this.getCustomerID() + "\n";
    str += "productID: " + this.getProductID() + "\n";
    str += "planType: " + this.getPlanType() + "\n";
    str += "subscriptionID: " + this.getSubscriptionID() + "\n";
    str += "state: " + this.getSubscriptionState() + "\n";

    if (this.validUntil == null) {
      str += "Valid to: not started yet, payment necessary\n";
    } else {
      str += "Valid to: " + this.validUntil + "\n";
    }
    str += "\n";

    return str;
  }
}
