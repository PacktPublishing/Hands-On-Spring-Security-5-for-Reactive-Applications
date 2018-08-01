<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring Security JAAS Sample</title>
</head>
<body>

<p>Your Session id is: "${pageContext.request.session.id}"</p>
<br>
<h1>This is more secured page</h1>
<p>This page can only be accessed by users having ADMIN role.</p>
<br>
<h2>
    Welcome : ${pageContext.request.userPrincipal.name} |
</h2>
<div>
    <c:url value="/logout" var="logOutUrl"/>
    <form:form name="form" action="${logOutUrl}" method="post">
        <input type="submit" value="log Out" />
    </form:form>
</div>

</body>
</html>