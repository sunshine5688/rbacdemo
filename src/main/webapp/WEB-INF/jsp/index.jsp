<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html>
<head>
    <title>index</title>
</head>
<body>
<form action="/login" method="post">
    <label for="username">用户名 : </label>
    <input id="username" type="text" name="username"/><br/>
    <label for="password">密码 : </label>
    <input id="password" type="password" name="password"/><br/>
    <input type="submit" value="登录">
</form>

<p style="color:red;">${errormsg}</p>
</body>
</html>
