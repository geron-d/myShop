<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 25.04.2022
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Headphones</title>
</head>
<body>

<c:import url="/pages/userHeader.jsp"/>

<ul>
    <li><h1>Headphones</h1></li>
    <%--@elvariable id="headphones" type="java.util.List"--%>
    <c:forEach var="headphone" items="${headphones}">
        <li><a href="${pageContext.request.contextPath}/product?id=${headphone.id}">${headphone.name}</a></li>
    </c:forEach>
</ul>
</body>
</html>
