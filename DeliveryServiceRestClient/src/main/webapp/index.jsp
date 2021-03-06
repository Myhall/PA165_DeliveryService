<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="deliveryservice.main">
    <s:layout-component name="body">
        <div class="jumbotron">
            <h1 class="navbar-brand">Delivery service</h1>
            <div style="clear:both; padding-top: 50px"></div>
           
            <div class="row">
                <div class="col-lg-5 text-center">
                    <s:link beanclass="cz.muni.fi.pa165.deliveryservice.rest.client.CourierRestClientBean" class="btn btn-success btn-lg">
                        <span class="glyphicon glyphicon-globe"></span> <f:message key="courier.title"/>
                    </s:link>
                </div>
                <div class="col-lg-5 text-center">
                    <s:link beanclass="cz.muni.fi.pa165.deliveryservice.rest.client.CustomerRestClientBean" class="btn btn-success btn-lg">
                        <span class="glyphicon glyphicon-home"></span> <f:message key="customer.list.title"/>
                    </s:link>
                </div>
            </div>

        </div>

    </s:layout-component>
</s:layout-render>

