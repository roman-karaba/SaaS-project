package client;

import core.*;

import java.util.ArrayList;

/**
 * Created by:
 * Jakub Gawrylkowicz a1326002
 * Roman Karaba a1301624
 *
 * Implementation of Abstract Factory Pattern, ConcreteProduct. This class is a subclass of the AbstractController,
 * which is also a AbstractProduct in term of the pattern design. In the constructor we instantiate the Controller class
 * which controls all the core functionality of the program.
 *
 *
 */
public class FastBillController extends AbstractController {


    //TODO establishing connection to the FastBIll API here

    Controller controller;


    public FastBillController(){
        this.controller = initController();
    }

    public void getFastBillData(){
      controller.getFastBillCustomers();
    }

    public Customer getCustomerByID(int id){
      return controller.getCustomerByRemoteID(id);
    }


    //TODO implementations of the abstract classes
    // all implementations of the abstract classes of the AbstractController need to be here
    // first you need to define them in the AbstractController

    public void test(){
        controller.test();
    }

    @Override
    protected void createCustomer(Customer c) {
        controller.createFastbillCustomer(c);
    }


  public void createInvoice(Customer customer, double price){
    controller.createFastBillInvoice(customer, "Invoice for Product", 20 , price, new PaymentPayPal("abc@abc.com"));
  }

    @Override
    protected void createInvoiceForCustomer(Invoice i, Customer c) {

    }

    @Override
    void createInvoiceForCustomerByLocalID(Invoice newInvoice, int customerLocalID) {
        controller.createFastBillInvoice(controller.getCustomerByLocalID(customerLocalID), "Test", 20, 20, new PaymentPayPal("acb@net.com"));

    }

    @Override
    void createInvoiceForCustomerByRemoteID(Invoice newInvoice, int customerRemoteID) {

    }

    @Override
    protected ArrayList<Invoice> getUnpaidInvoiceByCustomer(Customer c) {
        return null;
    }



    public ArrayList<Customer> getCustomerList(){
        return controller.getCustomers();
    }

    public ArrayList<Product> getSoldProductList(){
        return controller.getSoldProducts();

    }


    public ArrayList<Product> getNotSoldProductList(){
        return controller.getNotSoldProducts();

    }

    public ArrayList<Subscription> getSubscriptionList(){
        return controller.getSubscriptions();
    }


}
