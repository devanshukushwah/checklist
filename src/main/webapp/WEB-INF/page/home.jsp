<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../common/commonHead.jsp"></jsp:include>
<title>Home</title>
</head>
<script>
	
</script>
<body>
	<jsp:include page="../all-components/header.jsp"></jsp:include>

	<div class="ui container">
		<h2 class="ui header">My List</h2>
		<div class="ui list">
			<c:forEach var="item" items="${taskList}">
				<div class="item">
					<form class="ui checkbox" action="./updateStatus" method="post">
						<input type="hidden" name="id" value="${item.id}" name="title" />
						<input type="checkbox" name="task3" onClick="this.form.submit()"
							<c:if test="${item.status == true}">
                					checked
                					disabled
            				</c:if>>
						<c:choose>
							<c:when test="${item.status == true}">
							<label><s>${item.title}</s></label>
							</c:when>
							<c:otherwise>
							<label>${item.title}</label>
							</c:otherwise>
						</c:choose>
					</form>
				</div>
			</c:forEach>
		</div>

		<form class="ui action input" action='./add' method="post">
			<input type="text" placeholder="Enter task...." name="title" required>
			<button class="ui button">Add</button>
		</form>
	</div>

</body>
</html>