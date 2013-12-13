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
        CourierDTO[] allCouriers = null;
        try {
            allCouriers = rt.getForObject(getURL() + "/", CourierDTO[].class);
            return allCouriers;
        } catch (HttpClientErrorException e) {
            System.err.println("Chyba courier get");
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
        return ph.getCouriersURL();
    }

    @DefaultHandler
    public Resolution list() {
        return new ForwardResolution("/courier/list.jsp");
    }

    public Resolution edit() {
        return new ForwardResolution("/courier/edit.jsp");
    }

    public Resolution save() {
        rt.put(getURL() + "/{id}", courierDto, courierDto.getId());
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution create() {
        rt.put(getURL() + "/create", courierDto);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution update() {
        rt.put(getURL() + "/update/{id}", courierDto.getId(), courierDto);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution delete() {
        rt.delete(getURL() + "/delete/{id}", courierDto.getId());
        return new RedirectResolution(this.getClass(), "list");
    }

    public void loadCourierFromDatabase() {
        String id = context.getRequest().getParameter("courierDto.id");
        if (id != null) {
            courierDto = rt.getForObject(getURL() + "/", CourierDTO.class, id);
        }
    }
}
