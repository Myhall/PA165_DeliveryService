/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.dto.CustomerUserDTO;
import cz.muni.fi.pa165.deliveryservice.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
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
public class CustomerUserFacadeImpl implements CustomerUserFacade {

    @Autowired
    private CustomerService customerService;   
    @Autowired
    private UserService userService;
    
    @Override
    public void create(CustomerDTO customerDTO, UserDTO userDTO) {
        if (customerDTO == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (userDTO == null) {
            throw new IllegalArgumentException("user is null");
        }                
        if (customerDTO.getId() != null) {
            throw new IllegalArgumentException("customerDTO.id is null");
        }
        if (userDTO.getId() != null) {
            throw new IllegalArgumentException("userDTO.id is null");
        }        
        
        userService.createUser(userDTO);
        customerDTO.setUser(userDTO);
        customerService.createCustomer(customerDTO);
    }

    @Transactional(readOnly=true)
    @Override
    public CustomerUserDTO getByCustomerId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        CustomerDTO customer = customerService.findCustomer(id);
        UserDTO user = customer.getUser();
        return new CustomerUserDTO(customer, user);
    }

    @Override
    public CustomerUserDTO getByUsername(String username) {
        UserDTO userDTO = userService.findByUsername(username);        
        CustomerDTO customerDTO = customerService.findByUsername(username);
        
        return new CustomerUserDTO(customerDTO, userDTO);
    }

     @PreAuthorize("hasRole('ROLE_ADMIN') or "
    + "(hasRole('ROLE_USER') and principal.username == #customerUserDTO.user.username)")
    @Override
    public void remove(CustomerUserDTO customerUserDTO) {
        if (customerUserDTO == null) {
            throw new NullPointerException("customerUserDTO is null");
        }
        
        if (customerUserDTO.getCustomer().getId() == null) {
            throw new IllegalArgumentException("customer.id is null");
        }
        if (customerUserDTO.getUser().getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }
        
        userService.createUser(customerUserDTO.getUser());
        customerService.deleteCustomer(customerUserDTO.getCustomer());        
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or "
    + "(hasRole('ROLE_USER') and principal.username == #customerUserDTO.user.username)")
    @Override
    public void update(CustomerUserDTO customerUserDTO) {
        if (customerUserDTO == null) {
            throw new NullPointerException("customerUserDTO is null");
        }
        
        if (customerUserDTO.getCustomer().getId() == null) {
            throw new IllegalArgumentException("customer.id is null");
        }
        if (customerUserDTO.getUser().getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }
        
        UserDTO userDTO = customerUserDTO.getUser();        
        CustomerDTO customerDTO = customerUserDTO.getCustomer();        
        
        customerDTO.setUser(userDTO);       
        userService.updateUser(userDTO);
        customerService.createCustomer(customerDTO);
    }

    @Transactional(readOnly=true)
    @Override
    public List<CustomerUserDTO> findAll() {
        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
        List<CustomerUserDTO> cuDTOList = new ArrayList<>();
        for (CustomerDTO c : customerDTOList) {
            cuDTOList.add(new CustomerUserDTO(c, c.getUser()));
        }
        return cuDTOList;
    }
    
}
