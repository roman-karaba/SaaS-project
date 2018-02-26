package core;

import com.sun.istack.internal.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Jakub Gawrylkowicz a1326002
 * Finallized by Roman Karaba a1301624
 *
 * Methods for controlling the core functionality of the core package
 */
public class Controller {

  //TODO to every add: create a JSON Object, which can later be loaded into the controller class (Constructor?)

  private ArrayList<Customer> customers;
  private ArrayList<Subscription> subscriptions;
  private ArrayList<Product> emptyProducts;
  private ArrayList<Product> assignedProducts;

  private final String email = "swe2task3@gmail.com";
  private final String apiKey = "f290364e533055d3766a970cbbe1908dj0LdM76NY3dkJyy9c8KTL63OsAfdioct";
  private final String womboCombo = email + ":" + apiKey;
  private final String fastBillAuth = "Basic " + java.util.Base64.getEncoder().encodeToString((womboCombo).getBytes());

  public static final String FAST_BILL_DATA = "DATA";
  public static final String FAST_BILL_FILTER = "FILTER";

  public Controller() {
    customers = new ArrayList<Customer>();
    subscriptions = new ArrayList<Subscription>();
    emptyProducts = new ArrayList<Product>();
    assignedProducts = new ArrayList<Product>();
  }

  public void test() {
    System.out.println("test from the Controller Class");
  }


  /**
   * Creates a new customer and adds him/her to the customers array.
   */
  public void createCustomer(Customer newCustomer) {

    customers.add(newCustomer);

  }

  /*public void getUnpaidFastBillSubscriptionsForCustomer(Customer customer){

    JSONObject filter = new JSONObject();

    try {
      filter.put("CUSTOMER_ID", customer.getRemoteID());

    } catch (Exception e){
      e.printStackTrace();
    }

  }*/

  //TODO OCCURANCES FOR VALID UNTIL
  public void createFastBillSubscription(Customer customer, String description, double vat,
      double price, PaymentMethod paymentMethod, int occurances){

    JSONObject jsonData = new JSONObject();
    JSONObject items = new JSONObject();

    try{
      jsonData.put("CUSTOMER_ID", customer.getRemoteID());
      LocalDateTime time = LocalDateTime.now();
      String timeStr = String.valueOf(time.getYear())+"-"+String.valueOf(time.getMonthValue())+"-"
          +String.valueOf(time.getDayOfMonth());
      jsonData.put("START_DATE", timeStr);
      jsonData.put("FREQUENCY", "monthly");
      jsonData.put("OUTPUT_TYPE","Outgoing");

      items.put("DESCRIPTION", description);
      items.put("VAT_PERCENT", vat);
      items.put("UNIT_PRICE", price);

      jsonData.put("ITEMS", items);

      JSONObject fastBillResponse = getFastBillJson("recurring.create",
          Controller.FAST_BILL_DATA, jsonData);

      customer.addInvoice(fastBillResponse.getInt("INVOICE_ID"), (price+(price*(vat/100))),
          YearMonth.now(), paymentMethod);

    } catch (Exception e){
      e.printStackTrace();
    }

  }

  public void createFastBillInvoice(Customer customer, String description, double vat, double price,
      PaymentMethod paymentMethod){
    JSONObject jsonData = new JSONObject();
    JSONObject items = new JSONObject();


    try{
      System.out.println(customer.getLocalID());
      jsonData.put("CUSTOMER_ID",String.valueOf(customer.getLocalID()));

      items.put("DESCRIPTION", description);
      items.put("VAT_PERCENT", vat);
      items.put("UNIT_PRICE", price);

      jsonData.put("ITEMS", items);

      JSONObject fastBillResponse = getFastBillJson("invoice.create",
          Controller.FAST_BILL_DATA, jsonData);
      System.out.println(fastBillResponse);
      int invoiceID = fastBillResponse.getInt("INVOICE_ID");
      jsonData = new JSONObject();
      jsonData.put("INVOICE_ID", invoiceID);

      fastBillResponse = getFastBillJson("invoice.complete",
          Controller.FAST_BILL_DATA, jsonData);

      customer.addInvoice(0, (price+(price*(vat/100))), YearMonth.now(), paymentMethod);

    } catch (Exception e){
      e.printStackTrace();
    }
  }

