package cz.muni.fi.pa165.deliverysystemweb;

import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.dto.CustomerUserDTO;
import cz.muni.fi.pa165.deliveryservice.dto.UserDTO;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;
import cz.muni.fi.pa165.deliveryservice.service.CustomerUserFacade;
import cz.muni.fi.pa165.deliveryservice.service.UserService;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Filip Volner
 */
@UrlBinding("/registration/{$event}/")
public class RegistrationActionBean implements ActionBean {

    private static final Logger logger = LoggerFactory.getLogger(BaseActionBean.class);
    private ActionBeanContext context;
    private String regSucc;
    private String passwordError;
    private String usernameError;
    @ValidateNestedProperties(value = {
        @Validate(on = {"create", "save"}, field = "firstName", required = true),
        @Validate(on = {"create", "save"}, field = "lastName", required = true),
        @Validate(on = {"create", "save"}, field = "address", required = true)
    })
    private CustomerDTO customerDTO;
    @ValidateNestedProperties(value = {
        @Validate(on = {"create"}, field = "username", required = true),
        @Validate(on = {"create", "save"}, field = "password", required = true, minlength = 5)
    })
    private UserDTO userDTO;
    @Validate(on = {"create", "save"}, field = "password2", required = true, minlength = 5)
    private String password2;
    @SpringBean
    protected CustomerUserFacade cuFacade;
    @SpringBean
    protected UserService uService;
    @SpringBean
    protected CustomerService cService;

    @DefaultHandler
    public Resolution add() {
        logger.debug("add()");
        return new ForwardResolution("/registration/create.jsp");
    }

    public Resolution create() {
        logger.debug("newcustomer() cto={} uto]={}", customerDTO, userDTO);

        if (userDTO.getPassword().equals(password2)) {
            if (uService.availableUsername(userDTO.getUsername())) {
                userDTO.setRoleAdmin(false);
                cuFacade.create(customerDTO, userDTO);
                regSucc = "true";
                return new RedirectResolution("/security/login/").addParameter("regSucc", regSucc);
            }
            usernameError = "true";
            return new RedirectResolution(this.getClass()).addParameter("usernameError", usernameError).flash(this);
        }
        //password != password2
        passwordError = "true";
        return new RedirectResolution(this.getClass()).addParameter("passwordError", passwordError).flash(this);
    }

    public Resolution list() {
        logger.debug("list()");
        return new RedirectResolution("/customer/list");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void load() {
        if (getContext().getRequest().getParameter("id") == null) {
            return;
        }
        Long id = Long.parseLong(getContext().getRequest().getParameter("id"));
        CustomerUserDTO cuto = cuFacade.getByCustomerId(id);
        customerDTO = cuto.getCustomer();
        userDTO = cuto.getUser();

        this.setCto(customerDTO);
        this.setUto(userDTO);
        logger.debug("load() \ncuto={} \ncto={} \nuto={}", cuto, customerDTO, userDTO);
    }

    public Resolution edit() {
        logger.debug("edit() getCto={} \ngetUto={}", this.getCto(), this.getUto());
        return new ForwardResolution("/registration/edit.jsp");
    }

    public Resolution save() {
        logger.debug("save() cto={} \nuto={}", this.getCto(), this.getUto());
        logger.debug("save() cto={} \nuto={}", customerDTO, userDTO);
        if (userDTO.getPassword().equals(password2)) {
            logger.debug("save() cto={} \nid={}", customerDTO, customerDTO.getUser());
            cuFacade.update(new CustomerUserDTO(customerDTO, userDTO));            
            return new RedirectResolution("/customer/list");
        }
        passwordError = "true";
        return new RedirectResolution("/registration/edit.jsp").addParameter("passwordError", passwordError).flash(this);
    }

    public CustomerDTO getCto() {
        return customerDTO;
    }

    public void setCto(CustomerDTO cto) {
        this.customerDTO = cto;
    }

    public UserDTO getUto() {
        return userDTO;
    }

    public void setUto(UserDTO uto) {
        this.userDTO = uto;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password) {
        this.password2 = password;
    }

    public String getRegSucc() {
        return regSucc;
    }

    public void setRegSucc(String regSucc) {
        this.regSucc = regSucc;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    @Override
    public void setContext(ActionBeanContext abc) {
        this.context = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }
}
