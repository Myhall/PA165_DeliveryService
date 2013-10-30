package cz.muni.fi.pa165.deliveryservice.dao.jpa;

import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.dao.CustomerDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * DAO implementation for Customer entity
 *
 * @author Tomas Frkan
 */
public class JPACustomerDAO implements CustomerDAO {

    //@PersistenceContext
    private EntityManager em;

    public JPACustomerDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void createCustomer(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("customer is null");
        }
        em.persist(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("customer is null");
        }
        if (customer.getId() == null) {
            throw new IllegalStateException("customer.id is null");
        }

        Customer c = em.find(Customer.class, customer.getId());
        if (c != null) {
            em.remove(c);
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("customer is null");
        }
        return em.merge(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        Query query = em.createQuery("SELECT c FROM Customer c");
        List<Customer> results = query.getResultList();

        return results;
    }

    @Override
    public Customer findCustomer(Long id) {
        if (id == null) {
            throw new NullPointerException("ID is null.");
        }
        return em.find(Customer.class, id);
    }
}