  public void createFastbillCustomer(Customer customer){
    JSONObject jsonData = new JSONObject();
    try {
      jsonData.put("CUSTOMER_TYPE", "consumer");
      jsonData.put("FIRST_NAME", customer.getForename());
      jsonData.put("LAST_NAME", customer.getSurname());
      jsonData.put("SALUTATION", customer.getTitle());
      jsonData.put("ADDRESS", customer.getFirstAddressRow()+"/"+customer.getSecondAddressRow());
      jsonData.put("ZIPCODE", customer.getPostcode());
      jsonData.put("CITY", customer.getLocation());
      jsonData.put("SECONDARY_ADDRESS", customer.getDeliveryAddress());
      jsonData.put("PHONE", customer.getTelephoneNumber());
      jsonData.put("FAX", customer.getFaxNumber());
      jsonData.put("MOBILE", customer.getCellNumber());
      jsonData.put("EMAIL", customer.getEmail());
      jsonData.put("WEBSITE", customer.getWebsiteAddress());
      jsonData.put("CURRENCY_CODE", customer.getPaymentCurrency());

      JSONObject fastBillResponse = getFastBillJson("customer.create",
          Controller.FAST_BILL_DATA, jsonData);
      customer.setRemoteID(fastBillResponse.getInt("CUSTOMER_ID"));
    } catch (Exception e){
      e.printStackTrace();
    }

    customers.add(customer);
  }

  public void getFastBillCustomers(){

    JSONObject fastBillResponse;
    JSONArray fastBillCustomers;
    try {
      fastBillResponse = getFastBillJson("customer.get",
          Controller.FAST_BILL_FILTER, null);
      fastBillCustomers = fastBillResponse.getJSONArray("CUSTOMERS");
      for (int i=0; i<fastBillCustomers.length(); i++){
        Customer customer = new Customer();
        JSONObject currentCustomer = fastBillCustomers.getJSONObject(i);

        customer.setForename(currentCustomer.getString("FIRST_NAME"));
        customer.setSurname(currentCustomer.getString("LAST_NAME"));
        customer.setTitle(currentCustomer.getString("SALUTATION"));
        customer.setFirstAddressRow(currentCustomer.getString("ADDRESS"));
        String addresses = currentCustomer.getString("ADDRESS");

        if (addresses.indexOf("\\") != -1){
          String[] splitAddresses = addresses.split("/");
            switch (splitAddresses.length){
              case 0:
                customer.setFirstAddressRow("");
                customer.setSecondAddressRow("");
                break;
              case 1:
                customer.setFirstAddressRow(splitAddresses[0]);
                customer.setSecondAddressRow("");
                break;
              case 2:
                customer.setFirstAddressRow(splitAddresses[0]);
                customer.setSecondAddressRow(splitAddresses[1]);
                break;
              default:
                break;
            }
        }

        customer.setPostcode(currentCustomer.getString("ZIPCODE"));
        customer.setLocation(currentCustomer.getString("CITY"));
        customer.setDeliveryAddress(currentCustomer.getString("SECONDARY_ADDRESS"));
        customer.setTelephoneNumber(currentCustomer.getString("PHONE"));
        customer.setFaxNumber(currentCustomer.getString("FAX"));
        customer.setCellNumber(currentCustomer.getString("MOBILE"));
        customer.setEmail(currentCustomer.getString("EMAIL"));
        customer.setWebsiteAddress(currentCustomer.getString("WEBSITE"));
        customer.setPaymentCurrency(currentCustomer.getString("CURRENCY_CODE"));
        customer.setLocalID(Integer.parseInt(currentCustomer.getString("CUSTOMER_ID")));
        customer.setRemoteID(Integer.parseInt(currentCustomer.getString("CUSTOMER_ID")));

        System.out.println(fastBillResponse);
        System.out.println(customer.toString());
        customers.add(customer);
        //System.out.println(this.getCustomerByLocalID(customer.getLocalID()).toString());

      }

    } catch (Exception e){
      e.printStackTrace();
    }
  }

