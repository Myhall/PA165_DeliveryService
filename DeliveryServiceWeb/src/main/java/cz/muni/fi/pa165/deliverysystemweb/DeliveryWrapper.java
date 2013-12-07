/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliverysystemweb;

import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.dto.DeliveryDTO;
import cz.muni.fi.pa165.deliveryservice.dto.DeliveryItemDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
public class DeliveryWrapper extends DeliveryDTO {

    private List<Long> deliveryItemIds;

    public DeliveryWrapper() {
    }

    public DeliveryWrapper(DeliveryDTO ddto) {
        setAdditionalInformation(ddto.getAdditionalInformation());
        setCourier(ddto.getCourier());
        setCustomer(ddto.getCustomer());
        setId(ddto.getId());
        setItems(ddto.getItems());
        setPlaceFrom(ddto.getPlaceFrom());
        setPlaceTo(ddto.getPlaceTo());
        setPrice(ddto.getPrice());
        setStatus(ddto.getStatus());
        if (this.getItems() != null) {
            List<Long> ids = new ArrayList<>();
            for (DeliveryItemDTO didto : this.getItems()) {
                ids.add(didto.getId());
            }
            this.deliveryItemIds = ids;
        }
    }

    public List<Long> getDeliveryItemIds() {
        return deliveryItemIds;
    }

    public void setDeliveryItemIds(List<Long> deliveryItemIds) {
        this.deliveryItemIds = deliveryItemIds;
    }

    @Override
    public String toString() {
        return "DeliveryWrapper{" + "deliveryItemIds=" + deliveryItemIds + '}';
    }

}
