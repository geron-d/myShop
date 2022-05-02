<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 02.05.2022
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>headphones</title>
</head>
<body>
<c:import url="/pages/adminHeader.jsp"/>

<ul>
    <li><h1>Headphones</h1></li>
    <c:forEach var="headphone" items="${headphones}">
        <li><a href="${pageContext.request.contextPath}/products/product/admin?id=${headphone.id}">${headphone.name}</a></li>
    </c:forEach>
</ul>
</body>
</html>
