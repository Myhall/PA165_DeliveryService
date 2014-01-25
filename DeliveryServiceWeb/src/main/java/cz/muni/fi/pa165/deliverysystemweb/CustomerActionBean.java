package cz.muni.fi.pa165.deliverysystemweb;

import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.dto.CustomerUserDTO;
import cz.muni.fi.pa165.deliveryservice.dto.UserDTO;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;
import cz.muni.fi.pa165.deliveryservice.service.CustomerUserFacade;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 *
 * @author Tomas Frkan
 */
@UrlBinding("/customer/{$event}/")
public class CustomerActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected CustomerService customerService;
    @SpringBean
    protected CustomerUserFacade customerFacade;
    private List<CustomerDTO> customerList;
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"save", "update"}, field = "firstName", required = true, minlength = 1, maxlength = 255),
        @Validate(on = {"save", "update"}, field = "lastName", required = true, minlength = 1, maxlength = 255),
        @Validate(on = {"save", "update"}, field = "email", required = true, converter = EmailTypeConverter.class)
    })
    
    
    private CustomerDTO customerDTO;
    @ValidateNestedProperties(value = {
        @Validate(on = {"save", "update"}, field = "username", required = true, minlength = 3, maxlength = 255),
        @Validate(on = {"save", "update"}, field = "password", required = true, minlength = 3)
    })
    private UserDTO userDTO;
    @Validate(on = {"save", "update"}, field = "password2", required = true, minlength = 3, expression="this == userDTO.password")
    private String password2;

    @DefaultHandler
    public Resolution list() {
            customerList = customerService.getAllCustomers(false);
        return new ForwardResolution("/customer/list.jsp");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        customerList = customerService.getAllCustomers(false);
        return null;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public List<CustomerDTO> getCustomerList() {
        return customerList;
    }

    public Resolution save() {
        if(userDTO.getPassword().equals(password2)) {
            customerFacade.create(customerDTO, userDTO);
        }
        return new RedirectResolution(this.getClass(), "list");

    }

    public Resolution delete() {
        Long id = Long.parseLong(getContext().getRequest().getParameter("customerDTO.id"));
        customerFacade.remove(customerFacade.getByCustomerId(id));
        return new RedirectResolution(this.getClass(), "list");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "update"})
    public void loadCustomerFromDatabase() {
        String ids = getContext().getRequest().getParameter("customerDTO.id");
        
        if (ids == null) {
            return;
        }
        CustomerUserDTO cudto = customerFacade.getByCustomerId(Long.valueOf(ids));
        customerDTO = cudto.getCustomer();
        userDTO = cudto.getUser();
    }

    public Resolution edit() {
        return new ForwardResolution("/customer/edit.jsp");
    }

    public Resolution update() {
        customerFacade.update(new CustomerUserDTO(customerDTO, userDTO));
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution cancel() {
        return new RedirectResolution(this.getClass(), "list");
    }
}