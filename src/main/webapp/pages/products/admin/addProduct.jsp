<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 17.04.2022
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddProduct</title>
</head>
<body>
<c:import url="/pages/adminHeader.jsp"/>

<form action="${pageContext.request.contextPath}/products/add" method="post">
    <input type="text" name="category" placeholder="category">
    <input type="text" name="type" placeholder="type">
    <input type="text" name="name" placeholder="name">
    <input type="text" name="image" placeholder="image">
    <input type="text" name="producer" placeholder="producer">
    <input type="text" name="amount" placeholder="amount">
    <input type="text" name="price" placeholder="price">
    <input type="submit" value="Create">
</form>
</body>
</html>
