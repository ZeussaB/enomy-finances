<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Enomy-Finances</title>
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/resources/css/styles.css">
</head>
<body>
	<header>
		<h1>Enomy-Finances</h1>
		<nav>
			<ul>
				<li><a href="<c:url value='/'/>">Home</a></li>
				<li><a href="<c:url value='/login'/>">Login</a></li>
				<li><a href="<c:url value='/register'/>">Register</a></li>
				<li><a href="<c:url value='/account'/>">Account</a></li>
			</ul>
		</nav>
	</header>

	<main>
		<section id="welcom-message">
			<h2>Currency and Investment Services</h2>
			<p>At Enomy-Finances, we are dedicated to helping you achieve
				your financial goals through our comprehensive currency conversion
				and investment services. Whether you're looking to convert
				currencies at competitive rates or explore tailored investment
				opportunities, we provide the tools and expertise to support your
				financial journey. Our user-friendly platform ensures a seamless
				experience, allowing you to focus on what matters most – growing
				your wealth and securing your future.</p>
		</section>
		<!-- Currency Conversion Section -->
		<section id="currency-section">
			<h2>Currency Converter</h2>
			<form id="currencyForm">
				<label for="fromCurrency">From:</label> <select id="fromCurrency"
					name="fromCurrency" required>
					<option value="GBP">GBP</option>
					<option value="USD">USD</option>
					<option value="EUR">EUR</option>
					<option value="BRL">BRL</option>
					<option value="JPY">JPY</option>
					<option value="TRY">TRY</option>
				</select> <label for="toCurrency">To:</label> <select id="toCurrency"
					name="toCurrency" required>
					<option value="GBP">GBP</option>
					<option value="USD">USD</option>
					<option value="EUR">EUR</option>
					<option value="BRL">BRL</option>
					<option value="JPY">JPY</option>
					<option value="TRY">TRY</option>
				</select> <label for="amount">Amount:</label> <input type="number"
					id="amount" name="amount" min="300" max="5000" required>

				<button type="submit">Convert</button>
			</form>

			<h3>Conversion Details:</h3>
			<div id="conversionResult">
				<p>
					<strong>Original Amount:</strong> <span id="originalAmount"></span>
				</p>
				<p>
					<strong>Fee Deducted:</strong> <span id="feeAmount"></span>
				</p>
				<p>
					<strong>Amount After Fee:</strong> <span id="amountAfterFee"></span>
				</p>
				<p>
					<strong>Converted Amount:</strong> <span id="convertedAmount"></span>
				</p>
			</div>
		</section>

		<!-- Investment Section -->
		<section id="investment-section">
			<h2>Investment Calculator</h2>

			<% if (request.getAttribute("error") != null) { %>
			<p style="color: red;"><%= request.getAttribute("error") %></p>
			<% } %>

			<form action="<%= request.getContextPath() %>/investment/quote"
				method="post">
				<label for="investmentType">Investment Type:</label> <select
					id="investmentType" name="investmentType" required
					onchange="updateInvestmentLimits()">
					<option value="basicSavings">Basic Savings Plan</option>
					<option value="savingsPlus">Savings Plan Plus</option>
					<option value="managedStockInvestments">Managed Stock
						Investments</option>
				</select> <label for="initialAmount">Initial Investment (£):</label> <input
					type="number" id="initialAmount" name="initialAmount" min="0"
					required> <label for="monthlyAmount">Monthly
					Contribution (£):</label> <input type="number" id="monthlyAmount"
					name="monthlyAmount" min="50" required>

				<button type="submit">Get Quote</button>
			</form>

			<h3>Investment Quote:</h3>
			<table>
				<tr>
					<th>Time Frame</th>
					<th>Expected Return</th>
					<th>Profit</th>
					<th>Fees</th>
					<th>Tax</th>
				</tr>
				<tr>
					<td>1 Year</td>
					<td><%= request.getAttribute("minReturn1") != null ? request.getAttribute("minReturn1") : "£0.00" %></td>
					<td><%= request.getAttribute("profit1") != null ? request.getAttribute("profit1") : "£0.00" %></td>
					<td><%= request.getAttribute("fees1") != null ? request.getAttribute("fees1") : "£0.00" %></td>
					<td><%= request.getAttribute("tax1") != null ? request.getAttribute("tax1") : "£0.00" %></td>
				</tr>
				<tr>
					<td>5 Years</td>
					<td><%= request.getAttribute("minReturn5") != null ? request.getAttribute("minReturn5") : "£0.00" %></td>
					<td><%= request.getAttribute("profit5") != null ? request.getAttribute("profit5") : "£0.00" %></td>
					<td><%= request.getAttribute("fees5") != null ? request.getAttribute("fees5") : "£0.00" %></td>
					<td><%= request.getAttribute("tax5") != null ? request.getAttribute("tax5") : "£0.00" %></td>
				</tr>
				<tr>
					<td>10 Years</td>
					<td><%= request.getAttribute("minReturn10") != null ? request.getAttribute("minReturn10") : "£0.00" %></td>
					<td><%= request.getAttribute("profit10") != null ? request.getAttribute("profit10") : "£0.00" %></td>
					<td><%= request.getAttribute("fees10") != null ? request.getAttribute("fees10") : "£0.00" %></td>
					<td><%= request.getAttribute("tax10") != null ? request.getAttribute("tax10") : "£0.00" %></td>
				</tr>
			</table>
		</section>
	</main>

	<footer>
		<p>&copy; 2025 Enomy-Finances. All Rights Reserved.</p>
	</footer>
	<script
		src="<%= request.getContextPath() %>/resources/js/currencyConverter.js"></script>
	<script
		src="<%= request.getContextPath() %>/resources/js/investment.js"></script>
</body>
</html>