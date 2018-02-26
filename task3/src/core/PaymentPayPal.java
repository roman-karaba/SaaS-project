package core;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jakub Gawrylkowicz a1326002
 *
 *
 * Class for storing customers' paypal account. It also checks if the customers email is a valid
 * email address. The algorithm can be found here: https://stackoverflow.com/questions/8204680/java-regex-email
 */
public class PaymentPayPal extends PaymentMethod {

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
  private ArrayList<PaymentMethod> availablePaymentMethods;
  private PaymentMethod currentPaymentMethod;
  private String accountName;


  public PaymentPayPal(String accountName) {
    availablePaymentMethods = new ArrayList<PaymentMethod>();
    setAccountName(accountName);
  }


  @Override
  public void printPaymentInfoToCMD() {
    System.out.println("Account name: " + getAccountName());
  }

  @Override
  public int getPaymentMethodType() {
    return TYPE_PAYPAL;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {

    if (validate(accountName)) {
      this.accountName = accountName;
    } else {
      throw new IllegalArgumentException("The email address is not correct.");
    }
  }

  private boolean validate(String accountName) {
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(accountName);
    return matcher.find();
  }


  public void addPaymentMethod(PaymentMethod paymentMethod) {
    if (paymentMethod.getPaymentMethodType() != PaymentMethod.TYPE_PAYPAL) {

      //If there is no available methods, the first method will be the current one
      if (availablePaymentMethods.isEmpty()) {
        currentPaymentMethod = paymentMethod;
      }
      availablePaymentMethods.add(paymentMethod);
    } else {
      throw new IllegalArgumentException(
          "You cannot add another PayPal account to existing PayPal account.");
    }

  }

  @Override
  public void deletePaymentMethod(PaymentMethod paymentMethod) {
    if (availablePaymentMethods != null
        && !availablePaymentMethods.isEmpty()
        && availablePaymentMethods.contains(paymentMethod)) {

      availablePaymentMethods.remove(paymentMethod);

    }
  }

  @Override
  public ArrayList<PaymentMethod> getAvailablePaymentMethods() {
    return availablePaymentMethods;
  }

  @Override
  public int getCreditCardType() {
    if (currentPaymentMethod != null) {
      return currentPaymentMethod.getCreditCardType();
    }
    throw new NullPointerException("No credit card added to this account");
  }

  @Override
  public int getSecureCode() {
    if (currentPaymentMethod != null) {
      return currentPaymentMethod.getSecureCode();
    }
    throw new NullPointerException("No credit card added to this account");
  }

  @Override
  public String getCardNumber() {
    if (currentPaymentMethod != null) {
      return currentPaymentMethod.getCardNumber();
    }
    throw new NullPointerException("No credit card added to this account");
  }

  @Override
  public YearMonth getExpirationDate() {
    if (currentPaymentMethod != null) {
      return currentPaymentMethod.getExpirationDate();
    }
    throw new NullPointerException("No credit card added to this account");
  }


}
