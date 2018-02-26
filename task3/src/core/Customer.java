package core;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;

/**
 * Created by Jakub Gawrylkowicz a1326002
 *
 * The model for the customer.
 */
public class Customer {


  public static final int LOCAL_ID_MIN = 100000;
  public static final int LOCAL_ID_MAX = 199999;

  public static final int REMOTE_ID_MIN = 200000;
  public static final int REMOTE_ID_MAX = 299999;

  public Boolean isNewCustomer = true;

  public static final String TITLE_TYPE_MR = "Herr";
  public static final String TITLE_TYPE_MRS = "Frau";
  public static final String TITLE_TYPE_NONE = "keine";

  static private int localCounter = LOCAL_ID_MIN;
  private int localID;
  private int remoteID;

  private String forename;
  private String surname;
  private String title;
  private String firstAddressRow;
  private String secondAddressRow;
  private String postcode;
  private String location;
  private String country;
  private String deliveryAddress;
  private String telephoneNumber;
  private String faxNumber;
  private String cellNumber;
  private String email;
  private String websiteAddress;
  private String paymentCurrency;

  private ArrayList<PaymentMethod> paymentMethods;
  private ArrayList<Invoice> invoices;


  public Customer() {

    this.localID = localCounter++;
    this.title = TITLE_TYPE_NONE;

    this.invoices = new ArrayList<Invoice>();
    this.paymentMethods = new ArrayList<PaymentMethod>();
  }

  public void setLocalID(int localID) {
    this.localID = localID;
  }

  public void setRemoteID(int remoteID) {
    this.remoteID = remoteID;
  }

  public void setForename(String forename) {
    this.forename = forename;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setFirstAddressRow(String firstAddressRow) {
    this.firstAddressRow = firstAddressRow;
  }


  public void setSecondAddressRow(String secondAddressRow) {
    this.secondAddressRow = secondAddressRow;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setDeliveryAddress(String deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public void setTelephoneNumber(String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  public void setFaxNumber(String faxNumber) {
    this.faxNumber = faxNumber;
  }

  public void setCellNumber(String cellNumber) {
    this.cellNumber = cellNumber;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setWebsiteAddress(String websiteAddress) {
    this.websiteAddress = websiteAddress;
  }

  public void setPaymentCurrency(String paymentCurrency) {
    this.paymentCurrency = paymentCurrency;
  }

  public int getLocalID() {
    return this.localID;
  }

  public int getRemoteID() {
    return this.remoteID;
  }

  public String getForename() {
    return forename;
  }

  public String getSurname() {
    return surname;
  }

  public String getTitle() {
    return title;
  }

  public String getFirstAddressRow() {
    return firstAddressRow;
  }

  public String getSecondAddressRow() {
    return secondAddressRow;
  }

  public String getPostcode() {
    return postcode;
  }

  public String getLocation() {
    return location;
  }

  public String getCountry() {
    return country;
  }

  public String getDeliveryAddress() {
    return deliveryAddress;
  }

  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public String getFaxNumber() {
    return faxNumber;
  }

  public String getCellNumber() {
    return cellNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getWebsiteAddress() {
    return websiteAddress;
  }

  public String getPaymentCurrency() {
    return paymentCurrency;
  }


  public void addPaymentMethod(PaymentMethod newPaymentMethod) {

    paymentMethods.add(newPaymentMethod);
  }


  public ArrayList<PaymentMethod> getPaymentMethods() {

    if (paymentMethods != null) {
      return paymentMethods;
    }
    return null;

  }


  public int addInvoice(int subscriptionID, double chargedAmount, YearMonth yearMonth,
      PaymentMethod paymentMethod) {

    Invoice invoice = new Invoice(this.localID, subscriptionID, chargedAmount, yearMonth,
        paymentMethod);
    this.invoices.add(invoice);

    return invoice.getInvoiceID();

  }

  public ArrayList<Invoice> getAllInvoices() {

    if (this.invoices == null) {
      return null;
    }
    if (this.invoices.isEmpty()) {
      return null;
    }

    return this.invoices;

  }


  public void payForInvoice(int invoiceID, PaymentMethod paymentMethod) {

    Invoice invoice = getInvoiceByID(invoiceID);
    if (invoice != null
        && !invoice.isPaid()) {

      if (!paymentMethods.contains(paymentMethod)) {
        paymentMethods.add(paymentMethod);
      }

      //TODO if the payment method is no longer valid, get the next one via Iterator pattern

      invoice.addPayment(paymentMethod);

    }

  }


  public Invoice getInvoiceByID(int invoiceID) {

    for (Invoice invoice : invoices) {

      if (invoice.getInvoiceID() == invoiceID) {
        return invoice;
      }

    }
    return null;

  }


  public ArrayList<Invoice> getUnpaidInvoices() {

    ArrayList<Invoice> unpaidInvoices = new ArrayList<Invoice>();

    for (Invoice invoice : invoices) {
      if (!invoice.isPaid()) {
        unpaidInvoices.add(invoice);
      }
    }

    return unpaidInvoices;
  }

  public Invoice getThisMonthInvoiceUnpaidBySubscriptionID(int subscriptionID) {

    ArrayList<Invoice> unpaidInvoices = getUnpaidInvoices();

    for (Invoice invoice : unpaidInvoices) {

      if (invoice.getDateCreated().getMonth() == LocalDateTime.now().getMonth()
          && invoice.getSubscriptionID() == subscriptionID) {

        return invoice;
      }

    }

    return null;
  }


  public ArrayList<Invoice> getThisMonthUnpaidInvoices() {

    ArrayList<Invoice> unpaidInvoices = getUnpaidInvoices();
    ArrayList<Invoice> thisMonthInvoices = new ArrayList<Invoice>();

    if (!unpaidInvoices.isEmpty()) {
      for (Invoice invoice : unpaidInvoices) {

        if (invoice.getDateCreated().getMonth() == LocalDateTime.now().getMonth()) {
          thisMonthInvoices.add(invoice);
        }
      }
    }

    return thisMonthInvoices;
  }

  @Override
  public String toString() {
    String str = "";
    str += "Name: " + this.getTitle() + " " + this.getForename() + " " + this.getSurname() + "\n";
    str += "LocalID, RemoteID:" + this.getLocalID() + ", " + this.getRemoteID() + "\n";
    str +=
        "First/Second AddressRow: " + this.getFirstAddressRow() + " " + this.getSecondAddressRow()
            + "\n";
    str +=
        "Country, location, postcode: " + this.getCountry() + " " + this.getLocation() + " " + this
            .getPostcode() + "\n";
    str += "Delivery Address: " + this.getDeliveryAddress() + "\n";
    str += "Tel Nr: " + this.getTelephoneNumber() + "\n";
    str += "Fax Nr: " + this.getFaxNumber() + "\n"; // FAX NR??? WHAT YEAR IS IT?!?!?!?!
    str += "Cell Nr: " + this.getCellNumber() + "\n";
    str += "Email: " + this.getEmail() + "\n";
    str += "Website Address: " + this.getWebsiteAddress() + "\n";
    str += "Payment Currency: " + this.getPaymentCurrency() + "\n\n";

    return str;
  }


}