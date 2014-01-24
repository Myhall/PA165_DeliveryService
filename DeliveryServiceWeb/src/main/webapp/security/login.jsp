<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<f:message key="login.title" var="msg"/>

<s:layout-render name="/layout.jsp" title="${msg}" titlekey="login.title">

    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliverysystemweb.SecurityActionBean" var="actionBean"/>
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliverysystemweb.RegistrationActionBean" var="registrationActionBean"/>
        <c:if test="${not empty actionBean.error}">
            <div class="alert alert-danger">
                <f:message key="login.error" />
            </div>
        </c:if>
        <c:url value="/j_spring_security_check" var="checkUrl"/>

        <form class="form-signin" role="form" action="${checkUrl}" method="POST">
            <h2 class="form-signin-heading"><f:message key="login.title" /></h2>
            <f:message key="login.label.username" var="lblUsername"/>
            <input id="inputUsername" name="j_username" type="text" class="form-control" placeholder="${lblUsername}" required autofocus>
            <f:message key="login.label.password" var="lblPassword"/>
            <input id="inputPassword" name="j_password" type="password" class="form-control" placeholder="${lblPassword}" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><f:message key="login.button.login"/></button>
        </form>

      
    </s:layout-component>
</s:layout-render>
