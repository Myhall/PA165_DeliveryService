/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliverysystemweb;

import cz.muni.fi.pa165.deliveryservice.dto.DeliveryItemDTO;
import cz.muni.fi.pa165.deliveryservice.service.DeliveryItemService;
import cz.muni.fi.pa165.deliveryservice.service.DeliveryItemServiceImpl;
import java.util.Collection;
import java.util.Locale;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.TypeConverter;
 
/**
 *
 * @author 
 */
public class DeliveryItemTypeConverter implements TypeConverter {
 
    private DeliveryItemService deliveryItemService = new DeliveryItemServiceImpl();
   
    @Override
    public DeliveryItemDTO convert(String id, Class user, Collection error) {
        if(id != null) {
            System.out.println(id);
            System.out.println(id.split(","));
            return deliveryItemService.findDeliveryItem(Long.valueOf(id));
        }
        return null;
    }
 
    @Override
    public void setLocale(Locale locale) {
    }
}
