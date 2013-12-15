/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.rest.client;

/**
 *
 * @author Michal Sorentiny
 */
import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.rest.util.PropertyHelper;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
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

    public CustomerDTO[] getAllCustomers() {
        List<CustomerDTO> allCustomers = new ArrayList<>();
        String xml = null;
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            xml = rt.getForObject(getURL() + "/", String.class); 
            Document doc = saxBuilder.build(new StringReader(xml));
            for(Element e : (List<Element>) doc.getRootElement().getChildren()) {
                allCustomers.add(rt.getForObject(getURL() + "/"+e.getChildText("id"), CustomerDTO.class));
            }            
        } catch (HttpClientErrorException e) {
            System.err.println("Chyba courier get");
        } catch (JDOMException ex) {
            System.err.println("Chyba JDOME courier get");
        } catch (IOException ex) {
            System.err.println("Chyba IO courier get");
        } 
        return allCustomers.toArray(new CustomerDTO[]{});
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

    public Resolution create() {
        rt.put(getURL() + "/create", customerDto);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution update() {
        rt.put(getURL() + "/{id}", customerDto.getId(), customerDto);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution delete() {
        rt.delete(getURL() + "/{id}", customerDto.getId());
        return new RedirectResolution(this.getClass(), "list");
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "update"})
    public void loadCourierFromDatabase() {
        String id = context.getRequest().getParameter("customerDto.id");
        if (id != null) {
            customerDto = rt.getForObject(getURL() + "/", CustomerDTO.class, id);
        }
    }
}
