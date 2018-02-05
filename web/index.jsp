
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Forwarding test</title>
</head>
<body>
<form action="nameservlet" style="display: none">
  Введите имя:
  <input name="name" type="text"><br/>
  Введите фамилиию:
  <input name="lastName" type="text"><br/>
  <input type="submit" value="Отправить"/>
</form>

<form action="forward">Создать заказ через <h2>forward</h2>
  Введите id пользователя:
  <input name="userId" type="text"/><br/>
  Введите id товара:
  <input name="productId" type="text"/><br/>
  Введите количество товара:
  <input name="quantity" type="text"/><br/>
  <input type="submit" value="Создать заказ c Forward"/>
</form>
<br/>
<form action="redirect">Создать заказ через <h2>redirect</h2>
  Введите id пользователя:
  <input name="userId" type="text"/><br/>
  Введите id товара:
  <input name="productId" type="text"/><br/>
  Введите количество товара:
  <input name="quantity" type="text"/><br/>
  <input type="submit" value="Создать заказ c Redirect"/>
</form>
</body>
</html>





<%--&lt;%&ndash;--%>
  <%--Created by IntelliJ IDEA.--%>
  <%--User: aLexus--%>
  <%--Date: 02.02.2018--%>
  <%--Time: 15:16--%>
  <%--To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
  <%--<head>--%>
    <%--<title>$Title$</title>--%>
  <%--</head>--%>
  <%--<body>--%>
  <%--$END$--%>
  <%--</body>--%>
<%--</html>--%>
