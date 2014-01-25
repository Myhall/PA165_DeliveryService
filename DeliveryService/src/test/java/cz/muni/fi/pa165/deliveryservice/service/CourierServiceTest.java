package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.CourierTestBase;
import cz.muni.fi.pa165.deliveryservice.dao.CourierDAO;
import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.main.AbstractIntegrationTest;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
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
        verify(dao).updateCourier(courier);
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
    
    @Test(expected = NullPointerException.class)
    public void testUpdateCourierNullArgument()
    {
        courierService.updateCourier(null);
    }
    
    @Test
    public void testGetAllCouriers()
    {
        courierService.getAllCouriers();
        verify(dao).getAllCouriers(true);
    }
}
