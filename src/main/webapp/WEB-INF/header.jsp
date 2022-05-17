<%--
  Created by IntelliJ IDEA.
  User: tomek
  Date: 16.05.2022
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="navbar" ...>
  <ul class="nav navbar-nav pull-right">
    <li><a href="<c:url value="/logout" />">Logout</a>
      Logout</li>
  </ul>
    <security:authorize access="isAuthenticated()">
        authenticated as <security:authentication property="principal.email" />
    </security:authorize><br>
</div>
</body>
</html>
