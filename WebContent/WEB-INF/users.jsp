<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
				<div class="box box-success box-solid">
					<div class="box-header with-border">
						<h3 class="box-title">Danh sách người dùng</h3>
					</div>
					<div class="box-body table-responsive no-padding">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th class="col-md-5 col-sm-5">Tên tài khoản</th>
									<th class="col-md-5 col-sm-5">Mật khẩu</th>
									<th class="col-md-2 col-sm-2">Hành động</th>
								</tr>
							</thead>
							<tbody id="users-refresh-body">
							</tbody>
						</table>
					</div>
					<div class="box-footer">
						<div class="pull-right">
							<button type="button" class="btn btn-default" id="users-refresh-btn">Tải lại</button>
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#users-create-modal">Thêm người dùng</button>
						</div>
					</div>
				</div>

				<form id="users-create-form" action="">
					<div class="modal fade" id="users-create-modal">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">Thêm người dùng</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-sm-12" id="users-create-alert"></div>
									</div>
									<div class="row">
										<div class="col-sm-6 col-xs-12">
											<div class="form-group">
												<label class="required" for="users-create-username">Tên đăng nhập</label>
												<input type="text" class="form-control" id="users-create-username" name="user.username" required />
											</div>
										</div>
										<div class="col-sm-6 col-xs-12">
											<div class="form-group">
												<label class="required" for="users-create-password">Mật khẩu</label>
												<input type="text" class="form-control" id="users-create-password" name="user.password" required />
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary" id="users-create-btn">Thêm</button>
								</div>
							</div>
						</div>
					</div>
				</form>

				<form id="users-delete-form" action="">
					<input type="hidden" id="users-delete-userId" name="user.userId" />
					<div class="modal fade" id="users-delete-modal">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">Xoá người dùng</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-sm-12" id="users-delete-alert"></div>
									</div>
									<div class="row">
										<div class="col-sm-12"><p>Xem xét kỹ yêu cầu xoá của bạn trước khi thực hiện.</p></div>
									</div>
									<div class="row">
										<div class="col-sm-6 col-xs-12">
											<div class="form-group">
												<label class="required" for="users-delete-username">Tên đăng nhập</label>
												<input type="text" class="form-control" id="users-delete-username" name="user.username" readonly />
											</div>
										</div>
										<div class="col-sm-6 col-xs-12">
											<div class="form-group">
												<label class="required" for="users-delete-password">Mật khẩu</label>
												<input type="text" class="form-control" id="users-delete-password" name="user.password" readonly />
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-danger" id="users-delete-btn">Xoá</button>
								</div>
							</div>
						</div>
					</div>
				</form>
				
				<form id="users-edit-form" action="">
					<input type="hidden" id="users-edit-userId" name="user.userId" />
					<div class="modal fade" id="users-edit-modal">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">Sửa người dùng</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-sm-12" id="users-edit-alert"></div>
									</div>
									<div class="row">
										<div class="col-sm-6 col-xs-12">
											<div class="form-group">
												<label class="required" for="users-edit-username">Tên đăng nhập</label>
												<input type="text" class="form-control" id="users-edit-username" name="user.username" required />
											</div>
										</div>
										<div class="col-sm-6 col-xs-12">
											<div class="form-group">
												<label class="required" for="users-edit-password">Mật khẩu</label>
												<input type="text" class="form-control" id="users-edit-password" name="user.password" required />
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary" id="users-edit-btn">Sửa</button>
								</div>
							</div>
						</div>
					</div>
				</form>
				
			</section>
		</div>
		<jsp:include page="_footer.jsp" />
	</div>
	<jsp:include page="_scripts.jsp" />
	<script src="/js/users.js" type="text/javascript"></script>
</body>
</html>