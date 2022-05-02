<%--@elvariable id="product" type="javax.xml.stream.util.StreamReaderDelegate"--%>
<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 18.04.2022
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product</title>
</head>
<body>

<c:import url="/pages/userHeader.jsp"/>

<ul>
    <li><h1>Product added to bucket</h1></li>
    <li><h3>${product.name}</h3></li>
    <li><h5>Category:</h5>${product.category}</li>
    <li><h5>Type:</h5>${product.type}</li>
    <li><h5>Photo:</h5><img src="${pageContext.request.contextPath}${product.image_path}" alt="Photo"></li>
    <li><h5>Producer:</h5>${product.producer}</li>
    <li><h5>Amount:</h5>${product.amount}</li>
    <li><h5>Price:</h5>${product.price}</li>
    <li>
        <form action="${pageContext.request.contextPath}/product?id=${product.id}" method="post">
            <input type="submit" value="AddToBucket">
        </form>
    </li>
</ul>
</body>
</html>
