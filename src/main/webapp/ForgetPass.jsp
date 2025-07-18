<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>
</head>
<body>
    <h2>Forgot Password</h2>

    <%
        String msg = (String) request.getAttribute("msg");
        if (msg != null) {
    %>
        <div style="color: red"><%= msg %></div>
    <%
        }
    %>

    <form action="ForgetPass" method="post">
        <label>Email:</label>
        <input type="email" name="email" required>
        <input type="submit" value="Send Reset Link">
    </form>
</body>
</html>
