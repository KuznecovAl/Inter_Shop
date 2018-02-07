<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<TABLE>
    <tr>
        <th>№</th>
        <th>Order Id</th>
        <th>User id</th>
        <th>Sum</th>
    </tr>

    <c:forEach var="order" items="${orders}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${order.id}</td>
            <td>${order.userId}</td>
            <td>${order.total}</td>
        </tr>
    </c:forEach>
</TABLE>
