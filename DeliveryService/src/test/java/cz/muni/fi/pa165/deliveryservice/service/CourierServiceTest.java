package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.CourierTestBase;
import cz.muni.fi.pa165.deliveryservice.dao.CourierDAO;
import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.main.AbstractIntegrationTest;
import java.sql.SQLException;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author Michal Sorentiny
 */
@RunWith(MockitoJUnitRunner.class)
public class CourierServiceTest extends AbstractIntegrationTest {
    
    private CourierService courierService;
    private CourierDTO courierDTO;
    private Courier courier;
    private DozerBeanMapper mapper;
    
    @Mock
    CourierDAO dao;
    
    public CourierServiceTest() {
    }

    @Before
    public void setUp() {
        courierService = new CourierServiceImpl();
        mapper = new DozerBeanMapper();
        ReflectionTestUtils.setField(courierService, "courierDao", dao);
        ReflectionTestUtils.setField(courierService, "mapper", mapper);
        
        courier = CourierTestBase.getTestCourierInstance("");
        courier.setId(Long.valueOf(1));
        courierDTO = mapper.map(courier, CourierDTO.class);
        
        when(dao.updateCourier(courier)).thenReturn(courier);
        when(dao.findCourier(courier.getId())).thenReturn(courier);
    }

    @After
    public void tearDown() {
        courierService = null;
        mapper = null;
        courier = null;
        courierDTO = null;
    }
    
    @Test
    public void testCreateCourier() {
        courierService.createCourier(courierDTO);
        verify(dao).createCourier(courier);
    }
    
    @Test(expected = NullPointerException.class)
    public void testCreateCourierNullArgument()
    {
        courierService.createCourier(null);
    }
    
    @Test
    public void testDeleteCourier()
    {
        courierService.deleteCourier(courierDTO);
        verify(dao).deleteCourier(courier);
    }
    
    @Test(expected = NullPointerException.class)
    public void testDeleteCourierNullArgument() {
        courierService.deleteCourier(null);
    }
    
    @Test
    public void testFindCourier()
    {
        courierService.findCourier(courier.getId());
        verify(dao).findCourier(courier.getId());
    }
    
    @Test(expected = NullPointerException.class)
    public void testFindCourierNullArgument()
    {
        courierService.findCourier(null);
    }
    
    @Test
    public void testUpdateCourier()
    {
        courierService.updateCourier(courierDTO);
        verify(dao).updateCourier(courier);
    }
    
    @Test(expected = NullPointerException.class)
    public void testUpdateCourierNullArgument()
    {
        courierService.updateCourier(null);
    }
    
    @Test
    public void testGetAllCouriers()
    {
        courierService.getAllCouriers();
        verify(dao).getAllCouriers();
    }
    
    @Test(expected = DataAccessException.class)
    public void testFindAllCouriersPersistenceException()
    {
        when(dao.getAllCouriers()).thenThrow(SQLException.class);
        courierService.getAllCouriers();
    }
    
    @Test(expected = DataAccessException.class)
    public void testUpdateCourierPersistenceException()
    {
        when(dao.updateCourier(courier)).thenThrow(SQLException.class);
        courierService.updateCourier(courierDTO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testCreateCourierPersistenceException()
    {
        doThrow(SQLException.class).when(dao).createCourier(courier);
        courierService.createCourier(courierDTO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testDeleteCourierPersistenceException()
    {
        doThrow(SQLException.class).when(dao).deleteCourier(courier);
        courierService.deleteCourier(courierDTO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testFindCourierPersistenceException()
    {
        when(dao.findCourier(courier.getId())).thenThrow(SQLException.class);
        courierService.findCourier(courier.getId());
    }
}
