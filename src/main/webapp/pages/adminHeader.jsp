<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 02.05.2022
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>adminHeader</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>

<body>
<nav class="top-menu">
    <p><a href="${pageContext.request.contextPath}/start"><img src="${pageContext.request.contextPath}/logo.jpg"
                                                               alt="Logo"></a></p>
    <ul class="menu-main">
        <li>
            <form action="${pageContext.request.contextPath}/products/search/admin" method="get">
                <label>
                    <input type="text" name="search" placeholder="search">
                </label>
                <input type="submit" value="Search">
            </form>
        </li>
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
</body>
</html>
