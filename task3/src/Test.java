import client.Client;
import client.ProxyClient;
import client.RealClient;
import core.*;

import java.time.YearMonth;
import java.util.ArrayList;

/**
 * Created by jakub on 09/06/2017.
 */
public class Test {

    public static void main (String[] args){


        /*
        //creating new client
            ProxyClient proxyClient = new ProxyClient(Client.MOCKUP);


        //adding new customers
            Customer customer1 = new Customer();
            customer1.setForename("Max");
            customer1.setSurname("Mustermann");


            Customer customer2 = new Customer();
            customer2.setForename("Max");
            customer2.setSurname("Mustermann");



            proxyClient.createCustomer(customer1);
            proxyClient.createCustomer(customer2);


        //adding new invoices
            Invoice invoice1 = new Invoice();
            Invoice invoice2 = new Invoice();
        //you can also create a empty customer object with one of the ID and the customer object will be returned
            proxyClient.createInvoiceForCustomer(invoice1,customer2);
            proxyClient.createInvoiceForCustomerByLocalID(invoice2, 100001);


        // proxyClient.createProduct()


        ArrayList<Invoice> invoices = proxyClient.getUnpaidInvoiceByCustomer(customer2);

        System.out.println(" size: " + invoices.size() + " id: " + customer2.getLocalID());
        System.out.println(invoices.toString());

        */


        Controller controller = new Controller();

        //System.out.println("_____________________________________");
        //System.out.println("ADD NEW CUSTOMER");
        //System.out.println();

        //TODO create customers by names, not by object
        Customer customer = new Customer();
        customer.setForename("Max");
        customer.setSurname("Mustermann");
        controller.createCustomer(customer);
       //System.out.println("CustomerID: " + controller.getCustomerByLocalID(customer.getLocalID()).getLocalID());
        //System.out.println("New customer created");
        //System.out.println("_____________________________________");
        //System.out.println("ADD NEW EMPTY PRODUCT");
        //System.out.println();

        controller.createProduct(Product.PRODUCT_TYPE_EMAIL, "eyeCloud",  "Pro cloud for pro users");
        Product product = controller.getProductByProductName("eyeCloud");

        //System.out.println("Product: " + product.getProductName() + ", productID: " + product.getID());
        //System.out.println("New empty product without assigned plans created");


        //System.out.println("_____________________________________");
        //System.out.println("ADD NEW PLAN TO A EXISTING PRODUCT");
        //System.out.println();

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


        //System.out.println("New plan assigned to the plan");

        //System.out.println("_____________________________________");
        //System.out.println("PRINT ALL PLANS");
        //System.out.println();

        //System.out.println("All assigned plans: ");

        /*for (Product p : controller.getAllProductsAssignedPlans()){

            System.out.println("ProductID: " +  p.getID() + ", ProductName: "
                    +  p.getProductName() + ", Plan: " + p.getPlanType() +  ", PlanName: " + p.getPlanName());

        }*/



        System.out.println("SUBSCRIPTIONS");
        System.out.println("_____________________________________");
        //System.out.println();

        //System.out.println("Product planID: " + controller.getProductPlanByID(product.getID(), Product.PLAN_TYPE_STANDARD).getPlanName());

        controller.subscribeToPlan(customer.getLocalID(), product.getID(), Product.PLAN_TYPE_STANDARD);
        System.out.println("Subscription successful");

        System.out.println("_____________________________________");
        //System.out.println("Customer subscriptions: " + controller.getSubscriptionsByCustomerLocalID(customer.getLocalID()).toString());
        System.out.println("All customer subscriptions: ");

        ArrayList<Subscription> subs = controller.getSubscriptionsByCustomerLocalID(customer.getLocalID());

        for (Subscription sub : subs){
            sub.printToCMD();
        }

        System.out.println();
        System.out.println("INVOICES");
        System.out.println("_____________________________________");



        ArrayList<Invoice> invs = customer.getAllInvoices();

        for (Invoice i : invs){
            i.printToCMD();
        }


        PaymentMethod paymentMethod = new PaymentPayPal("test@gmail.com");


        System.out.println();
        System.out.println("AFTER PAYMENT");
        System.out.println("_____________________________________");



        customer.addPaymentMethod(paymentMethod);
        //customer.payForInvoice(700000, paymentMethod);

        //controller.payForInvoice(customer.getLocalID(), 700000, paymentMethod);
        controller.payForSubscription(customer.getLocalID(), 400000, paymentMethod);
        controller.payForSubscription(customer.getLocalID(), 400000, paymentMethod);

        //controller.payForInvoice(customer.getLocalID(), 700000, paymentMethod);


        Subscription subscription = controller.getSubscriptionsByID(400000);


        System.out.println();
        System.out.println("SUBSCRIPTIONS");
        System.out.println("_____________________________________");


        for (Subscription sub : subs){
            sub.printToCMD();
        }



        System.out.println();
        System.out.println("INVOICES");
        System.out.println("_____________________________________");

        invs = customer.getAllInvoices();

        for (Invoice i : invs){
            System.out.println("__________________________");
            i.printToCMD();
        }



        //System.out.println(YearMonth.now().toString());
    }
}
