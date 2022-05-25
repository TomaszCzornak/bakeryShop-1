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
<%--@elvariable id="product" type="java.util.List<pl.coderslab.entity.Product>"--%>
<table>
    <c:forEach var="item" items="${purchases}">
        <tr>
            <td>${item.id}</td>
            <td>${item.status}</td>
            <td>${item.paymentMethod}</td>
            <td>${item.created}</td>
            <td>${item.cart.total_amount}</td>
            <td><a href="/admin/remove_product/${item.id}">usuń</a></td>
            <td><a href="/admin/edit_product/${item.id}">edytuj</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
