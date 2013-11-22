<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title>Example</title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.css" />
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
                        <ul class="nav navbar-nav">
                            <!--<li class="active"><a href="#">Link</a></li>-->
                            <li><s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.CourierActionBean">Couriers</s:link></li>
                            <li><s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.DeliveryItemActionBean">Delivery Items</s:link></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="active"><a href="./">Default</a></li>
                            <li><a href="#">Static top</a></li>
                            <li><a href="#">Fixed top</a></li>
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
