package client;


import core.Customer;
import core.Invoice;

import java.util.ArrayList;

/**
 * Created by:
 * Jakub Gawrylkowicz a1326002
 * Roman Karaba a1301624
 *
 * Implementation of the proxy pattern, ProxyObject. The ProxyClient class avoids creating new RealObject class and
 * reduces the memory allocation, so the RealClient needs to be loaded only once during the runtime.
 *
 **/

public class ProxyClient extends Client  {

    private RealClient realClient;
    private int clientType;

    public ProxyClient(int clientType){
        this.clientType = clientType;
    }

    //TODO proxy methods accessing the RealClient go in here

    public void test(){
        if(realClient == null){
            realClient = new RealClient(this.clientType);
        }

        realClient.test();
    }

    protected MockupController getMockupController() {
        return null;
    }

    @Override
    protected FastBillController getFastBillController() {
        return null;
    }

    @Override
    public void createCustomer(Customer newCustomer) {

        if(realClient == null){
            realClient = new RealClient(this.clientType);
        }
        realClient.createCustomer(newCustomer);
    }


    @Override
    public void createInvoiceForCustomer(Invoice newInvoice, Customer customer) {

        if(realClient == null){
            realClient = new RealClient(this.clientType);
        }

        realClient.createInvoiceForCustomer(newInvoice, customer);
    }

    @Override
    public void createInvoiceForCustomerByLocalID(Invoice newInvoice, int customerLocalID) {
        if(realClient == null){
            realClient = new RealClient(this.clientType);
        }
        realClient.createInvoiceForCustomerByLocalID(newInvoice, customerLocalID);
    }

    @Override
    void createInvoiceForCustomerByRemoteID(Invoice newInvoice, int customerRemoteID) {
        if(realClient == null){
            realClient = new RealClient(this.clientType);
        }
        realClient.createInvoiceForCustomerByRemoteID(newInvoice, customerRemoteID);
    }

    @Override
    public ArrayList<Invoice> getUnpaidInvoiceByCustomer(Customer customer) {

        if(realClient == null){
            realClient = new RealClient(this.clientType);
        }

        return realClient.getUnpaidInvoiceByCustomer(customer);
    }

}
