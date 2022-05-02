<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 02.05.2022
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>editProduct</title>
</head>
<body>
<c:import url="/pages/adminHeader.jsp"/>

<ul>
    <li><h3>${product.name}</h3></li>
    <li><h5>Category:</h5>${product.category}</li>
    <li><h5>Type:</h5>${product.type}</li>
    <li><h5>Photo:</h5>${product.image_path}</li>
    <li><h5>Producer:</h5>${product.producer}</li>
    <li><h5>Amount:</h5>${product.amount}</li>
    <li><h5>Price:</h5>${product.price}</li>
</ul>

<form action="${pageContext.request.contextPath}/products/edit?id=${product.id}" method="post">
    <input type="text" name="category" placeholder="category">
    <input type="text" name="type" placeholder="type">
    <input type="text" name="name" placeholder="name">
    <input type="text" name="image" placeholder="image">
    <input type="text" name="producer" placeholder="producer">
    <input type="text" name="amount" placeholder="amount">
    <input type="text" name="price" placeholder="price">
    <input type="submit" value="Edit">
</form>
</body>
</html>
