/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliverysystemweb;

import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
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
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author janvorcak
 */
@UrlBinding("/courier/${event}")
public class CourierActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected CourierService courierService;

    private List<CourierDTO> courierDTOs;

    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "firstName", required = true, minlength = 3, maxlength = 255),
        @Validate(on = {"add", "save"}, field = "lastName", required = true, minlength = 3, maxlength = 255),
        @Validate(on = {"add", "save"}, field = "email", required = true, converter = EmailTypeConverter.class)
    })
    private CourierDTO courierDTO;

    @DefaultHandler
    public Resolution list() {
        try {
            courierDTOs = courierService.getAllCouriers();
        } catch (DataAccessException ex) {
            return new ErrorResolution(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return new ForwardResolution("/courier/list.jsp");
    }

    public Resolution save() {
        courierDTO.setFirstName(escapeHTML(courierDTO.getFirstName()));
        courierDTO.setLastName(escapeHTML(courierDTO.getLastName()));
        courierDTO.setEmail(escapeHTML(courierDTO.getEmail()));
        try {
            courierService.createCourier(courierDTO);
        } catch (DataAccessException ex) {
            return new ErrorResolution(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution view() {
        String id = getContext().getRequest().getParameter("id");

        try {
            courierDTO = courierService.findCourier(Long.valueOf(id));
        } catch (DataAccessException ex) {
            return new ErrorResolution(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        if (courierDTO == null) {
            return new ErrorResolution(HttpServletResponse.SC_NOT_FOUND);
        }
        return new ForwardResolution("/courier/view.jsp");
    }

    public Resolution edit() {
        return new ForwardResolution("/courier/edit.jsp");
    }

    public Resolution update() {
        courierDTO.setFirstName(escapeHTML(courierDTO.getFirstName()));
        courierDTO.setLastName(escapeHTML(courierDTO.getLastName()));
        courierDTO.setEmail(escapeHTML(courierDTO.getEmail()));
        try {
            courierService.updateCourier(courierDTO);
        } catch (DataAccessException ex) {
            return new ErrorResolution(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution delete() {
        String id = getContext().getRequest().getParameter("id");
        try {
            courierDTO = courierService.findCourier(Long.valueOf(id));
            courierService.deleteCourier(courierDTO);
        } catch (DataAccessException ex) {
            return new ErrorResolution(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return new RedirectResolution(this.getClass(), "list");
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

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        return null;
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "update"})
    public void loadCourierFromDatabase() {
        String id = getContext().getRequest().getParameter("courierDTO.id");
        if (id == null) {
            return;
        }

        courierDTO = courierService.findCourier(Long.valueOf(id));
    }

}
