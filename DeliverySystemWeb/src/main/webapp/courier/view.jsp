<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="courier">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliverysystemweb.CourierActionBean" var="actionBean"/>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><f:message key="courier" /> - ${actionBean.courierDTO.firstName} ${actionBean.courierDTO.lastName}</h3>
            </div>
            <div class="panel-body">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th><f:message key="courierDTO.firstName"/></th>
                            <th><f:message key="courierDTO.lastName"/></th>
                            <th><f:message key="courierDTO.email"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${actionBean.courierDTO.firstName}</td>                          </td>
                            <td>${actionBean.courierDTO.lastName}</td>
                            <td>${actionBean.courierDTO.email}</td>
                        </tr>
                    </tbody>
                </table><!--
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th><f:message key="courierDTO.firstName"/></th>
                            <th><f:message key="courierDTO.lastName"/></th>
                            <th><f:message key="courierDTO.email"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${actionBean.courierDTO.firstName}</td>                          </td>
                            <td>${actionBean.courierDTO.lastName}</td>
                            <td>${actionBean.courierDTO.email}</td>
                        </tr>
                    </tbody>
                </table>-->
            </div>
        </div>


    </s:layout-component>
</s:layout-render>

