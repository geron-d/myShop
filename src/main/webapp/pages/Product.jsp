<%@ page import="by.it.academy.entities.Product" %>
<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 17.04.2022
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product</title>
</head>
<body>

<ul>
    <li>${product.id}</li>
    <li>${product.name}</li>
    <li>${product.category}</li>
    <li>${product.type}</li>
    <li>${product.image_path}</li>
    <li>${product.localDate}</li>
    <li>${product.producer}</li>
    <li>${product.amount}</li>
    <li>${product.price}</li>
</ul>
</body>
</html>
