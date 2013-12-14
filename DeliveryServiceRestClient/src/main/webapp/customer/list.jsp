<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="customer.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliveryservice.rest.client.CustomerRestClientBean" var="actionBean"/>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><f:message key="customer.list.title" /></h3>
            </div>
            <div class="panel-body">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th><f:message key="customer.firstName"/></th>
                            <th><f:message key="customer.lastName"/></th>
                            <th><f:message key="customer.email"/></th>
                            <th><f:message key="customer.street"/></th>
                            <th><f:message key="customer.city"/></th>
                            <th><f:message key="customer.zipCode"/></th>
                            <th><f:message key="customer.country"/></th>
                            <th><f:message key="customer.telephoneNumber"/></th>
                            <th><f:message key="buttons.update"/></th>
                            <th><f:message key="buttons.delete"/></th>
                        </tr>
                    </thead>
                      <c:forEach items="${actionBean.allCustomers}" var="customer">
                         <tr>                             
                            <td><c:out value="${customer.firstName}"/></td>
                            <td><c:out value="${customer.lastName}"/></td>
                            <td><c:out value="${customer.email}"/></td>
                            <td><c:out value="${customer.street}"/></td>
                            <td><c:out value="${customer.city}"/></td>
                            <td><c:out value="${customer.zipCode}"/></td>
                            <td><c:out value="${customer.country}"/></td>
                            <td><c:out value="${customer.telephoneNumber}"/></td>
                            <td>
                                <s:link beanclass="cz.muni.fi.pa165.deliveryservice.rest.client.CustomerRestClientBean" 
                                        event="edit" class="btn btn-xs btn-primary" >
                                    <s:param name="customerDto.id" value="${customer.id}" />
                                    <f:message key="buttons.update" />
                                </s:link>
                            </td>
                            <td>
                                <s:link beanclass="cz.muni.fi.pa165.deliveryservice.rest.client.CustomerRestClientBean" 
                                        event="delete" class="btn btn-xs btn-danger" >
                                    <s:param name="customerDto.id" value="${courier.id}" />
                                    <f:message key="buttons.delete" />
                                </s:link>
                            </td>
                        </tr>
                     </c:forEach>
                </table>
                <s:link beanclass="cz.muni.fi.pa165.deliveryservice.rest.client.CustomerRestClientBean" event="edit" class="btn btn-primary">
                    <f:message key="customer.create" />
                </s:link>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>
