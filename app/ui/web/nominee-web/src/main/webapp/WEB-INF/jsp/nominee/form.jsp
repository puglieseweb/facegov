<%--
  Created by IntelliJ IDEA.
  User: facegov
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nominate a favorite member</title>
</head>
<body>
    <h1>Nominate a favorite Member</h1>
    <form:form modelAttribute="member">
        <div>First name: <form:input path="firstName"/></div>
        <div>Last name: <form:input path="lastName"/></div>
        <div><input type="submit" value="Submit"/></div>
    </form:form>
</body>
</html>