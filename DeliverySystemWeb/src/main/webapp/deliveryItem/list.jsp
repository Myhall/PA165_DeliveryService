<%-- 
    Document   : list
    Created on : 21-Nov-2013, 19:30:03
    Author     : Filip Volner <volner@mail.muni.cz>
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="deliveryItem.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryItemActionBean" var="actionBean"/>

        <p><f:message key ="deliveryItem.list.allitems"/></p>

        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th><f:message key="deliveryItem.name"/></th>
                    <th><f:message key="deliveryItem.description"/></th>
                    <th><f:message key="deliveryItem.weight"/></th>
                </tr>
            </thead>
            <c:forEach items="${actionBean.deliveryItems}" var="deliveryItem">
                <tr>
                    <td><c:out value="${deliveryItem.name}"/></td>
                    <td><c:out value="${deliveryItem.description}"/></td>
                    <td><c:out value="${deliveryItem.weight}"/></td>
                    <td>
                        <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryItemActionBean" event="edit">
                            <s:param name="deliveryItem.id" value="${deliveryItem.id}"/><f:message key="edit.edit"/>
                        </s:link>
                    </td>
                    <td>
                        <s:form beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryItemActionBean">
                            <s:hidden name="deliveryItem.id" value="${deliveryItem.id}"/>
                            <s:submit name="delete"><f:message key="list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:form beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryItemActionBean">
            <fieldset>
                <legend>
                    <f:message key="deliveryItem.new"/>
                </legend>
                <%@include file="form.jsp"%>
                <s:submit name="add">
                    <f:message key="list.create"/>
                </s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>
