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


<%--@elvariable id="purchase" type="com.slodkacysia.bakeryshop.entity.Purchase"--%>
<form:form modelAttribute="purchase">
    <form:select path="paymentMethod.id" items="${payment}" itemLabel="name" itemValue="id"/>
    <form:errors path="paymentMethod.id" cssClass="error"/>
    <br>
    <input type="submit">
</form:form>




<br>
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


<br>
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
    <c:forEach var="cart" items="${checkout}">
        <tr>
                <%--            <td>${calaLista.quantity}</td>--%>
            <td>${cart.product.id}</td>
            <td>${cart.product.name}</td>
            <td>${cart.product.price}</td>
            <td>${cart.product.description}</td>
            <td>${cart.quantity}</td>
            <td><a href="/remove/${cart.product.id}">usuń</a></td>
            <td><a href="/rest/cart/add/quantity/${cart.product.id}">dodaj_sztukę</a></td>
            <br>

        </tr>
    </c:forEach>

    <c:forEach var="cart" items="${checkout}">
        <tr>
            <c:set var="cartTotal" value="${0}" />
            <c:forEach var="cart" items="${checkout}">
                <c:set var="cartTotal" value="${cartTotal + (cart.product.price* cart.quantity)}" />
            </c:forEach>
        </tr>
    </c:forEach>
    <td></td>
    <td></td>
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
