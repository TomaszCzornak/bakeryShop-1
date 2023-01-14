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
LISTA AKTUALNYCH ZAMÓWIEŃ
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
        <th>User Id</th>
        <th>Suma Koszyka</th>
        <th>Rodzaj płatności</th>
        <th>Data Zamówienia</th>
        <th>Numer Klienta</th>
        <th>status</th>

    </tr><br>
<%--@elvariable id="purchase" type="java.util.List<pl.coderslab.entity.Purchase>"--%>
    <c:forEach var="item" items="${purchases}">
        <tr>
            <td>${item.buyer.id}</td>
            <td>${item.cart.total_amount}</td>
            <td>${item.paymentMethod.name}</td>
            <td>${item.created}</td>
            <td>${item.cart.buyer.first_name}</td>
            <td>${item.status}</td>
            <td><a href="/admin/purchases/details/${item.cart.id}">szczegóły</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
