<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2018/6/17
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="java.util.Map" %>
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
    <link rel="stylesheet" type="text/css" href="css/dialog.css">
    <script src="js/zepto.min.js"></script>
    <script type="text/javascript" src="js/dialog.min.js"></script>
    <script src="js/hotSearch.js"></script>
    <script src="js/search.js"></script>
    <script src="js/remind.js"></script>
    <script src="js/message.js"></script>
    <script src="js/navbar.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#mycontent").keyup(function(){
                //判断输入的字符串长度
                var content_len = $("#mycontent").val().length;
                $(".tips").text("已经输入"+content_len+"个字");
                if(content_len==0){
                    // alert(content);
                    $(".tips").text("");
                    $("#send").addClass("disabled");
                    return false;
                }else{
                    $("#send").removeClass("disabled");
                }
            });
            $("#send").click(function () {
                $.ajax({
                    type:'Post',
                    url:'sendMessage',
                    data:{'messageInfo':$("#mycontent").val()},
                    dataType: 'json',
                    async:true,
                    success:function(data){
                        //成功发布微博,消除文本框中的内容
                        var info=$("#mycontent").val();
                        $("#mycontent").val("");
                        popup({type:'success',msg:"发布微博成功",delay:1000,callBack:function(){;}});
                        var myweibo=" <div style=\"background-color: white;margin: 5px;\">\n" +
                            "                    <!--上层div-->\n" +
                            "                    <div class=\"row clearfix\" style=\"padding-bottom: 1.5rem;\">\n" +
                            "                        <div class=\"col-md-12 column\">\n" +
                            "                            <div class=\"col-md-2 column\" style=\"padding-left: 25px;padding-top: 10px;\">\n" +
                            "                                <!--点击头像 进入用户空间-->\n" +
                            "                                <a href=\"toUser?userid=<%=user.getUserId()%>\"><img src=\"<%=user.getIcon()%>\" class=\"img-circle\" width=\"60px;\"></a>\n" +
                            "                            </div>\n" +
                            "                            <div class=\"col-md-10 column\">\n" +
                            "                                <h4 style=\"font-weight: bold;\"><%=user.getUserNikename()%></h4>\n" +
                            "                                <h6>0分钟前 来自miniweibo.com</h6>\n" +
                            "                                <p>"+info+"</p>\n" +
                            "                            </div>\n" +
                            "                    </div>\n" +
                            "                </div>\n" +
                            "                <!--下层div-->\n" +
                            "                <div class=\"row clearfix\" style=\"border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;\">\n" +
                            "                    <div class=\"col-md-3 column\" style=\"text-align: center;padding: 10px;border-right: 1px solid #ddd;\">\n" +
                            "                        <span class=\"glyphicon glyphicon-link\" onclick=\"transponds(this)\" data-toggle=\"modal\" data-target=\"#TransPondModal\">转发0</span>\n" +
                            "                    </div>\n" +
                            "                    <div class=\"col-md-3 column\" style=\"text-align: center;padding: 10px;border-right: 1px solid #ddd;\">\n" +
                            "                        <!--得到微博的收藏状态和收藏的次数-->\n" +
                            "                        <span class=\"glyphicon glyphicon-star-empty\" onclick=\"collection(this)\">收藏</span>\n" +
                            "                        <input value=\""+data+"\" style=\"display: none\">\n" +
                            "                        <input value=\"<%=user.getUserId()%>\" style=\"display: none;\">\n" +
                            "                    </div>\n" +
                            "                    <div class=\"col-md-3 column\" style=\"text-align: center;padding: 10px;border-right: 1px solid #ddd;\">\n" +
                            "                        <span id=\"showcomment\" class=\"glyphicon glyphicon-edit\" onclick=\"comment(this)\">评价0</span>\n" +
                            "                        <input id=\"MessageId\" value=\""+data+"\" style=\"display: none\">\n" +
                            "                    </div>\n" +
                            "                    <div class=\"col-md-3 column\" style=\"text-align: center;padding: 10px;\">\n" +
                            "                        <!--得到微博的赞同状态和赞同次数-->\n" +
                            "                        <span class=\"glyphicon glyphicon-thumbs-up\" onclick=\"agree(this)\">0</span>\n" +
                            "                        <input value=\""+data+"\" style=\"display: none\">\n" +
                            "                        <input value=\"<%=user.getUserId()%>\" style=\"display: none;\">\n" +
                            "                    </div>\n" +
                            "                </div>\n" +
                            "                <!--点击评价显示出来的div-->\n" +
                            "                <div id=\"comment\" style=\"padding-left: 25px;background-color: #eee;display: none\">\n" +
                            "                    <!--分割线-->\n" +
                            "                    <hr>\n" +
                            "                    <div class=\"row clearfix\">\n" +
                            "                        <div class=\"col-md-1 column\">\n" +
                            "                            <!--点击头像 进入用户空间-->\n" +
                            "                            <img src=\"<%=user.getIcon()%>\" width=\"35px;\">\n" +
                            "                        </div>\n" +
                            "                        <div class=\"col-md-11 column\" style=\"padding-right: 35px;\">\n" +
                            "                            <form role=\"form\" onsubmit='return false'>\n" +
                            "                                <div class=\"form-group\">\n" +
                            "                                    <input type=\"text\" class=\"form-control\" style=\"height: 30px;\">\n" +
                            "                                </div>\n" +
                            "                                <div class=\"form-group\">\n" +
                            "                                    <span class=\"face\"></span>\n" +
                            "                                    <span class=\"pic\"></span>\n" +
                            "                                    <button type=\"submit\" class=\"btn btn-default pull-right\" onclick=\"pinlun(this)\" style=\"background-color: orange;height: 30px;\">评论</button>\n" +
                            "                                    <input value=\""+data+"\" style=\"display: none\">\n" +
                            "                                    <input id=\"sessionuserid\" value=\"<%=user.getUserId()%>\" style=\"display: none\">\n" +
                            "                                    <input id=\"sessionusername\" value=\"<%=user.getUserNikename()%>\" style=\"display: none\">\n" +
                            "                                </div>\n" +
                            "                            </form>\n" +
                            "                            <!--分割线-->\n" +
                            "                            <hr>\n" +
                            "                        </div>\n" +
                            "                        <!--评论-->\n" +
                            "                        <!--自己发布的评论显示在这里-->\n" +
                            "                        <div>\n" +
                            "                        </div>\n" +
                            "                    </div>\n" +
                            "                </div>\n" +
                            "            </div>";
                        $("#myWeibo").prepend(myweibo);
                        $("#mymessagenum").text((parseInt($("#mymessagenum").text())+1));
                    },
                    error: function (err) {
                        //成功发布微博
                        alert("微博发布失败!");
                    }
                });
            });
        });
    </script>
    <style type="text/css">
        body {
            background: url("images/bg.jpg") center top;
        }
        #index_panel {
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
        .face {
            background: url("images/face.png") no-repeat;
            padding: 1px 0 10px 25px;
            cursor: pointer;
            font-size: 15px;
        }
        .pic {
            background: url("images/pic.png") no-repeat;
            margin-left: 10px;
            padding: 1px 0 10px 25px;
            cursor: pointer;
            font-size: 15px;
        }
        .item_hot {
            padding: 10px 20px;
        }
        .item_num {
            color: #999;
        }
        .span {
            cursor: pointer;
        }
    </style>
