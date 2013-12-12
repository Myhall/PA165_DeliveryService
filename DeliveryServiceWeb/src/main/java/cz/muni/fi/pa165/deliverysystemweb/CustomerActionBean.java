package cz.muni.fi.pa165.deliverysystemweb;

import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;
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
    private List<CustomerDTO> customerList;
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "firstName", required = true, minlength = 1, maxlength = 255),
        @Validate(on = {"add", "save"}, field = "lastName", required = true, minlength = 1, maxlength = 255),
        @Validate(on = {"add", "save"}, field = "email", required = true, converter = EmailTypeConverter.class)
    })
    private CustomerDTO customerDTO;

    @DefaultHandler
    public Resolution list() {
            customerList = customerService.getAllCustomers();
        return new ForwardResolution("/customer/list.jsp");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        customerList = customerService.getAllCustomers();
        return null;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public List<CustomerDTO> getCustomerList() {
        return customerList;
    }

    public Resolution save() {
        customerService.createCustomer(customerDTO);
        return new RedirectResolution(this.getClass(), "list");

    }

    public Resolution delete() {
        customerDTO = customerService.findCustomer(customerDTO.getId());
        customerService.deleteCustomer(customerDTO);
        return new RedirectResolution(this.getClass(), "list");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "update"})
    public void loadCustomerFromDatabase() {
        String ids = getContext().getRequest().getParameter("customerDTO.id");
        if (ids == null) {
            return;
        }
        customerDTO = customerService.findCustomer(Long.valueOf(ids));
    }

    public Resolution edit() {
        return new ForwardResolution("/customer/edit.jsp");
    }

    public Resolution update() {
        customerService.updateCustomer(customerDTO);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution cancel() {
        return new RedirectResolution(this.getClass(), "list");
    }
}