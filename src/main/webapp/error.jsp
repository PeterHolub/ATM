<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error!</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<div class="operations">

    <c:if test="${wrongCardNumber!=null}">
        <h1 style="color: #528ECC; text-align: center "><c:out value="${wrongCardNumber}"/></h1>
        <button onclick="location.href = 'home.jsp'; " class="buttons">Back</button>
    </c:if>
    <c:if test="${cardBlocked!=null}">
        <h1 style="color: #528ECC; text-align: center "><c:out value="${cardBlocked}"/></h1>
        <button onclick="location.href = 'home.jsp'; " class="buttons">Back</button>
    </c:if>

    <c:if test="${pinError!=null}">
        <h1 style="color: #528ECC; text-align: center "><c:out value="${pinError}"/></h1>
        <button onclick="location.href = '/jsp/pinentry.jsp'; " class="buttons">Back</button>
    </c:if>

    <c:if test="${cardIsBlocked!=null}">
        <h1 style="color: #528ECC; text-align: center "><c:out value="${cardIsBlocked}"/></h1>
        <button onclick="location.href = 'home.jsp'; " class="buttons">Back</button>
    </c:if>

    <c:if test="${amountExceed!=null}">
        <h1 style="color: #528ECC; text-align: center "><c:out value="${amountExceed}"/></h1>
        <button onclick="location.href = '/jsp/withdrawals.jsp'; " class="buttons">Back</button>
    </c:if>
</div>
</body>

</html>