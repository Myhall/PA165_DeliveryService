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

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><f:message key="deliveryItem.list.allitems" /></h3>
            </div>
            <div class="panel-body">

                <table class="table table-striped">
                    <thead>
                        <tr>
                           <th><f:message key="deliveryItem.name"/></th>
                            <th><f:message key="deliveryItem.description"/></th>
                            <th><f:message key="deliveryItem.weight"/></th>
                            <th><f:message key="label.edit"/></th>
                            <th><f:message key="label.delete"/></th>
                        </tr>
                    </thead>
                    <c:forEach items="${actionBean.deliveryItems}" var="deliveryItem">
                        <tr>
                            <td><c:out value="${deliveryItem.name}"/></td>
                            <td><c:out value="${deliveryItem.description}"/></td>
                            <td><c:out value="${deliveryItem.weight}"/></td>
                            <td>
                                <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryItemActionBean" event="edit" class="btn btn-xs btn-primary">
                                    <s:param name="deliveryItem.id" value="${deliveryItem.id}"/><f:message key="buttons.edit"/>
                                </s:link>
                            </td>
                            <td>
                                <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryItemActionBean" event="delete" class="btn btn-xs btn-danger">
                                    <s:param name="deliveryItem.id" value="${deliveryItem.id}"/><f:message key="buttons.delete"/>
                                </s:link>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryItemActionBean" event="edit" class="btn btn-primary">
                    <f:message key="deliveryItem.new" />
                </s:link>

            </div>
        </div>
    </s:layout-component>
</s:layout-render>
