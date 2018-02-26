package client;

import core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by:
 * Jakub Gawrylkowicz a1326002
 * Roman Karaba a1301624
 *
 * Implementation of Abstract Factory Pattern, AbstractProduct. This class defines all methods for all the controllers that
 * will be accessed by the Client class later on.
 *
 * For instantiation the Controller class uses a Singleton pattern
 */
public abstract class AbstractController {

    private static Controller controller;

    public Controller initController(){

        if(controller != null)
            return controller;
        else
            controller = new Controller();

        return controller;
    }

    //TODO abstract methods for the controller go here
    //abstract methods need to be created here first, which later
    //will be implemented in the Controller class.


    //this abstract test method is being implemented in the concrete clients and is accessed in the Client class.
    abstract void test();

    abstract void createCustomer(Customer c);

    abstract void createInvoiceForCustomer(Invoice i, Customer c);
    abstract void createInvoiceForCustomerByRemoteID(Invoice newInvoice, int customerRemoteID);
    abstract void createInvoiceForCustomerByLocalID(Invoice newInvoice, int customerLocalID);
    abstract ArrayList<Customer> getCustomerList();
    abstract ArrayList<Product> getSoldProductList();
    abstract ArrayList<Product> getNotSoldProductList();
    abstract ArrayList<Subscription> getSubscriptionList();


    abstract ArrayList<Invoice> getUnpaidInvoiceByCustomer(Customer c);
}
