<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="customer.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliverysystemweb.CustomerActionBean" var="actionBean"/>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><f:message key="customer.list.allitems" /></h3>
            </div>
            <div class="panel-body">
                <table class="table table-striped table-bordered">
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
                    <c:forEach items="${actionBean.customerList}" var="customer">
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
                                <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.CustomerActionBean" 
                                        event="edit" class="btn btn-xs btn-primary" >
                                    <s:param name="customerDTO.id" value="${customer.id}" />
                                    <f:message key="buttons.update" />
                                </s:link>
                            </td>
                            <td>
                                <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.CustomerActionBean" 
                                        event="delete" class="btn btn-xs btn-danger" >
                                    <s:param name="customerDTO.id" value="${customer.id}" />
                                    <f:message key="buttons.delete" />
                                </s:link>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.CustomerActionBean" event="edit" class="btn btn-primary">
                    <f:message key="customer.create" />
                </s:link>
            </div>
        </div> 
    </s:layout-component>
</s:layout-render>