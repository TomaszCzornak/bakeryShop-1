<%--
  Created by IntelliJ IDEA.
  User: tomek
  Date: 25.05.2022
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/WEB-INF/header.jsp" %>
<br>
SZCZEGÓŁY ZAMÓWIENIA ${cartId}
<br>
<br>
<br>
<br>
<br>
<br>
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
<table>
    <tr>
        <th>Numer zamówienia</th>
        <th>Cena</th>
        <th>Ilość</th>
        <th>Suma</th>
        <th>Produkt</th>

    </tr><br>
    <%--@elvariable id="cartItems" type="java.util.List<pl.coderslab.entity.CartItems>"--%>
    <c:forEach var="item" items="${details}">
        <tr>
<%--            <td>${item.id}</td>--%>
            <td>${item.id}</td>
            <td>${item.price}</td>
            <td>${item.total_price}</td>
            <td>${item.product.name}</td>
            <td>${item.quantity}</td>
            <td>${item.status}</td>
        </tr>
    </c:forEach>
    <c:forEach var="cart" items="${details}">
        <tr>
            <c:set var="cartTotal" value="${0}" />
            <c:forEach var="cart" items="${details}">
                <c:set var="cartTotal" value="${cartTotal + (cart.price* cart.quantity)}" />
            </c:forEach>
        </tr>
    </c:forEach>
    <td></td>
    <td>Suma Zakupów</td>
    <td>${cartTotal}</td>
    <br>
    <br>
    <br>
</table>
</body>
</html>
