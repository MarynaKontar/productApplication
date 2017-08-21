<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.08.2017
  Time: 8:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Product</title>
</head>
<body>
<p>This is search results for product</p>
<table>
    <tr>
        <td>name</td>
        <td>cost</td>
        <td>finalStorageDate</td>
        <s:authorize access="hasRole('ADMIN')"> <%--"hasAnyRole("USER", "ADMIN")" - если для нескольких ролей--%>
            <td>id</td> <%-- надпись id и manufacturer показываем только админу - прописываем authorize для тех полей, которые д.б. видны только определенным ролям--%>
            <td>manufacturer</td>
        </s:authorize>
    </tr>
    <c:if test="${not empty product}"> <%--такое же название как у view (product.jsp) и в методе findProduct в ShowProductsController --%>
        <tr>
            <td>${product.name}</td>
            <td>${product.cost}</td>
            <td>${product.finalStorageDate}</td>
            <s:authorize access="hasAnyRole('ADMIN','USER')">
                <td>${product.id}</td> <%-- id и manufacturer показываем только админу--%>
                <td>${product.manufacturer.name}</td>
            </s:authorize>
        </tr>
    </c:if>
</table>
</body>
</html>

