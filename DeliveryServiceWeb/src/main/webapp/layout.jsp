<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title>Delivery service - <f:message key="${titlekey}"/></title>
            <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed' rel='stylesheet' type='text/css'>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.css" />
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css" />
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/signin.css" />
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta name="description" content="">
            <meta name="author" content="">

            <s:layout-component name="header"/>
        </head>
        <body>

            <div class="container">

                <!-- Static navbar -->
                <div class="navbar navbar-default" role="navigation">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="${pageContext.request.contextPath}">Delivery service</a>
                    </div>
                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
                                <li><s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryActionBean" class="glyphicon glyphicon-globe">&nbsp;<f:message key="delivery.title"/></s:link></li>
                                </sec:authorize>

                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <li><s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.CourierActionBean" class="glyphicon glyphicon-globe">&nbsp;<f:message key="courier.title"/></s:link></li>
                                <li><s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryItemActionBean" class="glyphicon glyphicon-briefcase">&nbsp;<f:message key="deliveryItem.list.title"/></s:link></li>
                                <li><s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.CustomerActionBean" class="glyphicon glyphicon-user">&nbsp;<f:message key="customer.list.title"/></s:link></li>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
                                <li><s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.SecurityActionBean" event="logout" class="glyphicon glyphicon-log-out">&nbsp;<f:message key="login.logout"/>(${pageContext.request.userPrincipal.name})</s:link></li>
                                </sec:authorize>

                            <sec:authorize access="not isAuthenticated()">
                                <li><s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.SecurityActionBean" class="glyphicon glyphicon-log-in">&nbsp;<f:message key="login.title"/></s:link></li>
                                </sec:authorize>

                        </ul>

                    </div><!--/.nav-collapse -->
                </div>

                <s:layout-component name="body"/>

            </div> <!-- /container -->


            <!-- include jQuery -->
            <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
            <!-- include Bootstrap.js -->
            <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
        </body>
    </html>
</s:layout-definition>
