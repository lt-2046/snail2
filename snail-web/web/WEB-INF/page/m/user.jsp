<!DOCTYPE html>
<html lang="zh-CN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>


</head>

<body>
<div class="row">
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-user"></i> Datatable + Responsive</h2>

                <div class="box-icon">
                    <a href="#" class="btn btn-setting btn-round btn-default"><i class="glyphicon glyphicon-cog"></i></a>
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i class="glyphicon glyphicon-chevron-up"></i></a>
                    <a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
                </div>
            </div>
            <div class="box-content">
                <table id="table_local" class="table table-striped table-condensed table-bordered bootstrap-datatable datatable responsive">
                    <thead>
                    <tr>
                        <th>用户名</th>
                        <th>别名</th>
                        <th>电子邮件</th>
                        <th>电话</th>
                        <th>注册时间</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userList}" var="emp">
                    <tr>
                        <td>${emp.userName}</td>
                        <td class="center">${emp.aliasName}</td>
                        <td class="center">${emp.email}</td>
                        <td class="center">
                            <span class="label-success label label-default">${emp.phone}</span>
                        </td>
                        <td class="center">
                            <span class="label-success label label-default">${emp.createTime}</span>
                        </td>
                        <td class="center">
                            <span class="label-success label label-default">${emp.start}</span>
                        </td>
                        <td class="center">
                            <a class="btn btn-success" href="#">
                                <i class="glyphicon glyphicon-zoom-in icon-white"></i>
                                View
                            </a>
                            <a class="btn btn-info" href="#">
                                <i class="glyphicon glyphicon-edit icon-white"></i>
                                Edit
                            </a>
                            <a class="btn btn-danger" href="#">
                                <i class="glyphicon glyphicon-trash icon-white"></i>
                                Delete
                            </a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!--/span-->

</div><!--/row-->



</body>
</html>
