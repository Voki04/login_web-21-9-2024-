<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
</head>
<body>

<h1>Welcome!</h1>

    <!-- Logout Button -->
    <form action="${pageContext.request.contextPath}/logout" method="get">
        <button type="submit">Log out</button>
    </form>
</body>
</html>