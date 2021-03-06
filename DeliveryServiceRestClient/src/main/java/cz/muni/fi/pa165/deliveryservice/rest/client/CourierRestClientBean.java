/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.rest.client;

/**
 *
 * @author Bufo
 */
import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
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

@UrlBinding("/courier/{$event}/")
public class CourierRestClientBean implements ActionBean {

    private ActionBeanContext context;
    @SpringBean
    private RestTemplate rt;
    @SpringBean
    private PropertyHelper ph;

    private CourierDTO courierDto;

    public CourierDTO getCourierDto() {
        return courierDto;
    }

    public void setCourierDto(CourierDTO courierDto) {
        this.courierDto = courierDto;
    }

    public RestTemplate getRt() {
        return rt;
    }

    public void setRt(RestTemplate rt) {
        this.rt = rt;
    }

    public PropertyHelper getPh() {
        return ph;
    }

    public void setPh(PropertyHelper ph) {
        this.ph = ph;
    }

    public CourierDTO[] getAllCouriers() {
        List<CourierDTO> allCouriers = new ArrayList<>();
        String xml = null;
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            xml = rt.getForObject(getURL() + "/", String.class); 
            Document doc = saxBuilder.build(new StringReader(xml));
            for(Element e : (List<Element>) doc.getRootElement().getChildren()) {
                allCouriers.add(rt.getForObject(getURL() + "/"+e.getChildText("id"), CourierDTO.class));
            }            
        } catch (HttpClientErrorException e) {
            System.err.println("Chyba courier get");
        } catch (JDOMException ex) {
            System.err.println("Chyba JDOME courier get");
        } catch (IOException ex) {
            System.err.println("Chyba IO courier get");
        } 
        return allCouriers.toArray(new CourierDTO[]{});
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
        return ph.getCouriersURL();
    }

    @DefaultHandler
    public Resolution list() {
        
        return new ForwardResolution("/courier/list.jsp");
    }

    public Resolution edit() {
        return new ForwardResolution("/courier/edit.jsp");
    }

    public Resolution create() {
        rt.put(getURL() + "/create", courierDto);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution update() {
        rt.put(getURL() + "/{id}", courierDto, courierDto.getId());
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution delete() {
        rt.delete(getURL() + "/{id}", courierDto.getId());
        return new RedirectResolution(this.getClass(), "list");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "update"})
    public void loadCourierFromDatabase() {
        String id = context.getRequest().getParameter("courierDto.id");
        if (id != null) {
            courierDto = rt.getForObject(getURL() + "/{id}", CourierDTO.class, id);
        }
    }
}
