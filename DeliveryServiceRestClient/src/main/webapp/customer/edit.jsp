<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="customer.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliveryservice.rest.client.CustomerRestClientBean" var="actionBean"/>

        <s:form beanclass="cz.muni.fi.pa165.deliveryservice.rest.client.CustomerRestClientBean">
            <s:hidden name="customerDto.id"/>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><f:message key="customer.create" /></h3>
                </div>
                <div class="panel-body" style="width:60%">
                    <s:errors/>
                    <div class="form-group">
                        <label class="control-label" for="inputSuccess"><s:label name="customer.firstName"/></label>
                        <s:text name="customerDto.firstName" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="inputWarning"><s:label name="customer.lastName"/></label>
                        <s:text name="customerDto.lastName" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="inputError"><s:label name="customer.email"/></label>
                        <div class="input-group">
                            <span class="input-group-addon">@</span>
                            <s:text name="customerDto.email" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="inputWarning"><s:label name="customer.street"/></label>
                        <s:text name="customerDto.street" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="inputWarning"><s:label name="customer.city"/></label>
                        <s:text name="customerDto.city" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="inputWarning"><s:label name="customer.zipCode"/></label>
                        <s:text name="customerDto.zipCode" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="inputWarning"><s:label name="customer.country"/></label>
                        <s:text name="customerDto.country" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="inputWarning"><s:label name="customer.telephoneNumber"/></label>
                        <s:text name="customerDto.telephoneNumber" class="form-control"/>
                    </div>
                    <c:choose>
                        <c:when test="${not empty actionBean.customerDto.id}">
                            <s:submit name="update" class="btn btn-primary">
                                <f:message key="buttons.update" />
                            </s:submit>
                        </c:when>

                        <c:otherwise>
                            <s:submit name="create" class="btn btn-primary">
                                <f:message key="buttons.save" />
                            </s:submit>
                        </c:otherwise>

                    </c:choose>

                    <s:link beanclass="cz.muni.fi.pa165.deliveryservice.rest.client.CustomerRestClientBean" class="btn btn-default">
                        <f:message key="buttons.cancel" />
                    </s:link>
                </div>
            </div>

        </s:form>     
    </s:layout-component>
</s:layout-render>
