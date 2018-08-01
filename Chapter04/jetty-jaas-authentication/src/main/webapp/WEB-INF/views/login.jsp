<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
    <title>Spring Security JAAS Sample</title>
</head>
<body>
<c:url value="/login" var="loginUrl"/>
<form:form name="form" action="${loginUrl}" method="post">

    <c:if test="${param.error != null}">
        Invalid username or password.
    </c:if>
    <table>
        <tr>
            <td><label for="username">Username</label></td>
            <td><input type="text" id="username" name="username" value="${username}"/></td>
        </tr>
        <tr>
            <td><label for="password">Password</label></td>
            <td><input type="password" id="password" name="password"/></td>
        </tr>
        <tr><td><input type="submit" value="SignIn" /></td></tr>
    </table>
</form:form>
</body>
</html>