<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 02.05.2022
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>headphones</title>
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

<ul>
    <li><h1>Headphones</h1></li>
    <c:forEach var="headphone" items="${headphones}">
        <li><a href="${pageContext.request.contextPath}/products/product/admin?id=${headphone.id}">${headphone.name}</a></li>
    </c:forEach>
</ul>
</body>
</html>
