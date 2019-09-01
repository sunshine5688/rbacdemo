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
    <title>添加用户</title>
</head>
<body>
    <form action="/view/adduser" method="post">
        <label for="username">用户名 : </label>
        <input id="username" type="text" name="username"/><br/>
        <label for="password">密码 : </label>
        <input id="password" type="password" name="password"/><br/>
        <label for="username">用户组 : </label><br/>
        <c:forEach items="${groupList }" var="item">
            <input type="checkbox" name="groups" value="${item.id}"> ${item.name} &nbsp;
        </c:forEach>
        <br/>
        <label for="username">用户角色 : </label><br/>
        <c:forEach items="${roleList }" var="item">
            <input type="checkbox" name="roles" value="${item.id}"> ${item.name} &nbsp;
        </c:forEach>
        <br/>
        <input type="submit" value="添加用户">
    </form>

</body>
</html>
