package cz.muni.fi.pa165.deliveryservice.dto;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.DeliveryItem;
import cz.muni.fi.pa165.deliveryservice.DeliveryStatus;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Michal Sorentiny
 */
public class DeliveryDTO {
    
    private Long id;
    private BigDecimal price;
    private Customer customer;
    private Courier courier;
    private String additionalInformation;
    private DeliveryStatus status;
    private List<DeliveryItem> items;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
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

    public List<DeliveryItem> getItems() {
        return items;
    }

    public void setItems(List<DeliveryItem> items) {
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

    public DeliveryDTO(Long id, BigDecimal price, Customer customer, Courier courier, String additionalInformation, DeliveryStatus status, List<DeliveryItem> items, String placeFrom, String placeTo) {
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
    
}
