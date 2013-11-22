<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp">
    <s:layout-component name="body">
        <div class="jumbotron">
            <h1 class="navbar-brand">Delivery service</h1>
            <div style="clear:both; padding-top: 50px"></div>


            <!-- Three columns of text below the carousel -->
            <div class="row">
                <div class="col-lg-4 text-center">

                    <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.CourierActionBean" class="btn btn-success btn-lg">
                        <span class="glyphicon glyphicon-globe"></span> Couriers
                    </s:link>

                </div><!-- /.col-lg-4 -->
                <div class="col-lg-4 text-center">


                    <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.CourierActionBean" class="btn btn-success btn-lg">
                        <span class="glyphicon glyphicon-briefcase"></span> Deliveries
                    </s:link>

                </div><!-- /.col-lg-4 -->
                <div class="col-lg-4 text-center">
                    <s:link beanclass="cz.muni.fi.pa165.deliverysystemweb.CustomerActionBean" class="btn btn-success btn-lg">
                        <span class="glyphicon glyphicon-user"></span> Customers
                    </s:link>
                </div><!-- /.col-lg-4 -->
            </div><!-- /.row -->

        </div><!-- /.container -->

    </s:layout-component>
</s:layout-render>

