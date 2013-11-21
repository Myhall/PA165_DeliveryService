<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.deliverysystemweb.CourierActionBean" var="actionBean"/>

        <s:form beanclass="cz.muni.fi.pa165.deliverysystemweb.CourierActionBean">
            <s:hidden name="courierDTO.id"/>
            <fieldset><legend>Test message</legend>
                <s:errors/>
                <table>
                    <tr>
                        <th><s:label for="b1" name="courierDTO.firstName"/></th>
                        <td><s:text id="b1" name="courierDTO.firstName"/></td>
                    </tr>
                </table>
                <s:submit name="save">Save</s:submit>
                </fieldset>
        </s:form>     
    </s:layout-component>
</s:layout-render>
