<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 25.04.2022
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Headphones</title>
</head>
<body>

<nav class="top-menu">
    <p><a href="${pageContext.request.contextPath}/pages/startPageUser.jsp"><img src="${pageContext.request.contextPath}/logo.jpg" alt="Logo"></a></p>
    <ul class="menu-main">
        <li><a href="${pageContext.request.contextPath}/pages/startPageUser.jsp">Start Page</a></li>
        <li>
            <a href="${pageContext.request.contextPath}/products">Products</a>
            <ul>
                <li><a href="${pageContext.request.contextPath}/products">All products</a></li>
                <li><a href="${pageContext.request.contextPath}/headphones">Headphones</a></li>
            </ul>
        </li>
        <li><a href="${pageContext.request.contextPath}/bucket">Bucket</a></li>
        <li><a href="">Private room</a></li>
    </ul>
</nav>

<ul>
    <li><h1>Headphones</h1></li>
    <c:forEach var="headphone" items="${headphones}">
        <li><a href="${pageContext.request.contextPath}/product?id=${headphone.id}">${headphone.name}</a></li>
    </c:forEach>
</ul>
</body>
</html>
