<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Address</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1>Add New Address</h1>

    <form action="${pageContext.request.contextPath}/address/" method="post" class="address-form">
        <div class="form-group">
            <label for="personName">Person Name:</label>
            <input type="text" id="personName" name="personName" required>
        </div>

        <div class="form-group">
            <label for="streetNumber">Street Number:</label>
            <input type="text" id="streetNumber" name="streetNumber" required>
        </div>

        <div class="form-group">
            <label for="streetName">Street Name:</label>
            <input type="text" id="streetName" name="streetName" required>
        </div>

        <div class="form-group">
            <label for="cityName">City:</label>
            <input type="text" id="cityName" name="cityName" required>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn">Save</button>
            <a href="${pageContext.request.contextPath}/address/" class="btn cancel">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>