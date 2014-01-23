<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:errors/>                    
<div class="control-group">
    <s:label class="control-label" for="inputFirstName" name="customer.form.firstName"/>
    <div class="controls">        
        <s:text id="inputFirstName" name="customerDTO.firstName"/>
    </div>               
</div>

<div class="control-group">        
    <s:label class="control-label" for="inputLastName" name="customer.form.lastName"/>
    <div class="controls">     
        <s:text id="inputLastName" name="customerDTO.lastName"/>             
    </div>
</div>

<div class="control-group">
    <s:label class="control-label" for="inputAddress" name="customer.form.address"/>
    <div class="controls">               
        <s:text id="inputAddress" name="customerDTO.address"/>             
    </div>
</div>

<div class="control-group">
    <s:label class="control-label" for="inputPhone" name="customer.form.phone"/>                               
    <div class="controls">             
        <s:text id="inputPhone" name="customerDTO.phone"/>             
    </div>
</div>
    
<div class="control-group">
    <s:label class="control-label" for="username" name="user.form.name"/>                               
    <div class="controls">             
        <s:text id="username" name="userDTO.username"/>             
    </div>
</div>
    
<div class="control-group">
    <s:label class="control-label" for="password" name="user.form.password"/>                               
    <div class="controls">             
        <s:password id="password" name="userDTO.password"/>             
    </div>
</div>
    
<div class="control-group">
    <s:label class="control-label" for="password2" name="user.form.password2"/>                               
    <div class="controls">             
        <s:password id="password2" name="password2"/>             
    </div>
</div>