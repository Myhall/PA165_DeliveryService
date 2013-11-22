/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliverysystemweb;

import cz.muni.fi.pa165.deliveryservice.dto.DeliveryItemDTO;
import cz.muni.fi.pa165.deliveryservice.service.DeliveryItemService;
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
 * @author Filip Volner <volner@mail.muni.cz>
 */
@UrlBinding("/deliveryItems/{$event}/{deliveryItem.id}")
public class DeliveryItemActionBean extends BaseActionBean implements ValidationErrorHandler  {

    @SpringBean
    protected DeliveryItemService deliveryItemService;
    
    private List<DeliveryItemDTO> deliveryItems;

    @DefaultHandler
    public Resolution list() {
        deliveryItems = deliveryItemService.getAllDeliveryItems();
        return new ForwardResolution("/deliveryItem/list.jsp");
    }

    public List<DeliveryItemDTO> getDeliveryItems() {
        return deliveryItems;
    }
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true),
        //@Validate(on = {"add", "save"}, field = "weight", required = true, minvalue = 0)
    })
    private DeliveryItemDTO deliveryItem;

    public Resolution add() {
        deliveryItemService.createDeliveryItem(deliveryItem);
        return new RedirectResolution(this.getClass(),"list");
    }

    public DeliveryItemDTO getDeliveryItem() {
        return deliveryItem;
    }

    public void setDeliveryItem(DeliveryItemDTO item) {
        this.deliveryItem = item;
    }
    
    public Resolution delete() {
        deliveryItem = deliveryItemService.findDeliveryItem(deliveryItem.getId());
        deliveryItemService.deleteDeliveryItem(deliveryItem);
        return new RedirectResolution(this.getClass(), "list");
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadDeliveryItemFromDatabase() {
        String ids = getContext().getRequest().getParameter("deliveryItem.id");
        if (ids == null) return;
        deliveryItem = deliveryItemService.findDeliveryItem(Long.parseLong(ids));
    }
    
    public Resolution edit() {
        return new ForwardResolution("/deliveryItem/edit.jsp");
    }
    
    public Resolution save() {        
        deliveryItemService.updateDeliveryItem(deliveryItem);
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution cancel() {       
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        deliveryItems = deliveryItemService.getAllDeliveryItems();
        return null;
    }
}
