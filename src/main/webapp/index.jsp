<%--
  Created by IntelliJ IDEA.
  User: clash
  Date: 1/4/17
  Time: 2:47 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.bytonsoftware.view.BankUtil,
                                                                       org.json.JSONObject,
                                                                       com.bytonsoftware.business.TimeBank,
                                                                       org.json.JSONArray" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="req" value="${pageContext.request}"/>
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}"/>
<html>
<head>
    <title>Game Bank</title>
    <link rel="stylesheet" href="css/tableDesign.css">
</head>
<body>
<%
    TimeBank bank = new TimeBank();
    JSONArray log = bank.getLog();
    JSONObject logEntry;
%>
<div id="balance"><%= BankUtil.obtainBalance()%><br>
</div>
<form name="hoursSubmitForm" action="${baseURL}/resources/bank/updateTime"
      onsubmit="return validateHours()">
    <input type="text" name="hours" autocomplete="off" required> <input type="submit" value="Add Time">
</form>
<div id="logDisplay">
    <table>
        <thead>
        <tr>
            <th>Date</th>
            <th>Hours</th>
        </tr>
        </thead>

        <tbody>
        <%
            if (log != null) {
                for (int i = (log.length() - 1); i >= 0; i--) {
                    logEntry = (JSONObject) log.get(i);
        %>
        <tr>
            <td><%=logEntry.getString("date")%>
            </td>
            <td><%=logEntry.getString("hours")%>
            </td>
        </tr>
        <%
                }
            }%>
        </tbody>
    </table>
</div>
</body>
<script>
    function validateHours() {
        var hours = document.forms["hoursSubmitForm"]["hours"].value;
        var isNum = isANumber(document.forms["hoursSubmitForm"]["hours"]);
        hours = hours - 0;
        if (isNum) {
            if (hours > 200) {
                alert("Yo, I know for a fact you didn't earn that many hours.\nTry a lower, realistic number, eh?");
                return false;
            } else if (hours < -200) {
                alert("Sheesh! That must have been one major screwup!\nHowever, I don't think that is quite fair.\nTry a number a bit closer to 0.");
                return false;
            }
        } else {
            alert("Alright, what happened?\nWhatever that was, it wasn't a number.\nTry again.");
            return false;
        }
    }
    function isANumber(input) {
        var numbers = /^[-+]?[0-9.]+$/;
        if (input.value.match(numbers)) {
            return true;
        }
        else {
            return false;
        }
    }
</script>
</html>
