<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<header class="main-header">
	<a class="logo hidden-xs" data-toggle="push-menu" role="button">
		<span class="logo-mini">
			<i class="fa fa-bars"></i>
		</span>
		<span class="logo-lg">
			<b>Learning</b>
			Servlet
		</span>
	</a>
	<nav class="navbar navbar-static-top">
		<a class="sidebar-toggle visible-xs" data-toggle="push-menu" role="button">
			<span class="sr-only">Toggle navigation</span>
		</a>
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<li class="dropdown user user-menu">
					<a class="dropdown-toggle" data-toggle="dropdown">
						<s:property value="#session.user.username" />
					</a>
					<ul class="dropdown-menu">
						<li class="user-body">
							<div class="row">
								<span class="col-sm-4">Username:</span>
								<strong class="col-sm-8">
									<s:property value="#session.user.username" />
								</strong>
							</div>
						</li>
						<li class="user-footer">
							<div class="pull-right">
								<a href="<s:url action='logout' />" class="btn btn-primary btn-flat">Sign out</a>
							</div>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
</header>