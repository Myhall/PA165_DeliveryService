/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.rest.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bufo
 */
@WebServlet(urlPatterns = "/Rest/Courier/*")
public class RestCourier extends HttpServlet {

    private CourierService courierService;

    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
        ObjectMapper mapper = new ObjectMapper();

        for (Entry<String, String[]> entry : params.entrySet()) {
            if (entry.getKey().contains("find")) {
                Long id = Long.valueOf(request.getParameter("find"));
                CourierDTO courier = courierService.findCourier(id);
                response.setContentType("application/json");
                mapper.writeValue(response.getOutputStream(), courier);

            } else if (entry.getKey().contains("getAllCouriers")) {
                List<CourierDTO> list = courierService.getAllCouriers();
                response.setContentType("application/json");
                mapper.writeValue(response.getOutputStream(), list);

            }
        }
    }

    protected synchronized void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
        for (Entry<String, String[]> e : params.entrySet()) {
            if (e.getKey().contains("create")) {
                CourierDTO courierDto = new CourierDTO();
                courierDto.setFirstName(params.get("firstName")[0]);
                courierDto.setLastName(params.get("lastName")[0]);
                courierDto.setEmail(params.get("email")[0]);
                courierService.createCourier(courierDto);
            } else if (e.getKey().contains("update")) {
                CourierDTO courierDto = courierService.findCourier(Long.valueOf(params.get("id")[0]));
                if (courierDto != null) {
                    courierDto.setFirstName(params.get("firstName")[0]);
                    courierDto.setLastName(params.get("lastName")[0]);
                    courierDto.setEmail(params.get("username")[0]);
                    courierService.updateCourier(courierDto);
                }
            }
        }
    }

    protected synchronized void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
        for (Entry<String, String[]> entry : params.entrySet()) {
            if (entry.getKey().contains("delete")) {
                CourierDTO courierDto = courierService.findCourier(Long.valueOf(request.getParameter("delete")));
                if (courierDto != null) {
                    courierService.deleteCourier(courierDto);
                }
            }
        }
    }

    protected synchronized void put(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
        for (Entry<String, String[]> entry : params.entrySet()) {
            if (entry.getKey().contains("update")) {
                ObjectMapper mapper = new ObjectMapper();
                CourierDTO courierDto = mapper.readValue(request.getInputStream(), CourierDTO.class);
                courierService.updateCourier(courierDto);
            }
        }
    }
}
