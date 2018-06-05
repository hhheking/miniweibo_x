<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2018/5/17
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>mini微博——发现身边的好玩事</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!--jquery插件-->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- 包括所有已编译的插件 -->
    <script src="js/bootstrap.min.js"></script>
    <!-- 引入 Bootstrap -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="text/javascript">
        $(document).ready(function(){
            $("#index_sousuo").focus(function(){
                $("#index_panel").css("display","");
            });
            $("#index_sousuo").blur(function(){
                $("#index_panel").css("display","none");
            });
        });
    </script>
    <style type="text/css">
        #index_panel{
            position: fixed;
            top: 45px;
            left: 260px;
            width: 400px;
        }
        #myModal{
            text-align: center;
        }
    </style>
</head>
<body>
<!--导航栏 基于bootstrap框架-->
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">

    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand"><script type="text/javascript">for(var i=1;i<=30;i++){document.write("&nbsp;");}</script>mini微博</a>
        </div>
        <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="大家正在搜:东华最美毕业生" size="40" id="index_sousuo">
            </div>
            <button type="submit" class="btn btn-default">搜索</button>
        </form>
        <div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-home"></span>&nbsp;首页</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-eye-open"></span>&nbsp;发现</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-facetime-video"></span>&nbsp;视频</a></li>
                <li><a href="register"><span class="glyphicon glyphicon-user"></span>&nbsp;注册</a></li>
                <li><a data-toggle="modal" data-target="#myModal" id="myid"><span class="glyphicon glyphicon-log-in"></span>&nbsp;登录</a></li>
                <li><script type="text/javascript">for(var i=1;i<=30;i++){document.write("&nbsp;");}</script></li>
            </ul>
        </div>
    </div>
</nav>

