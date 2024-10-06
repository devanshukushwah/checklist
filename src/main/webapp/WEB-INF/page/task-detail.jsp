<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../common/commonHead.jsp"></jsp:include>
<title>Task Detail</title>
</head>
<body>
	<jsp:include page="../all-components/header.jsp"></jsp:include>

	<div class="ui container" style="margin-top: '50px'">
		<h2 class="ui header">Task Detail</h2>

		<!-- Example Semantic UI Table -->
		<table class="ui celled table">
			<thead>
				<tr>
					<th>Title</th>
					<th>Status</th>
					<th>Created Time</th>
					<th>Completed Time</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty taskList}">
					<c:forEach var="task" items="${taskList}">
						<tr>
							<td>${task.title}</td>
							<c:choose>
								<c:when test="${task.status == true}">
									<td>Completed</td>		
								</c:when>
								<c:otherwise>
									<td>Failed</td>
								</c:otherwise>
							</c:choose>
							<td>${task.createdDate}</td>
							<td>${task.changedDate}</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty taskList}">
					<tr>
						<td colspan="4">No Task found.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</body>
</html>