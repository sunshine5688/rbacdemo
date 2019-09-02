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
    <title>添加角色</title>
</head>
<body>
    <form action="/view/addrole" method="post">
        <%--@declare id="menus"--%><label for="rolename">角色名</label>
        <input id="rolename" type="text" name="rolename"/><br/>
        <label for="menus">菜单 : </label><br/>
        <c:forEach items="${menuList }" var="item">
            <input type="checkbox" name="menus" value="${item.id}"> ${item.name} &nbsp;
        </c:forEach>
        <br/>
        <label for="roles">互斥角色 : </label><br/>
        <c:forEach items="${roleList }" var="item">
            <input type="checkbox" name="roles" value="${item.id}"> ${item.name} &nbsp;
        </c:forEach>
        <br/>
        <input type="submit" value="添加角色">
    </form>

</body>
</html>