  public JSONObject getFastBillJson(String service,
      String filter_data_identifier, JSONObject filter_data){

    HttpClient httpClient = HttpClientBuilder.create().build();
    JSONObject serviceRequest = new JSONObject();
    JSONObject returnObj = null;
    HttpResponse apiResponse;

    try{

      HttpPost apiRequest = new HttpPost("https://my.fastbill.com/api/1.0/api.php");
      apiRequest.addHeader("Authorization", fastBillAuth);
      apiRequest.addHeader("Content-Type", "application/json");
      serviceRequest.put("SERVICE", service);

      if (filter_data != null) {
        serviceRequest.put(filter_data_identifier, filter_data);
      } else {
        serviceRequest.put(filter_data_identifier, new JSONObject());
      }

      apiRequest.setEntity(new StringEntity(serviceRequest.toString()));
      apiResponse = httpClient.execute(apiRequest);
      returnObj = decodeFastBillApiStream(apiResponse.getEntity().getContent());
      returnObj = (JSONObject) returnObj.get("RESPONSE");

    } catch (Exception e){
      e.printStackTrace();
    }

    return returnObj;
  }


  private JSONObject decodeFastBillApiStream (InputStream inputStream){
    String line;
    String jsonString = "";
    BufferedReader br = null;
    StringBuilder sb = new StringBuilder();
    JSONObject returnObj = null;

    try {
      br = new BufferedReader(new InputStreamReader(inputStream));
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }
      jsonString = sb.toString();
      returnObj = new JSONObject(jsonString);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return returnObj;
  }


  public ArrayList<Customer> getCustomers() {
    return this.customers;
  }

  public ArrayList<Subscription> getSubscriptions() {
    return this.subscriptions;
  }

  public ArrayList<Product> getNotSoldProducts() {
    return this.emptyProducts;
  }

  public ArrayList<Product> getSoldProducts() {
    return this.assignedProducts;
  }

  /**
   * This function returns a customer by his/her names. It can happen that there are customers with
   * same names. In this case the null value will be returned.
   */
  private Customer getCustomerByNames(String forename, String surname) {

    Customer customerTemp = null;
    int numCustomersFound = 0;

    for (Customer customer : customers) {
      if (customer.getForename().equals(forename) && customer.getSurname().equals(surname)) {
        customerTemp = customer;
        numCustomersFound++;
      }
    }

    if (numCustomersFound == 1) {
      return customerTemp;
    } else {
      return null;
    }
  }

  /**
   * Return a customer object by a localID, which can later be used by various MockupClient classes.
   */
  public Customer getCustomerByLocalID(int id) {

    if (id >= Customer.LOCAL_ID_MIN && id <= Customer.LOCAL_ID_MAX) {
      for (Customer customer : customers) {
        if (customer.getLocalID() == id) {
          return customer;
        }
      }
    }
    return null;
  }


  /**
   * Return a customer object by its remoteID, which can later be used by various FastBillClient
   * clients classes.
   */
  public Customer getCustomerByRemoteID(int id) {

    //if (id >= Customer.REMOTE_ID_MIN && id <= Customer.REMOTE_ID_MAX) {
    System.out.println(customers.toString());
      for (Customer customer : customers) {
        if (customer.getRemoteID() == id) {
          return customer;
        }
      }
    System.out.println("returning null....");
   // }
    return null;
  }


