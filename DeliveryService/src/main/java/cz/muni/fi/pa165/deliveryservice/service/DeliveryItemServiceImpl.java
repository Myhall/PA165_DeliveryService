/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.DeliveryItem;
import cz.muni.fi.pa165.deliveryservice.dao.DeliveryItemDAO;
import cz.muni.fi.pa165.deliveryservice.dto.DeliveryItemDTO;
import cz.muni.fi.pa165.deliveryservice.exceptions.DataPersistenceException;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (deliveryItemDTO == null) {
            throw new NullPointerException("deliveryItemDTO");
        }

        DeliveryItem deliveryItem = mapper.map(deliveryItemDTO, DeliveryItem.class);
        deliveryItemDao.createDeliveryItem(deliveryItem);
    }

    @Override
    public void deleteDeliveryItem(DeliveryItemDTO deliveryItemDTO) {
        if (deliveryItemDTO == null) {
            throw new NullPointerException("deliveryItemDTO");
        }

        DeliveryItem deliveryItem = deliveryItemDao.findDeliveryItem(deliveryItemDTO.getId());
        deliveryItemDao.deleteDeliveryItem(deliveryItem);
    }

    @Override
    public DeliveryItemDTO updateDeliveryItem(DeliveryItemDTO deliveryItemDTO) {
        if (deliveryItemDTO == null) {
            throw new NullPointerException("deliveryItemDTO");
        }

        DeliveryItem deliveryItem = deliveryItemDao.findDeliveryItem(deliveryItemDTO.getId());
        return mapper.map(deliveryItemDao.updateDeliveryItem(deliveryItem), DeliveryItemDTO.class);
    }

    @Override
    public DeliveryItemDTO findDeliveryItem(Long id) {
        if (id == null) {
            throw new NullPointerException("id");
        }

        return mapper.map(deliveryItemDao.findDeliveryItem(id), DeliveryItemDTO.class);
    }
}
