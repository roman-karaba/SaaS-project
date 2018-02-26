package client;

import core.Controller;

/**
 * Created by:
 * Jakub Gawrylkowicz a1326002
 * Roman Karaba a1301624
 *
 * Implementation of Abstract Factory Pattern, ConcreteFactory. This class returns the controller which accesses the test
 * client.
 *
 */
public class MockupClientFactory extends AbstractClientFactory {

    @Override
    public MockupController createMockupController() {
        return new MockupController();
    }

    //this method needs to be implemented in this class and return a null value
    // since we use abstract methods in the superclass.
    @Override
    public FastBillController createFastBillController() {
        return null;
    }
}
