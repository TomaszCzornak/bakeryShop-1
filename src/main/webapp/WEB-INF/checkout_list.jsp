<%--
  Created by IntelliJ IDEA.
  User: tomek
  Date: 19.05.2022
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
        <style>
            .error {
                color: red;
            }
        </style>
</head>
<body>
<%@include file="/WEB-INF/header.jsp" %><br>

Kasa: wybierz metodę płatności<br><br>

<%--@elvariable id="purchase" type="com.slodkacysia.bakeryshop.entity.Purchase"--%>
<form:form modelAttribute="purchase">
<br>
<form:select path="paymentMethod" items="${payment}" itemLabel="name" itemValue="id"/>
<form:errors path="paymentMethod" cssClass="error"/>
<br>
</form:form>




<html>

<head>
    <title>Title</title>
    <style>
        table {
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid grey;
        }

        th, td {
            text-align: center;
            padding: 6px;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h1>
    Oto twój koszyk
</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Nazwa</th>
        <th>Cena</th>
        <th>Opis</th>
        <th>Ilość</th>
        <th>Akcja</th>
        <th>Akcja</th>
    </tr>
    <br>
    <c:forEach var="cartItems" items="${checkout}">
        <tr>
<%--            <td>${cart.id}</td>--%>
            <td>${cartItems.product.id}</td>
            <td>${cartItems.product.name}</td>
            <td>${cartItems.product.price}</td>
            <td>${cartItems.quantity}</td>
            <td><a href="/checkout/${cart.product.id}">usuń</a></td>
            <br>

        </tr>
    </c:forEach>

    <c:forEach var="cartItems" items="${checkout}">
        <tr>
            <c:set var="cartTotal" value="${0}" />
            <c:forEach var="cartItems" items="${checkout}">
                <c:set var="cartTotal" value="${cartTotal + (cartItems.product.price* cart.quantity)}" />
            </c:forEach>
        </tr>
    </c:forEach>
    <td></td>
    <td>Suma do zapłaty:</td>
    <td>${cartTotal}</td>
    <br>
    <br>
    <br>
</table><br>
<a href="/rest/cart/products_to_buy"> powrót do zakupów</a>

<h1>
    <a href="/finalization/${cartId}"> ZAMÓW</a>
</h1>
</body>
</html>
</body>
</html>
