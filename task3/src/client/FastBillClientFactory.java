package client;

/**
 * Created by:
 * Jakub Gawrylkowicz a1326002
 * Roman Karaba a1301624
 *
 * Implementation of Abstract Factory Pattern, ConcreteFactory. This class returns the controller which is responsible
 * for accessing the FastBill api.
 *
 */
public class FastBillClientFactory extends AbstractClientFactory {

    //this method needs to be implemented in this class and return a null value
    // since we use abstract methods in the superclass.
    @Override
    public MockupController createMockupController() {
        return null;
    }


    @Override
    public FastBillController createFastBillController() {
        return new FastBillController();
    }
}
