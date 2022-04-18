<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 18.04.2022
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product</title>
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

<ul>
    <li><h3>${product.name}</h3></li>
    <li><h5>Category:</h5>${product.category}</li>
    <li><h5>Type:</h5>${product.type}</li>
    <li><h5>Photo:</h5>${product.image_path}</li>
    <li><h5>Producer:</h5>${product.producer}</li>
    <li><h5>Amount:</h5>${product.amount}</li>
    <li><h5>Price:</h5>${product.price}</li>
    <li>
        <form action="${pageContext.request.contextPath}/product?id=${product.id}" method="post">
            <input type="submit" value="Buy">
        </form>

    </li>
    <li><h1>Product added to bucket</h1></li>
</ul>
</body>
</html></html>
