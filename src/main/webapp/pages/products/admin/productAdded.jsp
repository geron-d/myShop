<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 18.04.2022
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProductAdded</title>
</head>
<body>
<nav class="top-menu">
    <p><a href="${pageContext.request.contextPath}/start"><img src="${pageContext.request.contextPath}/logo.jpg" alt="Logo"></a></p>
    <ul class="menu-main">
        <li><a href="${pageContext.request.contextPath}/start">Start Page</a></li>
        <li>
            <a href="${pageContext.request.contextPath}/products/admin">Products</a>
            <ul>
                <li><a href="${pageContext.request.contextPath}/products/admin">All products</a></li>
                <li><a href="${pageContext.request.contextPath}/products/headphones/admin">Headphones</a></li>
            </ul>
        </li>
        <li><a href="${pageContext.request.contextPath}/products/add">Add product</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/adminPrivateRoom">Private room</a></li>
    </ul>
</nav>

<h1>Product has been added</h1>

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
