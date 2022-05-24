<%--
  Created by IntelliJ IDEA.
  User: tomek
  Date: 22.05.2022
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %><html>
<head>
    <title>Metoda Płatności</title>
</head>
<a>
    <%@include file="/WEB-INF/header.jsp" %>
</a><br>
<body>
    WYBIERZ METODĘ PŁATNOŚCI
<%--@elvariable id="purchase" type="com.slodkacysia.bakeryshop.entity.Purchase"--%>
<form:form modelAttribute="purchase">
    <br>
    <form:select path="paymentMethod.id" items="${payment}" itemLabel="name" itemValue="id"/>
    <form:errors path="paymentMethod.id" cssClass="error"/>
    <br><br>
    <input type="submit">
</form:form>
</body>
</html>
