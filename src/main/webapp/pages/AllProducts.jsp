<%@ page import="java.util.List" %>
<%@ page import="by.it.academy.entities.Product" %><%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 16.04.2022
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Products</title>
</head>
<body>
    <thead>
        <tr>
            <th><a href="${pageContext.request.contextPath}/index.jsp">StartPage</a></th>
            <th>
                <form action="${pageContext.request.contextPath}/products" method="get">
                    <td><input type="submit" value="Categories"></td>
                </form>
            </th>
            <th>PrivateRoom</th>
        </tr>
    </thead>

<ul>
    <c:forEach var="product" items="${products}">
        <li><a href="${pageContext.request.contextPath}/product?id=${product.id}">${product.name}</a></li>
    </c:forEach>
</ul>
</body>
</html>
