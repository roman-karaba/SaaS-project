package client;
import core.Controller;

/**
 * Created by:
 * Jakub Gawrylkowicz a1326002
 * Roman Karaba a1301624
 *
 * Implementation of Abstract Factory Pattern, AbstractFactory. The class defines the basic static int variables
 * and abstract methods that used by concrete factories to create clients as in controllers: Mockup and Fastbill Controller.
 *
 */


public abstract class AbstractClientFactory {

    abstract public MockupController createMockupController();
    abstract public FastBillController createFastBillController();


}
