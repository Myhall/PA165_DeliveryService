package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.Delivery;
import cz.muni.fi.pa165.deliveryservice.DeliveryItem;
import cz.muni.fi.pa165.deliveryservice.dao.DeliveryItemDAO;
import cz.muni.fi.pa165.deliveryservice.dto.DeliveryItemDTO;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Objects;
import org.dozer.DozerBeanMapper;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.After;
import static org.junit.Assert.*;
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
 * @author Tomas Frkan
 */
@RunWith(MockitoJUnitRunner.class)
public class DeliveryItemServiceTest {

    @Mock
    private DeliveryItemDAO deliveryItemDao;
    private DeliveryItemService deliveryItemService;
    private DozerBeanMapper mapper;
    private DeliveryItem deliveryItem;
    private DeliveryItemDTO deliveryItemDto;

    @Before
    public void setUp() {
        mapper = new DozerBeanMapper();
        deliveryItemService = new DeliveryItemServiceImpl();
        ReflectionTestUtils.setField(deliveryItemService, "deliveryItemDao", deliveryItemDao);
        ReflectionTestUtils.setField(deliveryItemService, "mapper", mapper);
        //
        Customer customer = new Customer("jozo", "Mrkva", "bla@gmail.com", "Boston", "1st ave 55", "H4474a", "murica", "+42584444");
        Delivery delivery = new Delivery(customer, "test place from", "test place to");
        deliveryItem = createDeliveryItemInstance(1L, delivery, "Jan", "ulica 1", BigDecimal.valueOf(55));
        deliveryItemDto = mapper.map(deliveryItem, DeliveryItemDTO.class);
        when(deliveryItemDao.updateDeliveryItem(deliveryItem)).thenReturn(deliveryItem);
        when(deliveryItemDao.findDeliveryItem(deliveryItem.getId())).thenReturn(deliveryItem);

    }

    @After
    public void tearDown() {
        deliveryItemService = null;
    }

    @Test
    public void testCreateDeliveryItemService() {
        deliveryItemService.createDeliveryItem(deliveryItemDto);
        verify(deliveryItemDao).createDeliveryItem(deliveryItem);
    }

    @Test
    public void testCreateDeliveryItemServiceDeep() {
        deliveryItemService.createDeliveryItem(deliveryItemDto);
        final DeliveryItem d = deliveryItem;
        verify(deliveryItemDao).createDeliveryItem(argThat(new BaseMatcher<DeliveryItem>() {
            @Override
            public boolean matches(Object item) {
                if (!(item instanceof DeliveryItem)) {
                    return false;
                }
                final DeliveryItem other = (DeliveryItem) item;
                if (!Objects.equals(d.getId(), other.getId())) {
                    return false;
                }
                if (!Objects.equals(d.getDelivery(), other.getDelivery())) {
                    return false;
                }
                if (!Objects.equals(d.getDescription(), other.getDescription())) {
                    return false;
                }
                if (!Objects.equals(d.getWeight(), other.getWeight())) {
                    return false;
                }
                return true;
            }

            @Override
            public void describeTo(Description description) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }));
    }

    @Test
    public void testFindDeliveryItemService() {
        when(deliveryItemDao.findDeliveryItem(1L)).thenReturn(deliveryItem);
        DeliveryItemDTO d = deliveryItemService.findDeliveryItem(1L);
        assertEquals(d, deliveryItemDto);
    }

    @Test
    public void testUpdateDeliveryItemService() {
        deliveryItemService.updateDeliveryItem(deliveryItemDto);
        verify(deliveryItemDao).updateDeliveryItem(deliveryItem);
    }

    @Test
    public void testRemoveDeliveryItemService() {
        deliveryItemService.deleteDeliveryItem(deliveryItemDto);
        verify(deliveryItemDao).deleteDeliveryItem(deliveryItem);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateDeliveryItemServiceWithNull() {
        deliveryItemService.createDeliveryItem(null);
    }

    @Test(expected = NullPointerException.class)
    public void testFindDeliveryItemServiceWithNull() {
        deliveryItemService.findDeliveryItem(null);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateDeliveryItemServiceWithNull() {
        deliveryItemService.updateDeliveryItem(null);
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteDeliveryItemServiceWithNull() {
        deliveryItemService.deleteDeliveryItem(null);
    }

    @Test(expected = DataAccessException.class)
    public void testCreateDeliveryItemPersistenceException() {
        doThrow(SQLException.class).when(deliveryItemDao).createDeliveryItem(deliveryItem);
        deliveryItemService.createDeliveryItem(deliveryItemDto);
    }

    @Test(expected = DataAccessException.class)
    public void testUpdateDeliveryItemPersistenceException() {
        when(deliveryItemDao.updateDeliveryItem(deliveryItem)).thenThrow(SQLException.class);
        deliveryItemService.updateDeliveryItem(deliveryItemDto);
    }

    @Test(expected = DataAccessException.class)
    public void testFindDeliveryItemPersistenceException() {
        when(deliveryItemDao.findDeliveryItem(deliveryItem.getId())).thenThrow(SQLException.class);
        deliveryItemService.findDeliveryItem(deliveryItem.getId());
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteDeliveryItemPersistenceException() {
        doThrow(SQLException.class).when(deliveryItemDao).deleteDeliveryItem(deliveryItem);
        deliveryItemService.deleteDeliveryItem(deliveryItemDto);
    }

    private static DeliveryItem createDeliveryItemInstance(Long id, Delivery delivery, String name, String description, BigDecimal weight) {
        DeliveryItem d = new DeliveryItem();
        d.setId(id);
        d.setDelivery(delivery);
        d.setName(name);
        d.setDescription(description);
        d.setWeight(weight);
        return d;
    }
}
