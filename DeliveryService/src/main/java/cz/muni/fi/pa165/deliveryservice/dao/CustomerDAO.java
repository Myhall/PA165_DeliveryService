/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao;
import cz.muni.fi.pa165.deliveryservice.Customer;
import java.util.List;
/**
 * DAO interface for Customer entity
 * @author Tomáš Frkáň
 */
public interface CustomerDAO {
    /*
     * Stores new customer into db.
     * @param customer New customer to add into db.
     * @throws IllegalArgumentException if customer is null.
     */
    void createCustomer(Customer customer);
    /*
     * Deletes customer from db.
     * @param customer Customer to remove from db.
     * @throws IllegalArgumentException if customer is null.
     */
    void deleteCustomer(Customer customer);
    /*
     * Updates customer in database.
     * @param customer Customer to update from database.
     * @throws IllegalArgumentException if customer is null.
     */
    void updateCustomer(Customer customer);
    /*
     * Returns list of all customers.
     * @return List<Customer> list of all customers.
     */
    List<Customer> getAllCustomers();
    /*
     * Returns specific customer by given id from database.
     * @param id Customer's id
     * @return Customer with given id
     */
    Customer findCustomer(Long id);
}
