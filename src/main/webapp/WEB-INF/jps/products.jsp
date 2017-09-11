<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.08.2017
  Time: 7:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Products list</title>
</head>
<body>
<table>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>
                    <a  href="/product/find/${product.id}" >${product.name}</a>
                </td>
            </tr>
        </c:forEach>
    <a href="#top">Go to top</a>
    </table>

<form action="/user/logout" method="post">
    <input type="submit" value="logout">
</form>

</body>
</html>
