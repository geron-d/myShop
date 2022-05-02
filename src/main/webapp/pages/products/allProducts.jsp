<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 16.04.2022
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Products</title>
</head>
<body>

<c:import url="/pages/userHeader.jsp"/>

<ul>
    <li><h1>All products</h1></li>
    <%--@elvariable id="products" type="java.util.List"--%>
    <c:forEach var="product" items="${products}">
        <li><a href="${pageContext.request.contextPath}/product?id=${product.id}">${product.name}</a></li>
    </c:forEach>
</ul>
</body>
</html>
