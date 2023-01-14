<%--
  Created by IntelliJ IDEA.
  User: tomek
  Date: 30.03.2022
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=2">
    <title>Cart</title>
</head>
<body>
<%@include file="/WEB-INF/header.jsp" %><br>
<h3>Dodano do koszyka</h3>
<title>Multiply</title>
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
<c:set var="total" value="0"></c:set>
<table>
    <tr>
        <th>Id</th>
        <th>Nazwa</th>
        <th>Cena</th>
        <th>Opis</th>
        <th>Ilość</th>
    </tr><br>
<%--    <c:forEach var="cart" items="${addedItems}">--%>
        <tr>
                <%--            <td>${calaLista.quantity}</td>--%>
            <td>${addedItems.product.id}</td>
            <td>${addedItems.product.name}</td>
            <td>${addedItems.product.price}</td>
            <td>${addedItems.product.description}</td>
            <td>${addedItems.quantity}</td>

        </tr>
</table>
<br>
<br>
<br>
    <a href="/rest/cart/products_to_buy"> powrót do zakupów</a><br><br><br>
    <a href="/buyer/customer/cart/${cart.id}"> koszyk</a>




</body>
</html>
