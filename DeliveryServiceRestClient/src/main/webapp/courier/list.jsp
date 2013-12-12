<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="courier.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliveryservice.rest.client.CourierRestClientBean" var="actionBean"/>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><f:message key="courier.list.title" /></h3>
            </div>
            <div class="panel-body">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th></th>
                            <th><f:message key="courierDTO.firstName"/></th>
                            <th><f:message key="courierDTO.lastName"/></th>
                            <th><f:message key="courierDTO.email"/></th>
                            <th><f:message key="buttons.update"/></th>
                            <th><f:message key="buttons.delete"/></th>
                        </tr>
                    </thead>
                  
                </table>

                <s:link beanclass="cz.muni.fi.pa165.deliveryservice.rest.client.CourierRestClientBean" event="edit" class="btn btn-primary">
                    <f:message key="courier.createMessage" />
                </s:link>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>
