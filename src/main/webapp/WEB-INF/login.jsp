<%--
  Created by IntelliJ IDEA.
  User: mariu
  Date: 22/10/2024
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
  <p><input type="text" name="username"></p>
  <p><input type="password" name="password"></p>
  <p><input type="submit" value="Login">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></p>
</form>
</body>
</html>
