/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliverysystemweb;

import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 *
 * @author janvorcak
 */
@UrlBinding("/couriers/${event}")
public class CourierActionBean extends BaseActionBean {

    @SpringBean
    protected CourierService courierService;

    private List<CourierDTO> courierDTOs;
    private CourierDTO courierDTO;

    @DefaultHandler
    public Resolution list() {
        courierDTOs = courierService.getAllCouriers();
        return new ForwardResolution("/courier/list.jsp");
    }

    public Resolution save() {
        courierService.createCourier(courierDTO);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution edit() {
        return new ForwardResolution("/courier/edit.jsp");
    }

    public List<CourierDTO> getCourierDTOs() {
        return courierDTOs;
    }

    public void setCourierDTOs(List<CourierDTO> courierDTOs) {
        this.courierDTOs = courierDTOs;
    }

    public CourierDTO getCourierDTO() {
        return courierDTO;
    }

    public void setCourierDTO(CourierDTO courierDTO) {
        this.courierDTO = courierDTO;
    }

}
