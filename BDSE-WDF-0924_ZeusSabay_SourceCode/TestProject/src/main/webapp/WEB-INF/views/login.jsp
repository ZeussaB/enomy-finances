<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login - Enomy-Finances</title>
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/resources/css/styles.css">
</head>
<body>
	<header>
		<h1>Enomy-Finances</h1>
		<nav>
			<ul>
				<li><a href="<c:url value='/'/>">Home</a></li>
			</ul>
		</nav>
	</header>

	<main>
		<section id="login-section">
			<h2>Login</h2>
			<form action="<%= request.getContextPath() %>/login" method="post">
				<label for="username">Username:</label> <input type="text"
					id="username" name="username" required> <label for="email">E-mail:</label>
				<input type="email" id="email" name="email" required> <label
					for="password">Password:</label> <input type="password"
					id="password" name="password" required>

				<button type="submit">Login</button>
			</form>
			<p>
				Don't have an account? <a href="<c:url value='/register'/>">Register</a>.
			</p>
		</section>
	</main>

	<footer>
		<p>&copy; 2025 Enomy-Finances. All Rights Reserved.</p>
	</footer>
</body>
</html>