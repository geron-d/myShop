<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 02.05.2022
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>product</title>
</head>
<body>
<c:import url="/pages/adminHeader.jsp"/>

<ul>
    <li><h3>${product.name}</h3></li>
    <li><h5>Category:</h5>${product.category}</li>
    <li><h5>Type:</h5>${product.type}</li>
    <li><h5>Photo:</h5><img src="${pageContext.request.contextPath}${product.image_path}" alt="Photo"></li>
    <li><h5>Producer:</h5>${product.producer}</li>
    <li><h5>Amount:</h5>${product.amount}</li>
    <li><h5>Price:</h5>${product.price}</li>
    <li>
        <form action="${pageContext.request.contextPath}/products/product/admin?id=${product.id}&submit=edit" method="post">
            <input type="submit" value="Edit">
        </form>
    </li>
    <li>
        <form action="${pageContext.request.contextPath}/products/product/admin?id=${product.id}&submit=delete" method="post">
            <input type="submit" value="Delete">
        </form>
    </li>
</ul>

</body>
</html>
