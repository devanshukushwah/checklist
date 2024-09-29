<html>
<head>
	<jsp:include page="../common/commonHead.jsp"></jsp:include>
	<title>Login</title>
</head>
<style>
        /* Custom styles for login form */
        body {
            background-color: #f7f7f7;
        }
        .login-container {
            width: 100%;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .login-form {
            width: 350px;
            background: #fff;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
    </style>
<body>
	<div class="login-container">
        <div class="ui raised segment login-form">
            <h2 class="ui header">Login</h2>
            <form class="ui form" action="./login" method="post">
                <!-- Username Field -->
                <div class="field">
                    <label for="email">Email</label>
                    <input type="text" name="email" id="email" placeholder="Enter your email" required>
                </div>

                <!-- Password Field -->
                <div class="field">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" placeholder="Enter your password" required>
                </div>

                <!-- Submit Button -->
                <button class="ui primary button" type="submit">Login</button>

                <!-- Optional: Link to Registration Page -->
                <div class="ui message">
                    New user? <a href="register">Sign up</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>