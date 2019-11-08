
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization" />
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="ua" ${language == 'ua' ? 'selected' : ''}>Ukrainian</option>
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>

    </select>
</form>

<form action="user" method="post" >
    <br>
    <label for="name"><fmt:message key="registration.label.name" />:</label>
    <input type="text" id="name" name="name">
    <br>
    <label for="surname"><fmt:message key="registration.label.surname" />:</label>
    <input type="text" id="surname" name="surname">
    <br>
    <label for="email"><fmt:message key="login.label.email" />:</label>
    <input type="text" id="email" name="email">
    <br>
    <label for="password"><fmt:message key="login.label.password" />:</label>
    <input type="password" id="password" name="password">
    <br>
    <label for="confirmPassword"><fmt:message key="registration.label.confirmPassword" />:</label>
    <input type="password" id="confirmPassword" name="confirmPassword">
    <br>
    <fmt:message key="login.button.submit" var="register" />
    <input type="submit" name="command" value="register" >
</form>


<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
</body>
