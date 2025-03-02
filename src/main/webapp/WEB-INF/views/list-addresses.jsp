<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Address Book - ${addressBookName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1>Address Book: ${addressBookName}</h1>

    <div class="actions">
        <a href="${pageContext.request.contextPath}/address/new" class="btn">Add New Address</a>
    </div>

    <div class="search-form">
        <form action="${pageContext.request.contextPath}/address/search" method="get">
            <label for="searchTerm">Search:</label>
            <input type="text" id="searchTerm" name="searchTerm" required>

            <label for="searchType">Search by:</label>
            <select id="searchType" name="searchType">
                <option value="person">Person Name</option>
                <option value="city">City</option>
            </select>

            <button type="submit" class="btn">Search</button>
        </form>
    </div>

    <c:if test="${searchPerformed}">
        <div class="search-results">
            <h3>Search Results for: ${searchTerm}</h3>
        </div>
    </c:if>

    <c:if test="${message != null}">
        <div class="message">${message}</div>
    </c:if>

    <c:choose>
        <c:when test="${empty addresses}">
            <p>No addresses found.</p>
        </c:when>
        <c:otherwise>
            <table class="addresses-table">
                <thead>
                <tr>
                    <th>Person</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="address" items="${addresses}">
                    <tr>
                        <td>${address.personName}</td>
                        <td>${address.streetNumber} ${address.streetName}</td>
                        <td>${address.cityName}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/address/delete" method="post">
                                <input type="hidden" name="personName" value="${address.personName}">
                                <input type="hidden" name="streetName" value="${address.streetName}">
                                <input type="hidden" name="streetNumber" value="${address.streetNumber}">
                                <input type="hidden" name="cityName" value="${address.cityName}">
                                <button type="submit" class="btn delete">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>

