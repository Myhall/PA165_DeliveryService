/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dto;

/**
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
public class CustomerUserDTO {
    
    private CustomerDTO customer;
    private UserDTO user;
    
    
    
    public CustomerUserDTO(CustomerDTO customer, UserDTO user) {
        this.customer = customer;
        this.user = user;
    }           

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
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
        hash = 63 * hash + (this.customer != null ? this.customer.hashCode() : 0);
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
        final CustomerUserDTO other = (CustomerUserDTO) obj;
        if (this.customer != other.customer && (this.customer == null || !this.customer.equals(other.customer))) {
            return false;
        }
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }

}
