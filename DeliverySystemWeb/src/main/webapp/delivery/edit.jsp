<%-- 
    Document   : edit
    Created on : Nov 22, 2013, 8:35:31 PM
    Author     : Michal Sorentiny
--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryActionBean" var="actionBean"/>

        <s:form beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryActionBean">
            <s:hidden name="delivery.id"/>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><f:message key="delivery.createMessage" /></h3>
                </div>
                <div class="panel-body" style="width:60%">
                    <s:errors/>
                    <div class="form-group">
                        <label class="control-label" for="inputSuccess"><s:label name="delivery.customer"/></label>
                        <s:select name="customer" class="control-select" >
                            <s:options-collection collection="actionBean.loadCustomers" value="id" label="firstName" />
                        </s:select>
                        <s:text name="delivery.customer" class="form-control" />
                    </div>
<!--                    <div class="form-group">
                        <label class="control-label" for="inputWarning"><s:label name="courierDTO.lastName"/></label>
                        <s:text name="courierDTO.lastName" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="inputError"><s:label name="courierDTO.email"/></label>
                        <div class="input-group">
                            <span class="input-group-addon">@</span>
                            <s:text name="courierDTO.email" class="form-control" />
                        </div>
                    </div>-->

                    <c:choose>

                        <c:when test="${not empty actionBean.delivery.id}">
                            <s:submit name="update" class="btn btn-primary">
                                <f:message key="buttons.update" />
                            </s:submit>
                        </c:when>

                        <c:otherwise>
                            <s:submit name="save" class="btn btn-primary">
                                <f:message key="buttons.save" />
                            </s:submit>
                        </c:otherwise>

                    </c:choose>

                    <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryActionBean" class="btn btn-default">
                        <f:message key="buttons.cancel" />
                    </s:link>
                </div>
            </div>

        </s:form>     
    </s:layout-component>
</s:layout-render>
