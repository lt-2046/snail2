<!DOCTYPE html>
<html lang="zh-CN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <link href='${pageContext.request.contextPath}/bootstrap-treeview/bootstrap-treeview.min.css' rel='stylesheet'>
    <script src="${pageContext.request.contextPath}/bootstrap-treeview/bootstrap-treeview.min.js"></script>
    <script>
        var tree = [
            {
                text:"Parent 1",
                icon:"glyphicon glyphicon-home",
                selectable:false,
                nodes: [
                    {
                        text:"Child 1",
                        nodes: [
                            {
                                text:"Grandchild 1",
                                state: {
                                    checked:true
                                }
                            },
                            {
                                text:"Grandchild 2"
                            }
                        ]
                    },
                    {
                        text:"Child 2"
                    }
                ]
            },
            {
                text:"Parent 2"
            },
            {
                text:"Parent 3"
            },
            {
                text:"Parent 4"
            },
            {
                text:"Parent 5"
            }
        ];
        jQuery(document).ready(function () {
            $('#table_local tr').click(function () {

                if($(this).hasClass('selected')){
                    $(this).removeClass('selected');
                    $(this).removeClass('background: red');
                }else{
                    $(this).addClass('selected');
                    $(this).addClass('background: red');
                    $.ajax({
                        url: "${pageContext.request.contextPath}/m/findPermissionByRole.do",
                        //data: data,
                        type: "post",
                        dataType: "json",
                        success: function (result) {
                            console.log(result);
                            $('#tree').treeview({
                                data: result,         // data is not optional
                                levels: 2,
                                showCheckbox: true,   //是否显示复选框
                                highlightSelected: true
                            });
                        },
                        error: function (e) {
                            console.log(e, e.message);
                        }
                    });
                }

            });

            $('#tree').treeview({
                data: tree,         // data is not optional
                levels: 2,
                showCheckbox: true,   //是否显示复选框
                highlightSelected: true
            });
        });

    </script>
</head>

<body>
<div class="row">
    <div class="box col-md-6">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-user"></i>角色</h2>

                <div class="box-icon">
                    <a href="#" class="btn btn-setting btn-round btn-default"><i class="glyphicon glyphicon-cog"></i></a>
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i class="glyphicon glyphicon-chevron-up"></i></a>
                    <a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
                </div>
            </div>
            <div class="box-content">
                <div class="">
                    <a class="btn btn-success" href="#">
                        <i class="glyphicon glyphicon-plus icon-white "></i>
                        新增
                    </a>
                    <a class="btn btn-info" href="#">
                        <i class="glyphicon glyphicon-edit icon-white"></i>
                        修改
                    </a>
                    <a class="btn btn-danger" href="#">
                        <i class="glyphicon glyphicon-trash icon-white"></i>
                        删除
                    </a>
                </div>

                <table id="table_local" class="table table-striped table-condensed table-bordered bootstrap-datatable table-hover responsive">
                    <thead>
                    <tr>

                        <th>角色名称</th>
                        <th>状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${roleList}" var="emp">
                    <tr>

                        <td>${emp.roleName}</td>
                        <td>
                            ${emp.start}
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!--/span-->
    <div class="box col-md-6">
        <div class="box-header well" data-original-title="">
            <h2><i class="glyphicon glyphicon-user"></i>菜单</h2>

            <div class="box-icon">
                <a href="#" class="btn btn-setting btn-round btn-default"><i class="glyphicon glyphicon-cog"></i></a>
                <a href="#" class="btn btn-minimize btn-round btn-default"><i class="glyphicon glyphicon-chevron-up"></i></a>
                <a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
            </div>
        </div>
        <div id="tree"></div>
    </div>
</div><!--/row-->



</body>
</html>
