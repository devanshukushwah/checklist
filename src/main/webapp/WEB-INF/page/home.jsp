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
					<div class="ui checkbox" action="./clickCheckbox" method="post">
						<input type="hidden" name="id" value="${item.id}" /> <input
							type="checkbox" name="task3"
							<c:if test="${item.status == true}">
                					checked
            				</c:if>>
						<label>${item.title}</label>
					</div>
				</div>
			</c:forEach>
		</div>

		<form class="ui action input" action='./add-checklist' method="post">
			<input type="text" placeholder="Enter task....">
			<button class="ui button">Add</button>
		</form>
	</div>

</body>
</html>