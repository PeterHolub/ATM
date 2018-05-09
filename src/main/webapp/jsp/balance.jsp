<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet" type="text/css">
    <title>Balance</title>

</head>
<body>

<div class="operations">
    <h4 style="color: #528ECC; text-align: center ">Account info:</h4>
    <h5>Today’s date: <c:out value="${dateTime}"/></h5>
    <h5>Amount: <c:out value="${amount}"/></h5>
    <button onclick="location.href = '/jsp/operations.jsp'; " class="buttonsOperations">Back</button>

    <button onclick="location.href = 'home.jsp'; " class="buttonsOperations">Exit</button>

</div>
</body>

</html>