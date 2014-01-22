/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.dao.CustomerDAO;
import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerDAO customerDao;
    private DozerBeanMapper mapper;
    private CustomerService customerService;
    private Customer customer;
    private CustomerDTO customerDto;
    private Long customerId = 1L;

    public CustomerServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        customerService = new CustomerServiceImpl();
        mapper = new DozerBeanMapper();
        ReflectionTestUtils.setField(customerService, "customerDao", customerDao);
        ReflectionTestUtils.setField(customerService, "mapper", mapper);

        customer = new Customer("Joseph", "Carrot", "joe@carrot.com", "Brno", "Botanicka", "60200", "CZ", "123");
        customer.setId(customerId);
        customerDto = mapper.map(customer, CustomerDTO.class);


        when(customerDao.findCustomer(customerId)).thenReturn(customer);
        when(customerDao.updateCustomer(customer)).thenReturn(customer);
    }

    @After
    public void tearDown() {
        customerService = null;
        mapper = null;
        customer = null;
        customerDto = null;
    }

    @Test
    public void testCreateCustomer() {
        customerService.createCustomer(customerDto);
        verify(customerDao).createCustomer(customer);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNullCustomer() {
        customerService.createCustomer(null);
    }

    @Test
    public void testDelteCustomer() {
        customerService.deleteCustomer(customerDto);
        verify(customerDao).deleteCustomer(customer);
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteNullCustomer() {
        customerService.deleteCustomer(null);
    }


    @Test(expected = NullPointerException.class)
    public void testUpdateNullCustomer() {
        customerService.updateCustomer(null);
    }

    @Test
    public void testGetAllCustomers() {
        customerService.getAllCustomers();
        verify(customerDao).getAllCustomers();
    }

    @Test
    public void testFindCustomer() {
        customerService.findCustomer(customerId);
        verify(customerDao).findCustomer(customerId);
    }

    @Test(expected = NullPointerException.class)
    public void testFindCustomerByNullId() {
        customerService.findCustomer(null);
    }
}