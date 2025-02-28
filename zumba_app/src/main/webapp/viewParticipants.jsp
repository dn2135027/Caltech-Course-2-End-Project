<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>View All Participants Details</h2>

<table border="1">
	<tr>
		<th>Participant Id</th>
		<th>Name</th>
		<th>Age</th>
		<th>Batch Id</th>
	</tr>
	<core:forEach items="${sessionScope.participants}" var="participants">
		<tr>
			<td><core:out value="${participants.getPid()}"></core:out> </td>
			<td><core:out value="${participants.getName()}"></core:out> </td>
			<td><core:out value="${participants.getAge()}"></core:out> </td>
			<td><core:out value="${participants.getBid()}"></core:out> </td>
		</tr>
	</core:forEach>
</table>
<br/>
<a href="index.html">Back</a>
</body>
</html>