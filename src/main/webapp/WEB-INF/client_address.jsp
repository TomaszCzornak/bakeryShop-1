<%--
  Created by IntelliJ IDEA.
  User: tomek
  Date: 27.05.2022
  Time: 09:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adres do Wysyłki</title>
</head>
<body>
<tr>
    <%--            <td>${item.id}</td>--%>
    Imię i Nazwisko:
    <td>${clientDetails.first_name} ${clientDetails.last_name}</td>
    <br>
    Kod Pocztowy:
    <td>${clientDetails.post_code}</td>
        <br>
        Ulica:
    <td>${clientDetails.street}</td>
        <br>
        Telefon:
    <td>${clientDetails.phone}</td>
    <br>
    <br>
</tr>
</body>

powrót do listy zamówień: <a href="/admin/purchases">przejdź</a>
</html>
