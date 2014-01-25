package cz.muni.fi.pa165.deliverysystemweb;

import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.Delivery;
import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.dto.DeliveryDTO;
import cz.muni.fi.pa165.deliveryservice.dto.DeliveryItemDTO;
import cz.muni.fi.pa165.deliveryservice.enums.DeliveryStatus;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;
import cz.muni.fi.pa165.deliveryservice.service.DeliveryItemService;
import cz.muni.fi.pa165.deliveryservice.service.DeliveryService;
import cz.muni.fi.pa165.deliverysystemweb.security.CustomUserDetails;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
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
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Michal Sorentiny
 */
@UrlBinding("/delivery/${event}")
public class DeliveryActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected DeliveryService deliveryService;
    @SpringBean
    protected CustomerService customerService;
    @SpringBean
    protected CourierService courierService;
    @SpringBean
    protected DeliveryItemService deliveryItemService;
    private List<DeliveryDTO> deliveries;
    @ValidateNestedProperties(value = {
        //            @Validate(on = {"add", "save"}, field = "customer", required = true),
        //            @Validate(on = {"add", "save"}, field = "status", required = true),
        @Validate(on = {"add", "save"}, field = "placeFrom", required = true, minlength = 1, maxlength = 255),
        @Validate(on = {"add", "save"}, field = "placeTo", required = true, minlength = 1, maxlength = 255), //            @Validate(on = {"add", "save"}, field = "items", converter = DeliveryItemTypeConverter.class)
    })
    private DeliveryWrapper delivery;
    //private DeliveryDTO delivery;

    @DefaultHandler
    public Resolution list() {

        CustomerDTO customer = getLoggedCustomer();
        
        if (customer != null) {
            deliveries = deliveryService.getDeliveriesByCustomer(customer);
        } else {
            deliveries = deliveryService.getAllDeliveries();
        }

        return new ForwardResolution("/delivery/list.jsp");
    }

    public Resolution save() {
        try {
            if (delivery.getDeliveryItemIds() != null) {
                List<DeliveryItemDTO> didtos = new ArrayList<>();
                for (Long id : delivery.getDeliveryItemIds()) {
                    DeliveryItemDTO didto = deliveryItemService.findDeliveryItem(id);
                    if (didto != null) {
                        didtos.add(didto);
                    }
                }
                delivery.setItems(didtos);
            }
            CustomerDTO customer = getLoggedCustomer();
            if(customer != null) {
                delivery.setCustomer(customer);
            }
            deliveryService.createDelivery(delivery);
        } catch (DataAccessException ex) {
            return new ErrorResolution(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution update() {
        try {
            if (delivery.getDeliveryItemIds() != null) {
                List<DeliveryItemDTO> didtos = new ArrayList<>();
                for (Long id : delivery.getDeliveryItemIds()) {
                    DeliveryItemDTO didto = deliveryItemService.findDeliveryItem(id);
                    if (didto != null) {
                        didtos.add(didto);
                    }
                }
                delivery.setItems(didtos);
            }
            deliveryService.updateDelivery(delivery);
        } catch (DataAccessException ex) {
            return new ErrorResolution(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution edit() {
        return new ForwardResolution("/delivery/edit.jsp");
    }

    public Resolution delete() {
        String id = getContext().getRequest().getParameter("id");
        DeliveryDTO toRemove = deliveryService.findDelivery(Long.valueOf(id));
        deliveryService.deleteDelivery(toRemove);
        return new RedirectResolution(this.getClass(), "list");
    }

    private CustomerDTO getLoggedCustomer() {
        // get security context from thread local
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }

        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return null;
        }

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ROLE_USER".equals(auth.getAuthority())) {
                CustomUserDetails cud = (CustomUserDetails) authentication.getPrincipal();
                if (cud != null) {
                    return cud.getCustomer();
                }
                
            }
        }

        return null;
    }

    public List<DeliveryDTO> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryDTO> value) {
        deliveries = value;
    }

    public DeliveryWrapper getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryWrapper delivery) {
        this.delivery = delivery;
    }

    public List<DeliveryItemDTO> getAllDeliveryItems() {
        return deliveryItemService.getAllDeliveryItems();
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public List<CourierDTO> getAllCouriers() {
        return courierService.getAllCouriers();
    }

    public List<DeliveryStatus> getAllStatuses() {
        return Arrays.asList(DeliveryStatus.values());
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        return null;
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit"})
    public void loadDeliveryFromDatabase() {
        String id = getContext().getRequest().getParameter("id");
        if (id == null) {
            return;
        }

        delivery = new DeliveryWrapper(deliveryService.findDelivery(Long.valueOf(id)));
    }
}
