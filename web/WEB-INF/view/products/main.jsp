<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="font-size: large">
    <c:if test="${not empty message}">INFO : ${message}</c:if> <br/>
</div>
<div>
    Товары
    <div class="productTable">
        <c:forEach var="product" items="${products}" varStatus="status">
            <div id="${product.id}">${product.supplier} ${product.model} ${product.price}</div>
        </c:forEach>
    </div>
</div>




