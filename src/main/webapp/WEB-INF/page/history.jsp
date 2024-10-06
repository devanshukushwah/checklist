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
		<h2 class="ui header">Task History</h2>

		<!-- Example Semantic UI Table -->
		<table class="ui celled table">
			<thead>
				<tr>
					<th>Created Date</th>
					<th>Completed</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty taskHistory}">
					<c:forEach var="thistory" items="${taskHistory}">
						<tr>
							<td><a href="./detail/${thistory.createdDate}">${thistory.createdDate}</a></td>
							<td>${thistory.completed} / ${thistory.totalRecords}</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty taskHistory}">
					<tr>
						<td colspan="4">No Task History found.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</body>
</html>