  /**
   * This function tries to find a customer first and later assign to an invoice to him/her.
   */
    /*
    public void createInvoiceForCustomer(Invoice newInvoice, Customer customer){

        //Finding the customer
        Customer customerTemp = null;

        //Trying to find by names
        if (customer.getSurname() != null && customer.getForename() != null) {
            customerTemp = getCustomerByNames(customer.getForename(), customer.getSurname());
        }

        //Then by the localID
        if (customer.getLocalID() >= Customer.LOCAL_ID_MIN
                && customer.getLocalID() <= Customer.LOCAL_ID_MAX
                && customerTemp == null) {
                customerTemp = getCustomerByLocalID(customer.getLocalID());
        }
        //Then by the remoteID
        if (customer.getRemoteID() >= Customer.REMOTE_ID_MIN
                && customer.getRemoteID() <= Customer.REMOTE_ID_MAX
                && customerTemp == null) {
                customerTemp = getCustomerByRemoteID(customer.getRemoteID());
        }


        //adding an invoice after the customer has been found
        if (customerTemp!=null){
            customerTemp.addInvoice(newInvoice);
        }else
            throw new IllegalArgumentException("No such customer could be found!");


    }


    public void createInvoiceForCustomerByLocalID(Invoice newInvoice, int customerLocalID){

        Customer customer = null;
        if (customerLocalID >= Customer.LOCAL_ID_MIN
                && customerLocalID <= Customer.LOCAL_ID_MAX) {
            customer = getCustomerByLocalID(customerLocalID);
        }

        if (customer!=null){
            customer.addInvoice(newInvoice);
        }else
            throw new IllegalArgumentException("Wrong LocalID. No such customer could be found!");

    }


    public void createInvoiceForCustomerByRemoteID(Invoice newInvoice, int customerRemoteID){

        Customer customer = null;
        if (customerRemoteID >= Customer.REMOTE_ID_MIN
                && customerRemoteID <= Customer.REMOTE_ID_MAX) {
            customer = getCustomerByRemoteID(customerRemoteID);
        }

        if (customer!=null){
            customer.addInvoice(newInvoice);
        }else
            throw new IllegalArgumentException("Wrong RemoteID. No such customer could be found!");

    }

    */
  public ArrayList<Invoice> getUnpaidInvoiceByCustomer(Customer customer) {

    ArrayList<Invoice> unpaidInvoices = null;
    ArrayList<Invoice> allInvoicesFromCustomer = null;
    boolean found = false;

    allInvoicesFromCustomer = customer.getAllInvoices();

    if (allInvoicesFromCustomer != null) {

      unpaidInvoices = new ArrayList<>();

      for (Invoice invoice : allInvoicesFromCustomer) {

        if (!invoice.isPaid()) {
          unpaidInvoices.add(invoice);
          found = true;
        }
      }

      if (found) {
        return unpaidInvoices;
      }

      return null;
    }

    return null;
  }


  public void createPlanForProduct(int productID, int planType, String planName,
      String planDescription, double planRate) {

    Product product = getProductByID(productID);

    if (product != null) {
      switch (planType) {
        case Product.PLAN_TYPE_STANDARD:
          product = new PlanStandard(product, planName, planDescription, planRate);
          break;
        case Product.PLAN_TYPE_PREMIUM:
          product = new PlanPremium(product, planName, planDescription, planRate);
          break;
        case Product.PLAN_TYPE_PRO:
          product = new PlanPro(product, planName, planDescription, planRate);
          break;
        default:
          throw new IllegalArgumentException("Plan type not found");
      }

      //TODO add to list of products and plans, from which later subscription can be added with just the productID
      if (true) { //TODO check if there isn't a plan of the specific type already in the array; one plan type for a product
        assignedProducts.add(product);
      }
    }

  }


  public void createProduct(int productType, String productName, String description) {

    Product product = null;

    switch (productType) {
      case Product.PRODUCT_TYPE_CLOUD:
        product = new ProductCloud(productName);
        break;
      case Product.PRODUCT_TYPE_EMAIL:
        product = new ProductEmail(productName);
        break;
      case Product.PRODUCT_TYPE_VPN:
        product = new ProductVPN(productName);
        break;
      default:
        throw new IllegalArgumentException("Product type not found");
    }

    if (product != null) {
      product.setProductDescription(description);
      addEmptyProduct(product);
    } else {
      throw new NullPointerException("Product could not be added");
    }

  }


  private void addEmptyProduct(Product product) {

    if (!emptyProducts.contains(product)) {
      emptyProducts.add(product);

    } else {
      throw new IllegalArgumentException("The same product already exists");
    }

  }

  public Product getProductByID(int productID) {

    for (Product product : emptyProducts) {
      if (product.getID() == productID) {
        return product;
      }
    }
    return null;
  }

  public int getProductIDByProductName(String productName) {

    for (Product product : emptyProducts) {
      if (product.getProductName() == productName) {
        return product.getID();
      }
    }
    return 0;
  }