</head>
<body>
<!--导航栏 基于bootstrap框架-->
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">
                <script type="text/javascript">for (var i = 1; i <= 30; i++) {
                    document.write("&nbsp;");
                }</script>
                mini微博</a>
        </div>
        <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="大家正在搜:东华最美毕业生" size="40" id="index_sousuo">
            </div>
            <button type="submit" class="btn btn-default">搜索</button>
        </form>
        <div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="tohotUser" style="color: orange"><span class="glyphicon glyphicon-home"></span>&nbsp;首页</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-facetime-video"></span>&nbsp;视频</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-eye-open"></span>&nbsp;发现</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-equalizer"></span>&nbsp;游戏</a></li>
                <li><a href="personspaceUser"><span class="glyphicon glyphicon-user"></span>&nbsp;<%=user.getUserNikename()%></a></li>
                <li class="dropdown" style="border-left: 1px solid #ddd">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-comment" style="font-size: 20"></span>
                        <span id="tip" class="badge" style="background-color: orange;position: absolute;top: 6px;right: 0px;font-size: 6"></span>
                    </a>
                    <ul class="dropdown-menu" style="font-size: 12;min-width:100%;">
                        <li><a href="jump?param=transpond">转发&nbsp&nbsp<span id="tip1" class="badge" style="background-color: grey;font-size: 5"></span></a></li>
                        <li class="divider"></li>
                        <li><a href="jump?param=comment">评论&nbsp&nbsp<span id="tip2" class="badge" style="background-color: grey;font-size: 5"></span></a></li>
                        <li class="divider"></li>
                        <li><a href="jump?param =agree">点赞&nbsp&nbsp<span id="tip3" class="badge" style="background-color: grey;font-size: 5"></span></a></li>
                        <li class="divider"></li>
                        <li><a href="jump?param =letter">私信&nbsp&nbsp<span id="tip4" class="badge" style="background-color: grey;font-size: 5"></span></a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-cog"></span>
                    </a>
                    <ul class="dropdown-menu" style="font-size: 12;min-width:100%;">
                        <li><a href="#">账号设置</a></li>
                        <li class="divider"></li>
                        <li><a href="toUserinfo">信息完善</a></li>
                        <li class="divider"></li>
                        <li><a href="#">隐私设置</a></li>
                        <li class="divider"></li>
                        <li><a href="exitUser">退出</a></li>
                    </ul>
                </li>
                <li><script type="text/javascript">for(var i=1;i<=30;i++){document.write("&nbsp;");}</script></li>
            </ul>
        </div>
    </div>
