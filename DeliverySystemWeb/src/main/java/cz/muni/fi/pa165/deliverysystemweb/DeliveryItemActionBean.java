/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliverysystemweb;

import cz.muni.fi.pa165.deliveryservice.dto.DeliveryItemDTO;
import cz.muni.fi.pa165.deliveryservice.service.DeliveryItemService;
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

/**
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
@UrlBinding("/deliveryItems/{$event}/{deliveryItem.id}")
public class DeliveryItemActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected DeliveryItemService deliveryItemService;
    private List<DeliveryItemDTO> deliveryItems;

    @DefaultHandler
    public Resolution list() {
        try {
            deliveryItems = deliveryItemService.getAllDeliveryItems();
        } catch (DataAccessException ex) {
            return new ErrorResolution(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return new ForwardResolution("/deliveryItem/list.jsp");
    }

    public List<DeliveryItemDTO> getDeliveryItems() {
        return deliveryItems;
    }
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", maxlength = 255, required = true),
        @Validate(on = {"add", "save"}, field = "description", maxlength = 255),
        @Validate(on = {"add", "save"}, field = "weight", minvalue = 0)
    })
    private DeliveryItemDTO deliveryItem;

    public Resolution add() {
        try {
            deliveryItemService.createDeliveryItem(deliveryItem);
        } catch (DataAccessException ex) {
            return new ErrorResolution(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    public DeliveryItemDTO getDeliveryItem() {
        return deliveryItem;
    }

    public void setDeliveryItem(DeliveryItemDTO item) {
        this.deliveryItem = item;
    }

    public Resolution delete() {
        try {
            deliveryItem = deliveryItemService.findDeliveryItem(deliveryItem.getId());
            deliveryItemService.deleteDeliveryItem(deliveryItem);
        } catch (DataAccessException ex) {
            return new ErrorResolution(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadDeliveryItemFromDatabase() {
        String ids = getContext().getRequest().getParameter("deliveryItem.id");
        if (ids == null) {
            return;
        }
        deliveryItem = deliveryItemService.findDeliveryItem(Long.parseLong(ids));
    }

    public Resolution edit() {
        return new ForwardResolution("/deliveryItem/edit.jsp");
    }

    public Resolution save() {
        try {
            deliveryItemService.updateDeliveryItem(deliveryItem);
        } catch (DataAccessException ex) {
            return new ErrorResolution(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution cancel() {
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        try {
            deliveryItems = deliveryItemService.getAllDeliveryItems();
        } catch (DataAccessException ex) {
            return new ErrorResolution(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}
