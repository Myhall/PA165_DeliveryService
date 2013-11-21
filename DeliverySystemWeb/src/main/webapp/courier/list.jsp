<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp">
    <s:layout-component name="body">
       <s:useActionBean beanclass="cz.muni.fi.pa165.deliverysystemweb.CourierActionBean" var="actionBean"/>

       <c:forEach items="${actionBean.courierDTOs}" var="courier">
           ${courier.firstName}<br />
       </c:forEach>
       
       <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.CourierActionBean" event="edit">Create courier</s:link>
    </s:layout-component>
</s:layout-render>
