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
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/hotSearch.js"></script>
    <script src="js/search.js"></script>
    <style type="text/css">
        body {
            background: url("images/bg.jpg") center top;
        }
        #index_panel{
            position: fixed;
            top: 45px;
            left: 260px;
            width: 400px;
        }
        #searchResult {
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
            <a href="https://weibo.com/?category=0" class="list-group-item">热门</a>
            <a href="https://weibo.com/?category=1760" class="list-group-item">头条</a>
            <a href="https://krcom.cn/" class="list-group-item">视频</a>
            <a href="https://weibo.com/?category=novelty" class="list-group-item">新鲜事</a>
            <a href="https://weibo.com/?category=99991" class="list-group-item">榜单</a>
            <a href="https://weibo.com/?category=10011" class="list-group-item">搞笑</a>
            <a href="https://weibo.com/?category=7" class="list-group-item">社会</a>
            <a href="https://weibo.com/?category=12" class="list-group-item">时尚</a>
            <a href="https://weibo.com/?category=10018" class="list-group-item">电影</a>
            <a href="https://weibo.com/?category=10007" class="list-group-item">美女</a>
            <a href="https://weibo.com/?category=3" class="list-group-item">体育</a>
            <a href="https://weibo.com/?category=10005" class="list-group-item">动漫</a>
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
                            <img  src="images/default1.jpg">
                        </div>
                        <div class="item">
                            <img  src="images/default.jpg">
                        </div>
                        <div class="item">
                            <img  src="images/default2.jpg">
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

                <!--关注好友的所有微博动态-->
                <s:iterator value="weibos" var="weibo">
                <div style="background-color: white;margin: 5px;">
                    <!--上层div-->
                    <div class="row clearfix" style="padding-bottom: 1.5rem;">
                        <div class="col-md-12 column">
                            <div class="col-md-2 column" style="padding-left: 25px;padding-top: 10px;">
                                <!--点击头像 进入用户空间-->
                                <a href="toUser?userid=${weibo.getId()}"><img src="${weibo.getImage()}" class="img-circle" width="60px;"></a>
                            </div>
                            <div class="col-md-10 column">
                                <h4 style="font-weight: bold;">${weibo.getNikename()}</h4>
                                <h6>${weibo.getTime()}分钟前 来自miniweibo.com</h6>
                                <p style="display: none">${weibo.getWeiboInfo()}</p>
                                    ${weibo.getWeiboInfo()}
                                <s:if test="#weibo.isTransponpd== \"true\"">
                                <s:iterator value="#weibo.tranList" var="tran">
                                <s:if test="#tran.message.messageType==\"Transpond\"">
                                    //<a href="toUser?userid=${tran.getUser().getUserId()}" ><b>@${tran.getUser().getUserNikename()}:</b></a>${tran.getMessage().getMessageInfo()}
                                </s:if>
                                <s:else>
                            </div>
                            <div class="col-md-12 column" style="background-color:#eee;max-height: 500px;padding-top: 1rem;padding-left: 0px;">
                                <div class="col-md-10 column pull-right" onclick="location.href='toMessage?messageID=${tran.getMessage().getMessageId()}'">
                                    <a href="toUser?userid=${tran.getUser().getUserId()}" ><b>@${tran.getUser().getUserNikename()}</b></a>
                                    <p>${tran.getMessage().getMessageInfo()}</p>
                                    <br>
                                    <div>
                                        <h6 class="pull-left">${tran.getMessage().getMessageTime()}</h6>
                                        <h6 class="pull-right"><span class="glyphicon glyphicon-link">${tran.getMessage().getMessageTranspondnum()}</span>&nbsp;
                                            <span class="glyphicon glyphicon-edit">${tran.getMessage().getMessageCommentnum()}</span>&nbsp;
                                            <span class="glyphicon glyphicon-thumbs-up">${tran.getMessage().getMessageAgreenum()}</span>
                                        </h6>
                                    </div>
                                </div>
                            </div>
                            </s:else>
                            </s:iterator>
                            </s:if>
                            <s:else>
                        </div>
                        </s:else>
                    </div>
                </div>
                <!--下层div-->
                <div class="row clearfix" style="border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;">
                    <div class="col-md-3 column" style="text-align: center;padding: 10px;border-right: 1px solid #ddd;">
                        <span class="glyphicon glyphicon-link" onclick="transponds(this)" data-toggle="modal" data-target="#TransPondModal">转发${weibo.getTranspond()}</span>
                    </div>
                    <div class="col-md-3 column" style="text-align: center;padding: 10px;border-right: 1px solid #ddd;">
                        <!--得到微博的收藏状态和收藏的次数-->
                        <s:if test="#weibo.collect_status == \"no\""><span class="glyphicon glyphicon-star-empty" onclick="collection(this)">收藏</span></s:if>
                        <s:else>
                            <span class="glyphicon glyphicon-star-empty" onclick="collection(this)" style="color: coral">已收藏</span>
                        </s:else>
                        <input value="${weibo.getMessid()}" style="display: none">
                    </div>
                    <div class="col-md-3 column" style="text-align: center;padding: 10px;border-right: 1px solid #ddd;">
                        <span id="showcomment" class="glyphicon glyphicon-edit" onclick="comment(this)">评价${weibo.getComment()}</span>
                        <input id="MessageId" value="${weibo.getMessid()}" style="display: none">
                    </div>
                    <div class="col-md-3 column" style="text-align: center;padding: 10px;">
                        <!--得到微博的赞同状态和赞同次数-->
                        <s:if test="#weibo.agree_status == \"no\""><span class="glyphicon glyphicon-thumbs-up" onclick="agree(this)">${weibo.getAgree()}</span></s:if>
                        <s:else>
                            <span class="glyphicon glyphicon-thumbs-up" onclick="agree(this)" style="color: coral">${weibo.getAgree()}</span>
                        </s:else>
                        <input value="${weibo.getMessid()}" style="display: none">
                    </div>
                </div>
                <!--点击评价显示出来的div-->
                <div id="comment" style="padding-left: 25px;background-color: #eee;display: none">
                    <!--分割线-->
                    <hr>
                    <div class="row clearfix">
                        <div class="col-md-1 column">
                            <!--点击头像 进入用户空间-->
                        </div>
                        <div class="col-md-11 column" style="padding-right: 35px;">
                            <form role="form" onsubmit='return false'>
                                <div class="form-group">
                                    <input type="text" class="form-control" style="height: 30px;">
                                </div>
                                <div class="form-group">
                                    <span class="face"></span>
                                    <span class="pic"></span>
                                    <button type="submit" class="btn btn-default pull-right" onclick="pinlun(this)" style="background-color: orange;height: 30px;">评论</button>
                                    <input value="${weibo.getMessid()}" style="display: none">
                                </div>
                            </form>
                            <!--分割线-->
                            <hr>
                        </div>
                        <!--评论-->
                        <!--自己发布的评论显示在这里-->
                        <div>
                        </div>
                    </div>
                </div>
            </div>
            </s:iterator>

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
                        <p class="list-group-item-text">狗狗和鹅之间的爱情，天造地设的一对，隔着屏幕都在秀恩爱</p>
                    </a>

                    <a href="#" class="list-group-item">
                        <h5 class="list-group-item-heading">世界奇闻</h5>
                        <p class="list-group-item-text">未解之谜：为何古墓中的长明灯永不熄灭</p>
                    </a>

                    <a href="#" class="list-group-item">
                        <h5 class="list-group-item-heading">值得一看的视频</h5>
                        <p class="list-group-item-text">新时代来啦！十九大报告中，习近平总书记指出：“经过长期努力，中国特色社会主义进入了新时代，这是我国发展新的历史方位。”</p>
                    </a>

                    <a href="#" class="list-group-item">
                        <h5 class="list-group-item-heading">好看的漫画在这里</h5>
                        <p class="list-group-item-text">为守护伙伴们的羁绊，鸣人不断修炼变强，在追求梦想的过程中不断突破自我，贯彻了自身的忍道，获得人们的认可。</p>
                    </a>

                    <a href="#" class="list-group-item">
                        <h5 class="list-group-item-heading">热门美食精选</h5>
                        <p class="list-group-item-text">被知乎票选为“为了吃都值得一去的城市”TOP1，你确定不要了解一下？</p>
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
        </div>
    <div id="searchResult" style="display: none">

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