<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="font-size: large">
    <c:if test="${not empty message}">INFO : ${message}</c:if> <br/>
</div>
<div>
    <div class="container-fluid">
        <div class="col-md-12">Товары</div>
        <table class="table">
            <tr>
                <th class="col-md-4">Supplier</th>
                <div class="col-md-8">
                    <th class="col-md-4">Model</th>
                    <th class="col-md-1">Quantity</th>
                    <th class="col-md-1">Price</th>
                    <th class="col-md-1"></th>
                    <th class="col-md-1"></th>
                </div>
            </tr>
            <script>
                function callAlert(productId) {
                    alert(productId);
                }
            </script>
            <c:forEach var="product" items="${products}" varStatus="status">
                <tr class="">
                    <td class="col-md-4">${product.supplier}</td>
                    <div class="col-md-8">
                        <td class="col-md-4">${product.model}</td>
                        <td id="count${product.id}" class="col-md-1">0</td>
                        <td class="col-md-1">${product.price}</td>
                        <td class="col-md-1"><input id="${product.id}" class="btn-primary addProductBtn" type="button" title="Добавить в корзину" value="+"/></td>
                        <td class="col-md-1"></td>
                    </div>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>




