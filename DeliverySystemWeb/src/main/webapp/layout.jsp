<%-- 
    Document   : layout.jsp
    Created on : Nov 15, 2013, 10:07:14 PM
    Author     : janvorcak
--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-definition>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
  <title>Example</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
  <s:layout-component name="header"/>
</head>
<body>
   Content
   <div id="content">
       <s:layout-component name="body"/>
    </div>
</body>
</html>
</s:layout-definition>
