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
                    <i class="fa fa-list-alt"></i>
                    <span>Danh sách người dùng</span>
                </a>
            </li>
            <li>
                <a href="<s:url action='users' />">
                    <i class="fa fa-list"></i>
                    <span>Danh sách người dùng AJAX</span>
                </a>
            </li>
            <li>
                <a href="<s:url action='user_list_excel' />">
                    <i class="fa fa-file-excel-o"></i>
                    <span>Danh sách người dùng Excel</span>
                </a>
            </li>
        </ul>
    </section>
</aside>