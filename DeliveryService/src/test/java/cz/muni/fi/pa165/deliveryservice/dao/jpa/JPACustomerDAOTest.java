/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao.jpa;

import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.dao.CustomerDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for JPACustomerDAO class.
 * 
 * @author Filip Volner <volner@mail.muni.cz>
 */
public class JPACustomerDAOTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private CustomerDAO dao;
    private int numOfTestCustomerInstances;

    public JPACustomerDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("DeliveryServiceInMemoryPu");
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        dao = new JPACustomerDAO(em);
        numOfTestCustomerInstances = 0;
    }

    @After
    public void tearDown() {
        em.close();
        dao = null;
    }

    /**
     * Test adding null customer to DB.
     */
    @Test
    public void testCreateNullCustomer() {
        Customer customer = null;
        try {
            em.getTransaction().begin();
            dao.createCustomer(customer);
            em.getTransaction().commit();
        } catch (NullPointerException e) {
            return;
        }
        fail("Inserting null customer into DB should fail with NullPointerException.");
    }

    /**
     * Test adding customer to DB.
     */
    @Test
    public void testCreateCustomer() {
        Customer customer1 = createTestCustomer();
        Customer customer2 = createTestCustomer();

        em.getTransaction().begin();
        dao.createCustomer(customer1);
        dao.createCustomer(customer2);
        em.getTransaction().commit();

        em.clear();

        Long id = customer1.getId();

        assertEquals("Customer should be the same as its inserted counterpart in DB.",
                customer1, em.find(Customer.class, id));


        assertNotEquals("Customer should not be the same as different customer in DB.",
                customer2, em.find(Customer.class, id));

    }

    /**
     * Test deleting null customer.
     */
    @Test
    public void testDeleteNullCustomer() {
        Customer customer = null;
        try {
            em.getTransaction().begin();
            dao.deleteCustomer(customer);
            em.getTransaction().commit();
        } catch (NullPointerException e) {
            return;
        }
        fail("Deleting null customer from DB should fail with NullPointerException.");
    }

    /**
     * Test deleting customer with null ID.
     */
    @Test
    public void testDeleteCustomerWithNullID() {
        Customer notPersistedCustomer = createTestCustomer();

        try {
            em.getTransaction().begin();
            dao.deleteCustomer(notPersistedCustomer);
            em.getTransaction().commit();
        } catch (NullPointerException e) {
            return;
        }
        fail("Deleting customer with null ID should throw an exception.");
    }

    /**
     * Test deleting customer from DB.
     */
    @Test
    public void testDeleteCustomer() {
        Customer customer = createTestCustomer();

        em.getTransaction().begin();
        dao.createCustomer(customer);
        em.getTransaction().commit();

        assertNotNull("Customer should be present in DB after create.", em.find(Customer.class, customer.getId()));

        em.getTransaction().begin();
        dao.deleteCustomer(customer);
        em.getTransaction().commit();

        assertNull("Customer should not be present in DB after delete.", em.find(Customer.class, customer.getId()));

        try {
            em.getTransaction().begin();
            dao.deleteCustomer(customer);
            em.getTransaction().commit();
        } catch(IllegalArgumentException e) {
            fail("Deleting already removed customer should not throw an exception.");
        }

    }

    /**
     * Test updating null customer.
     */
    @Test
    public void testUpdateNullCustomer() {
        Customer customer = null;
        try {
            em.getTransaction().begin();
            dao.updateCustomer(customer);
            em.getTransaction().commit();
        } catch (NullPointerException e) {
            return;
        }
        fail("Updating null customer from DB should fail with NullPointerException.");
    }

    /**
     * Test updating customer with null ID.
     */
    @Test
    public void testUpdateCustomerWithNullID() {
        Customer notPersistedCustomer = createTestCustomer();

        try {
            em.getTransaction().begin();
            dao.updateCustomer(notPersistedCustomer);
            em.getTransaction().commit();
        } catch (NullPointerException e) {
            return;
        }
        fail("Updating customer with null ID should throw an exception.");
    }

    /**
     * Test updating customer.
     */
    @Test
    public void testUpdateCustomer() {
        Customer customer = createTestCustomer();
        String newFirstName = "NewFirstName";

        em.getTransaction().begin();
        dao.createCustomer(customer);
        em.getTransaction().commit();

        em.clear();

        customer.setFirstName(newFirstName);

        em.getTransaction().begin();
        dao.updateCustomer(customer);
        em.getTransaction().commit();

        em.clear();

        Customer updatedCustomer = em.find(Customer.class, customer.getId());

        assertEquals(customer, updatedCustomer);
        assertEquals("Name should be changed after update.", newFirstName, updatedCustomer.getFirstName());
    }

    /**
     * Test getAllCustomers method from JPACustomerDAO.
     */
    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = dao.getAllCustomers();
        Customer customer = createTestCustomer();

        assertNotNull("GetAllCustomers on empty table should return emtpy list.", customers);
        assertTrue("GetAllCustomers on empty table should return emtpy list.", customers.isEmpty());

        em.getTransaction().begin();
        dao.createCustomer(customer);
        em.getTransaction().commit();

        customers = dao.getAllCustomers();

        assertNotNull("GetAllCustomers on table with 1 customer should return a list.", customers);
        assertFalse("GetAllCustomers on table with 1 customer should should return a list of size 1.", customers.isEmpty());
        assertTrue("GetAllCustomers on table with 1 customer should should return a list of size 1.", customers.size() == 1);
    }
    
    /**
     * Test finding customer in DB by ID.
     */
    @Test
    public void testFindCustomer() {
        Customer customer = createTestCustomer();
        em.getTransaction().begin();
        dao.createCustomer(customer);
        em.getTransaction().commit();
                        
        assertNotNull("Should find customer by his ID after adding to DB.", em.find(Customer.class, customer.getId()));
        
        assertNull("Should not return customer by ID which is not present in DB.", em.find(Customer.class, customer.getId()+1));
        
        em.getTransaction().begin();
        em.remove(customer);
        em.getTransaction().commit();
        
        assertNull("Should not find customer by his ID after removing him from DB.",em.find(Customer.class, customer.getId()));
        
    }
    
    private Customer createTestCustomer() {
        numOfTestCustomerInstances++;
        return new Customer("FirstName", "LastName",
                "test" + numOfTestCustomerInstances + "@email.com",
                "address", "12345");
    }
}