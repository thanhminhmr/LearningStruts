<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="_header.jsp">
	<jsp:param name="title" value="Hello World!" />
</jsp:include>
</head>
<body class="hold-transition skin-blue fixed sidebar-mini">
	<div class="wrapper">
		<jsp:include page="_navbar.jsp" />
		<jsp:include page="_sidebar.jsp" />
		<div class="content-wrapper">
			<section class="content">
			</section>
		</div>
		<jsp:include page="_footer.jsp" />
	</div>
	<jsp:include page="_scripts.jsp" />
</body>
</html>