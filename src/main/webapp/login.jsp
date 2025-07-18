<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
    }
    .container {
        width: 400px;
        margin: 80px auto;
        padding: 30px;
        background-color: white;
        border-radius: 10px;
        box-shadow: 0 0 15px rgba(0,0,0,0.2);
    }
    h2 {
        text-align: center;
        color: #333;
    }
    label {
        display: block;
        margin-bottom: 6px;
        font-weight: bold;
    }
    input[type="text"], input[type="password"], input[type="email"] {
        width: 100%;
        padding: 8px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    input[type="submit"] {
        width: 100%;
        padding: 10px;
        background-color: #28a745;
        color: white;
        font-size: 16px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    input[type="submit"]:hover {
        background-color: #218838;
    }
    .error-msg {
        color: red;
        text-align: center;
        margin-bottom: 15px;
        font-weight: bold;
    }
</style>
</head>
<body>

<div class="container">
    <h2>Login</h2>

    <%
        String msg = (String) request.getAttribute("msg");
        if (msg != null) {
    %>
        <div class="error-msg"><%= msg %></div>
    <%
        }
    %>

    <form action="loginForm" method="post">
        <label>Email:</label>
        <input type="email" name="email" required
            pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
            title="Enter a valid email like user@example.com" />

       <label>Password:</label>
<input type="password" name="pass1" id="pass1" required 
    pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$" 
    title="Must contain 1 uppercase, 1 lowercase, 1 number, 1 special character, and 8–20 characters" />

<div style="margin-top: -10px; margin-bottom: 15px;">
    <input type="checkbox" onclick="togglePassword()" id="showPass" />
    <label for="showPass" style="font-weight: normal; cursor: pointer;">Show Password</label>
</div>


        <input type="submit" value="Login" />
        <div style="text-align: center; margin-top: 10px;">
    <a href="ForgetPass.jsp" style="text-decoration: none; color: #007bff;">Forgot Password?</a>
</div>
        
    </form>
</div>

<script>
function togglePassword() {
    const pass = document.getElementById("pass1");
    pass.type = (pass.type === "password") ? "text" : "password";
}
</script>

</body>
</html>
