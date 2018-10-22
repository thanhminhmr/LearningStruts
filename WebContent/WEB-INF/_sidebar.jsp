<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<aside class="main-sidebar">
    <section class="sidebar">
        <ul class="sidebar-menu" data-widget="tree">
            <li>
                <a href="<s:url action='user_create' />">
                    <i class="fa fa-pencil-square-o"></i>
                    <span>Thêm người dùng</span>
                </a>
            </li>
            <li>
                <a href="<s:url action='user_list' />">
                    <i class="fa fa-archive"></i>
                    <span>Danh sách người dùng</span>
                </a>
            </li>
        </ul>
    </section>
</aside>