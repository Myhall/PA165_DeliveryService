/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.DeliveryItem;
import cz.muni.fi.pa165.deliveryservice.dao.DeliveryItemDAO;
import cz.muni.fi.pa165.deliveryservice.dto.DeliveryItemDTO;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
@Transactional
public class DeliveryItemServiceImpl implements DeliveryItemService {

    DeliveryItemDAO deliveryItemDao;
    
    @Autowired
    private Mapper mapper;

    @Autowired
    public void setDeliveryItemDao(DeliveryItemDAO deliveryItemDao) {
        this.deliveryItemDao = deliveryItemDao;
    }

    @Override
    public void createDeliveryItem(DeliveryItemDTO deliveryItemDTO) {
        try {
            DeliveryItem deliveryItem = mapper.map(deliveryItemDTO, DeliveryItem.class);
            deliveryItemDao.createDeliveryItem(deliveryItem);
        } catch (MappingException | NullPointerException ex) {
            throw new DataIntegrityViolationException(ex.getMessage());
        }
    }

    @Override
    public void deleteDeliveryItem(DeliveryItemDTO deliveryItemDTO) {
        try {
            DeliveryItem deliveryItem = deliveryItemDao.findDeliveryItem(deliveryItemDTO.getId());
            deliveryItemDao.deleteDeliveryItem(deliveryItem);
        } catch (NullPointerException | IllegalStateException ex) {
            throw new DataRetrievalFailureException(ex.getMessage());
        }
    }

    @Override
    public DeliveryItemDTO updateDeliveryItem(DeliveryItemDTO deliveryItemDTO) {
        try {
            DeliveryItem deliveryItem = deliveryItemDao.findDeliveryItem(deliveryItemDTO.getId());
            return mapper.map(deliveryItemDao.updateDeliveryItem(deliveryItem), DeliveryItemDTO.class);
        } catch (MappingException | NullPointerException ex) {
            throw new DataRetrievalFailureException(ex.getMessage());
        }
    }

    @Override
    public DeliveryItemDTO findDeliveryItem(Long id) {
        try {
            return mapper.map(deliveryItemDao.findDeliveryItem(id), DeliveryItemDTO.class);
        } catch (NullPointerException | IllegalStateException ex) {
            throw new DataRetrievalFailureException(ex.getMessage());
        }
    }
}
