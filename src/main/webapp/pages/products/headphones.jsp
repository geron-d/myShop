<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 25.04.2022
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>headphones</title>
</head>
<body>

<c:import url="/pages/userHeader.jsp"/>
<table>
    <tbody>
    <tr>
        <td>
            <ul>
                <form action="${pageContext.request.contextPath}/sort" method="get">
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
                <li><h1>Headphones</h1></li>
                <%--@elvariable id="headphones" type="java.util.List"--%>
                <c:forEach var="headphone" items="${headphones}">
                    <li><a href="${pageContext.request.contextPath}/product?id=${headphone.id}">${headphone.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>
