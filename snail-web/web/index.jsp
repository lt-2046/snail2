<!DOCTYPE html>
<html lang="zh-CN">
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
    <!--
        ===
        This comment should NOT be removed.

        Charisma v2.0.0

        Copyright 2012-2014 Muhammad Usman
        Licensed under the Apache License v2.0
        http://www.apache.org/licenses/LICENSE-2.0

        http://usman.it
        http://twitter.com/halalit_usman
        ===
    -->
    <meta content="text/html" http-equiv="CONTENT-TYPE" charset="UTF-8">
    <title>Free HTML5 Bootstrap Admin Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
    <meta name="author" content="Muhammad Usman">

    <!-- The styles -->
    <link id="bs-css" href="${pageContext.request.contextPath}/charisma/css/bootstrap-cerulean.min.css"
          rel="stylesheet">

    <link href="${pageContext.request.contextPath}/charisma/css/charisma-app.css" rel="stylesheet">
    <link href='${pageContext.request.contextPath}/charisma/css/noty_theme_default.css' rel='stylesheet'>
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/charisma/bower_components/jquery/jquery.min.js"></script>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <style>
        /*noty提示相关*/
        .noty {
            position: fixed;
            right: 10px;
            bottom: 10px;
            width: 400px;
            list-style: none;
            overflow: hidden;
        }

        .noty > li, .noty .noty_close {
            margin-top: 5px;
        }

    </style>
    <!-- The fav icon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/charisma/img/favicon.ico">
    <script>
        var _notyContent = {
            'text': '',
            'type': '',
            'timeout': 10000,
            'layout': 'bottomRight',
            'animateOpen': {"opacity": "show"},
            'closeButton': 'true'
        };

        //显示提示消息
        //通常只需要前面2个参数就足够
        //type参数：success information warning error
        function notyAlert(title, desc, type, timeout, icon) {
            title = title || '';
            desc = desc || '';
            type = type || 'warning';
            var text = '<strong><i class="glyphicon ';
            if (icon != undefined) {
                text += icon;
            } else if (type == 'success') {
                text += 'glyphicon-ok-sign';
            } else if (type == 'information') {
                text += 'glyphicon-info-sign';
            } else if (type == 'warning') {
                text += 'glyphicon-exclamation-sign';
            } else if (type == 'error') {
                text += 'glyphicon-remove-sign';
            }
            //获取提示HTML
            _notyContent.text = text + '"></i> ' + title + '</strong><br /><p>' + desc + '</p>';
            //获取提示类型
            _notyContent.type = type;
            //获取响应时间
            _notyContent.timeout = timeout || 10000;
            noty(_notyContent);
        }
        ;
        jQuery(document).ready(function () {
            //回车事件绑定
            document.onkeydown = function (event) {
                var e = event || window.event || arguments.callee.caller.arguments[0];
                if (e && e.keyCode == 13) {
                    $('#login').click();
                }
            };
            //登录操作
            $('#login').click(function () {

                var username = $('#username').val();
                var password = $('#password').val();
                var rememberMe = $("#rememberMe").is(':checked');
                if (username == '') {
                    notyAlert('失败', '请输入用户名', 'error');
                    return false;
                }
                if (password == '') {
                    notyAlert('失败', '请输入密码', 'error');
                    return false;
                }
//                var pswd = MD5(username +"#" + password),
                data = {password: password, username: username, rememberMe: rememberMe};
                $.ajax({
                    url: "${pageContext.request.contextPath}/m/login.do",
                    data: data,
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        //  alert(result);
                        if (result && result.status != 200) {
                            $('.password').val('');
                            return;
                        } else {
                            setTimeout(function () {
                                //登录返回
                                window.location.href = result.back_url || "${pageContext.request.contextPath}/";
                            }, 1000)
                        }
                    },
                    error: function (e) {
                        console.log(e, e.message);
                    }
                });
            });
        });
    </script>
</head>

<body>
<div class="ch-container">
    <div class="row">

        <div class="row">
            <div class="col-md-12 center login-header">
                <h2>欢迎登陆</h2>
            </div>
            <!--/span-->
        </div><!--/row-->

        <div class="row">
            <div class="well col-md-5 center login-box">
                <div class="alert alert-info">
                    Please login with your Username and Password.
                </div>
                <%--<form class="form-horizontal" action="" method="post">--%>
                <fieldset>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
                        <input id="username" name="username" type="text" class="form-control" placeholder="Username">
                    </div>
                    <div class="clearfix"></div>
                    <br>

                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
                        <input id="password" name="password" type="password" class="form-control"
                               placeholder="Password">
                    </div>
                    <div class="clearfix"></div>

                    <div class="input-prepend">
                        <label class="remember" for="remember"><input type="checkbox" name="rememberMe" id="remember">
                            Remember me</label>
                    </div>
                    <div class="clearfix"></div>

                    <p class="center col-md-5">
                        <button type="submit" id="login" class="btn btn-primary">Login</button>
                    </p>
                </fieldset>
                <%--</form>--%>
            </div>
            <!--/span-->
        </div><!--/row-->
    </div><!--/fluid-row-->
</div>
<ul class="noty noty_cont noty_layout_bottomRight"></ul>
<%--<!-- notification plugin -->--%>
<script src="${pageContext.request.contextPath}/charisma/js/jquery.noty.js"></script>
</body>
</html>
