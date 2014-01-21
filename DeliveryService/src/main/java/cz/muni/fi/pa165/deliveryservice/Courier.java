package cz.muni.fi.pa165.deliveryservice;

import cz.muni.fi.pa165.deliveryservice.enums.DeliveryStatus;
import cz.muni.fi.pa165.deliveryservice.exceptions.BadDeliveryState;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Courier entity class
 * @author Jan Vorcak
 */
@Entity
public class Courier implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Delivery> deliveries;
    private User user;

    public Courier() {
    }
    
    public Courier(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "courier")
    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    /**
     * Pick delivery from a customer
     * @param delivery - delivery to be picked
     * @throws BadDeliveryState if delivery is already assigned or delivered
     */
    public void pickDelivery(Delivery delivery) throws BadDeliveryState {
        if (delivery.getStatus() != DeliveryStatus.CREATED) {
            throw new BadDeliveryState("Trying to pick delivery with illegal status");
        }

        delivery.setStatus(DeliveryStatus.PICKED);

        delivery.setCourier(this);
    }

    /**
     * Deliver delivery to the specified address
     * @param delivery - delivery to be delivered
     * @throws BadDeliveryState if it's not picked up or it's already delivered
     */
    public void deliver(Delivery delivery) throws BadDeliveryState {
        if (delivery.getStatus() != DeliveryStatus.PICKED) {
            throw new BadDeliveryState("Trying to deliver delivery with illegal status");
        }

        if (!delivery.getCourier().equals(this)) {
            throw new BadDeliveryState("Trying to deliver delivery which is now owned");
        }

        delivery.setStatus(DeliveryStatus.DELIVERED);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.email != null ? this.email.hashCode() : 0);
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
        final Courier other = (Courier) obj;
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Courier{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + '}';
    }
}