  public Product getProductByProductName(String productName) {

    for (Product product : emptyProducts) {
      if (product.getProductName() == productName) {
        return product;
      }
    }
    return null;

  }

  public Product getProductPlanByID(int productID, int planType) {

    for (Product product : assignedProducts) {

      if (product.getID() == productID && product.getPlanType() == planType) {
        return product;
      }
    }

    return null;
  }

  private boolean isSubscribedToProduct(int customerLocalID, int productID) {

    int numOfSubscriptions = 0;
    for (Subscription subscription : subscriptions) {

      if (numOfSubscriptions > 1) {
        return true;
      }
      if (subscription.getCustomerID() == customerLocalID
          && subscription.getProductID() == productID) {
        numOfSubscriptions++;
      }
    }

    return false;

  }


  public void subscribeToPlan(int customerLocalID, int productID, int planType) {

    Customer customer = getCustomerByLocalID(customerLocalID);
    Product productPlan = getProductPlanByID(productID, planType);

    //if (productPlan == null) System.out.println("is null");
    //System.out.println("is subscribed?: " + isSubscribedToProduct(customerLocalID, productID));

    boolean isNewCustomer = isNewCustomer(customerLocalID, productID);

    if (customer != null
        && productPlan != null
        && !isSubscribedToProduct(customerLocalID, productID)) {

      Subscription subscription = new Subscription(customerLocalID, productID, planType,
          isNewCustomer);
      subscriptions.add(subscription);

      double chargedAmount = productPlan.getPlanRate();
      if (isNewCustomer) {
        chargedAmount = 0;
      }

      LocalDateTime date = subscription.getValidUntil();
      YearMonth paidMonth = YearMonth.from(date);
      //if chargedAmount is 0, the first month is free for the customer and the invoice is "paid"
      customer.addInvoice(subscription.getSubscriptionID(), chargedAmount, paidMonth, null);

    }


  }

  private boolean isNewCustomer(int customerLocalID, int productID) {

    for (Subscription subscription : subscriptions) {

      if (subscription.getCustomerID() == customerLocalID
          && subscription.getProductID() == productID) {
        return false;
      }
    }

    return true;
  }

  public ArrayList<Subscription> getAllSubscriptions() {

    return subscriptions;

  }

  public ArrayList<Product> getAllProductsAssignedPlans() {

    return assignedProducts;
  }

  public ArrayList<Subscription> getSubscriptionsByCustomerLocalID(int customerLocalID) {

    ArrayList<Subscription> customerSubs = new ArrayList<Subscription>();

    for (Subscription sub : subscriptions) {
      if (sub.getCustomerID() == customerLocalID) {
        customerSubs.add(sub);
      }
    }

    return customerSubs;


  }

  public Subscription getSubscriptionsByID(int subscriptionID) {

    for (Subscription sub : subscriptions) {
      if (sub.getSubscriptionID() == subscriptionID) {
        return sub;
      }
    }

    return null;
  }

  public ArrayList<Subscription> getExpiredSubscriptions() {

    ArrayList<Subscription> expiredSubscriptions = new ArrayList<Subscription>();

    for (Subscription subscription : subscriptions) {
      if (subscription.getValidUntil().isBefore(LocalDateTime.now())) {
        expiredSubscriptions.add(subscription);
      }
    }

    return expiredSubscriptions;

  }

  public ArrayList<Subscription> getExpiredSubscriptionsByCustomer(int customerLocalID) {

    ArrayList<Subscription> expiredSubscriptions = new ArrayList<Subscription>();

    for (Subscription subscription : subscriptions) {
      if (subscription.getValidUntil().isBefore(LocalDateTime.now())
          && subscription.getCustomerID() == customerLocalID) {
        expiredSubscriptions.add(subscription);
      }
    }
    return expiredSubscriptions;

  }

  private void paymentReceived(int customerLocalID, int subscriptionID) {

    Subscription subscription = getSubscriptionsByID(subscriptionID);
    if (subscription == null) {
      throw new IllegalArgumentException("No subscription found.");
    }

    Customer customer = getCustomerByLocalID(customerLocalID);
    if (customer == null) {
      throw new IllegalArgumentException("No customer found.");
    }

    //Invoice invoice = getInvoiceByID(customerLocalID, );

    //if the latest invoice is paid, the subscription is active
    //

    Invoice invoice = customer.getThisMonthInvoiceUnpaidBySubscriptionID(subscriptionID);

    if (invoice != null) {
      if (!invoice.isPaid()) {
        subscription.paymentReceived();
      }
      throw new IllegalArgumentException("Subscription is payed for this month already");
    }

  }

