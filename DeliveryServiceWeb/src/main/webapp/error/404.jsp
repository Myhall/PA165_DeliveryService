<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="deliveryservice.main">
    <s:layout-component name="body">
        <div class="jumbotron">
            <div class="text-center">
                <h1 class="navbar-brand">404</h1>

                <div style="clear:both; padding-top: 5px">
                    <img src="${pageContext.request.contextPath}/static/images/404.jpg" class="img-thumbnail"/>
                </div>

            </div>
        </div>
    </s:layout-component>
</s:layout-render>
