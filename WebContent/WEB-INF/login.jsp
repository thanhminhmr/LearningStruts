<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>

<head>
<jsp:include page="_header.jsp">
	<jsp:param name="title" value="Login" />
</jsp:include>
</head>

<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<b>Learning</b>
			Struts
		</div>
		<div class="login-box-body">
			<s:form action="login">
				<s:if test="error.error">
					<div class="alert alert-warning alert-dismissible">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						<s:if test="error.failed">Sai tên tài khoản hoặc mật khẩu.</s:if>
						<s:if test="error.empty">Tên tài khoản và mật khẩu không được bỏ trống.</s:if>
					</div>
				</s:if>
				<div class="form-group has-feedback">
					<s:textfield type="text" class="form-control" placeholder="Tên tài khoản" name="user.username" required="true" />
					<span class="form-control-feedback">
						<i class="fa fa-user"></i>
					</span>
				</div>
				<div class="form-group has-feedback">
					<s:textfield type="password" class="form-control" placeholder="Mật khẩu" name="user.password" required="true" value="" />
					<span class="form-control-feedback">
						<i class="fa fa-lock"></i>
					</span>
				</div>
				<s:submit type="submit" class="btn btn-primary btn-block btn-flat" value="Đăng nhập" />
			</s:form>
		</div>
	</div>
	<jsp:include page="_scripts.jsp" />
</body>

</html>