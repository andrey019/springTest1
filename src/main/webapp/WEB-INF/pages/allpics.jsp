<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>allpics</title>
</head>
<body>
<h2>All photos</h2>
<form:form method="POST" action="/test">
<table>
    <tr>
        <th>Photo ID</th>
        <th>Photo</th>
    </tr>
  <%--  <c:forEach items="${photos}" var="photoId"> --%>
        <tr>
            <td><form:checkboxes items="${photos}" path="photos"/></td>
            <td>${photoId}</td>
         <%--   <td>${photoMap.value}</td> --%>
        <%--    <td><img src="/photo/${photoId}" height="150"/></td> --%>
        </tr>
 <%--   </c:forEach> --%>
</table>
    <input type="submit" value="test" />
    </form:form>
<br />
<%-- <input type="button" value="Back" onclick="javascript:history.back()" /> --%>

<form action="/">
    <input type="submit" value="home page">
</form>
</body>
</body>
</html>
