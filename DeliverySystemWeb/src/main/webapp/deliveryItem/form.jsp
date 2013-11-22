<%-- 
    Document   : form
    Created on : 21-Nov-2013, 20:12:06
    Author     : Filip Volner <volner@mail.muni.cz>
--%>

<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="e1" name="deliveryItem.name"/></th>
        <td><s:text id="e1" name="deliveryItem.name"/></td>
    </tr>
    <tr>
        <th><s:label for="e2" name="deliveryItem.description"/></th>
        <td><s:text id="e2" name="deliveryItem.description"/></td>
    </tr>
    <tr>
        <th><s:label for="e5" name="deliveryItem.weight"/></th>
        <td><s:text id="e5" name="deliveryItem.weight" size="5"/></td>
    </tr>
</table>
