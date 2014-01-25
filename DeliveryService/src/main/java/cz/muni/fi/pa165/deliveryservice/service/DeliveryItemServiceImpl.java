/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.DeliveryItem;
import cz.muni.fi.pa165.deliveryservice.dao.DeliveryItemDAO;
import cz.muni.fi.pa165.deliveryservice.dto.DeliveryItemDTO;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
@Service
@Transactional
public class DeliveryItemServiceImpl implements DeliveryItemService {

    @Autowired
    DeliveryItemDAO deliveryItemDao;
    @Autowired
    private Mapper mapper;

    public void setDeliveryItemDao(DeliveryItemDAO deliveryItemDao) {
        this.deliveryItemDao = deliveryItemDao;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public DeliveryItemDTO createDeliveryItem(DeliveryItemDTO deliveryItemDTO) {
        if (deliveryItemDTO == null) {
            throw new NullPointerException("deliveryItemDTO");
        }

        DeliveryItem deliveryItem = mapper.map(deliveryItemDTO, DeliveryItem.class);
        deliveryItemDao.createDeliveryItem(deliveryItem);
        return mapper.map(deliveryItem, DeliveryItemDTO.class);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void deleteDeliveryItem(DeliveryItemDTO deliveryItemDTO) {
        if (deliveryItemDTO == null) {
            throw new NullPointerException("deliveryItemDTO");
        }

        DeliveryItem deliveryItem = deliveryItemDao.findDeliveryItem(deliveryItemDTO.getId());
        deliveryItemDao.deleteDeliveryItem(deliveryItem);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or "
            + "(hasRole('ROLE_USER'))")
    @Override
    public DeliveryItemDTO updateDeliveryItem(DeliveryItemDTO deliveryItemDTO) {
        if (deliveryItemDTO == null) {
            throw new NullPointerException("deliveryItemDTO");
        }

        DeliveryItem deliveryItem = mapper.map(deliveryItemDTO, DeliveryItem.class);
        return mapper.map(deliveryItemDao.updateDeliveryItem(deliveryItem), DeliveryItemDTO.class);
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    @Override
    public List<DeliveryItemDTO> getAllDeliveryItems() {
        List<DeliveryItemDTO> list = new ArrayList<>();
        for (DeliveryItem deliveryItem : deliveryItemDao.getAllDeliveryItems()) {
            list.add(mapper.map(deliveryItem, DeliveryItemDTO.class));
        }
        return list;
    }
}
