<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.test.model.User"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    HttpSession userSession = request.getSession(false);
    User user = (userSession != null) ? (User) userSession.getAttribute("loggedInUser") : null;
    if (user == null) {
        response.sendRedirect("login");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Account - Enomy-Finances</title>
<link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>">
</head>
<body>
	<header>
		<h1>Enomy-Finances</h1>
		<nav>
			<ul>
				<li><a href="<c:url value='/'/>">Home</a></li>
				<li><a href="<c:url value='/logout'/>">Logout</a></li>
			</ul>
		</nav>
	</header>

	<main>
		<section class="user-info">
			<h2>
				Welcome,
				<c:out value="${loggedInUser.username}" />
			</h2>
			<p>
				<strong>Email:</strong>
				<c:out value="${loggedInUser.email}" />
			</p>
		</section>

		<section class="password-update">
			<h3>Update Password</h3>
			<form action="<c:url value='/update-password'/>" method="post">
				<label for="currentPassword">Current Password:</label> <input
					type="password" id="currentPassword" name="currentPassword"
					required> <label for="newPassword">New Password:</label> <input
					type="password" id="newPassword" name="newPassword" required>

				<label for="confirmNewPassword">Confirm New Password:</label> <input
					type="password" id="confirmNewPassword" name="confirmNewPassword"
					required>

				<button type="submit">Update Password</button>
			</form>
		</section>
	</main>

	<footer>
		<p>&copy; 2025 Enomy-Finances. All Rights Reserved.</p>
	</footer>
</body>
</html>