<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring Security JAAS Sample</title>
</head>
<body>
<div style="color:red;font-weight: bold;">
    You are not authorized to access this page.
</div>
<div style="color:green;margin-top:50px;font-weight: bold;">
    <a href="${pageContext.request.contextPath}/">Go Home</a>
</div>
</body>
</html>