</nav>

<!--网站主题内容-->
<div class="container" style="margin-top: 60px;">
    <div class="row clearfix">
        <!--网站侧标导航栏-->
        <div  style="text-align: left; font-size:14px;position: fixed;margin-left: 120px;width: 160px;">
            <a href="tohotUser" class="list-group-item"><b>首页</b></a>
            <a href="toMycollectUser" class="list-group-item">我的收藏</a>
            <a href="toMyagreeUser" class="list-group-item">我的赞</a>
            <a href="#" class="list-group-item">热门微博</a>
            <a href="#" class="list-group-item">热门视频</a>
            <a href="#" class="list-group-item">好友圈</a>
            <a href="#" class="list-group-item">特别关注</a>
            <a href="#" class="list-group-item">V+微博</a>
            <a href="#" class="list-group-item">作家</a>
            <a href="#" class="list-group-item">时尚</a>
            <a href="#" class="list-group-item">搞笑</a>
            <a href="#" class="list-group-item">电影</a>
            <a href="#" class="list-group-item">求职</a>
        </div>

        <div class="col-md-10 column" style="margin-left: 260px;">
            <div class="col-md-8 column">
                <!--发微博消息输入框-->
                <div style="margin-top: 5px;padding: 15px;background-color: white;">
                    <form role="form" onsubmit='return false'>
                        <div class="form-group">
                            <span class="pull-left">有什么新鲜事想告诉大家</span>
                            <span class="tips pull-right"></span><p>？</p>
                        </div>
                        <div class="form-group">
                            <textarea class="form-control" rows="4" id="mycontent" name="messageInfo"></textarea>
                        </div>
                        <div class="form-group">
                            <span class="face">表情</span>
                            <span class="pic">图片</span>
                            <button type="submit" id="send" class="btn btn-default pull-right disabled" style="background-color: orange;">发布</button>
                        </div>
                    </form>
                </div>
                <!--tab选项卡-->
                <nav class="navbar naybar-default" role="navigation" style="background-color: white;">
                    <div class="container-fluid">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="tohotUser">热门</a></li>
                            <li><a href="tohomeUser">关注</a></li>
                            <li><a href="#">图片</a></li>
                            <li><a href="#">文章</a></li>
                        </ul>
                    </div>
                </nav>
                <!--未读消息提示,后续可以增加-->
                <div>

                </div>
                <!--在页面中动态添加一个div,这里显示用户发布的微博-->
                <div id="myWeibo">
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
                            <input value="<%=user.getUserId()%>" style="display: none;">
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
                            <input value="<%=user.getUserId()%>" style="display: none;">
                        </div>
                    </div>
                    <!--点击评价显示出来的div-->
                    <div id="comment" style="padding-left: 25px;background-color: #eee;display: none">
                        <!--分割线-->
                        <hr>
                        <div class="row clearfix">
                            <div class="col-md-1 column">
                                <!--点击头像 进入用户空间-->
                                <img src="<%=user.getIcon()%>" width="35px;">
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
                                        <input id="sessionuserid" value="<%=user.getUserId()%>" style="display: none">
                                        <input id="sessionusername" value="<%=user.getUserNikename()%>" style="display: none">
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

        <div class="col-md-4 column">
            <!--个人信息概览-->
            <div class="row text-center inform" style="margin-top: 5px;padding: 15px;background-color: white;">
                <a href="personspaceUser"><img src="<%=user.getIcon()%>" class="img-circle" width="90px;" id="pic"></a>
                <h4 style="font-weight: bold;"><%=user.getUserNikename()%></h4>
                <div class="col-sm-12" >
                    <a href="idolUser"><div class="col-sm-4 col-xs-4">
                        <div>${idols}</div>
                        <div class="sort">关注</div>
                    </div></a>
                    <a href="fanUser">
                        <div class="col-sm-4 col-xs-4">
                            <div>${fans}</div>
                            <div class="sort">粉丝</div>
                        </div>
                    </a>
                    <a href="personspaceUser">
                        <div class="col-sm-4 col-xs-4">
                            <div id="mymessagenum">${mymessageList.size()}</div>
                            <div class="sort">微博</div>
                        </div>
                    </a>
                </div>
            </div>
            <!--大家正在看-->
            <div class="row part_hot" style="margin-top: 5px;padding: 15px;background-color: white;">
                <div class="col-sm-12">
                    <span class="pull-left" style="padding: 10px;font-size:16px;font-weight: bold;">热门话题</span>
                    <span class="pull-right" style="padding: 10px;">换话题</span>
                </div>
                <hr style="margin: 0;padding: 0;width: 100%">

                <div class="col-sm-12 item_hot" >
                    <span class="pull-left">#何张出道#</span>
                    <span class="pull-right item_num">34.6亿</span>
                </div>

                <div class="col-sm-12 item_hot" >
                    <span class="pull-left">#男生夺冠#</span>
                    <span class="pull-right item_num">2.6亿</span>
                </div>

                <div class="col-sm-12 item_hot" >
                    <span class="pull-left">#rng冠军#</span>
                    <span class="pull-right item_num">10.4亿</span>
                </div>

                <div class="col-sm-12 item_hot" >
                    <span class="pull-left">#李志谦回归#</span>
                    <span class="pull-right item_num">1.5亿</span>
                </div>

                <div class="col-sm-12 item_hot" >
                    <span class="pull-left">#突然开心#</span>
                    <span class="pull-right item_num">1.1亿</span>
                </div>
                <hr style="margin: 0;padding: 0;width: 100%">

                <div class="col-sm-12 text-center" style="padding: 10px"><a href="#">查看更多</a></div>

            </div>

        </div>
    </div>
