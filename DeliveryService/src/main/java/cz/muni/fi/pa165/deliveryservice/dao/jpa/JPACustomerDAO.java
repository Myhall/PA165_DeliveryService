package cz.muni.fi.pa165.deliveryservice.dao.jpa;

import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.dao.CustomerDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * DAO implementation for Customer entity
 *
 * @author Bufo
 */
public class JPACustomerDAO implements CustomerDAO {

    //@PersistenceContext
    private EntityManager em;

    public JPACustomerDAO(EntityManager em) {
        this.em = em;
    }

    public void createCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (customer.getId() != null) {
            throw new IllegalArgumentException("customer.id is not null");
        }
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
    }

    public void deleteCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (customer.getId() == null) {
            throw new IllegalArgumentException("customer.id is null");
        }
        Customer c = em.find(Customer.class, customer.getId());
        if (c != null) {
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        }
    }

    public void updateCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (customer.getId() == null) {
            throw new IllegalArgumentException("customer.id is null");
        }
        em.getTransaction().begin();
        em.merge(customer);
        em.getTransaction().commit();
    }

    public List<Customer> getAllCustomers() {
        Query query = em.createQuery("SELECT c FROM Customer c");
        List<Customer> results = query.getResultList();

        return results;
    }
}
