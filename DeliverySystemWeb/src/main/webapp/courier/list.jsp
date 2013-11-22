<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliverysystemweb.CourierActionBean" var="actionBean"/>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th><f:message key="courierDTO.firstName"/></th>
                    <th><f:message key="courierDTO.lastName"/></th>
                    <th><f:message key="courierDTO.email"/></th>
                    <th><f:message key="buttons.delete"/></th>
                </tr>
            </thead>
            <c:forEach items="${actionBean.courierDTOs}" var="courier">
                <tr>
                    <td>${courier.firstName}</td>
                    <td>${courier.lastName}</td>
                    <td>${courier.email}</td>
                    <td>
                        <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.CourierActionBean" 
                                event="delete" class="btn btn-xs btn-danger" >
                            <s:param name="id" value="${courier.id}" />
                            <f:message key="buttons.delete" />
                        </s:link>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.CourierActionBean" event="edit" class="btn btn-primary">
            <f:message key="courier.createMessage" />
        </s:link>
    </s:layout-component>
</s:layout-render>
