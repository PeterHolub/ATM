<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet"
          type="text/css">
    <title>Operations</title>

</head>

<body>

<div class="operations">
    <h4 style="color: #528ECC; text-align: center ">Choose the operation</h4>

    <button onclick="location.href = '/Balance'; " class="buttonsOperations">Balance</button>

    <button onclick="location.href = '/jsp/withdrawals.jsp'; " class="buttonsOperations">RemoveSum</button>

    <button onclick="location.href = 'home.jsp'; " class="buttonsOperations">Exit</button>

</div>
</body>

</html>