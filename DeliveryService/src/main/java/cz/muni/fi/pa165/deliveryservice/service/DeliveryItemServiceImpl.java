/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.Delivery;
import cz.muni.fi.pa165.deliveryservice.DeliveryItem;
import cz.muni.fi.pa165.deliveryservice.dao.DeliveryItemDAO;
import cz.muni.fi.pa165.deliveryservice.dto.DeliveryItemDTO;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
@Transactional
public class DeliveryItemServiceImpl implements DeliveryItemService {

    @Autowired
    DeliveryItemDAO deliveryItemDao;
    @Autowired
    private Mapper mapper;

    public void setDeliveryItemDao(DeliveryItemDAO deliveryItemDao) {
        this.deliveryItemDao = deliveryItemDao;
    }

    @Override
    public DeliveryItemDTO createDeliveryItem(DeliveryItemDTO deliveryItemDTO) {
        if (deliveryItemDTO == null) {
            throw new NullPointerException("deliveryItemDTO");
        }

        DeliveryItem deliveryItem = mapper.map(deliveryItemDTO, DeliveryItem.class);
        deliveryItemDao.createDeliveryItem(deliveryItem);
        return mapper.map(deliveryItem, DeliveryItemDTO.class);
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

//        DeliveryItem deliveryItem = deliveryItemDao.findDeliveryItem(deliveryItemDTO.getId());
//        deliveryItem.setDelivery(deliveryItemDTO.getDelivery() == null ? null : mapper.map(deliveryItemDTO.getDelivery(), Delivery.class));
//        deliveryItem.setDescription(deliveryItemDTO.getDescription());
//        deliveryItem.setName(deliveryItemDTO.getName());
//        deliveryItem.setWeight(deliveryItemDTO.getWeight());
        DeliveryItem deliveryItem = mapper.map(deliveryItemDTO, DeliveryItem.class);
        return mapper.map(deliveryItemDao.updateDeliveryItem(deliveryItem), DeliveryItemDTO.class);
    }

    @Override
    public DeliveryItemDTO findDeliveryItem(Long id) {
        if (id == null) {
            throw new NullPointerException("id");
        }
        DeliveryItem deliveryItem = deliveryItemDao.findDeliveryItem(id);
        if (deliveryItem == null) {
            return null;
        }
        return mapper.map(deliveryItem, DeliveryItemDTO.class);
    }

    @Override
    public List<DeliveryItemDTO> getAllDeliveryItems() {
        List<DeliveryItemDTO> list = new ArrayList<>();
        for (DeliveryItem deliveryItem : deliveryItemDao.getAllDeliveryItems()) {
            list.add(mapper.map(deliveryItem, DeliveryItemDTO.class));
        }
        return list;
    }
}
