


import core.Controller;
import core.Customer;
import core.Invoice;
import core.PaymentMethod;
import core.Product;
import core.Subscription;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Konrad Laaber 1301118
 */
public class JunitTest {


  public JunitTest() {}


  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}


  /**
   * Test of createCustomer method, of class Controller.
   */
  @Test
  public void testCreateCustomer() {
    System.out.println("createCustomer");
    Customer newCustomer = new Customer();
    newCustomer.setForename("Martina");
    newCustomer.setSurname("Glanz");
    newCustomer.setTitle("Frau");
    newCustomer.setFirstAddressRow("Siedlung 53");
    newCustomer.setSecondAddressRow("Siedlung 54");
    newCustomer.setPostcode("1456");
    newCustomer.setLocation("Graz");
    newCustomer.setCountry("AUT");
    newCustomer.setDeliveryAddress("Siedlung 53");
    newCustomer.setCellNumber("258641");
    newCustomer.setFaxNumber("258642");
    newCustomer.setCellNumber("0667541248");
    newCustomer.setEmail("martina@glanz.com");
    newCustomer.setWebsiteAddress("martina.glanz.com");
    newCustomer.setPaymentCurrency("Euro");
    Controller instance = new Controller();
    instance.createCustomer(newCustomer);

  }

  /**
   * Test of getCustomers method, of class Controller.
   */
  @Test
  public void testGetCustomers() {
    System.out.println("getCustomers");

    Customer newCustomer = new Customer();
    newCustomer.setForename("Martina");
    newCustomer.setSurname("Glanz");
    newCustomer.setTitle("Frau");
    newCustomer.setFirstAddressRow("Siedlung 53");
    newCustomer.setSecondAddressRow("Siedlung 54");
    newCustomer.setPostcode("1456");
    newCustomer.setLocation("Graz");
    newCustomer.setCountry("AUT");
    newCustomer.setDeliveryAddress("Siedlung 53");
    newCustomer.setCellNumber("258641");
    newCustomer.setFaxNumber("258642");
    newCustomer.setCellNumber("0667541248");
    newCustomer.setEmail("martina@glanz.com");
    newCustomer.setWebsiteAddress("martina.glanz.com");
    newCustomer.setPaymentCurrency("Euro");
    Controller instance = new Controller();
    instance.createCustomer(newCustomer);


    ArrayList<Customer> expResult = new ArrayList<Customer>();
    expResult.add(newCustomer);
    ArrayList<Customer> result = instance.getCustomers();
    assertEquals(expResult, result);

  }

  /**
   * Test of getSubscriptions method, of class Controller.
   */
  @Test
  public void testGetSubscriptions() {
    System.out.println("getSubscriptions");
    
    Customer newCustomer = new Customer();
    newCustomer.setForename("Martina");
    newCustomer.setSurname("Glanz");
    newCustomer.setTitle("Frau");
    newCustomer.setFirstAddressRow("Siedlung 53");
    newCustomer.setSecondAddressRow("Siedlung 54");
    newCustomer.setPostcode("1456");
    newCustomer.setLocation("Graz");
    newCustomer.setCountry("AUT");
    newCustomer.setDeliveryAddress("Siedlung 53");
    newCustomer.setCellNumber("258641");
    newCustomer.setFaxNumber("258642");
    newCustomer.setCellNumber("0667541248");
    newCustomer.setEmail("martina@glanz.com");
    newCustomer.setWebsiteAddress("martina.glanz.com");
    newCustomer.setPaymentCurrency("Euro");
    Controller instance = new Controller();
    instance.createCustomer(newCustomer);

    instance.createProduct(Product.PRODUCT_TYPE_EMAIL, "eyeMail", "Pro mail for pro users");
    Product product = instance.getProductByProductName("eyeMail");

    instance.createPlanForProduct(product.getID(), Product.PLAN_TYPE_STANDARD, "standard", "", 10);

    instance.subscribeToPlan(newCustomer.getLocalID(), product.getID(), Product.PLAN_TYPE_STANDARD);

    
    ArrayList<Subscription> expResult = new ArrayList<Subscription>();
    ArrayList<Subscription> subs =
        instance.getSubscriptionsByCustomerLocalID(newCustomer.getLocalID());

    for (Subscription sub : subs) {
      expResult.add(sub);
    }
   
    ArrayList<Subscription> result = instance.getSubscriptions();
    assertEquals(expResult, result);
  }


  /**
   * Test of getUnpaidInvoiceByCustomer method, of class Controller.
   */
  @Test
  public void testGetUnpaidInvoiceByCustomer() {
    System.out.println("getUnpaidInvoiceByCustomer");
    
    Customer newCustomer = new Customer();
    newCustomer.setForename("Martina");
    newCustomer.setSurname("Glanz");
    newCustomer.setTitle("Frau");
    newCustomer.setFirstAddressRow("Siedlung 53");
    newCustomer.setSecondAddressRow("Siedlung 54");
    newCustomer.setPostcode("1456");
    newCustomer.setLocation("Graz");
    newCustomer.setCountry("AUT");
    newCustomer.setDeliveryAddress("Siedlung 53");
    newCustomer.setCellNumber("258641");
    newCustomer.setFaxNumber("258642");
    newCustomer.setCellNumber("0667541248");
    newCustomer.setEmail("martina@glanz.com");
    newCustomer.setWebsiteAddress("martina.glanz.com");
    newCustomer.setPaymentCurrency("Euro");
    Controller instance = new Controller();
    instance.createCustomer(newCustomer);

    instance.createProduct(Product.PRODUCT_TYPE_EMAIL, "eyeMail", "Pro mail for pro users");
    Product product = instance.getProductByProductName("eyeMail");

    instance.createPlanForProduct(product.getID(), Product.PLAN_TYPE_STANDARD, "standard", "", 10);

    instance.subscribeToPlan(newCustomer.getLocalID(), product.getID(), Product.PLAN_TYPE_STANDARD);
   
    ArrayList<Invoice> invs = newCustomer.getAllInvoices();
    invs = newCustomer.getAllInvoices();
    
    
    ArrayList<Invoice> expResult = new ArrayList<Invoice>();
    
    for (Invoice i : invs){
        if (i.isPaid()==false){
          expResult.add(i);
        }
    }
    ArrayList<Invoice> result = instance.getUnpaidInvoiceByCustomer(newCustomer);
    assertEquals(expResult, result);
  }

  @Test
  public void testCreatePlanForProductEmail() {
    System.out.println("createPlanForProduct");
    Controller instance = new Controller();
    instance.createProduct(Product.PRODUCT_TYPE_EMAIL, "eyeMail", "Pro mail for pro users");
    Product product = instance.getProductByProductName("eyeMail");
    
    int planType = 3;
    String planName = "standard";
    String planDescription = "";
    double planRate = 10;
    
    int planType2 = 5;
    String planName2 = "Pro";
    String planDescription2 = "";
    double planRate2 = 20;
    
    
    instance.createPlanForProduct(product.getID(), planType, planName, planDescription, planRate);
    instance.createPlanForProduct(product.getID(), planType2, planName2, planDescription2, planRate2);
  }

  @Test
  public void testCreatePlanForProductCloud() {
    System.out.println("createPlanForProduct");
    Controller instance = new Controller();
    instance.createProduct(Product.PRODUCT_TYPE_CLOUD, "eyeCloud", "Pro cloud for pro users");
    Product product = instance.getProductByProductName("eyeCloud");
   
    int planType = 4;
    String planName = "Premium";
    String planDescription = "";
    double planRate = 30;
    
    instance.createPlanForProduct(product.getID(), planType, planName, planDescription, planRate);
  }
  
  @Test
  public void testCreatePlanForProductVPN() {
    System.out.println("createPlanForProduct");
    Controller instance = new Controller();
    instance.createProduct(Product.PRODUCT_TYPE_VPN, "eyeVPN",  "Pro vpn for pro users");
    Product product = instance.getProductByProductName("eyeVPN");
    
    
    int planType = 3;
    String planName = "standard";
    String planDescription = "";
    double planRate = 10;
    
    int planType2 = 4;
    String planName2 = "Premium";
    String planDescription2 = "";
    double planRate2 = 30;
    
    int planType3 = 5;
    String planName3 = "Pro";
    String planDescription3 = "";
    double planRate3 = 20;
    
    
    instance.createPlanForProduct(product.getID(), planType, planName, planDescription, planRate);
    instance.createPlanForProduct(product.getID(), planType2, planName2, planDescription2, planRate2);
    instance.createPlanForProduct(product.getID(), planType3, planName3, planDescription3, planRate3);
  }

  /**
   * Test of createProduct method, of class Controller.
   */
  @Test
  public void testCreateProductMail() {
    System.out.println("createProduct Mail");
    int productType = 2;
    String productName = "eyeMail";
    String description = "Pro mail for pro users";

    Controller instance = new Controller();

    instance.createProduct(productType, productName, description);

  }

  @Test
  public void testCreateProductCloud() {
    System.out.println("createProduct Cloud");
    int productType = 0;
    String productName = "eyeCloud";
    String description = "Pro cloud for pro users";

    Controller instance = new Controller();

    instance.createProduct(productType, productName, description);
  }

  @Test
  public void testCreateProductVPN() {
    System.out.println("createProduct VPN");
    int productType = 1;
    String productName = "eyeVPN";
    String description = "VPN cloud for pro users";

    Controller instance = new Controller();

    instance.createProduct(productType, productName, description);

  }

  /**
   * Test of subscribeToPlan method, of class Controller.
   */
  @Test
  public void testSubscribeToPlan() {
    System.out.println("subscribeToPlan");

    Customer newCustomer = new Customer();
    newCustomer.setForename("Martina");
    newCustomer.setSurname("Glanz");
    newCustomer.setTitle("Frau");
    newCustomer.setFirstAddressRow("Siedlung 53");
    newCustomer.setSecondAddressRow("Siedlung 54");
    newCustomer.setPostcode("1456");
    newCustomer.setLocation("Graz");
    newCustomer.setCountry("AUT");
    newCustomer.setDeliveryAddress("Siedlung 53");
    newCustomer.setCellNumber("258641");
    newCustomer.setFaxNumber("258642");
    newCustomer.setCellNumber("0667541248");
    newCustomer.setEmail("martina@glanz.com");
    newCustomer.setWebsiteAddress("martina.glanz.com");
    newCustomer.setPaymentCurrency("Euro");
    Controller instance = new Controller();
    instance.createCustomer(newCustomer);

    instance.createProduct(Product.PRODUCT_TYPE_EMAIL, "eyeMail", "Pro mail for pro users");
    Product product = instance.getProductByProductName("eyeMail");

    instance.createPlanForProduct(product.getID(), Product.PLAN_TYPE_STANDARD, "standard", "", 10);

    instance.subscribeToPlan(newCustomer.getLocalID(), product.getID(), product.getPlanType());

  }

  /**
   * Test of getAllSubscriptions method, of class Controller.
   */
  @Test
  public void testGetAllSubscriptions() {
    System.out.println("getAllSubscriptions");
    
    Controller instance = new Controller();
    Customer customer1 = new Customer();
    customer1.setForename("Max");
    customer1.setSurname("Mustermann");
    customer1.setTitle("Heer");
    customer1.setFirstAddressRow("Lange Straße 2");
    customer1.setSecondAddressRow("Lange Straße 3");
    customer1.setPostcode("1200");
    customer1.setLocation("Vienna");
    customer1.setCountry("AUT");
    customer1.setDeliveryAddress("Lange Straße 2");
    customer1.setCellNumber("0660548711");
    customer1.setFaxNumber("0660548712");
    customer1.setCellNumber("0660548711");
    customer1.setEmail("max@mustermann.com");
    customer1.setWebsiteAddress("max.mustermann.com");
    customer1.setPaymentCurrency("Euro");
    
    instance.createCustomer(customer1);

    Customer customer2 = new Customer();
    customer2.setForename("Peter");
    customer2.setSurname("Lustig");
    customer2.setTitle("Heer");
    customer2.setFirstAddressRow("Hauptraße 20");
    customer2.setSecondAddressRow("Hauptstraße 21");
    customer2.setPostcode("2054");
    customer2.setLocation("Haugsdorf");
    customer2.setCountry("AUT");
    customer2.setDeliveryAddress("Hauptraße 20");
    customer2.setCellNumber("224875");
    customer2.setFaxNumber("224876");
    customer2.setCellNumber("0664574238");
    customer2.setEmail("peter@lustig.com");
    customer2.setWebsiteAddress("peter.lustig.com");
    customer2.setPaymentCurrency("Euro");
    
    instance.createCustomer(customer2);
    
    instance.createProduct(Product.PRODUCT_TYPE_CLOUD, "eyeCloud", "Pro cloud for pro users");
    Product product = instance.getProductByProductName("eyeCloud");
    
    instance.createPlanForProduct(product.getID(),
        Product.PLAN_TYPE_STANDARD,
        "standard",
        "",
        10);
    
    instance.subscribeToPlan(customer1.getLocalID(),product.getID(),Product.PLAN_TYPE_PREMIUM);
    instance.subscribeToPlan(customer2.getLocalID(),product.getID(),Product.PLAN_TYPE_PREMIUM);
    
    ArrayList<Subscription> expResult = new ArrayList<Subscription>();
    
    ArrayList<Subscription> subs =
        instance.getSubscriptionsByCustomerLocalID(customer1.getLocalID());

    for (Subscription sub : subs) {
      expResult.add(sub);
    }
    
    ArrayList<Subscription> subs2 =
        instance.getSubscriptionsByCustomerLocalID(customer2.getLocalID());

    for (Subscription sub2 : subs2) {
      expResult.add(sub2);
    }
    
    ArrayList<Subscription> result = instance.getAllSubscriptions();
    assertEquals(expResult, result);
  }


  /**
   * Test of payForSubscription method, of class Controller.
   */
  @Test
  public void testPayForSubscription() {
    System.out.println("payForSubscription");

    Customer newCustomer = new Customer();
    newCustomer.setForename("Martina");
    newCustomer.setSurname("Glanz");
    newCustomer.setTitle("Frau");
    newCustomer.setFirstAddressRow("Siedlung 53");
    newCustomer.setSecondAddressRow("Siedlung 54");
    newCustomer.setPostcode("1456");
    newCustomer.setLocation("Graz");
    newCustomer.setCountry("AUT");
    newCustomer.setDeliveryAddress("Siedlung 53");
    newCustomer.setCellNumber("258641");
    newCustomer.setFaxNumber("258642");
    newCustomer.setCellNumber("0667541248");
    newCustomer.setEmail("martina@glanz.com");
    newCustomer.setWebsiteAddress("martina.glanz.com");
    newCustomer.setPaymentCurrency("Euro");
    Controller instance = new Controller();
    instance.createCustomer(newCustomer);

    instance.createProduct(Product.PRODUCT_TYPE_EMAIL, "eyeMail", "Pro mail for pro users");
    Product product = instance.getProductByProductName("eyeMail");

    instance.createPlanForProduct(product.getID(), Product.PLAN_TYPE_STANDARD, "standard", "", 10);

    instance.subscribeToPlan(newCustomer.getLocalID(), product.getID(), Product.PLAN_TYPE_STANDARD);

    int subscriptionID = 0;

    ArrayList<Subscription> subs =
        instance.getSubscriptionsByCustomerLocalID(newCustomer.getLocalID());

    for (Subscription sub : subs) {
      subscriptionID = sub.getSubscriptionID();
    }
    
    System.out.println(subscriptionID);
    PaymentMethod paymentMethod = null;
    instance.payForSubscription(newCustomer.getLocalID(), subscriptionID, paymentMethod);
  }

 

}

