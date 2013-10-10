package cz.muni.fi.pa165.deliveryservice.dao.jpa;

import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.dao.CustomerDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * DAO implementation for Customer entity
 *
 * @author Tomáš Frkáň
 */
public class JPACustomerDAO implements CustomerDAO {

    //@PersistenceContext
    private EntityManager em;

    public JPACustomerDAO(EntityManager em) {
        this.em = em;
    }

    public void createCustomer(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("customer is null");
        }
        if (customer.getId() != null) {
            throw new NullPointerException("customer.id is not null");
        }
        em.persist(customer);
    }

    public void deleteCustomer(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("customer is null");
        }
        if (customer.getId() == null) {
            throw new NullPointerException("customer.id is null");
        }
        Customer c = em.find(Customer.class, customer.getId());
        if (c != null) {
            em.remove(c);
        }
    }

    public void updateCustomer(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("customer is null");
        }
        if (customer.getId() == null) {
            throw new NullPointerException("customer.id is null");
        }
        em.merge(customer);
    }

    public List<Customer> getAllCustomers() {
        Query query = em.createQuery("SELECT c FROM Customer c");
        List<Customer> results = query.getResultList();

        return results;
    }

    public Customer findCustomer(Long id) {
        return em.find(Customer.class, id);
    }
}
