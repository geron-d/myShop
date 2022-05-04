<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 04.05.2022
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>sort</title>
</head>
<body>

<c:import url="/pages/adminHeader.jsp"/>

<table>
    <tbody>
    <tr>
        <td>
            <ul>
                <form action="${pageContext.request.contextPath}/products/sort/admin" method="get">
                    <li>Category</li>
                    <label>
                        <li><input type="checkbox" name="category" value="headphones">headphones</li>
                    </label>
                    <li>Type</li>
                    <label>
                        <li><input type="checkbox" name="type" value="wire">wire</li>
                    </label>
                    <label>
                        <li><input type="checkbox" name="type" value="wire overhead">wire overhead</li>
                    </label>
                    <label>
                        <li><input type="checkbox" name="type" value="wireless">wireless</li>
                    </label>
                    <label>
                        <li><input type="checkbox" name="type" value="wireless overhead">wireless overhead</li>
                    </label>
                    <input type="submit" value="Sort">
                </form>
            </ul>
        </td>
        <td>
            <ul>
                <li><h1>Find products</h1></li>
                <%--@elvariable id="products" type="java.util.List"--%>
                <c:forEach var="product" items="${products}">
                    <li><a href="${pageContext.request.contextPath}/product?id=${product.id}">${product.name}</a></li>
                </c:forEach>
            </ul>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>