</div>
</div>
<!--标题搜索栏获得焦点后，显示下拉搜索列表-->
<div id="index_panel" style="display:none; ">
    <a href="#" class="list-group-item"> <span class="badge" style="background-color: #FF4500;">爆</span>1.第一条热搜第一条热搜第一条热搜</a>
    <a href="#" class="list-group-item"><span class="badge" style="background-color: #FF0000;">热</span>2.第二条热搜第二条热搜第二条热搜</a>
    <a href="#" class="list-group-item"><span class="badge" style="background-color: #DC143C;">新</span>3.第三条热搜第三条热搜第三条热搜</a>
</div>
<div id="searchResult" style="display: none">

</div>
<!---点击转发弹出模态框->
<!-- 模态框（Modal） -->
<div class="modal fade" id="TransPondModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 480px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">转发微博</h4>
            </div>
            <div class="modal-body" style="margin-top: 5px;margin-left: 10px;margin-right: 10px;">
                <form class="form-horizontal" role="form" action="###" method="post" style="border: 1px;" onsubmit='return false'>
                    <div class="form-group" style="background-color: #F5F5F5;">
                        <div style="padding: 10px;">
                            微博内容:<p id="transpond_info">#</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <textarea class="form-control" rows="3" placeholder="请输入转发的理由......" id="transpond_reason"></textarea>
                    </div>
                    <div class="form-group">
                        <div class="checkbox" style="float: left">
                            <span class="face"></span>
                            <span class="pic"></span>
                            <label style="margin-left: 10px;"><input type="checkbox">同时评论给</label>
                            <span id="transpond_username">#</span>
                            <input id="messID" style="display: none">
                        </div>
                        <div style="float: right">
                            <button id="transpondweibo" class="btn btn-primary btn-sm btn-block">转发</button>
                        </div>
                    </div>

                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<span id="touxiang" style="display: none"><%=user.getIcon()%></span>
</body>
</html>
