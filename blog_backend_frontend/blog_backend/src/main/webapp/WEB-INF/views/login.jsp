<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>

	<h2>Login</h2>
	<form action="login" method="post">
		Email: <input type="text" name="email" /><br /> Password: <input
			type="password" name="password" /><br />
		<button type="submit">Login</button>
	</form>

	<!-- Show error message if available -->
	<p style="color: red;">${error}</p>

</body>
</html>
