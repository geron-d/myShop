<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 02.05.2022
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>allProducts</title>
</head>
<body>
<c:import url="/pages/adminHeader.jsp"/>

<ul>
    <li><h1>All products</h1></li>
    <c:forEach var="product" items="${products}">
        <li><a href="${pageContext.request.contextPath}/products/product/admin?id=${product.id}">${product.name}</a></li>
    </c:forEach>
</ul>
</body>
</html>
