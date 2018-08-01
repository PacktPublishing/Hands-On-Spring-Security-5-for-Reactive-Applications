<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring Security JAAS Sample</title>
</head>
<body>
<h2>
    Welcome : ${pageContext.request.userPrincipal.name}
</h2>
<br>
<p>Your Session id is: "${pageContext.request.session.id}"</p>
<br>
<a href="${pageContext.request.contextPath}/admin/moresecured">Admin Only page</a>
<br>
<br>
<div>
    <c:url value="/logout" var="logOutUrl"/>
    <form:form name="form" action="${logOutUrl}" method="post">
        <input type="submit" value="log Out" />
    </form:form>
</div>
</body>
</html>