<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/31
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>menu</title>
</head>
<body>
<c:if test="${adduser}">
    <a href="/view/init_adduser">添加用户</a><br/><br/>
</c:if>

<c:if test="${addrole}">
    <a href="/view/init_addrole">添加角色</a><br/><br/>
</c:if>

<c:if test="${menu1}">
    <a href="/view/menu1">菜单1</a><br/><br/>
</c:if>
<c:if test="${menu2}">
    <a href="/view/menu2">菜单2</a><br/><br/>
</c:if>
<c:if test="${menu3}">
    <a href="/view/menu3">菜单3</a><br/><br/>
</c:if>
<c:if test="${menu4}">
    <a href="/view/menu4">菜单4</a><br/><br/>
</c:if>
<c:if test="${menu5}">
    <a href="/view/menu5">菜单5</a><br/><br/>
</c:if>
<c:if test="${menu6}">
    <a href="/view/menu6">菜单6</a><br/><br/>
</c:if>
<c:if test="${menu7}">
    <a href="/view/menu7">菜单7</a><br/><br/>
</c:if>
<c:if test="${menu8}">
    <a href="/view/menu8">菜单8</a><br/><br/>
</c:if>


<br/><br/><br/><br/>
<a href="/index">注销</a><br/><br/>
</body>
</html>
