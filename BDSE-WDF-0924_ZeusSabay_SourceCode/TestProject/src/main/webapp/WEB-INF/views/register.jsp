<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register - Enomy-Finances</title>
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
		<section id="register-section">
			<h2>Register</h2>
			<form action="<%= request.getContextPath() %>/register" method="post">
				<label for="username">Username:</label> <input type="text"
					id="username" name="username" required> <label for="email">Email:</label>
				<input type="email" id="email" name="email" required> <label
					for="password">Password:</label> <input type="password"
					id="password" name="password" required> <label
					for="confirmPassword">Confirm Password:</label> <input
					type="password" id="confirmPassword" name="confirmPassword"
					required>

				<button type="submit">Register</button>
			</form>
			<p>
				Already have an account? <a href="<c:url value='/login'/>">Login</a>.
			</p>
		</section>
	</main>

	<footer>
		<p>&copy; 2025 Enomy-Finances. All Rights Reserved.</p>
	</footer>
</body>
</html>