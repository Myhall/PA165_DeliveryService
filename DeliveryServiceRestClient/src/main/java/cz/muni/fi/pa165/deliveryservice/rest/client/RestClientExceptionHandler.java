package cz.muni.fi.pa165.deliveryservice.rest.client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.exception.DefaultExceptionHandler;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.client.HttpServerErrorException;

public class RestClientExceptionHandler extends DefaultExceptionHandler {
        
    private String rootCause;
      
    public Resolution handleServletException(ServletException exc, HttpServletRequest request, HttpServletResponse response) {
        rootCause = this.getCause(exc).toString();
        return new ForwardResolution(ErrorHandlerActionBean.class, "error").addParameter("exception", exc.toString()).addParameter("rootCause", rootCause);                
    }
        
    
    public Resolution handleServerErrorException(HttpServerErrorException exc, HttpServletRequest request, HttpServletResponse response) {
        rootCause = this.getCause(exc).toString();
        return new ForwardResolution(ErrorHandlerActionBean.class, "error").addParameter("exception", exc).addParameter("rootCause", rootCause);
    }
    
    
    public Resolution handleGeneric(Exception exc, HttpServletRequest request, HttpServletResponse response) {
        // general exception handling
        rootCause = getCause(exc).toString();
        return new ForwardResolution(ErrorHandlerActionBean.class, "error").addParameter("exception", exc).addParameter("rootCause", rootCause);
    }
    
    public Throwable getCause(Throwable exc) {
        return ExceptionUtils.getRootCause(exc);
    }
}
