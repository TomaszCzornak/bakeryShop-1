<%--
  Created by IntelliJ IDEA.
  User: tomek
  Date: 08.05.2022
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Twoja lista produktów</title>
</head>
<body>
    <%@include file="/WEB-INF/header.jsp" %>
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
        <th>Nr Produktu</th>
        <th>Nazwa Produktu</th>
        <th>Opis Produktu</th>
        <th>Cena Produktu</th>
        <th>Akcja</th>

    </tr>
    <br>

<%--@elvariable id="product" type="java.util.List<pl.coderslab.entity.Product>"--%>
    <c:forEach var="item" items="${offer}">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.description}</td>
            <td>${item.price}</td>
            <td><a href="/rest/cart/add/${item.id}">    dodaj do koszyka</a></td>
        </tr>
    </c:forEach>

</table>
<br>
<br>
<br>
<a href="/buyer/customer/cart/${cart.id}"> koszyk</a>
</body>
</html>