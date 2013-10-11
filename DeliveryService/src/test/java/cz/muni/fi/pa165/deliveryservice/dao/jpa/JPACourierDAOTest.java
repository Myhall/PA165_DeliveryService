package cz.muni.fi.pa165.deliveryservice.dao.jpa;

import cz.muni.fi.pa165.deliveryservice.CourierTestBase;
import cz.muni.fi.pa165.deliveryservice.Courier;
import static cz.muni.fi.pa165.deliveryservice.CourierTestBase.TEST_COURIER_EMAIL;
import static cz.muni.fi.pa165.deliveryservice.CourierTestBase.getTestCourierInstance;
import static cz.muni.fi.pa165.deliveryservice.CourierTestBase.TEST_COURIER_FIRST_NAME;
import static cz.muni.fi.pa165.deliveryservice.CourierTestBase.TEST_COURIER_LAST_NAME;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class tests JPA implementation of CourierDAO.
 * 
 * @author Michal Sorentiny
 */
public class JPACourierDAOTest {
    private static final Logger logger = Logger.getAnonymousLogger();
    
    private EntityManagerFactory emf;
    private EntityManager em;
    JPACourierDAO dao;
    
    public JPACourierDAOTest() {
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
    
    @Test(expected = NullPointerException.class)
    public void testPassingNullToCreateCourier()
    {
        em.getTransaction().begin();
        dao.createCourier(null);
        em.getTransaction().commit();
    }
    
    @Test(expected = NullPointerException.class)
    public void testPassingNullToUpdateCourier()
    {
        em.getTransaction().begin();
        dao.updateCourier(null);
        em.getTransaction().commit();
    }
    
    @Test
    public void testInsertingCourierInDBUsingUpdate()
    {
        Courier c = getTestCourierInstance("");
        em.getTransaction().begin();
        c = dao.updateCourier(c);
        em.getTransaction().commit();
        
        Courier fromDB = em.find(Courier.class, c.getId());
        assertEquals("Courier's first name was wrong.", TEST_COURIER_FIRST_NAME, fromDB.getFirstName());
        assertEquals("Courier's last name was wrong.", TEST_COURIER_LAST_NAME, fromDB.getLastName());
        assertEquals("Courier's email was wrong.", TEST_COURIER_EMAIL, fromDB.getEmail());
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

    /**
     * Test of updateCourier method, of class JPACourierDAO.
     */
    @Test
    public void testUpdateCourier() {
        Courier c = getTestCourierInstance("");
        String suffix = "1";
        try {
           em.getTransaction().begin();   
           dao.createCourier(c);
           em.getTransaction().commit();
           
           c.setFirstName(TEST_COURIER_FIRST_NAME+suffix);
           c.setLastName(TEST_COURIER_LAST_NAME+suffix);
           c.setEmail(TEST_COURIER_EMAIL+suffix);
           
           em.getTransaction().begin();
           dao.updateCourier(c);
           em.getTransaction().commit();
           
           Courier fromDB = em.find(Courier.class, c.getId());
           assertEquals("Courier IDs did not match.",c.getId(), fromDB.getId());
           assertEquals("Courier's firstname was not updated.", (TEST_COURIER_FIRST_NAME+suffix), fromDB.getFirstName());
           assertEquals("Courier's lastname was not updated.", (TEST_COURIER_LAST_NAME+suffix), fromDB.getLastName());
           
       } catch (Exception e)
       {
           logger.log(Level.SEVERE, "Failed updating courier", e);
           fail("Updating courier failed: " + e.getMessage());
       }
    }
    

    /**
     * Test of getAllCouriers method, of class JPACourierDAO.
     */
    @Test
    public void testGetAllCouriers() {
        try {
            em.getTransaction().begin();
            for (int i = 0; i < 3; i++) {
                dao.createCourier(getTestCourierInstance(Integer.toString(i)));
            }
            em.getTransaction().commit();
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Failed preparing data in database", e);
            fail("Failed preparing data in database");
        }
        
        List<Courier> results = dao.getAllCouriers();
        for (int i = 0; i < results.size(); i++) {
            assertEquals(i+". Courier's first name was wrong", (TEST_COURIER_FIRST_NAME + i), results.get(i).getFirstName());
            assertEquals(i+". Courier's last name was wrong", (TEST_COURIER_LAST_NAME + i), results.get(i).getLastName());
            assertEquals(i+". Courier's email was wrong", (TEST_COURIER_EMAIL + i), results.get(i).getEmail());
        }
    }
}