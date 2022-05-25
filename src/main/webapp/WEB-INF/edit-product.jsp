<%--
  Created by IntelliJ IDEA.
  User: tomek
  Date: 30.04.2022
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Edit Product</title>
</head>
<body>
<a>
    <%@include file="/WEB-INF/header.jsp" %></a><br>
<%--@elvariable id="product" type="pl.coderslab.entity.Product"--%>
<form:form modelAttribute="product" method="post">
    <form:hidden path="id"/>
    Nazwa Produktu: <form:input path="name"/>
    Opis Produktu: <form:input path="description"/>
    Adres www: <form:input path="image_url"/>
    Cena: <form:input path="price"/>
    Dostępna Ilość: <form:input path="available_quantity"/>
    Kategoria: <form:select path="category.id" items="${categories}" itemLabel="fullNameCategory" itemValue="id"/>
    <input type="submit">
</form:form>
</body>
</html>
