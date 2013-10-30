package cz.muni.fi.pa165.deliveryservice;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.Delivery;
import cz.muni.fi.pa165.deliveryservice.DeliveryItem;

/**
 * Class holds common methods and constants for testing Courier.
 * 
 * @author Michal Sorentiny
 */
public class CourierTestBase {
    
    public static final String TEST_COURIER_FIRST_NAME = "Bonifac";
    public static final String TEST_COURIER_LAST_NAME = "Strakaty";
    public static final String TEST_COURIER_EMAIL = "bony@strako.com";
    
    public static Courier getTestCourierInstance(String suffix) {
        return new Courier(TEST_COURIER_FIRST_NAME + suffix, TEST_COURIER_LAST_NAME + suffix, TEST_COURIER_EMAIL + suffix);
    }
    
    public static Delivery getDeliveryInstance(String suffix) {
        Customer c = new Customer("Foo" + suffix, "Bar" + suffix, "foo@bar.com", "San Mateo","123 Main Street"," 94401 ","CA", "555333987");
        Delivery d = new Delivery(c, "Trnava", "Hong Kong");
        d.addDeliveryItem(new DeliveryItem());
        return d;
    }
}
