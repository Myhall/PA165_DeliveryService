<%-- 
    Document   : form
    Created on : 21-Nov-2013, 20:12:06
    Author     : Filip Volner <volner@mail.muni.cz>
--%>

<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<div class="row">
    <div class="form-group col-lg-4">
        <th><s:label for="e1" name="deliveryItem.name"/></th>
        <td><s:text id="e1" name="deliveryItem.name" class="form-control" /></td>
    </div>
</div>
<div class="row">
    <div class="form-group col-lg-10">
        <th><s:label for="e2" name="deliveryItem.description"/></th>
        <td><s:textarea id="e2" name="deliveryItem.description" class="form-control" rows="3"/></td>
    </div>
</div>
<div class="row">
    <div class="form-group col-lg-2">
        <th><s:label for="e5" name="deliveryItem.weight"/></th>
        <td><s:text id="e5" name="deliveryItem.weight" size="5" class="form-control"/></td>
    </div>
</div>
