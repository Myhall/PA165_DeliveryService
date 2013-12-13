/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.rest.client;

/**
 *
 * @author Bufo
 */
import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.rest.util.PropertyHelper;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@UrlBinding("/customer/{$event}/")
public class CustomerRestClientBean implements ActionBean {

    private ActionBeanContext context;
    @SpringBean
    private RestTemplate rt;
    @SpringBean
    private PropertyHelper ph;
    
  
    private CustomerDTO customerDto;

    public CustomerDTO getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDTO customerDto) {
        this.customerDto = customerDto;
    }

    public CustomerDTO[] getAllCustomres() {
        CustomerDTO[] allCouriers = null;
        try {
            allCouriers = rt.getForObject(getURL() + "/", CustomerDTO[].class);
            return allCouriers;
        } catch (HttpClientErrorException e) {
            System.err.println("Chyba customer get");
        }
        return allCouriers;
    }

    @Override
    public void setContext(ActionBeanContext actionBeanContext) {
        this.context = actionBeanContext;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    private String getURL() {
        return ph.getCustomersURL();
    }

    @DefaultHandler
    public Resolution list() {
        return new ForwardResolution("/customer/list.jsp");
    }

    public Resolution edit() {
        return new ForwardResolution("/customer/edit.jsp");
    }

    public Resolution save() {
        rt.put(getURL() + "/{id}", customerDto, customerDto.getId());
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution create() {
        rt.put(getURL() + "/create", customerDto);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution update() {
        rt.put(getURL() + "/update/{id}", customerDto.getId(), customerDto);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution delete() {
        rt.delete(getURL() + "/delete/{id}", customerDto.getId());
        return new RedirectResolution(this.getClass(), "list");
    }

    public void loadCourierFromDatabase() {
        String id = context.getRequest().getParameter("customerDto.id");
        if (id != null) {
            customerDto = rt.getForObject(getURL() + "/", CustomerDTO.class, id);
        }
    }
}
