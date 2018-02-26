package client;
import core.Controller;
import core.Customer;
import core.Invoice;

import java.util.ArrayList;

/**
 * Created by:
 * Jakub Gawrylkowicz a1326002
 * Roman Karaba a1301624
 *
 * The client is used to access via the abstract factory pattern the instance of the AbstractController controller:
 *        * MockupController, which is used for testing purposes only
 *        * FastBillController, which is connects to the FastBill API
 *
 * Furthermore, the client uses the proxy pattern to avoid creating the "expensive" RealClient over and over again.
 *
 * Implementation of the proxy pattern, Object. The Client is a abstract superclass for both RealClient and ProxyClient
 * classes and provides abstract methods for both of them.
 */

public abstract class Client {

    public static final int MOCKUP = 0;
    public static final int FASTBILL = 1;

    public Client(){}

    protected abstract MockupController getMockupController();
    protected abstract FastBillController getFastBillController();

    //TODO abstract methods for accessing the controllers go in here

    abstract void test();

    abstract void createCustomer(Customer c);

    abstract void createInvoiceForCustomer(Invoice i, Customer c);
    abstract void createInvoiceForCustomerByLocalID(Invoice newInvoice, int customerLocalID);
    abstract void createInvoiceForCustomerByRemoteID(Invoice newInvoice, int customerRemoteID);

    abstract ArrayList<Invoice> getUnpaidInvoiceByCustomer(Customer c);
    // TODO abstract ArrayList<Invoice> getUnpaidInvoiceByCustomerByLocalID(int customerLocalID);



}
