<%--
  Created by IntelliJ IDEA.
  User: tomek
  Date: 08.05.2022
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Product administration</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<a>
    <%@include file="/WEB-INF/header.jsp" %>
</a><br>

JESTEŚ ADMINISTATOREM<br><br><br>
<%--@elvariable id="product" type="pl.coderslab.entity.Product"--%>
<form:form modelAttribute="product">
    Nazwa <form:input path="name"/>
    <form:errors path="name" cssClass="error"/><br>

    Opis <form:input path="description"/>
    <form:errors path="description" cssClass="error"/><br>

    Image URL <form:input path="image_url"/>
    <form:errors path="image_url" cssClass="error"/><br>

    <br/>

    Price <form:input path="price"/>
    <form:errors path="price" cssClass="error"/><br>

    <br>
    Available Quantity <form:input path="available_quantity"/>
    <form:errors path="available_quantity" cssClass="error"/><br>

    <br>
    <form:select path="category.id" items="${categories}" itemLabel="fullNameCategory" itemValue="id"/>
    <form:errors path="category.id" cssClass="error"/><br>

    <br>
    <input type="submit">
</form:form>
</body>
</html>