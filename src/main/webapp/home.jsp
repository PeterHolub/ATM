<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet"
          type="text/css">
    <script src="${pageContext.servletContext.contextPath}/js/home.js" type="text/javascript"></script>
    <title>Home</title>

</head>

<body onload="attachHandlers()">
<c:out value="${cardNumber}"/>
<div id="keypad" class="keybord">
    <h4 style="color: #528ECC; text-align: center ">Enter your card number</h4>
    <form action="${pageContext.servletContext.contextPath}/Home" method="post"  >

        <div>

            <input type="text" name="keyboardValues" id="atmkeybord" style="text-align:center" value required="" size="21" pattern="[0-9,-]*">

        </div>

        <div id="row1">

            <input type="button" name="number" value="1" id="_1" class="buttons">

            <input type="button" name="number" value="2" id="_2" class="buttons">

            <input type="button" name="number" value="3" id="_3" class="buttons">

        </div>

        <div id="row2">

            <input type="button" name="number" value="4" id="_4" class="buttons">

            <input type="button" name="number" value="5" id="_5" class="buttons">

            <input type="button" name="number" value="6" id="_6" class="buttons">

        </div>

        <div id="row3">

            <input type="button" name="number" value="7" id="_7" class="buttons">

            <input type="button" name="number" value="8" id="_8" class="buttons">

            <input type="button" name="number" value="9" id="_9" class="buttons">
        </div>

        <div id="row4">

            <input type="submit" name="ok" value="OK" id="OKButton" class="buttons">

            <input type="button" name="number" value="0" id="_0" class="buttons">

            <input type="button" name="number" value="Clean" id="CleanButton" class="buttons">

        </div>

    </form>
</div>
</body>

</html>