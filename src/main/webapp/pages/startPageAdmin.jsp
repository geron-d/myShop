<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 22.04.2022
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>startPageAdmin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>

<body>
<c:import url="/pages/adminHeader.jsp"/>

<ul>
    <li><h1>Last products</h1></li>
    <%--@elvariable id="lastProducts" type="java.util.List"--%>
    <c:forEach var="lastProduct" items="${lastProducts}">
        <li>
            <a href="${pageContext.request.contextPath}/products/product/admin?id=${lastProduct.id}">${lastProduct.name}</a>
        </li>
    </c:forEach>
</ul>

</body>

</html>
