<!-- src/main/webapp/WEB-INF/views/error.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container error">
    <h1>Error</h1>
    <p class="error-message">${errorMessage}</p>
    <a href="${pageContext.request.contextPath}/address/" class="btn">Back to Address Book</a>
</div>
</body>
</html>