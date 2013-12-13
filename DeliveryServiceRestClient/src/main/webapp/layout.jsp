<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title>Delivery service rest client<f:message key="${titlekey}"/></title>
            <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed' rel='stylesheet' type='text/css'>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.css" />
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css" />
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta name="description" content="">
            <meta name="author" content="">

            <s:layout-component name="header"/>
        </head>
        <body>
            <div class="container">
                <div class="navbar navbar-default" role="navigation">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="${pageContext.request.contextPath}">Delivery service rest client</a>
                    </div>
                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li><s:link beanclass="cz.muni.fi.pa165.deliveryservice.rest.client.CourierRestClientBean" class="glyphicon glyphicon-globe">&nbsp;<f:message key="courier.title"/></s:link></li>
                            </ul>
                            <ul class="nav navbar-nav navbar-right">
                                <li><s:link beanclass="cz.muni.fi.pa165.deliveryservice.rest.client.CustomerRestClientBean" class="glyphicon glyphicon-globe">&nbsp;<f:message key="customer.list.title"/></s:link></li>
                            </ul>
                        </div>
                    </div>
                <s:layout-component name="body"/>
            </div> 
            <!-- include jQuery -->
            <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
            <!-- include Bootstrap.js -->
            <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
        </body>
    </html>
</s:layout-definition>
