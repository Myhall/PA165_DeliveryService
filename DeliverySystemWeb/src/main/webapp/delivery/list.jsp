<%-- 
    Document   : list
    Created on : Nov 22, 2013, 6:54:42 PM
    Author     : Michal Sorentiny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryActionBean" var="actionBean"/>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th><f:message key="delivery.customer"/></th>
                    <th><f:message key="delivery.placeFrom"/></th>
                    <th><f:message key="delivery.placeTo"/></th>
                    <th><f:message key="delivery.courier"/></th>
                    <th><f:message key="delivery.status"/></th>
                    <th><f:message key="buttons.update"/></th>
                    <th><f:message key="buttons.delete"/></th>
                </tr>
            </thead>
            <c:forEach items="${actionBean.deliveries}" var="del">
                <tr>
                    <td>${del.customer.email}</td>
                    <td>${del.placeFrom}</td>
                    <td>${del.placeTo}</td>
                    <td>${del.courier.email}</td>
                    <td>${del.status}</td>
                    
                    <td>
                        <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryActionBean" 
                                event="edit" class="btn btn-xs btn-primary" >
                            <s:param name="id" value="${del.id}" />
                            <f:message key="buttons.update" />
                        </s:link>
                    </td>
                    <td>
                        <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryActionBean" 
                                event="delete" class="btn btn-xs btn-danger" >
                            <s:param name="id" value="${del.id}" />
                            <f:message key="buttons.delete" />
                        </s:link>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryActionBean" event="edit" class="btn btn-primary">
            <f:message key="delivery.createMessage" />
        </s:link>
    </s:layout-component>
</s:layout-render>