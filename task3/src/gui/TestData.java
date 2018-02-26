package gui;

import client.MockupController;
import core.Controller;
import core.Customer;
import core.Product;

public class TestData {

  public static void generateTestData(Controller controller) {

    //create 6 customer

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
    
    controller.createCustomer(customer1);

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
    
    controller.createCustomer(customer2);
    
    Customer customer3 = new Customer();
    customer3.setForename("Hans");
    customer3.setSurname("Müller");
    customer3.setTitle("Heer");
    customer3.setFirstAddressRow("Kirchesteig 5");
    customer3.setSecondAddressRow("Kirchensteig 5");
    customer3.setPostcode("2020");
    customer3.setLocation("Hollabrunn");
    customer3.setCountry("AUT");
    customer3.setDeliveryAddress("Kirchensteig 5");
    customer3.setCellNumber("145287");
    customer3.setFaxNumber("145288");
    customer3.setCellNumber("066451245");
    customer3.setEmail("hans@müller.com");
    customer3.setWebsiteAddress("hans.müller.com");
    customer3.setPaymentCurrency("Euro");
    
    controller.createCustomer(customer3);
    
    Customer customer4 = new Customer();
    customer4.setForename("Lisa");
    customer4.setSurname("Böck");
    customer4.setTitle("Frau");
    customer4.setFirstAddressRow("Sandberg 6");
    customer4.setSecondAddressRow("Sandberg 7");
    customer4.setPostcode("5421");
    customer4.setLocation("Stockerau");
    customer4.setCountry("AUT");
    customer4.setDeliveryAddress("Sandberg 6");
    customer4.setCellNumber("124578");
    customer4.setFaxNumber("124579");
    customer4.setCellNumber("0660254811");
    customer4.setEmail("lisa@böck.com");
    customer4.setWebsiteAddress("lisa.böck.com");
    customer4.setPaymentCurrency("Euro");
    
    controller.createCustomer(customer4);
    
    Customer customer5 = new Customer();
    customer5.setForename("Martina");
    customer5.setSurname("Glanz");
    customer5.setTitle("Frau");
    customer5.setFirstAddressRow("Siedlung 53");
    customer5.setSecondAddressRow("Siedlung 54");
    customer5.setPostcode("1456");
    customer5.setLocation("Graz");
    customer5.setCountry("AUT");
    customer5.setDeliveryAddress("Siedlung 53");
    customer5.setCellNumber("258641");
    customer5.setFaxNumber("258642");
    customer5.setCellNumber("0667541248");
    customer5.setEmail("martina@glanz.com");
    customer5.setWebsiteAddress("martina.glanz.com");
    customer5.setPaymentCurrency("Euro");
    
    controller.createCustomer(customer5);
    
    Customer customer6 = new Customer();
    customer6.setForename("Beate");
    customer6.setSurname("Freulich");
    customer6.setTitle("Frau");
    customer6.setFirstAddressRow("Baumstraße 7");
    customer6.setSecondAddressRow("Baumstraße 8");
    customer6.setPostcode("4587");
    customer6.setLocation("Linz");
    customer6.setCountry("AUT");
    customer6.setDeliveryAddress("Baumstraße 7");
    customer6.setCellNumber("457812");
    customer6.setFaxNumber("457813");
    customer6.setCellNumber("066451248");
    customer6.setEmail("beate@freulich.com");
    customer6.setWebsiteAddress("beate.freulich.com");
    customer6.setPaymentCurrency("Euro");

    
    controller.createCustomer(customer6);
    
    /*
    //print customer
    System.out.println("kunde 1:");
    System.out.println(customer1.getLocalID());
    System.out.println(customer1.getForename());
    
    System.out.println("kunde 2:");
    System.out.println(customer2.getLocalID());
    System.out.println(customer2.getForename());
    
    System.out.println("kunde 3:");
    System.out.println(customer3.getLocalID());
    System.out.println(customer3.getForename());
    
    System.out.println("kunde 4:");
    System.out.println(customer4.getLocalID());
    System.out.println(customer4.getForename());
    
    System.out.println("kunde 5:");
    System.out.println(customer5.getLocalID());
    System.out.println(customer5.getForename());
    
    System.out.println("kunde 6:");
    System.out.println(customer6.getLocalID());
    System.out.println(customer6.getForename());
    */
    
    //create 3 Products
    controller.createProduct(Product.PRODUCT_TYPE_EMAIL, "eyeMail",  "Pro mail for pro users");
    Product product = controller.getProductByProductName("eyeMail");
    
    controller.createProduct(Product.PRODUCT_TYPE_CLOUD, "eyeCloud", "Pro cloud for pro users");
    Product product2 = controller.getProductByProductName("eyeCloud");
    
    controller.createProduct(Product.PRODUCT_TYPE_VPN, "eyeVPN",  "Pro vpn for pro users");
    Product product3 = controller.getProductByProductName("eyeVPN");
    
    
    //create 2 Plans for Product 1
    controller.createPlanForProduct(product.getID(),
        Product.PLAN_TYPE_STANDARD,
        "standard",
        "",
        10);
    
    controller.createPlanForProduct(product.getID(),
        Product.PLAN_TYPE_PRO,
        "Pro",
        "",
        20);
    
    
    //create 1 Plan for Product 2
    controller.createPlanForProduct(product2.getID(),
        Product.PLAN_TYPE_PREMIUM,
        "Premium",
        "",
        30);
    
    
    //create 3 Plans for Product 3
    controller.createPlanForProduct(product3.getID(),
        Product.PLAN_TYPE_STANDARD,
        "standard",
        "",
        10);
    
    controller.createPlanForProduct(product3.getID(),
        Product.PLAN_TYPE_PRO,
        "Pro",
        "",
        20);
    
    controller.createPlanForProduct(product3.getID(),
        Product.PLAN_TYPE_PREMIUM,
        "Premium",
        "",
        30);



    controller.subscribeToPlan(customer1.getLocalID(),product.getID(),Product.PLAN_TYPE_STANDARD);
    controller.subscribeToPlan(customer1.getLocalID(),product2.getID(),Product.PLAN_TYPE_PREMIUM);
    controller.subscribeToPlan(customer2.getLocalID(),product.getID(),Product.PLAN_TYPE_STANDARD);
    controller.subscribeToPlan(customer3.getLocalID(),product2.getID(),Product.PLAN_TYPE_PREMIUM);
    controller.subscribeToPlan(customer4.getLocalID(),product2.getID(),Product.PLAN_TYPE_PREMIUM);
    controller.subscribeToPlan(customer5.getLocalID(),product3.getID(),Product.PLAN_TYPE_STANDARD);
    controller.subscribeToPlan(customer5.getLocalID(),product.getID(),Product.PLAN_TYPE_PRO);
    controller.subscribeToPlan(customer5.getLocalID(),product2.getID(),Product.PLAN_TYPE_PREMIUM);


    /*
    //print all Product and their plans
    for (Product p : controller.getAllProductsAssignedPlans()){

      System.out.println("ProductID: " +  p.getID() + ", ProductName: "
              +  p.getProductName() + ", Plan: " + p.getPlanType() +  ", PlanName: " + p.getPlanName());
    }
    */

  }

  public static void main(String[] args){
    TestData.generateTestData(new Controller());
  }

}
