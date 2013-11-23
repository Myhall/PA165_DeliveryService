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
                        <s:select name="delivery.customer.id" class="control-select" >

                            <s:options-collection collection="${actionBean.allCustomers}" value="id" label="email" />
                        </s:select>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="inputSuccess"><s:label name="delivery.courier"/></label>
                        <s:select name="delivery.courier.id" class="control-select" >
                            <s:option value="" />
                            <s:options-collection collection="${actionBean.allCouriers}" value="id" label="email" />
                        </s:select>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="inputWarning"><s:label name="delivery.from"/></label>
                        <s:text name="delivery.placeFrom" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="inputWarning"><s:label name="delivery.to"/></label>
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

                    <div class="form-group">
                        <label class="control-label" for="inputWarning"><s:label name="deliveryItems"/></label>
                        
                        <s:select name="delivery.deliveryItems" class="control-select" id="itemadd">
                            <s:options-collection collection="${actionBean.allDeliveryItems}" value="id" label="name" />
                        </s:select>
                        
                        <s:button class="btn btn-xs btn-primary" name="additem" onclick="" >
                            <f:message key="buttons.add" />
                        </s:button>
                        
                        
                    
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th><f:message key="deliveryItem.name"/></th>
                                    <th><f:message key="deliveryItem.weight"/></th>
                                    <th><f:message key="deliveryItem.description"/></th>
                                </tr>
                            </thead>
                            <c:forEach items="${actionBean.delivery.items}" var="it" varStatus="loop">
                                <tr>
                                    <td>${it.id}
                                        <s:hidden name="it[${loop.index}].id" value="${it.id}" />
                                    </td>
                                    <td>${it.name}</td>
                                    <td>${it.weight}</td>
                                    <td>${it.description}</td>              
                                    <td>
                                        <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryItemActionBean" 
                                                event="edit" class="btn btn-xs btn-primary" >
                                            <s:param name="id" value="${it.id}" />
                                            <f:message key="buttons.update" />
                                        </s:link>
                                    </td>
                                    <td>
                                        <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryItemActionBean" 
                                                event="delete" class="btn btn-xs btn-danger" >
                                            <s:param name="id" value="${it.id}" />
                                            <f:message key="buttons.delete" />
                                        </s:link>
                                    </td>
                                </tr>
                            </c:forEach>

                        </table>
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
