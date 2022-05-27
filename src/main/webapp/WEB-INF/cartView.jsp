<%--
  Created by IntelliJ IDEA.
  User: tomek
  Date: 20.05.2022
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<%@include file="/WEB-INF/header.jsp" %><br>
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
    <c:forEach var="cartItem" items="${fullCart}">
        <tr>
                <%--            <td>${calaLista.quantity}</td>--%>
            <td>${cartItem.product.id}</td>
            <td>${cartItem.product.name}</td>
            <td>${cartItem.product.price}</td>
            <td>${cartItem.product.description}</td>
            <td>${cartItem.quantity}</td>
            <td><a href="/rest/cart/remove/${cartItem.id}">usuń</a></td>
            <td><a href="/rest/cart/add/quantity/${cartItem.product.id}">dodaj_sztukę</a></td>
            <br>

        </tr>
    </c:forEach>

    <c:forEach var="cartItem" items="${fullCart}">
    <tr>
        <c:set var="cartTotal" value="${0}" />
        <c:forEach var="cartItem" items="${fullCart}">
            <c:set var="cartTotal" value="${cartTotal + (cartItem.product.price* cartItem.quantity)}" />
        </c:forEach>
    </tr>
    </c:forEach>
<td></td>
<td>Suma Zakupów</td>
<td>${cartTotal}</td>
    <br>
    <br>
    <br>
</table><br>
    <a href="/rest/cart/products_to_buy"> powrót do zakupów</a>
<br>
<br>
<br>
<a href="/checkout/payment/${cartId}"> do płatności</a>

</body>
</html>