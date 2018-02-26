package core;

import java.time.YearMonth;
import java.util.ArrayList;

/**
 * Created by Jakub Gawrylkowicz a1326002
 *
 * Strategy Pattern and also Composite pattern, since you can store other methods in a PayPal
 * account. Class for storing customers' methods of payment
 */
public abstract class PaymentMethod {

  static final int TYPE_PAYPAL = 0;
  static final int TYPE_CREDITCARD = 1;

  abstract public int getPaymentMethodType();

  abstract public void printPaymentInfoToCMD();

  abstract public void addPaymentMethod(PaymentMethod paymentMethod);

  abstract public int getCreditCardType();

  abstract public String getCardNumber();

  abstract public int getSecureCode();

  abstract public YearMonth getExpirationDate();

  abstract public ArrayList<PaymentMethod> getAvailablePaymentMethods();

  abstract public void deletePaymentMethod(PaymentMethod paymentMethod);

}
