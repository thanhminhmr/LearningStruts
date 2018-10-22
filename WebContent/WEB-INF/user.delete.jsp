<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="_header.jsp">
	<jsp:param name="title" value="Xoá người dùng" />
</jsp:include>
</head>
<body class="hold-transition skin-blue fixed sidebar-mini">
	<div class="wrapper">
		<jsp:include page="_navbar.jsp" />
		<jsp:include page="_sidebar.jsp" />
		<div class="content-wrapper">
			<section class="content">
				<s:form action="user_delete">
					<s:hidden name="user.userId" />
					<div class="box box-danger box-solid">
						<div class="box-header with-border">
							<h3 class="box-title">Xoá người dùng</h3>
						</div>
						<div class="box-body">
							<div class="alert alert-danger alert-dismissible">
								<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
								<h4>
									<i class="icon fa fa-info"></i>
									Thông báo
								</h4>
								<s:if test="error.failed">Có lỗi khi xoá tài khoản.</s:if>
								<s:else>Xem xét kỹ yêu cầu xoá của bạn trước khi thực hiện.</s:else>
							</div>
							<div class="row">
								<div class="col-sm-6 col-xs-12">
									<div class="form-group">
										<label class="required" for="request_username">Tên đăng nhập</label>
										<s:textfield class="form-control" id="request_username" name="user.username" readonly="true" />
									</div>
								</div>
								<div class="col-sm-6 col-xs-12">
									<div class="form-group">
										<label class="required" for="request_password">Mật khẩu</label>
										<s:textfield class="form-control" id="request_password" name="user.password" readonly="true" />
									</div>
								</div>
							</div>
						</div>
						<div class="box-footer">
							<div class="pull-right">
								<s:submit class="btn btn-danger" value="Xoá người dùng" />
							</div>
						</div>
					</div>
				</s:form>
			</section>
		</div>
		<jsp:include page="_footer.jsp" />
	</div>
	<jsp:include page="_scripts.jsp" />
</body>
</html>