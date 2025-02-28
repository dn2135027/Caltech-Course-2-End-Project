<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ page import="com.zumba.bean.Participants" %>
<%@ page import="com.zumba.bean.Batch" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Participants Management</title>
</head>
<body>

<h2>Batch Details</h2>
<table border="1">
	<tr>
		<th>Batch Id</th>
		<th>TypeOfBatch</th>
		<th>Time</th>
	</tr>
	<core:forEach items="${sessionScope.batches}" var="batch">
		<tr>
			<td><core:out value="${batch.getBid()}"></core:out> </td>
			<td><core:out value="${batch.getTypeofbatch()}"></core:out> </td>
			<td><core:out value="${batch.getTime()}"></core:out> </td>
		</tr>
	</core:forEach>
</table>
<br/>

<h2>Existing Participants</h2>
<%-- Display participants in a table --%>
<table border="1">
    <thead>
        <tr>
            <th>Participant ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Bid</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <core:forEach items="${sessionScope.participants}" var="participant">
            <tr>
                <td>${participant.pid}</td>
                <td>${participant.name}</td>
                <td>${participant.age}</td>
                <td>${participant.bid}</td>
                <td>
                    <a href="AddParticipantsController?action=delete&pid=${participant.pid}">Delete</a>
                    <a href="AddParticipantsController?action=edit&pid=${participant.pid}">Edit</a>
                </td>
            </tr>
        </core:forEach>
    </tbody>
</table>
<%-- Check if we are editing an existing participant --%>
<% 
    Participants participant = (Participants) request.getAttribute("participant"); 
    boolean isEdit = participant != null;
%>

<h2><%= isEdit ? "Edit Participant" : "Add Participant" %></h2>

<!-- Form for adding/updating participants -->
<form action="AddParticipantsController" method="POST">
    <%-- If it's an edit, we set the form action URL to update the participant --%>
    <input type="hidden" name="action" value="<%= isEdit ? "update" : "add" %>"/>

   <label>Participant Id</label>
   <input type="number" name="pid" value="<%= isEdit ? participant.getPid() : "" %>" required/><br/>
    
    <label>Name</label>
    <input type="text" name="name" value="<%= isEdit ? participant.getName() : "" %>" required/><br/>
    
    <label>Age</label>
    <input type="number" name="age" value="<%= isEdit ? participant.getAge() : "" %>" required/><br/>
    
    <label>Batch</label>
	<select name="bid" required>
    <core:forEach items="${sessionScope.batches}" var="batch">
        <option value="${batch.getBid()}" 
                <core:if test="${batch.getBid() == participant.getBid()}">selected</core:if>>
            ${batch.getTypeofbatch()} - ${batch.getTime()} (Bid: ${batch.getBid()})
        </option>
    </core:forEach>
</select>
    
<br/>
    <input type="submit" value="<%= isEdit ? "Update Participant" : "Add Participant" %>"/>
    <input type="reset" value="Reset" />
</form>

<br/>
<a href="index.html">Back</a>
<br/>
<!-- Display any messages -->
<p><%= request.getParameter("msg") != null ? request.getParameter("msg") : "" %></p>
<br/><br/>
</body>
</html>
