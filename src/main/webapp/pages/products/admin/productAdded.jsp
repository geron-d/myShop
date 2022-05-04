<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 18.04.2022
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>productAdded</title>
</head>
<body>
<c:import url="/pages/adminHeader.jsp"/>

<h1>Product has been added</h1>

<form action="${pageContext.request.contextPath}/products/add" method="post">
    <label>
        <input type="text" name="category" placeholder="category">
    </label>
    <label>
        <input type="text" name="type" placeholder="type">
    </label>
    <label>
        <input type="text" name="name" placeholder="name">
    </label>
    <label>
        <input type="text" name="image" placeholder="image">
    </label>
    <label>
        <input type="text" name="producer" placeholder="producer">
    </label>
    <label>
        <input type="text" name="amount" placeholder="amount">
    </label>
    <label>
        <input type="text" name="price" placeholder="price">
    </label>
    <input type="submit" value="Create">
</form>


</body>
</html>
