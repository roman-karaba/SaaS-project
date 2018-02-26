package core;

import java.time.YearMonth;
import java.util.ArrayList;

/**
 * Created by
 * Jakub Gawrylkowicz 1326002
 *
 * Subclass of PaymentMethod.
 * It stores all information for a credit card
 */
public class PaymentCreditCard extends PaymentMethod {

  public static final int TYPE_MASTERCARD = 0;
  public static final int TYPE_VISA = 1;

  private int type;
  private String cardNumber;
  private YearMonth expirationDate;
  private int secureCode;


  public PaymentCreditCard(String cardNumber, YearMonth expirationDate, int secureCode) {

    setCardNumber(cardNumber);
    setExpirationDate(expirationDate);
    setSecureCode(secureCode);

  }

  @Override
  public int getPaymentMethodType() {
    return TYPE_CREDITCARD;
  }

  @Override
  public void printPaymentInfoToCMD() {

    System.out.println("Number: " + getCardNumber());
    System.out.println("SC: " + getSecureCode());
    System.out.println("Expiration date: " + getExpirationDate().toString());

  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {

    setType(cardNumber);
    this.cardNumber = cardNumber;
  }

  public int getType() {
    return type;
  }

  public void setType(String cardNumber) {

    if (cardNumber.charAt(0) == '4') {
      this.type = TYPE_VISA;
    }
    if (cardNumber.charAt(0) == '5') {
      this.type = TYPE_MASTERCARD;
    }

  }

  public int getSecureCode() {
    return secureCode;
  }

  public void setSecureCode(int secureCode) {

    if ((secureCode >= 100 && secureCode <= 999) || secureCode == 0) {
      this.secureCode = secureCode;
    } else {
      throw new IllegalArgumentException("Error: Security code must be a 3 digit number.");
    }

  }

  public YearMonth getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(YearMonth expirationDate) {

    if (expirationDate.isAfter(YearMonth.now())) {
      this.expirationDate = expirationDate;
    } else {
      throw new IllegalArgumentException("The expiration date has already passed.");
    }
  }

  public void addPaymentMethod(PaymentMethod paymentMethod) {

  }

  @Override
  public int getCreditCardType() {
    return this.type;
  }

  @Override
  public ArrayList<PaymentMethod> getAvailablePaymentMethods() {
    return null;
  }

  @Override
  public void deletePaymentMethod(PaymentMethod paymentMethod) {
    setCardNumber(null);
    setExpirationDate(null);
    setSecureCode(0);
  }
}