  public void payForSubscription(int customerLocalID, int subscriptionID,
      PaymentMethod paymentMethod) {

    Customer customer = getCustomerByLocalID(customerLocalID);
    if (customer == null) {
      throw new IllegalArgumentException("No customer found.");
    }

    Subscription subscription = getSubscriptionsByID(subscriptionID);
    if (subscription == null) {
      throw new IllegalArgumentException("No subscription found.");
    }

    Invoice invoice = customer.getThisMonthInvoiceUnpaidBySubscriptionID(subscriptionID);

    if (invoice != null) {
      if (!invoice.isPaid()) {
        payForInvoice(customerLocalID, invoice.getInvoiceID(), paymentMethod);
      }
    } else {
      //no invoices from this month found, the subscription will be renewed
      renewSubscription(customerLocalID, subscriptionID, paymentMethod);
    }

  }


  private void renewSubscription(int customerLocalID, int subscriptionID,
      PaymentMethod paymentMethod) {

    System.out.println("This month is already payed, renewing subscription");

    Customer customer = getCustomerByLocalID(customerLocalID);
    Subscription subscription = getSubscriptionsByID(subscriptionID);

    if (customer != null
        && subscription != null) {

      int planType = subscription.getPlanType();
      int productID = subscription.getProductID();

      Product product = getProductPlanByID(productID, planType);
      double chargedAmount = product.getPlanRate();
      //System.out.println("chargedAmount: " + chargedAmount);

      subscription.paymentReceived();
      LocalDateTime date = subscription.getValidUntil();
      YearMonth paidMonth = YearMonth.from(date);

      System.out.println("creating new invoice");
      int invoiceID = customer
          .addInvoice(subscription.getSubscriptionID(), chargedAmount, paidMonth, paymentMethod);
      Invoice invoice = getInvoiceByID(customerLocalID, invoiceID);
      invoice.setPaid(true);


    }

  }

  private void payForInvoice(int customerLocalID, int invoiceID, PaymentMethod paymentMethod) {

    Customer customer = getCustomerByLocalID(customerLocalID);
    if (customer == null) {
      throw new IllegalArgumentException("No customer found.");
    }

    Invoice invoice = getInvoiceByID(customerLocalID, invoiceID);
    if (invoice == null) {
      throw new IllegalArgumentException("No invoice found.");
    }

    if (!invoice.isPaid()) {
      customer.payForInvoice(invoice.getInvoiceID(), paymentMethod);
      paymentReceived(customerLocalID, invoice.getSubscriptionID());
    } else {

      throw new IllegalArgumentException("This invoice was already paid.");
      //renewSubscription(customerLocalID, invoice.getSubscriptionID());
    }


  }


  public Invoice getInvoiceByID(int customerLocalID, int invoiceID) {

    Customer customer = getCustomerByLocalID(customerLocalID);

    if (customer != null) {
      ArrayList<Invoice> invoices = customer.getAllInvoices();
      for (Invoice invoice : invoices) {
        if (invoice.getInvoiceID() == invoiceID) {
          return invoice;
        }
      }
      return null;

    }
    throw new IllegalArgumentException("No customer under this ID has been found.");

  }

  //public createInvoices

  @Override
  public String toString() {
    System.out.println("Controller object printout: \n");

    String str = "Customers:\n";
    for (Customer customer : customers) {
      str += customer.toString();
    }

    str += "\nSubscriptions:\n";
    for (Subscription subscription : subscriptions) {
      str += subscription.toString();
    }

    str += "\nEmptyProducts:\n";
    for (Product emptyProduct : emptyProducts) {
      str += emptyProduct.toString();
    }

    str += "\nAssignedProducts:\n";
    for (Product assignedProduct : assignedProducts) {
      str += assignedProduct.toString();
    }

    return str;
  }





}
