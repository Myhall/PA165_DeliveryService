<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="deliveryservice.main">
    <s:layout-component name="body">
        <div class="jumbotron">
            <h1 class="navbar-brand">Delivery service</h1>
            <div style="clear:both; padding-top: 50px"></div>


            <!-- Three columns of text below the carousel -->
            <div class="row">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="col-lg-3 text-center">

                        <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.CourierActionBean" class="btn btn-success btn-lg">
                            <span class="glyphicon glyphicon-globe"></span> <f:message key="courier.title"/>
                        </s:link>

                    </div><!-- /.col-lg-4 -->
                    <div class="col-lg-3 text-center">


                        <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryItemActionBean" class="btn btn-success btn-lg">
                            <span class="glyphicon glyphicon-briefcase"></span> <f:message key="deliveryItem.list.title"/>
                        </s:link>

                    </div><!-- /.col-lg-4 -->
                    <div class="col-lg-3 text-center">


                        <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryActionBean" class="btn btn-success btn-lg">
                            <span class="glyphicon glyphicon-briefcase"></span> <f:message key="delivery.list.title"/>
                        </s:link>

                    </div><!-- /.col-lg-4 -->
                    <div class="col-lg-3 text-center">
                        <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.CustomerActionBean" class="btn btn-success btn-lg">
                            <span class="glyphicon glyphicon-user"></span> <f:message key="customer.list.title"/>
                        </s:link>
                    </div><!-- /.col-lg-4 -->
                </div><!-- /.row -->
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER')">
                <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryActionBean" class="btn btn-success btn-lg">
                    <span class="glyphicon glyphicon-briefcase"></span> <f:message key="delivery.list.title"/>
                </s:link>

            </sec:authorize>
            <sec:authorize access="not isAuthenticated()">

            </sec:authorize>
        </div><!-- /.container -->

    </s:layout-component>
</s:layout-render>

