<%-- 
    Document   : edit
    Created on : 21-Nov-2013, 20:17:28
    Author     : Filip Volner <volner@mail.muni.cz>
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="deliveryItem.form">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryItemActionBean" var="actionBean"/>

        <s:form beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryItemActionBean">
            <s:hidden name="deliveryItem.id"/>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <c:choose>
                            <c:when test="${not empty actionBean.deliveryItem.id}">
                                    <f:message key="deliveryItem.edit" />
                            </c:when>
                            <c:otherwise>
                                    <f:message key="deliveryItem.new" />
                            </c:otherwise>
                        </c:choose>
                    </h3>
                </div>
                <div class="panel-body" style="width:60%">
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

                    <s:submit name="cancel" class="btn btn-default">
                        <f:message key="buttons.cancel"/></s:submit>
                    </div>
                </div>
        </s:form>

    </s:layout-component>
</s:layout-render>