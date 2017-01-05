<%@ page import="com.bytonsoftware.view.BankUtil" %><%--
  Created by IntelliJ IDEA.
  User: clash
  Date: 1/4/17
  Time: 2:47 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game Bank</title>
</head>
<body>

<div id="balance"><%= BankUtil.obtainBalance()%>
</div>
<form name="hoursSubmitForm" action="http://localhost:8080/gamerBankWeb/resources/bank/updateTime"
      onsubmit="return validateHours()">
    <input type="text" name="hours" autocomplete="off" required> <input type="submit" value="Add Time">
</form>
</body>
<script>
    function validateHours() {
        var hours = document.forms["hoursSubmitForm"]["hours"].value;

        if (hours > 0x7FFFFFFF) {
            alert("Yo, I know for a fact you didn't earn that many hours.\nTry a lower, realistic number, eh?");
            return false;
        } else if (hours < -0x80000000) {
            alert("Sheesh! That must have been one major screwup!\nHowever, I don't think that is quite fair.\nTry a number a bit closer to 0.");
            return false;
        }
    }
</script>
</html>
