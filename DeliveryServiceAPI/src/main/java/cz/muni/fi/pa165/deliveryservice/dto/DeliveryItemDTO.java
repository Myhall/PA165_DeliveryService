/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
public class DeliveryItemDTO {
    
    private Long id;
    private DeliveryDTO delivery;
    private String name;
    private String description;
    private BigDecimal weight;

    public DeliveryItemDTO() {
    }

    public DeliveryItemDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public DeliveryItemDTO(String name, String description, BigDecimal weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeliveryDTO getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryDTO delivery) {
        this.delivery = delivery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final DeliveryItemDTO other = (DeliveryItemDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