<!--网站主题内容-->
<div class="container" style="margin-top: 60px;">
    <div class="row clearfix">
        <!--网站侧标导航栏-->
        <div class="col-md-2 column" style="text-align: center; font-size:18px;position: fixed;">
            <a href="#" class="list-group-item">热门</a>
            <a href="#" class="list-group-item">头条</a>
            <a href="#" class="list-group-item">视频</a>
            <a href="#" class="list-group-item">新鲜事</a>
            <a href="#" class="list-group-item">榜单</a>
            <a href="#" class="list-group-item">搞笑</a>
            <a href="#" class="list-group-item">社会</a>
            <a href="#" class="list-group-item">时尚</a>
            <a href="#" class="list-group-item">军事</a>
            <a href="#" class="list-group-item">美女</a>
            <a href="#" class="list-group-item">体育</a>
            <a href="#" class="list-group-item">动漫</a>
        </div>

        <div class="col-md-10 column" style="margin-left: 235px;">
            <div class="col-md-8 column">
                <!--幻灯片-->
                <div id="myCarousel" class="carousel slide" style="margin-bottom: 10px;">
                    <!-- 轮播（Carousel）指标 -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ol>
                    <!-- 轮播（Carousel）项目 -->
                    <div class="carousel-inner">
                        <div class="item active">
                            <img  src="images/default.jpg">
                        </div>
                        <div class="item">
                            <img  src="images/default.jpg">
                        </div>
                        <div class="item">
                            <img  src="images/default.jpg">
                        </div>
                    </div>
                    <!-- 轮播（Carousel）导航 -->
                    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>

                <div class="alert alert-success alert-dismissable" style="margin-bottom: 10px;text-align: center;">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <a href="#" class="alert-link">您有未读内容，点击查看</a>
                </div>

                <!--用户动态js脚本-->
                <script type="text/javascript">
                    for(var i=1;i<=20;i++){
                        document.write("<p style=\"background-color:#F5F5F5;\">网站主体内容网站主体内容网站主体内容网站主体内容网站主体内容网站主体内容网站主体内容网站主体内容网站主体内容网站主体内容网站主体内容网站主体内容网站主体内容网站主体内容网站主体内容网站主体内容</p>");
                    }
                </script>
            </div>

            <div class="col-md-4 column" style="padding:5px;">

                <form class="form-horizontal" role="form" action="loginUser" method="post" style="border: 1px;background-color:#F5F5F5;padding:25px;">

                    <div class="form-group">
                        <div class="col-sm-12">
                            <input type="text" class="form-control" id="nikename" placeholder="昵称/邮箱/手机号" name="user.userNikename">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12">
                            <input type="password" class="form-control" id="password" placeholder="密码" name="user.userPassword">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12">
                            <div class="checkbox">
                                <label><input type="checkbox">记住登录</label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12">
                            <button type="submit" class="btn btn-primary btn-sm btn-block">登录</button>
                        </div>
                    </div>

                </form>

                <div class="list-group" style="background-color:#F5F5F5;padding:5px;border: 1px;margin-top: 10px;">
                    <a href="#" class="list-group-item"><h4 class="list-group-item-heading">大家正在看...</h4></a>
                    <a href="#" class="list-group-item">
                        <h5 class="list-group-item-heading">萌宠新鲜事</h5>
                        <p class="list-group-item-text">萌宠新鲜事萌宠新鲜事萌宠新鲜事萌宠新鲜事萌宠新鲜事萌宠新鲜事</p>
                    </a>

                    <a href="#" class="list-group-item">
                        <h5 class="list-group-item-heading">世界奇闻</h5>
                        <p class="list-group-item-text">世界奇闻世界奇闻世界奇闻世界奇闻世界奇闻世界奇闻世界奇闻世界奇闻</p>
                    </a>

                    <a href="#" class="list-group-item">
                        <h5 class="list-group-item-heading">值得一看的视频</h5>
                        <p class="list-group-item-text">值得一看的视频值得一看的视频值得一看的视频值得一看的视频值得一看的视频</p>
                    </a>

                    <a href="#" class="list-group-item">
                        <h5 class="list-group-item-heading">好看的漫画在这里</h5>
                        <p class="list-group-item-text">好看的漫画在这里好看的漫画在这里好看的漫画在这里好看的漫画在这里</p>
                    </a>

                    <a href="#" class="list-group-item">
                        <h5 class="list-group-item-heading">联系我们</h5>
                        <p class="list-group-item-text">联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们</p>
                    </a>
                </div>

                <div class="panel panel-info" style="margin-top: 5px;">
                    <div class="panel-heading">
                        <h3 class="panel-title">联系我们</h3>
                    </div>
                    <div class="panel-body">Email:XXXXXXXXXX</div>
                </div>

            </div>
        </div>

        <!--标题搜索栏获得焦点后，显示下拉搜索列表-->
        <div id="index_panel" style="display:none; ">

            <a href="#" class="list-group-item"> <span class="badge" style="background-color: #FF4500;">爆</span>1.第一条热搜第一条热搜第一条热搜</a>
            <a href="#" class="list-group-item"><span class="badge" style="background-color: #FF0000;">热</span>2.第二条热搜第二条热搜第二条热搜</a>
            <a href="#" class="list-group-item"><span class="badge" style="background-color: #DC143C;">新</span>3.第三条热搜第三条热搜第三条热搜</a>
            <a href="#" class="list-group-item">4.第四条热搜第四条热搜第四条热搜</a>
            <a href="#" class="list-group-item">5.第五条热搜第五条热搜第五条热搜</a>
            <a href="#" class="list-group-item">6.第六条热搜第六条热搜第六条热搜</a>
            <a href="#" class="list-group-item">7.第七条热搜第七条热搜第七条热搜</a>

        </div>

    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 350px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">mini微博登录</h4>
            </div>
            <div class="modal-body" style="margin:20px;">
                <form class="form-horizontal" role="form" action="loginUser" method="post" style="border: 1px;">

                    <div class="form-group">
                        <div>
                            <input type="text" class="form-control" placeholder="昵称/邮箱/手机号" name="user.userNikename">
                        </div>
                    </div>

                    <div class="form-group">
                        <div>
                            <input type="password" class="form-control"  placeholder="密码" name="user.userPassword">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="checkbox" style="float: left">
                            <label><input type="checkbox">记住登录</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <div >
                            <button type="submit" class="btn btn-primary btn-sm btn-block">登录</button>
                        </div>
                    </div>

                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>