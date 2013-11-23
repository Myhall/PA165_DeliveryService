package cz.muni.fi.pa165.deliverysystemweb;

import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.dto.DeliveryDTO;
import cz.muni.fi.pa165.deliveryservice.dto.DeliveryItemDTO;
import cz.muni.fi.pa165.deliveryservice.enums.DeliveryStatus;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;
import cz.muni.fi.pa165.deliveryservice.service.DeliveryItemService;
import cz.muni.fi.pa165.deliveryservice.service.DeliveryService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 *
 * @author Michal Sorentiny
 */
@UrlBinding("/delivery/${event}")
public class DeliveryActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean protected DeliveryService deliveryService;
    @SpringBean protected CustomerService customerService;
    @SpringBean protected CourierService courierService;
    @SpringBean protected DeliveryItemService deliveryItemService;
    
    private List<DeliveryDTO> deliveries;
    
    @ValidateNestedProperties(value = {
//            @Validate(on = {"add", "save"}, field = "customer", required = true),
//            @Validate(on = {"add", "save"}, field = "status", required = true),
            @Validate(on = {"add", "save"}, field = "placeFrom", required = true, minlength = 1, maxlength = 255),
            @Validate(on = {"add", "save"}, field = "placeTo", required = true, minlength = 1, maxlength = 255)
    })
    private DeliveryDTO delivery;
    
    @DefaultHandler
    public Resolution list() {
        deliveries = deliveryService.getAllDeliveries();
        return new ForwardResolution("/delivery/list.jsp");
    }
    
    public Resolution save() {
        deliveryService.createDelivery(delivery);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution edit() {
        return new ForwardResolution("/delivery/edit.jsp");
    }
    
    public Resolution delete() {
        String id = getContext().getRequest().getParameter("id");
        delivery = deliveryService.findDelivery(Long.valueOf(id));
        deliveryService.deleteDelivery(delivery);
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution addDeliveryItem() {
        //for(String id: getContext().getRequest().getParameter("itemid"))
        String id = getContext().getRequest().getParameter("itemid");
        {
        DeliveryItemDTO item = deliveryItemService.findDeliveryItem(Long.valueOf(id));
            delivery.getItems().add(item);
        }
        return new ForwardResolution("/delivery/edit.jsp");
    }

    public List<DeliveryDTO> getDeliveries() {
        return deliveries;
    }
    
    public void setDeliveries(List<DeliveryDTO> value)
    {
        deliveries = value;
    }

    public DeliveryDTO getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryDTO delivery) {
        this.delivery = delivery;
    }
    
    public List<CustomerDTO> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }
    
    public List<CourierDTO> getAllCouriers()
    {
        return courierService.getAllCouriers();
    }
    
    public List<DeliveryItemDTO> getAllDeliveryItems()
    {
        return deliveryItemService.getAllDeliveryItems();
    }
    
    public List<DeliveryStatus> getAllStatuses()
    {
        return Arrays.asList(DeliveryStatus.values());
    }
      
    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        return null;
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit"})
    public void loadDeliveryFromDatabase() {
        String id = getContext().getRequest().getParameter("id");
        if (id == null) return;
        
        delivery = deliveryService.findDelivery(Long.valueOf(id));
    }
}
