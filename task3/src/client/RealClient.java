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
 * Implementation of the proxy pattern, RealObject. The class loads and accesses dependent on the parameter "type"
 * the concrete controller of the AbstractController class.
 *
 */


public class RealClient extends Client {

    AbstractController controller= null;


    public RealClient(int type){
        switch (type){
            case Client.MOCKUP:
                this.controller = getMockupController();
                break;
            case Client.FASTBILL:
                this.controller = getFastBillController();
                break;
            default: break;
        }

    }

    @Override
    protected MockupController getMockupController() {

        //mockup
         AbstractClientFactory abstractClientFactory = new MockupClientFactory();
        return abstractClientFactory.createMockupController();
    }


    protected FastBillController getFastBillController() {

        AbstractClientFactory abstractClientFactory = new FastBillClientFactory();
        return abstractClientFactory.createFastBillController();
    }


    //TODO the real implementation of the client methods go in here
    //test method that first is being abstracted in the AbstractController and
    //is implemented in the Mockup and FastBill Controllers

    protected void test(){controller.test();}


    @Override
    protected void createCustomer(Customer newCustomer) {
        controller.createCustomer(newCustomer);
    }


    @Override
    protected void createInvoiceForCustomer(Invoice newInvoice, Customer customer) {
        controller.createInvoiceForCustomer(newInvoice, customer);
    }

    @Override
    void createInvoiceForCustomerByRemoteID(Invoice newInvoice, int customerRemoteID) {
        controller.createInvoiceForCustomerByRemoteID(newInvoice, customerRemoteID);
    }

    @Override
    void createInvoiceForCustomerByLocalID(Invoice newInvoice, int customerLocalID) {
        controller.createInvoiceForCustomerByLocalID(newInvoice, customerLocalID);
    }

    @Override
    protected ArrayList<Invoice> getUnpaidInvoiceByCustomer(Customer customer) {
        return controller.getUnpaidInvoiceByCustomer(customer);
    }
}
