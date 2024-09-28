<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../common/commonHead.jsp"></jsp:include>
<title>Home</title>
</head>
<body>
	<jsp:include page="../all-components/header.jsp"></jsp:include>


	<div class="ui container">
		<div class="ui action input">
			<input type="text" placeholder="Enter task....">
			<button class="ui button">Add</button>
		</div>
	</div>
</body>
</html>