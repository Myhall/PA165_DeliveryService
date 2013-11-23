package cz.muni.fi.pa165.deliveryservice.dto;

import cz.muni.fi.pa165.deliveryservice.enums.DeliveryStatus;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Michal Sorentiny
 */
public class DeliveryDTO {
    
    private Long id;
    private BigDecimal price;
    private CustomerDTO customer;
    private CourierDTO courier;
    private String additionalInformation;
    private DeliveryStatus status;
    private List<DeliveryItemDTO> items;
    private String placeFrom;
    private String placeTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public CourierDTO getCourier() {
        return courier;
    }

    public void setCourier(CourierDTO courier) {
        this.courier = courier;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public List<DeliveryItemDTO> getItems() {
        return items;
    }

    public void setItems(List<DeliveryItemDTO> items) {
        this.items = items;
    }

    public String getPlaceFrom() {
        return placeFrom;
    }

    public void setPlaceFrom(String placeFrom) {
        this.placeFrom = placeFrom;
    }

    public String getPlaceTo() {
        return placeTo;
    }

    public void setPlaceTo(String placeTo) {
        this.placeTo = placeTo;
    }

    public DeliveryDTO() {
        items = new ArrayList<>();
    }

    public DeliveryDTO(Long id, BigDecimal price, CustomerDTO customer, CourierDTO courier, String additionalInformation, DeliveryStatus status, List<DeliveryItemDTO> items, String placeFrom, String placeTo) {
        this.id = id;
        this.price = price;
        this.customer = customer;
        this.courier = courier;
        this.additionalInformation = additionalInformation;
        this.status = status;
        this.items = items;
        this.placeFrom = placeFrom;
        this.placeTo = placeTo;
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DeliveryDTO other = (DeliveryDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
