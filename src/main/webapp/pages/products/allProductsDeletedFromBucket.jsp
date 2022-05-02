<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 01.05.2022
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>allProductsDeletedFromBucket</title>
</head>
<body>

<c:import url="/pages/userHeader.jsp"/>

<ul>
    <li><h1>Products deleted from bucket</h1></li>
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
