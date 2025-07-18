<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
</head>
<body>
    <h2>Reset Your Password</h2>

    <%
        String token = request.getParameter("token");
        String msg = (String) request.getAttribute("msg");
        if (msg != null) {
    %>
        <div style="color: red"><%= msg %></div>
    <%
        }
    %>

    <form action="ResetPasswordServlet" method="post">
        <input type="hidden" name="token" value="<%= token %>">
        <label>New Password:</label>
        <input type="password" name="newPassword" required>
        <input type="submit" value="Reset Password">
    </form>
</body>
</html>
