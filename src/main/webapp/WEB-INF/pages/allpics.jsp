<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>allpics</title>
</head>
<body>
<h2>All photos</h2>
<form:form method="POST" action="/allpics" modelAttribute="deletezip">
<table>
    <tr>
        <th>Photo ID</th>
        <th>Photo</th>
        <th>Checkbox</th>
    </tr>
    <c:forEach items="${deletezip.photosId}" var="photoId" varStatus="photoLoop">
        <tr>
            <td>${photoId}</td>
            <td><img src="/photo/${photoId}" height="150"/></td>
            <td><form:checkbox path="photosDelete" value="${photoId}"/></td>
        </tr>
    </c:forEach>
</table>


   <%-- <form:radiobutton path="deleteOrZip" value="d"/>delete <form:radiobutton path="deleteOrZip" value="z"/>zip --%>
    
    <input type="submit" class="button" name="delete" value="delete photos">
    <input type="submit" class="button" name="zip" value="zip photos">
    <input type="submit" value="test" />
    </form:form>
<br />

<form action="/">
    <input type="submit" value="home page">
</form>
</body>
</body>
</html>
