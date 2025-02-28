<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ page import="com.zumba.bean.Batch" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Batch Management</title>
</head>
<body>
<h2>Existing Batches</h2>
<%-- Display batches in a table --%>
<table border="1">
    <thead>
        <tr>
            <th>Batch ID</th>
            <th>Type of Batch</th>
            <th>Batch Time</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <core:forEach items="${sessionScope.batches}" var="batch">
            <tr>
                <td>${batch.bid}</td>
                <td>${batch.typeofbatch}</td>
                <td>${batch.time}</td>
                <td>
                    <a href="AddBatchController?action=delete&bid=${batch.bid}">Delete</a>
                    <a href="AddBatchController?action=edit&bid=${batch.bid}">Edit</a>
                </td>
            </tr>
        </core:forEach>
    </tbody>
</table>
<%-- Check if we are editing an existing batch --%>
<% 
    Batch batch = (Batch) request.getAttribute("batch"); 
    boolean isEdit = batch != null;
%>

<h2><%= isEdit ? "Edit Batch" : "Add Batch" %></h2>

<!-- Form for adding/updating batch -->
<form action="AddBatchController" method="POST">
    <%-- If it's an edit, we set the form action URL to update the batch --%>
    <input type="hidden" name="action" value="<%= isEdit ? "update" : "add" %>"/>

    <label>BatchId</label>
    <input type="number" name="bid" value="<%= isEdit ? batch.getBid() : "" %>" 
           <%= isEdit ? "readonly" : "" %>/><br/>
    
    <label>TypeOfBatch</label>
    <select name="typeofbatch">
        <option value="Morning" <%= isEdit && batch.getTypeofbatch().equals("Morning") ? "selected" : "" %>>Morning</option>
        <option value="Evening" <%= isEdit && batch.getTypeofbatch().equals("Evening") ? "selected" : "" %>>Evening</option>
    </select><br/>
    
    <label>Batch Time</label>
    <select name="time">
        <option value="7am-8am" <%= isEdit && batch.getTime().equals("7am-8am") ? "selected" : "" %>>7am-8am</option>
        <option value="8am-9am" <%= isEdit && batch.getTime().equals("8am-9am") ? "selected" : "" %>>8am-9am</option>
        <option value="9am-10am" <%= isEdit && batch.getTime().equals("9am-10am") ? "selected" : "" %>>9am-10am</option>
        <option value="7pm-8pm" <%= isEdit && batch.getTime().equals("7pm-8pm") ? "selected" : "" %>>7pm-8pm</option>
        <option value="8pm-9pm" <%= isEdit && batch.getTime().equals("8pm-9pm") ? "selected" : "" %>>8pm-9pm</option>
        <option value="9pm-10pm" <%= isEdit && batch.getTime().equals("9pm-10pm") ? "selected" : "" %>>9pm-10pm</option>
    </select><br/>
    
    <%-- Button text depends on whether it's an update or add action --%>
    <input type="submit" value="<%= isEdit ? "Update Batch" : "Add Batch" %>"/>
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
