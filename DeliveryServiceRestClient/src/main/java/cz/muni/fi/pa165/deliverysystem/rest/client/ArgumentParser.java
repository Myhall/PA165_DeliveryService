/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliverysystem.rest.client;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.Switch;
import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliverysystem.rest.RestClientCourierImpl;
import cz.muni.fi.pa165.deliverysystem.rest.RestClientCourierInterface;
import cz.muni.fi.pa165.deliverysystem.rest.RestClientCustomerImpl;
import cz.muni.fi.pa165.deliverysystem.rest.RestClientCustomerInterface;

/**
 *
 * @author osiris
 */
public class ArgumentParser {

    private JSAP jsap;
    private JSAPResult config;
    private RestClientCustomerInterface restCustomer;
    private RestClientCourierInterface restCourier;
    //which entity we want to manipulate
    private Switch customerSwitch; //--customer
    private Switch courierSwitch; //--courier
    //what operation we want to perform
    private Switch createSwitch;
    private Switch findAllSwitch;
    private FlaggedOption delete;
    private FlaggedOption update;
    private FlaggedOption find;
    //what property do we want to set / update (customer)
    private FlaggedOption firstName;
    private FlaggedOption lastName;
    private FlaggedOption email;
    private FlaggedOption city;
    private FlaggedOption street;
    private FlaggedOption zipCode;
    private FlaggedOption country;
    private FlaggedOption telephoneNumber;

    public ArgumentParser(JSAP jsap) throws JSAPException {


        customerSwitch = new Switch("customer").setLongFlag("customer");
        courierSwitch = new Switch("courier").setLongFlag("courier");
        createSwitch = new Switch("create").setLongFlag("create");
        findAllSwitch = new Switch("findall").setLongFlag("findall");

        delete = new FlaggedOption("delete")
                .setStringParser(JSAP.STRING_PARSER)
                .setLongFlag("delete");
        update = new FlaggedOption("update")
                .setStringParser(JSAP.STRING_PARSER)
                .setLongFlag("update");
        find = new FlaggedOption("find")
                .setStringParser(JSAP.STRING_PARSER)
                .setLongFlag("find");
        firstName = new FlaggedOption("firstname")
                .setStringParser(JSAP.STRING_PARSER)
                .setLongFlag("firstname");
        lastName = new FlaggedOption("lastname")
                .setStringParser(JSAP.STRING_PARSER)
                .setLongFlag("lastname");
        email = new FlaggedOption("email")
                .setStringParser(JSAP.STRING_PARSER)
                .setLongFlag("email");
        city = new FlaggedOption("city")
                .setStringParser(JSAP.STRING_PARSER)
                .setLongFlag("city");
        street = new FlaggedOption("street")
                .setStringParser(JSAP.STRING_PARSER)
                .setLongFlag("street");
        zipCode = new FlaggedOption("zipCode")
                .setStringParser(JSAP.STRING_PARSER)
                .setLongFlag("zipCode");
        country = new FlaggedOption("country")
                .setStringParser(JSAP.STRING_PARSER)
                .setLongFlag("country");
        telephoneNumber = new FlaggedOption("telephoneNumber")
                .setStringParser(JSAP.STRING_PARSER)
                .setLongFlag("telephoneNumber");


        jsap.registerParameter(createSwitch);
        jsap.registerParameter(findAllSwitch);
        jsap.registerParameter(customerSwitch);
        jsap.registerParameter(courierSwitch);
        jsap.registerParameter(delete);
        jsap.registerParameter(update);
        jsap.registerParameter(find);
        jsap.registerParameter(firstName);
        jsap.registerParameter(lastName);
        jsap.registerParameter(email);
        jsap.registerParameter(city);
        jsap.registerParameter(street);
        jsap.registerParameter(zipCode);
        jsap.registerParameter(country);
        jsap.registerParameter(telephoneNumber);

        this.jsap = jsap;
    }

    public void parseCliUiStart() {
        if (this.config.getBoolean("customer")) {
            parseCliUiCustomer();
        } else if (this.config.getBoolean("courier")) {
            parseCliUiCourier();
        } else {
            System.out.println("Error while parsing start UI");
        }

    }

    public void parseCliUiCustomer() {
        RestClientCustomerInterface rcci = new RestClientCustomerImpl("http://localhost:8080/pa165/Rest/Customer/?");
        restCustomer = rcci;

        if (config.getBoolean("create")) {
            CustomerDTO customer = new CustomerDTO();
            customer.setFirstName(config.getString("firstName"));
            customer.setLastName(config.getString("lastName"));
            customer.setEmail(config.getString("email"));
            customer.setCity(config.getString("city"));
            customer.setStreet(config.getString("street"));
            customer.setZipCode(config.getString("zipCode"));
            customer.setCountry(config.getString("country"));
            customer.setTelephoneNumber(config.getString("telephoneNumber"));
            restCustomer.createCustomer(customer);

        } else if (config.getString("delete") != null) {
            //restCustomer.deleteCustomer(customer));
        } else if (config.getString("update") != null) {
            CustomerDTO customer = new CustomerDTO();
            customer.setId(Long.valueOf(config.getString("update")));
            customer.setFirstName(config.getString("firstName"));
            customer.setLastName(config.getString("lastName"));
            customer.setEmail(config.getString("email"));
            customer.setCity(config.getString("city"));
            customer.setStreet(config.getString("street"));
            customer.setZipCode(config.getString("zipCode"));
            customer.setCountry(config.getString("country"));
            customer.setTelephoneNumber(config.getString("telephoneNumber"));
            restCustomer.updateCustomer(customer);
        } else if (config.getString("find") != null) {
            System.out.println("Find customer with ID: " + config.getString("find"));
            System.out.println("Entry is: " + restCustomer.findCustomer(config.getString("find")));
        } else if (config.getBoolean("getAllCustomers")) {
            restCustomer.getAllCustomers();
        }
    }

    public void parseCliUiCourier() {
        RestClientCourierInterface rcc = new RestClientCourierImpl("http://localhost:8080/pa165/Rest/Courier/?");
        restCourier = rcc;
        if (config.getBoolean("create")) {
            CourierDTO courier = new CourierDTO();
            courier.setFirstName(config.getString("firstName"));
            courier.setLastName(config.getString("lastName"));
            courier.setEmail(config.getString("email"));
            restCourier.createCourier(courier);
        } else if (config.getString("update") != null) {
            CourierDTO courier = new CourierDTO();
            courier.setId(Long.valueOf(config.getString("update")));
            courier.setFirstName(config.getString("firstName"));
            courier.setLastName(config.getString("lastName"));
            courier.setEmail(config.getString("email"));
            restCourier.createCourier(courier);
        } else if (config.getString("delete") != null) {
            //restCourier.deleteCourier(config.getString("delete"));
        } else if (config.getString("find") != null) {
            System.out.println("Find courier with ID: " + config.getString("find"));
            System.out.println("Entry is: " + restCourier.findCourier(config.getString("find")));
        } else if (config.getBoolean("getAllCustomers")) {
            restCourier.getAllCouriers();
        }
    }
}
