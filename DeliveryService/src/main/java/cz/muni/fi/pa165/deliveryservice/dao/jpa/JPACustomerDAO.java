package cz.muni.fi.pa165.deliveryservice.dao.jpa;

import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.User;
import cz.muni.fi.pa165.deliveryservice.dao.CustomerDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation for Customer entity
 *
 * @author Tomas Frkan
 */
@Repository
public class JPACustomerDAO implements CustomerDAO {

    @PersistenceContext
    private EntityManager em;

    public JPACustomerDAO() {
    }

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
    public List<Customer> getAllCustomers(boolean include_deleted) {
         String q = "";
        if(!include_deleted) {
            q = " WHERE c.active = true";
        }
        Query query = em.createQuery("SELECT c FROM Customer c" + q);
        return query.getResultList();
    }

    @Override
    public Customer findCustomer(Long id) {
        if (id == null) {
            throw new NullPointerException("ID is null.");
        }
        return em.find(Customer.class, id);
    }

    @Override
    public User getUser(Customer customer) {
        Query query;
        query = em.createQuery("SELECT u FROM User u WHERE u.id = :id ");
        query.setParameter("id", customer.getUser().getId());

        User user = (User) query.getSingleResult();
        return user;
    }

    @Override
    public Customer findByUsername(String username) {
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.user.username = :username");
        query.setParameter("username", username);

        Customer customer = (Customer) query.getSingleResult();
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return getAllCustomers(true);
    }
}
