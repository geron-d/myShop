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
    <a href="${pageContext.request.contextPath}/index.jsp"><img src="img/my_logo.jpg"></a>
    <ul class="menu-main">
        <li><a href="${pageContext.request.contextPath}/index.jsp">Start Page</a></li>
        <li><a href="${pageContext.request.contextPath}/products">Products</a></li>
        <li><a href="${pageContext.request.contextPath}/addProduct">Add product</a></li>
        <li><a href="">Bucket</a></li>
        <li><a href="">Private room</a></li>
    </ul>
</nav>

<form action="/myShop/addProduct" method="post">
    <input type="text" name="category" placeholder="category">
    <input type="text" name="type" placeholder="type">
    <input type="text" name="name" placeholder="name">
    <input type="text" name="image" placeholder="image">
    <input type="text" name="producer" placeholder="producer">
    <input type="text" name="amount" placeholder="amount">
    <input type="text" name="price" placeholder="price">
    <input type="submit" value="Create">
</form>

<h1>Product added</h1>


</body>
</html>
