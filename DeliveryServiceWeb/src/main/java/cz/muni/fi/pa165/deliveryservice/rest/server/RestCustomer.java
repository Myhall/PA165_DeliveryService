/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.rest.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;
import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 *
 * @author Bufo
 */
@WebServlet(urlPatterns = "/Rest/Customer/")
public class RestCustomer extends HttpServlet {

    @SpringBean
    private CustomerService customerService;

    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
        ObjectMapper mapper = new ObjectMapper();

        for (Entry<String, String[]> entry : params.entrySet()) {
            if (entry.getKey().contains("find")) {
                Long id = Long.valueOf(request.getParameter("find"));
                CustomerDTO customerDto = customerService.findCustomer(id);
                response.setContentType("application/json");
                mapper.writeValue(response.getOutputStream(), customerDto);
                
            } else if (entry.getKey().contains("getAllCustomers")) {
                List<CustomerDTO> list = customerService.getAllCustomers();
                response.setContentType("application/json");
                mapper.writeValue(response.getOutputStream(), list);
            }
        }

    }

    protected synchronized void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
        for (Entry<String, String[]> entry : params.entrySet()) {
            if (entry.getKey().contains("create")) {
                CustomerDTO customerDto = new CustomerDTO();
                customerDto.setFirstName(params.get("firstName")[0]);
                customerDto.setLastName(params.get("lastName")[0]);
                customerDto.setEmail(params.get("email")[0]);
                customerDto.setCity(params.get("city")[0]);
                customerDto.setStreet(params.get("street")[0]);
                customerDto.setZipCode(params.get("zipCode")[0]);
                customerDto.setCountry(params.get("country")[0]);
                customerDto.setTelephoneNumber(params.get("telephoneNumber")[0]);
                customerService.createCustomer(customerDto);
            } else if (entry.getKey().contains("update")) {
                CustomerDTO customerDto = customerService.findCustomer(Long.valueOf(params.get("id")[0]));
                if (customerDto != null) {
                    customerDto.setFirstName(params.get("firstName")[0]);
                    customerDto.setLastName(params.get("lastName")[0]);
                    customerDto.setEmail(params.get("email")[0]);
                    customerDto.setCity(params.get("city")[0]);
                    customerDto.setStreet(params.get("street")[0]);
                    customerDto.setZipCode(params.get("zipCode")[0]);
                    customerDto.setCountry(params.get("country")[0]);
                    customerDto.setTelephoneNumber(params.get("telephoneNumber")[0]);
                    customerService.updateCustomer(customerDto);
                }
            }
        }
    }

    protected synchronized void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
        for (Entry<String, String[]> entry : params.entrySet()) {
            if (entry.getKey().contains("delete")) {
                CustomerDTO customerDto = customerService.findCustomer(Long.valueOf(request.getParameter("delete")));
                if (customerDto != null) {
                    customerService.deleteCustomer(customerDto);
                }
            }
        }
    }

    protected synchronized void put(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
         for (Entry<String, String[]> entry : params.entrySet()) {
            if (entry.getKey().contains("update")) {
                ObjectMapper mapper = new ObjectMapper();
                CustomerDTO customerDto = mapper.readValue(request.getInputStream(), CustomerDTO.class);
                customerService.updateCustomer(customerDto);
            }
        }
    }
}
