/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dto;

/**
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
public class CourierUserDTO {
        private CourierDTO courier;
    private UserDTO user;
    
    
    
    public CourierUserDTO(CourierDTO courier, UserDTO user) {
        this.courier = courier;
        this.user = user;
    }           

    public CourierDTO getCourier() {
        return courier;
    }

    public void setCourier(CourierDTO courier) {
        this.courier = courier;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 63 * hash + (this.courier != null ? this.courier.hashCode() : 0);
        hash = 63 * hash + (this.user != null ? this.user.hashCode() : 0);
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
        final CourierUserDTO other = (CourierUserDTO) obj;
        if (this.courier != other.courier && (this.courier == null || !this.courier.equals(other.courier))) {
            return false;
        }
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }
}
