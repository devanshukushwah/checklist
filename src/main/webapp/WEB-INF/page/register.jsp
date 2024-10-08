<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<jsp:include page="../common/commonHead.jsp"></jsp:include>
	<title>Register</title>
</head>
<style>
         /* Custom styles for the registration form */
        body {
            background-color: #f7f7f7;
        }
        .register-container {
            width: 100%;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .register-form {
            width: 400px;
            background: #fff;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
    </style>
<body>
	<div class="register-container">
        <div class="ui raised segment register-form">
            <h2 class="ui header">Register</h2>
            <form class="ui form" action="./register" method="post">
                <!-- Full Name Field -->
                <div class="field">
                    <label for="fullname">Full Name</label>
                    <input type="text" name="fullName" id="fullname" placeholder="Enter your full name" required>
                </div>

                <!-- Email Field -->
                <div class="field">
                    <label for="email">Email</label>
                    <input type="email" name="email" id="email" placeholder="Enter your email" required>
                </div>

                <!-- Password Field -->
                <div class="field">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" placeholder="Enter your password" required>
                </div>

                <!-- Confirm Password Field -->
                <div class="field">
                    <label for="confirm_password">Confirm Password</label>
                    <input type="password" name="confirmPassword" id="confirm_password" placeholder="Confirm your password" required>
                </div>

				<c:if test="${not empty errorMessage}">
					<div class="ui mini red message">${errorMessage}</div>
				</c:if>

                <!-- Submit Button -->
                <button class="ui primary button" type="submit">Register</button>

                <!-- Optional: Link to Login Page -->
                <div class="ui message">
                    Already have an account? <a href="login">Log in</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>