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
SZCZEGÓŁY ZAMÓWIENIA ${details.cartItem.id}
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
    <%--@elvariable id="product" type="java.util.List<pl.coderslab.entity.Product>"--%>
    <c:forEach var="item" items="${details}">
        <tr>
<%--            <td>${item.id}</td>--%>
<%--            <td>${item.price}</td>--%>
<%--            <td>${item.quantity}</td>--%>
<%--            <td>${item.total_price}</td>--%>
<%--            <td>${item.cart.id}</td>--%>
<%--            <td>${item.product.name}</td>--%>
        </tr>
    </c:forEach>
</table>
</body>
</html>
