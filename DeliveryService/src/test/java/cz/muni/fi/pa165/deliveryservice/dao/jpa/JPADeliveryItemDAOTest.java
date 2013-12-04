/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao.jpa;

import cz.muni.fi.pa165.deliveryservice.DeliveryItem;
import cz.muni.fi.pa165.deliveryservice.dao.DeliveryItemDAO;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;

/**
 * Test class for deliveryItemUnit
 *
 * @author Tomas Frkan
 */
public class JPADeliveryItemDAOTest {

    private DeliveryItemDAO deliveryItemDAO;
    private static EntityManagerFactory emf;
    private EntityManager em;

    public JPADeliveryItemDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("DeliveryServiceInMemoryPu");
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        deliveryItemDAO = new JPADeliveryItemDAO(em);
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @After
    public void tearDown() {
        em.close();
        deliveryItemDAO = null;
    }

    private DeliveryItem createTestDeliveryItem() {
        DeliveryItem deliveryItem = new DeliveryItem();
        //deliveryItem.setDelivery(null);
        deliveryItem.setDescription("Test Description");
        deliveryItem.setName("Test Name");
        deliveryItem.setWeight(new BigDecimal("5"));
        return deliveryItem;
    }

    /*
     * Adding null delivery item to DB - test
     */
    @Test
    public void testCreateNullDeliveryItem() {
        DeliveryItem deliveryItem = null;
        try {
            em.getTransaction().begin();
            deliveryItemDAO.createDeliveryItem(deliveryItem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            return;
        }
        fail("Inserting null delivery item into DB should fail with NullPointerException.");
    }

    /**
     * Adding delivery item to DB - test
     */
    @Test
    public void testCreateDeliveryItem() {
        DeliveryItem deliveryItem = createTestDeliveryItem();
        try {
            em.getTransaction().begin();
            deliveryItemDAO.createDeliveryItem(deliveryItem);
            em.getTransaction().commit();
        } catch (IllegalStateException ex) {
            fail("Trying to manage transactions on wrong level");
        }
        DeliveryItem deliveryItemFromDB =
                em.find(DeliveryItem.class, deliveryItem.getId());
        assertEquals(deliveryItemFromDB, deliveryItem);
    }

    /**
     * Deleting null delivery item from DB - test
     */
    @Test
    public void testDeleteNullDeliveryItem() {
        DeliveryItem deliveryItem = null;
        try {
            em.getTransaction().begin();
            deliveryItemDAO.deleteDeliveryItem(deliveryItem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            return;
        }
        fail("Deleting null delivery item from DB should fail with NullPointerException.");
    }

    /**
     * Deleting delivery item with null ID from DB - test
     */
    @Test
    public void testDeleteDeliveryItemWithNullID() {
        DeliveryItem deliveryItem = createTestDeliveryItem();
        try {
            em.getTransaction().begin();
            deliveryItemDAO.deleteDeliveryItem(deliveryItem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            return;
        }
        fail("Deleting delivery item with null ID should throw an exception.");
    }

    /**
     * Deleting delivery item from DB - test
     */
    @Test
    public void testDeleteDeliveryItem() {
        DeliveryItem deliveryItem = createTestDeliveryItem();

        em.getTransaction().begin();
        deliveryItemDAO.createDeliveryItem(deliveryItem);
        em.getTransaction().commit();
        assertNotNull("Delivery item should be present in DB after create.",
                em.find(DeliveryItem.class, deliveryItem.getId()));

        em.getTransaction().begin();
        deliveryItemDAO.deleteDeliveryItem(deliveryItem);
        em.getTransaction().commit();
        assertNull("Delivery item should not be present in DB after delete.",
                em.find(DeliveryItem.class, deliveryItem.getId()));

        try {
            em.getTransaction().begin();
            deliveryItemDAO.deleteDeliveryItem(deliveryItem);
            em.getTransaction().commit();
        } catch (IllegalArgumentException ex) {
            fail("Deleting already removed delivery item should not throw an exception.");
        }

    }

    /**
     * Updating delivery item with null to DB - test
     */
    @Test
    public void testUpdateNullDeliveryItem() {
        DeliveryItem deliveryItem = null;
        try {
            em.getTransaction().begin();
            deliveryItemDAO.updateDeliveryItem(deliveryItem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            return;
        }
        fail("Updating null delivery item should fail with NullPointerException.");
    }

    /**
     * Updating delivery item with null ID to DB - test
     */
    @Test
    public void testUpdateDeliveryItemWithNullID() {
        DeliveryItem deliveryItem = createTestDeliveryItem();
        try {
            em.getTransaction().begin();
            deliveryItemDAO.updateDeliveryItem(deliveryItem);
            em.getTransaction().commit();
        } catch (Exception ex) {
           fail("Updating delivery item with null ID should throw an exception.");
        }
    }

    /**
     * Updating delivery item to DB - test
     */
    @Test
    public void testUpdateDeliveryItem() {
        DeliveryItem deliveryItem = createTestDeliveryItem();
        try {
            em.getTransaction().begin();
            deliveryItemDAO.createDeliveryItem(deliveryItem);
            em.getTransaction().commit();
        } catch (IllegalStateException ex) {
            fail("Trying to manage transactions on wrong level");
        }
        em.clear();

        deliveryItem.setDescription("Changed Test Description");

        em.getTransaction().begin();
        deliveryItemDAO.updateDeliveryItem(deliveryItem);
        em.getTransaction().commit();
        em.clear();

        DeliveryItem deliveryItemFromDB =
                em.find(DeliveryItem.class, deliveryItem.getId());
        assertEquals(deliveryItemFromDB, deliveryItem);
        assertEquals("Description should be changed after update.",
                "Changed Test Description", deliveryItemFromDB.getDescription());
    }

    /**
     * Locates delivery item in DB - test
     */
    @Test
    public void testFindDeliveryItem() {
        DeliveryItem deliveryItem = createTestDeliveryItem();

        em.getTransaction().begin();
        deliveryItemDAO.createDeliveryItem(deliveryItem);
        em.getTransaction().commit();


        assertNotNull("Should find delivery item by its ID after adding to DB",
                deliveryItemDAO.findDeliveryItem(deliveryItem.getId()));
        assertNull("Should not return delivery item by ID which is not present in DB.",
                deliveryItemDAO.findDeliveryItem(deliveryItem.getId() + 1));

        em.getTransaction().begin();
        em.remove(deliveryItem);
        em.getTransaction().commit();

        assertNull("Should not find delivery item by his ID after removing him from DB.",
                deliveryItemDAO.findDeliveryItem(deliveryItem.getId()));

    }
}
