package client;

import core.*;

import java.util.ArrayList;

/**
 * Created by:
 * Jakub Gawrylkowicz a1326002
 * Roman Karaba a1301624
 *
 * Edited by:
 * David Bakic a1148885
 * Roman Karaba a1301624
 *
 * Implementation of Abstract Factory Pattern, ConcreteProduct. This class is a subclass of the AbstractController,
 * which is also a AbstractProduct in term of the pattern design. In the constructor we instantiate the Controller class
 * which controls all the core functionality of the program. 
 *
 */
public class MockupController extends AbstractController {

    Controller controller;


    public MockupController(){
        this.controller = initController();
    }


    //TODO implementations of the abstract classes
    // all implementations of the abstract classes of the AbstractController need to be here
    // first you need to define them in the AbstractController

    public void test(){
        controller.test();
    }


    @Override
    void createCustomer(Customer newCustomer) {
        controller.createCustomer(newCustomer);

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



    @Override
    void createInvoiceForCustomer(Invoice newInvoice, Customer customer) {
        //controller.createInvoiceForCustomer(newInvoice, customer);
    }

    @Override
    void createInvoiceForCustomerByLocalID(Invoice newInvoice, int customerLocalID) {
        //controller.createInvoiceForCustomerByLocalID(newInvoice, customerLocalID);
    }

    @Override
    void createInvoiceForCustomerByRemoteID(Invoice newInvoice, int customerRemoteID) {

    }

    @Override
    ArrayList<Invoice> getUnpaidInvoiceByCustomer(Customer customer) {
        return controller.getUnpaidInvoiceByCustomer(customer);
    }

    @Override
    public String toString(){

        return controller.toString();
    }
}
