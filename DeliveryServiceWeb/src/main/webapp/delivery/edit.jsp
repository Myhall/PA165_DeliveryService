<%-- 
    Document   : edit
    Created on : Nov 22, 2013, 8:35:31 PM
    Author     : Michal Sorentiny
--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="delivery.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryActionBean" var="actionBean"/>

        <s:form beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryActionBean">
            <s:hidden name="delivery.id"/>
            <s:hidden name="deliveryItems" />

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><f:message key="delivery.createMessage" /></h3>
                </div>
                <div class="panel-body" style="width:60%">
                    <s:errors/>
                    <div class="form-group">
                        <label class="control-label" for="inputSuccess"><s:label name="delivery.customer"/></label>
                        <s:select name="delivery.customer.id" class="control-select" >

                            <s:options-collection collection="${actionBean.allCustomers}" value="id" label="email" />
                        </s:select>
                    </div>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <div class="form-group">
                            <label class="control-label" for="inputSuccess"><s:label name="delivery.courier"/></label>
                            <s:select name="delivery.courier.id" class="control-select" >
                                <s:option value="" />
                                <s:options-collection collection="${actionBean.allCouriers}" value="id" label="email" />
                            </s:select>
                        </div>
                    </sec:authorize>
                    <div class="form-group">
                        <label class="control-label" for="inputWarning"><s:label name="delivery.placeFrom"/></label>
                        <s:text name="delivery.placeFrom" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="inputWarning"><s:label name="delivery.placeTo"/></label>
                        <s:text name="delivery.placeTo" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="inputWarning"><s:label name="delivery.status"/></label>
                        <s:select name="delivery.status" class="control-select" >
                            <s:options-collection collection="${actionBean.allStatuses}" />
                        </s:select>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="inputWarning"><s:label name="delivery.additionalInformation"/></label>
                        <s:text name="delivery.additionalInformation" class="form-control"/>
                    </div>

                    <label class="cform-group" for="inputWarning" name="delivery.deliveryItemIds"><f:message key="deliveryItem.list.title"/></label>
                    <div class="controls">
                        <s:select id="inputWarning" multiple="multiple" name="delivery.deliveryItemIds"> 
                            <s:options-collection collection="${actionBean.allDeliveryItems}" value="id" label="name"/>
                        </s:select>
                    </div>

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
