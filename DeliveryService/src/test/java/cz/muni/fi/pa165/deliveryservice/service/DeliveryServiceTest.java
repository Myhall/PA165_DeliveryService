/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.Delivery;
import cz.muni.fi.pa165.deliveryservice.DeliveryStatus;
import cz.muni.fi.pa165.deliveryservice.dao.DeliveryDAO;
import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.dto.DeliveryDTO;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author Jan Vorcak
 */
@RunWith(MockitoJUnitRunner.class)
public class DeliveryServiceTest {
    
    @Mock
    private DeliveryDAO deliveryDAO;
    private DozerBeanMapper mapper;
    private DeliveryService deliveryService;
    
    private Courier courier;
    private Customer customer;
    
    private final static Long findId = 4L;
    private Delivery delivery, brnoDelivery;
    private DeliveryDTO deliveryDto, delivery2Dto;
    private List<Delivery> allDeliveries;
    
    public DeliveryServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        deliveryService = new DeliveryServiceImpl();
        mapper = new DozerBeanMapper();
        ReflectionTestUtils.setField(deliveryService, "deliveryDAO", deliveryDAO);
        ReflectionTestUtils.setField(deliveryService, "mapper", mapper);
            
        courier = new Courier();
        courier.setEmail("courier@example.com");
        
        customer = new Customer();
        customer.setEmail("customer@example.com");
        
        delivery = new Delivery();
        delivery.setId(findId);
        delivery.setStatus(DeliveryStatus.PICKED);
        delivery.setCustomer(customer);
     
        brnoDelivery = new Delivery();
        brnoDelivery.setId(5L);
        brnoDelivery.setPlaceTo("Brno");
        brnoDelivery.setStatus(DeliveryStatus.CREATED);
        brnoDelivery.setCourier(courier);
        
        deliveryDto = mapper.map(delivery, DeliveryDTO.class);
        delivery2Dto = mapper.map(brnoDelivery, DeliveryDTO.class);
        
        allDeliveries = new ArrayList<Delivery>() {{
            add(delivery);
            add(brnoDelivery);
        }};
        
        when(deliveryDAO.findDelivery(findId)).thenReturn(delivery);
        when(deliveryDAO.findDelivery(null)).thenThrow(NullPointerException.class);
        when(deliveryDAO.getAllDeliveries()).thenReturn(allDeliveries);
        when(deliveryDAO.getDeliveriesByStatus(DeliveryStatus.CREATED)).thenReturn(new ArrayList<Delivery>() {{
            add(brnoDelivery);
        }});
        when(deliveryDAO.getDeliveriesByCourier(courier)).thenReturn(new ArrayList<Delivery>() {{
            add(brnoDelivery);
        }});
        when(deliveryDAO.getDeliveriesByCustomer(customer)).thenReturn(new ArrayList<Delivery>() {{
            add(delivery);
        }});
    }
    
    @After
    public void tearDown() {
    }
    
    @Test(expected = NullPointerException.class)
    public void testCreateDeliveryWithNull() {
        deliveryService.createDelivery(null);
    }

    @Test(expected = NullPointerException.class)
    public void testFindDeliveryWithNull() {
        deliveryService.findDelivery(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void testDeleteDeliveryWithNull() {
        deliveryService.deleteDelivery(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void testUpdateDeliveryWithNull() {
        deliveryService.updateDelivery(null);
    }
    
    @Test
    public void testFindDelivery() {
        DeliveryDTO deliveryDtoReturn = deliveryService.findDelivery(findId);
        Assert.assertEquals(mapper.map(delivery, DeliveryDTO.class), deliveryDtoReturn);
    }
    
    @Test
    public void testCreateDelivery() {
        deliveryService.createDelivery(deliveryDto);
        verify(deliveryDAO).createDelivery(delivery);
    }
    
    @Test
    public void testDeleteDelivery() {
        deliveryService.deleteDelivery(deliveryDto);
        verify(deliveryDAO).deleteDelivery(delivery);
    }
    
    @Ignore
    @Test
    public void testUpdateDelivery() {
        deliveryService.updateDelivery(deliveryDto);
        verify(deliveryDAO).updateDelivery(delivery);
    }
    
    @Test
    public void testGetAllDeliveries() {
        List<DeliveryDTO> deliveriesDto = deliveryService.getAllDeliveries();
        verify(deliveryDAO).getAllDeliveries();
        
        Assert.assertEquals(new ArrayList<DeliveryDTO>() {{
            add(mapper.map(delivery, DeliveryDTO.class));
            add(mapper.map(brnoDelivery, DeliveryDTO.class));
        }}, deliveriesDto);
    }
    
    @Test
    public void testGetDeliveriesByStatus() {
        Assert.assertEquals(new ArrayList<DeliveryDTO>() {{
            add(mapper.map(brnoDelivery, DeliveryDTO.class));
        }}, deliveryService.getDeliveriesByStatus(DeliveryStatus.CREATED));
    }
    
    @Test
    public void testGetDeliveriesByCourier() {
        Assert.assertEquals(new ArrayList<DeliveryDTO>() {{
            add(mapper.map(brnoDelivery, DeliveryDTO.class));
        }}, deliveryService.getDeliveriesByCourier(mapper.map(courier, CourierDTO.class)));
    }
    
    @Test
    public void testGetDeliveriesByCustomer() {
        Assert.assertEquals(new ArrayList<DeliveryDTO>() {{
            add(mapper.map(delivery, DeliveryDTO.class));
        }}, deliveryService.getDeliveriesByCustomer(mapper.map(courier, CustomerDTO.class)));
    }   
}
