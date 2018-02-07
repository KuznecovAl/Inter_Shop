<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container text-center">
    <div class="error">${errorMsg}</div>
    <form action="frontController?command=login" method="post">
        <b>Login</b><input type="text" name="login" maxlength="30"/>
        <b>Password</b><input type="password" name="password" maxlength="20"/><br/>
        <input type="submit" value="Войти">
    </form>
</div>
