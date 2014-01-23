<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<f:message key="registration.update" var="msg"/>
<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.RegistrationActionBean" var="actionBean"/>

        <div id="createCustomer">
            <s:form class="form-horizontal" beanclass="cz.muni.fi.pa165.stis.web.RegistrationActionBean">
                <s:hidden name="customerDTO.id"/>
                <s:hidden name="userDTO.id"/>
                <s:hidden name="userDTO.username"/>
                <fieldset>
                    <legend>
                        <f:message key="registration.update"/>
                    </legend>
                    <font color="red">
                    <c:if test="${not empty actionBean.passwordError}">
                        <div class="errorblock">
                            <f:message key="password.error" />
                        </div>
                    </c:if>                    
                    </font>
                    <%@include file="/registration/form-update.jsp"%>    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary" name="save"><i class="icon-plus"></i> <f:message key="button.edit"/></button>
                        <s:link beanclass="cz.muni.fi.pa165.stis.web.RegistrationActionBean" class="btn" event="list"><i class="icon-ban-circle"></i> <f:message key="button.cancel"/></s:link>
                    </div>
                </fieldset>            
            </s:form>
        </div>        
    </s:layout-component>
</s:layout-render>
