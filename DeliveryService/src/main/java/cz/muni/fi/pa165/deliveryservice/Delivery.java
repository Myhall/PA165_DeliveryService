package cz.muni.fi.pa165.deliveryservice;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author Michal Sorentiny
 */
@Entity()
@Table(name = "DELIVERIES")
public class Delivery implements Serializable {
    
    private Long id;
    private BigDecimal price;
    private Customer customer;
    private Courier courier;
    private String additionalInformation;
    private DeliveryStatus status;
    private List<DeliveryItem> items;
    private String placeFrom;
    private String placeTo;

    public Delivery() {
        this.status = DeliveryStatus.CREATED;
        this.items = new ArrayList<>();
    }

    public Delivery(Customer customer, String placeFrom, String placeTo) {
        this.customer = customer;
        this.placeFrom = placeFrom;
        this.placeTo = placeTo;
        this.status = DeliveryStatus.CREATED;
        this.items = new ArrayList<>();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    
    @ManyToOne
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
    @ManyToOne
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

    @Enumerated(EnumType.STRING)
    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
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
    
    public void addDeliveryItem(DeliveryItem item)
    {
        if (item == null) {
            throw new NullPointerException("item cannot be null");
        }
        this.items.add(item);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Delivery other = (Delivery) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    
}
