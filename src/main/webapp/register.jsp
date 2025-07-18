<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Form</title>
<style>
    body {
        font-family: Arial;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
    }
    .container {
        width: 400px;
        margin: 60px auto;
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
        color: #555;
    }
    input[type="text"], input[type="password"], input[type="email"], select {
        width: 100%;
        padding: 8px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    .radio-group {
        margin-bottom: 15px;
    }
    .radio-group input {
        margin-right: 6px;
    }
    input[type="submit"] {
        width: 100%;
        padding: 10px;
        background-color: #4285F4;
        border: none;
        color: white;
        font-size: 16px;
        border-radius: 5px;
        cursor: pointer;
    }
    input[type="submit"]:hover {
        background-color: #357ae8;
    }
    .error {
        color: red;
        text-align: center;
        margin-bottom: 10px;
    }
    .login-link {
        text-align: center;
        margin-top: 15px;
    }
</style>
</head>
<body>

<div class="container">
    <% String msg = (String) request.getAttribute("msg"); %>
    <% if (msg != null) { %>
        <div class="error"><%= msg %></div>
    <% } %>

    <h2>Register Here</h2>
    <form action="regForm" method="post">
        <label>Name:</label>
        <input type="text" name="name1" required
    pattern="^[A-Za-z]{1,20}(_[A-Za-z]{1,20}){0,2}$"
    title="Only alphabets and underscores allowed9302."
    maxlength="30"
    placeholder="e.g., Rahul_Kumar or Rahul_Kumar_Singh" />
        
        
        

        
<label>Email:</label>
<input type="email" id="email" name="email" required
    pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
    title="Enter a valid email address like user@example.com"
    placeholder="Enter your email" />
<div id="email-error" class="error" style="display:none;"></div>

        
        
        
        <label>Password:</label>
        <input type="password" name="pass1" required 
            pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$" 
            title="Must contain at least one uppercase, one lowercase, one number, one special character and be at least 8 characters long" />

        <label>Gender:</label>
        <div class="radio-group">
            <input type="radio" name="gender1" value="Male" required /> Male
            <input type="radio" name="gender1" value="Female" /> Female
        </div>

        <label>City:</label>
        <select name="city1" required>
            <option value="">Select City</option>
            <option>Indore</option>
            <option>Mumbai</option>
            <option>Pune</option>
            <option>Banglore</option>
            <option>Delhi</option>
            <option>Chennai</option>
        </select>

        <input type="submit" value="Register" />
    </form>
    <div class="login-link">
        Already have an account? <a href="login.jsp">Login here</a>
    </div>
</div>
<script>
document.querySelector("form").addEventListener("submit", function(e) {
    const emailInput = document.getElementById("email");
    const errorDiv = document.getElementById("email-error");
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (!emailPattern.test(emailInput.value)) {
        e.preventDefault();
        errorDiv.style.display = "block";
        errorDiv.textContent = "Invalid email format. Please enter a valid email like user@example.com";
        emailInput.focus();
    } else {
        errorDiv.style.display = "none";
    }
});
</script>


</body>
</html>
