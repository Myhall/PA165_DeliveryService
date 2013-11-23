<%-- 
    Document   : edit
    Created on : 21-Nov-2013, 20:17:28
    Author     : Filip Volner <volner@mail.muni.cz>
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="Edit delivery item">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryItemActionBean" var="actionBean"/>

        <s:form beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryItemActionBean">
            <s:hidden name="deliveryItem.id"/>
            <fieldset><legend><f:message key="label.edit"/></legend>
                <%@include file="form.jsp"%>

                <c:choose>

                    <c:when test="${not empty actionBean.deliveryItem.id}">
                        <s:submit name="save" class="btn btn-primary">
                            <f:message key="buttons.save" />
                        </s:submit>
                    </c:when>

                    <c:otherwise>
                        <s:submit name="add" class="btn btn-primary">
                            <f:message key="buttons.create" />
                        </s:submit>
                    </c:otherwise>

                </c:choose>

                <s:submit name="cancel"><f:message key="buttons.cancel"/></s:submit>
                </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>