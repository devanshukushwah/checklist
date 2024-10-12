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
							<td>${thistory.completed}/ ${thistory.totalTasks}</td>
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

		<%
		    // Values for page number, total pages, and page size (could come from request or backend logic)
		    int pageNo = Integer.parseInt(request.getParameter("pageNo") != null ? request.getParameter("pageNo") : "1");
		    int totalPages = (int) request.getAttribute("totalPages"); // example total pages
		    int pageSize = (int) request.getAttribute("pageSize"); // example page size

		    // Determine the range of page numbers to display
		    int startPage = Math.max(2, pageNo - 2); // Display 2 previous pages
		    int endPage = Math.min(totalPages - 1, pageNo + 2); // Display 2 next pages
			%>

		<c:if test="${totalPages > 1}">
			<div class="ui pagination menu">
				<!-- Previous Button -->
				<a class="icon item" href="?pageNo=1&pageSize=<%=pageSize%>"> 1
				</a>

				<!-- Page Numbers -->
				<% for (int i = startPage; i <= endPage; i++) { %>
				<a class="item <%= (i == pageNo) ? "active" : "" %>"
					href="?pageNo=<%= i %>&pageSize=<%=pageSize%>"><%= i %></a>
				<% } %>

				<!-- Next Button -->
				<a class="icon item"
					href="?pageNo=<%=totalPages%>&pageSize=<%=pageSize%>"><%=totalPages %>
				</a>

			</div>
		</c:if>
	</div>

</body>
</html>