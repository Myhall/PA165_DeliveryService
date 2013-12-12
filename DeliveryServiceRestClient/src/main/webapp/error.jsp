<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- Short hand for the context root. --%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<f:message key="extraService.error" var="msg"/>

<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliveryservice.rest.client.ErrorHandlerActionBean" var="actionBean"/>
        <br>
        <f:message key="error.message"/>
        
        <hr>
        <div class="container-fluid">
            <div class="form-actions">
                <c:choose>                
                    <c:when test="${not empty actionBean.rootCause}">
                        <f:message key="rootCause"/> ${actionBean.rootCause}
                    </c:when>
                </c:choose>
            </div>
            <div class="form-actions">
            <br>
            <f:message key="error"/> ${actionBean.exception}
            </div>
        </div>        
    </s:layout-component>
</s:layout-render>