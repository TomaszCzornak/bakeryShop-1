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
SZCZEGÓŁY ZAMÓWIENIA nr ${cartId}
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
        <th>Nr</th>
        <th>Cena</th>
        <th>Ilość</th>
        <th>Cena</th>
        <th>Produkt</th>

    </tr>
    <br>
    <%--@elvariable id="cartItems" type="java.util.List<pl.coderslab.entity.CartItems>"--%>
    <c:forEach var="item" items="${details}" varStatus="count">
        <tr>
                <%--            <td>${item.id}</td>--%>
            <td>${count.count}</td>
            <td>${item.price}</td>
            <td>${item.quantity}</td>
            <td>${item.price}</td>
            <td>${item.product.name}</td>
        </tr>
    </c:forEach>
    <c:forEach var="cart" items="${details}">
        <tr>
            <c:set var="cartTotal" value="${0}"/>
            <c:forEach var="cart" items="${details}">
                <c:set var="cartTotal" value="${cartTotal + (cart.price* cart.quantity)}"/>
            </c:forEach>
        </tr>
    </c:forEach>
    <td></td>
    <td></td>
    <td>do zapłaty razem</td>
    <td>${cartTotal}</td>
    <br>
    <br>
    <br>
</table>
</body>
</html>
