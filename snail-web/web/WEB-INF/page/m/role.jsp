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
                };

            });
            //手工控制模态框的展示
            $('#newRole').click(function (){
                $('#roleNew').modal('show');
            });
            //调整模态框的展示位置，保持居中展示
            $('#roleNew').on('show.bs.modal',function(e){
                var $this = $(this);
                var dialog = $this.find('.modal-dialog');
                //此种方式，在使用动画第一次显示时有问题
                //解决方案，去掉动画fade样式
                var top = ($(window).height() - dialog.height()) / 4;
                dialog.css({
                    marginTop:top
                });
            });
            //树形结构控件
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

            </div>
            <div class="box-content">
                <div class="">
                    <a class="btn btn-success" href="#" id="newRole">
                        <i class="glyphicon glyphicon-plus icon-white" data-toggle="modal" data-target="#roleNew"></i>
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
        </div>
        <div id="tree"></div>
    </div>
</div><!--/row-->

<!-- Modal -->
<div class="modal fade" id="roleNew" tabindex="-1" role="dialog"  data-backdrop="static" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增角色</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="newRoleName">角色名称</label>
                        <input type="text" class="form-control" id="newRoleName" placeholder="角色名称">
                    </div>
                    <div class="form-group">
                        <label for="mark">描述</label>
                        <textarea class="form-control" rows="3" id="mark" placeholder="描述"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
