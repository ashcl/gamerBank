<%--
  Created by IntelliJ IDEA.
  User: clash
  Date: 1/4/17
  Time: 2:47 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.bytonsoftware.view.BankUtil" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="req" value="${pageContext.request}"/>
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}"/>
<html>
<head>
    <title>Game Bank</title>
</head>
<body>

<div id="balance"><%= BankUtil.obtainBalance()%><br>
</div>
<form name="hoursSubmitForm" action="${baseURL}/resources/bank/updateTime"
      onsubmit="return validateHours()">
    <input type="text" name="hours" autocomplete="off" required> <input type="submit" value="Add Time">
</form>
</body>
<script>
    function validateHours() {
        var hours = document.forms["hoursSubmitForm"]["hours"].value;
        if (typeof(hours) === 'number') {
            if (hours > 200) {
                alert("Yo, I know for a fact you didn't earn that many hours.\nTry a lower, realistic number, eh?");
                return false;
            } else if (hours < -200) {
                alert("Sheesh! That must have been one major screwup!\nHowever, I don't think that is quite fair.\nTry a number a bit closer to 0.");
                return false;
            }
        }else{
            alert("Alright, what happened?\nWhatever that was, it wasn't a number.\nTry again.");
            return false;
        }
    }
</script>
</html>
