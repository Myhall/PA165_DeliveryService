/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao.jpa;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.Delivery;
import cz.muni.fi.pa165.deliveryservice.DeliveryStatus;
import cz.muni.fi.pa165.deliveryservice.dao.DeliveryDAO;
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
 *
 * @author Jan Vorcak
 */
public class JPADeliveryDAOTest {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeliveryServiceInMemoryPu");
    static private EntityManager em;

    static private Delivery deliveryPersisted;
    static private Customer customer;
    static private Courier courier;
    static private DeliveryDAO instance;

    public JPADeliveryDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        em = emf.createEntityManager();
        instance = new JPADeliveryDAO(em);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        courier = new Courier("Jan", "Petrovic", "petrovic@example.com");
        customer = new Customer("Peter", "Petrovic", "petrovic2@example.com","Brno",
                "Komenskeho 32","60200","czech republic", "234324");

        deliveryPersisted = new Delivery();

        em.getTransaction().begin();
        instance.createDelivery(deliveryPersisted);
        em.persist(courier);
        em.persist(customer);

        for (int i = 0; i < 4; i++) {
            Delivery d = new Delivery();
            d.setStatus(DeliveryStatus.DELIVERED);
            d.setCustomer(customer);
            instance.createDelivery(d);
        }
        for (int i = 0; i < 5; i++) {
            Delivery d = new Delivery();
            d.setStatus(DeliveryStatus.PICKED);
            d.setCourier(courier);
            instance.createDelivery(d);
        }
        for (int i = 0; i < 6; i++) {
            Delivery d = new Delivery();
            d.setStatus(DeliveryStatus.CREATED);
            instance.createDelivery(d);
        }
        em.getTransaction().commit();
    }

    @After
    public void tearDown() {
        List<Delivery> allDeliveries = instance.getAllDeliveries();
        em.getTransaction().begin();
        for (Delivery d : allDeliveries) {
            instance.deleteDelivery(d);
        }
        em.remove(courier);
        em.remove(customer);
        em.getTransaction().commit();
    }

    /**
     * Test of createDelivery method, of class JPADeliveryDAO.
     */
    @Test
    public void testCreateDeliveryWithNull() {
        System.out.println("createDelivery");
        Delivery delivery = null;
        try {
            instance.createDelivery(null);
        } catch (NullPointerException ex) {
            return;
        }
        fail("Should throw NullPointerException");
    }

    /**
     * Test of status for new Delivery of class JPADeliveryDAO.
     */
    @Test
    public void testCreateDeliveryStatus() {
        System.out.println("createDeliveryStatus");
        Delivery delivery = new Delivery();

        em.getTransaction().begin();
        instance.createDelivery(delivery);

        Delivery deliveryCreated = em.find(Delivery.class, delivery.getId());

        em.getTransaction().commit();

        assertEquals(DeliveryStatus.CREATED, deliveryCreated.getStatus());

    }

    /**
     * Test of deleteDelivery method, of class JPADeliveryDAO.
     */
    @Test
    public void testDeleteDelivery() {
        System.out.println("deleteDelivery");
        instance.deleteDelivery(deliveryPersisted);
    }

    /**
     * Test of deleteDelivery, should throw and exception if trying to delete
     * entity which is not persisted
     */
    @Test
    public void testDeleteNonPersitedDelivery() {
        System.out.println("deleteNonPersistedDelivery");
        try {
            instance.deleteDelivery(new Delivery());
        } catch (IllegalStateException ex) {
            return;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        fail("Should thow IllegalStateException");
    }

    /**
     * Test of updateDelivery
     */
    @Test
    public void testUpdateDelivery() {
        System.out.println("deleteNonPersistedDelivery");
        String additionalInfo = "Additional information comes here";
        deliveryPersisted.setAdditionalInformation(additionalInfo);

        instance.updateDelivery(deliveryPersisted);

        Delivery testDelivery
                = em.find(Delivery.class, deliveryPersisted.getId());

        assertEquals(additionalInfo, testDelivery.getAdditionalInformation());
    }

    @Test
    public void testGetAllDeliveries() {
        List<Delivery> allDeliveries = instance.getAllDeliveries();
        assertEquals(16, allDeliveries.size());
    }

    @Test
    public void testGetDeliveriesByStatus() {
        List<Delivery> pickedDeliveries = instance.getDeliveriesByStatus(DeliveryStatus.PICKED);
        List<Delivery> createdDeliveries = instance.getDeliveriesByStatus(DeliveryStatus.CREATED);
        List<Delivery> deliveredDeliveries = instance.getDeliveriesByStatus(DeliveryStatus.DELIVERED);
        assertEquals(5, pickedDeliveries.size());
        assertEquals(7, createdDeliveries.size());
        assertEquals(4, deliveredDeliveries.size());
    }

    @Test
    public void testGetDeliveriesByCustomer() {
        List<Delivery> deliveredDeliveries = instance.getDeliveriesByStatus(DeliveryStatus.DELIVERED);
        for (Delivery d : deliveredDeliveries) {
            assertEquals(customer, d.getCustomer());
        }
    }

    @Test
    public void testGetDeliveriesByCourier() {
        List<Delivery> pickedDeliveries = instance.getDeliveriesByStatus(DeliveryStatus.PICKED);
        for (Delivery d : pickedDeliveries) {
            assertEquals(courier, d.getCourier());
        }
    }
}
