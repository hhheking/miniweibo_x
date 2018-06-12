<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2018/6/2
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="java.util.Map" %>
<%@ page import="pojo.User" %>
<%
    Map<String, Object> s= ActionContext.getContext().getSession();
    User user=(User)s.get("user");
%>
<html>
<head>
    <meta charset="utf-8">
    <title>mini微博——发现身边的好玩事</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!--jquery插件-->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- 包括所有已编译的插件 -->
    <script src="js/comment.js"></script>
    <script src="js/transpond.js"></script>
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
            $(".col-sm-2").click(function(){
                var span=$(this).children('span');
                $.ajax({
                    url:'judgeRelation',
                    data:{'user_id':$(this).children('input').val()},
                    dataType: 'json',
                    async:true,
                    success:function (data) {
                        span.html(data);
                    },
                    error:function (err) {
                        alert("fail");
                    }
                });
            })

        });
    </script>
    <style type="text/css">
        body{
            background: url("images/bg.jpg") center top;
        }
        #index_panel{
            position: fixed;
            top: 45px;
            left: 260px;
            width: 400px;
        }
        .face{
            background: url("images/face.png")  no-repeat;
            padding:1px 0 10px 25px;
            cursor: pointer;
            font-size: 15px;
        }
        .pic{
            background: url("images/pic.png")  no-repeat;
            margin-left: 10px;
            padding:1px 0 10px 25px;
            cursor: pointer;
            font-size: 15px;
        }
        .item_hot{
            padding: 10px 20px;
        }
        .item_num{
            color: #999;
        }
        .span{
            cursor: pointer;
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
                <li><a href="#"><span class="glyphicon glyphicon-facetime-video"></span>&nbsp;视频</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-eye-open"></span>&nbsp;发现</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-equalizer"></span>&nbsp;游戏</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-user"></span>&nbsp;<%=user.getUserNikename()%></a></li>
                <li><a href="#"><span class="glyphicon glyphicon-comment"></span>&nbsp;通知</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-cog"></span>&nbsp;设置</a></li>
                <li><script type="text/javascript">for(var i=1;i<=30;i++){document.write("&nbsp;");}</script></li>
            </ul>
        </div>
    </div>
</nav>
<!--左侧空白div-->
<div class="col-md-2 column">

</div>
<!--网站主题内容-->
<div class="col-md-8 column" style="margin-top: 70px;">
    <!--包含昵称和头像的div-->
    <div style="height: 280px;background-color: white;">
        <div class="row text-center inform" style="margin-top: 5px;padding: 50px;">
            <img src="<%=user.getIcon()%>" class="img-circle" width="110px;">
            <h4 style="font-weight: bold;"><%=user.getUserNikename()%></h4>
            <div class="col-sm-12" >
                <p>一句话介绍一下自己吧，让别人更了解你</p>
            </div>
        </div>
    </div>
    <!--选项卡div-->
    <div class="container-fluid">
        <div class="row clearfix" style="background-color: white;padding: 7px;">
            <div class="col-md-4 column" style="text-align: center;">
                <div>我的主页</div>
            </div>
            <div class="col-md-4 column" style="text-align: center;">
                <div>我的相册</div>
            </div>
            <div class="col-md-4 column" style="text-align: center;">
                <div>管理中心</div>
            </div>
        </div>
    </div>
    <!--主体div-->
    <div class="row clearfix" style="margin-top: 10px;">
        <div class="col-md-4 column">
            <!--显示关注、粉丝、微博-->
            <div class="col-sm-12" style="background-color: white;padding: 10px;">
                <div class="col-sm-4" style="text-align: center;">
                    <div><b>${idols}</b></div>
                    <div>关注</div>
                </div>
                <div class="col-sm-4" style="text-align: center;">
                    <div><b>${fans}</b></div>
                    <div>粉丝</div>
                </div>
                <div class="col-sm-4" style="text-align: center;">
                    <div><b>${mymessageList.size()}</b></div>
                    <div>微博</div>

                </div>
            </div>
            <!--资料完成度-->
            <div class="col-sm-12" style="background-color: white;margin-top: 5px;padding: 10px;">
                <h5>申请认证</h5>
                <!--横线-->
                <hr>
                <div>个人资料完成度<strong>30%</strong></div>
                <div class="progress" style="margin-top: 10px;">
                    <div class="progress-bar" role="progressbar" aria-valuenow="60"
                         aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
                        <span class="sr-only">40% 完成</span>
                    </div>
                </div>
                <!--横线-->
                <hr>
                <div style="text-align: center;">编辑个人资料></div>
            </div>
            <!--个人点赞记录-->
            <div class="col-sm-12" style="background-color: white;margin-top: 10px;padding: 10px;">
                <strong>赞</strong>
                <!--横线-->
                <hr>
                <div style="text-align: center;">查看更多></div>
            </div>
        </div>
        <!--右侧主体div-->
        <div class="col-md-8 column">
            <div class="row clearfix" style="background-color: white;">
                <div style="padding-top: 15px;padding-left: 10PX;">
                    <b>粉丝&nbsp;</b>${fans}
                </div>
                <hr>
                <s:iterator value="fanList" var="fa">
                    <!--粉丝或者关注的人-->
                    <div class="col-sm-12" style="padding-left: 5px;padding-bottom: 15px;">
                        <!--粉丝或者关注的人的头像-->
                        <div class="clearfix">
                            <div class="col-sm-2" style="text-align: center;padding-top: 10px;">
                                <a href="toUser?userid=${fa.getUserid()}"><img src="${fa.getImageurl()}" class="img-circle" width="60px;"></a>
                            </div>
                            <!--基本资料-->
                            <div class="col-sm-10">
                                <div class="row clearfix" style="margin-top: 5px;">
                                    <div class="col-sm-3" style="font-size: 15;"><b>${fa.getName()}</b></div>
                                    <div class="col-sm-5"></div>
                                    <div class="col-sm-2" style="text-align: center;padding:1px;background-color: white;cursor: pointer;border: 1px solid #ddd;margin-right: 5px;"><span style="color: gray;">${fa.getStatus()}</span><input value="${fa.getUserid()}" style="display: none" ></div>
                                    <div class="col-sm-1" style="text-align: center;padding:1px;background-color: white;cursor: pointer;border: 1px solid #ddd;"><span style="color: gray; ">私信</span></div>
                                </div>
                                <!--关注、粉丝、微博-->
                                <div style="margin-top: 8px;">
                                    <span>关注&nbsp;</span><a><span style="padding-right: 5px; border-right: 1px solid #808080;">${fa.getIdols()}</span></a>
                                    <span>粉丝&nbsp;</span><a><span style="padding-right: 5px; border-right: 1px solid #808080;">${fa.getFans()}</span></a>
                                    <span>微博&nbsp;</span><a><span style="padding-right: 5px;">${fa.getWeibos()}</span></a>
                                </div>
                                <!--地址-->
                                <div style="margin-top: 5px;">
                                    <span style="font-size: 13;color:#808080;">地址&nbsp;&nbsp;</span>
                                    <span style="font-size: 13;">上海市 松江区</span>
                                </div>
                                <!--简介-->
                                <div style="margin-top: 5px;">
                                    <span style="font-size: 13;color:#808080;">简介&nbsp;&nbsp;</span>
                                    <span style="font-size: 13;">广交天下好友！</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </s:iterator>

            </div>
        </div>
    </div>
</div>
<!--右侧空白div-->
<div class="col-md-2 column">

</div>
</body>
<!--标题搜索栏获得焦点后，显示下拉搜索列表-->
<div id="index_panel" style="display:none; ">
    <a href="#" class="list-group-item"><span class="badge" style="background-color: #FF4500;">爆</span>1.第一条热搜第一条热搜第一条热搜</a>
    <a href="#" class="list-group-item"><span class="badge" style="background-color: #FF0000;">热</span>2.第二条热搜第二条热搜第二条热搜</a>
    <a href="#" class="list-group-item"><span class="badge" style="background-color: #DC143C;">新</span>3.第三条热搜第三条热搜第三条热搜</a>
    <a href="#" class="list-group-item">4.第四条热搜第四条热搜第四条热搜</a>
    <a href="#" class="list-group-item">5.第五条热搜第五条热搜第五条热搜</a>
    <a href="#" class="list-group-item">6.第六条热搜第六条热搜第六条热搜</a>
    <a href="#" class="list-group-item">7.第七条热搜第七条热搜第七条热搜</a>
</div>
</html>
