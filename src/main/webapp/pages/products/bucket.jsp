<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 25.04.2022
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>

<nav class="top-menu">
    <p><a href="${pageContext.request.contextPath}/start"><img src="${pageContext.request.contextPath}/logo.jpg" alt="Logo"></a></p>
    <ul class="menu-main">
        <li><a href="${pageContext.request.contextPath}/start">Start Page</a></li>
        <li>
            <a href="${pageContext.request.contextPath}/products">Products</a>
            <ul>
                <li><a href="${pageContext.request.contextPath}/products">All products</a></li>
                <li><a href="${pageContext.request.contextPath}/headphones">Headphones</a></li>
            </ul>
        </li>
        <li><a href="${pageContext.request.contextPath}/bucket">Bucket</a></li>
        <li><a href="${pageContext.request.contextPath}/user/userPrivateRoom">Private room</a></li>
    </ul>
</nav>

<ul>
    <li><h1>Bucket</h1></li>
    <c:forEach var="productInBucket" items="${productsInBucket}">
        <li>
            <a href="${pageContext.request.contextPath}/product?id=${productInBucket.product.id}">${productInBucket.product.name}</a>
            <h3>         amount: ${productInBucket.amount}       </h3>
            <form action="${pageContext.request.contextPath}/bucket?id=${productInBucket.product.id}&submit=delete" method="post">
                <input type="submit" value="Delete">
            </form>
        </li>
    </c:forEach>

    <li><h1>All cost products in bucket = ${allCost}</h1></li>
    <c:if test="${isBucketEmpty}">
        <form action="${pageContext.request.contextPath}/bucket?submit=deleteAll" method="post">
            <input type="submit" value="DeleteAll">
        </form>
        <form action="${pageContext.request.contextPath}/bucket?submit=buy" method="post">
            <input type="submit" value="Buy">
        </form>
    </c:if>

</ul>
</body>
</html>
