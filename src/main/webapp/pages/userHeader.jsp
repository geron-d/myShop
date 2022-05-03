<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 02.05.2022
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StartPageUser</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>

<body>
<nav class="top-menu">
    <p><a href="${pageContext.request.contextPath}/start"><img src="${pageContext.request.contextPath}/logo.jpg"
                                                               alt="Logo"></a></p>
    <ul class="menu-main">
        <li><form action="${pageContext.request.contextPath}/search" method="post">
            <label>
                <input type="text" name="search" placeholder="search">
            </label>
            <input type="submit" value="Search">
        </form></li>

        <li><a href="${pageContext.request.contextPath}/start">Start Page</a></li>
        <li>
            <a href="${pageContext.request.contextPath}/products">Products</a>
            <ul>
                <li><a href="${pageContext.request.contextPath}/products">All products</a></li>
                <li><a href="${pageContext.request.contextPath}/headphones">Headphones</a></li>
            </ul>
        </li>
        <li><a href="${pageContext.request.contextPath}/bucket">Bucket</a></li>
        <%--@elvariable id="user" type="java"--%>
        <c:if test="${user == null}">
            <li><a href="${pageContext.request.contextPath}/pages/user/logIn.jsp">LogIn</a></li>
        </c:if>
        <c:if test="${user != null}">
            <li><a href="${pageContext.request.contextPath}/user/userPrivateRoom">Private room</a></li>
        </c:if>
    </ul>

</nav>

</body>
</html>
