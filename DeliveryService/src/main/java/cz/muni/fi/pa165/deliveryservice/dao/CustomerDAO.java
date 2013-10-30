/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao;
import cz.muni.fi.pa165.deliveryservice.Customer;
import java.util.List;
/**
 * DAO interface for Customer entity
 * @author Tomas Frkan
 */
public interface CustomerDAO {
    /*
     * Stores new customer into db.
     * @param customer New customer to add into db.
     * @throws NullPointerException if customer is null.
     */
    void createCustomer(Customer customer);
    /*
     * Deletes customer from db.
     * @param customer Customer to remove from db.
     * @throws NullPointerException if customer is null.
     * @throws IllegalStateException if customer's id attribute is null.
     */
    void deleteCustomer(Customer customer);
    /*
     * Updates customer in database.
     * @param customer Customer to update from database.
     * @throws NullPointerException if customer is null.
     * @return Customer Customer to update
     */
    Customer updateCustomer(Customer customer);
    /*
     * Returns list of all customers.
     * @return List<Customer> list of all customers.
     */
    List<Customer> getAllCustomers();
    /*
     * Returns specific customer by given id from database.
     * @param id Customer's id
     * @return Customer with given id
     * @throws NullPointerException id is null
     */
    Customer findCustomer(Long id);
}
