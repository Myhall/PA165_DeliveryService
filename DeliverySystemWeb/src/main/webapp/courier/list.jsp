<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliverysystemweb.CourierActionBean" var="actionBean"/>

        <table class="table table-striped table-bordered">

            <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                </tr>
            </thead>
            <c:forEach items="${actionBean.courierDTOs}" var="courier">
                <tr>
                    <td>${courier.firstName}</td>
                    <td>${courier.lastName}</td>
                    <td>${courier.email}</td>
                </tr>
            </c:forEach>
        </table>

        <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.CourierActionBean" event="edit">Create courier</s:link>
    </s:layout-component>
</s:layout-render>
