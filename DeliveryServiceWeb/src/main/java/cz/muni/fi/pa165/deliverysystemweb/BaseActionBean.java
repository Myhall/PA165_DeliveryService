package cz.muni.fi.pa165.deliverysystemweb;

import cz.muni.fi.pa165.deliverysystemweb.security.CustomUserDetails;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.taglibs.standard.functions.Functions;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Base actionBean implementing the required methods for setting and getting context.
 *
 * @author Jan Vorcak
 */
public abstract class BaseActionBean implements ActionBean {
    
    private ActionBeanContext context;
     private CustomUserDetails user;
    
    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    public CustomUserDetails getUser() {
        return user;
    }

    public void setUser(CustomUserDetails user) {
        this.user = user;
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation)
    public void loadUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (o instanceof CustomUserDetails) {
                user = (CustomUserDetails) o;
            }
        }
    }
    
    public static String escapeHTML(String s) {
        return Functions.escapeXml(s);
    }
}
