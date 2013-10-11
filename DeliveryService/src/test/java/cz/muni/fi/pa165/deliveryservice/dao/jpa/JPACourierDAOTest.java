package cz.muni.fi.pa165.deliveryservice.dao.jpa;

import cz.muni.fi.pa165.deliveryservice.Courier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
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
 * @author Michal Sorentiny
 */
public class JPACourierDAOTest {
    private static Logger logger = Logger.getLogger(JPACourierDAOTest.class.getName());
    private Connection connection;
    
    public static final String TEST_COURIER_FIRST_NAME = "Bonifac";
    public static final String TEST_COURIER_LAST_NAME = "Strakaty";
    public static final String TEST_COURIER_EMAIL = "bony@strako.com";
    
    private EntityManagerFactory emf;
    private EntityManager em;
    JPACourierDAO dao;
    
    public JPACourierDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }    
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("DeliveryServicePu");
        em = emf.createEntityManager();
        dao = new JPACourierDAO(em);
    }

    
    @After
    public void tearDown() throws SQLException {
        em.close();
        emf.close();
        dao = null;
    }
    
    

    /**
     * Test of createCourier method, of class JPACourierDAO.
     */
    @Test
    public void testCreateCourier() {
        Courier courier = getTestCourierInstance("");
        try {
            em.getTransaction().begin();
            dao.createCourier(courier);
            em.getTransaction().commit();
        } catch (IllegalStateException ex) {
            fail("Trying to manage transactions on wrong level");
        }
        Courier fromDB = em.find(Courier.class, courier.getId());
        assertEquals(TEST_COURIER_FIRST_NAME, fromDB.getFirstName());
        assertEquals(TEST_COURIER_LAST_NAME, fromDB.getLastName());
        assertEquals(TEST_COURIER_EMAIL, fromDB.getEmail());
    }

    /**
     * Test of deleteCourier method, of class JPACourierDAO.
     */
    @Test
    public void testDeleteCourier() {
        Courier courier = getTestCourierInstance("");
        Courier fromDB = null;
        try {
            em.getTransaction().begin();
            dao.createCourier(courier);
            em.getTransaction().commit();

            fromDB = em.find(Courier.class, courier.getId());
            assertNotNull("Courier was inserted into DB",fromDB);

            fromDB = null;

            em.getTransaction().begin();
            em.remove(courier);
            em.getTransaction().commit();
        } catch (IllegalStateException ex) {
            fail("Trying to manage transactions on wrong level");
        }
        fromDB = em.find(Courier.class, courier.getId());
        assertNull("Courier was not deleted from DB", fromDB);
    }

//    /**
//     * Test of updateCourier method, of class JPACourierDAO.
//     */
//    @Test
//    public void testUpdateCourier() {
//        System.out.println("updateCourier");
//        Courier courier = null;
//        JPACourierDAO instance = null;
//        instance.updateCourier(courier);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAllCouriers method, of class JPACourierDAO.
//     */
//    @Test
//    public void testGetAllCouriers() {
//        System.out.println("getAllCouriers");
//        JPACourierDAO instance = null;
//        List expResult = null;
//        List result = instance.getAllCouriers();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    static Courier getTestCourierInstance(String suffix) {
        return new Courier(TEST_COURIER_FIRST_NAME + suffix, TEST_COURIER_LAST_NAME + suffix, TEST_COURIER_EMAIL + suffix);
    }
    
    
}