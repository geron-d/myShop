<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 22.04.2022
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StartPageUser</title>
    <link rel="stylesheet" href="/style.css">
</head>

<body>
<nav class="top-menu">
    <p><a href="${pageContext.request.contextPath}/pages/startPageUser.jsp"><img src="${pageContext.request.contextPath}/logo.jpg" alt="Logo"></a></p>
    <ul class="menu-main">
        <li><a href="${pageContext.request.contextPath}/pages/startPageUser.jsp">Start Page</a></li>
        <li><a href="${pageContext.request.contextPath}/products">Products</a></li>
        <li><a href="">Bucket</a></li>
        <li><a href="">Private room</a></li>
    </ul>
</nav>


</body>

</html>
