<%@ page import="java.util.Map" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    Map<String,Object> s=ActionContext.getContext().getSession();
    User user=(User)s.get("user");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
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
    <!-- 引入 Bootstrap -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/allchat.css" />
    <!-- 包括所有已编译的插件 -->
    <script src="js/notification.js"></script>
    <script src="js/hotSearch.js"></script>
    <script src="js/search.js"></script>
    <script src="js/remind.js"></script>
    <script src="js/comment.js"></script>
    <script src="js/transpond.js"></script>
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
        #searchResult{
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
            padding: 8px 10px;
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
<input id="param1" value="${param}" style="display: none">
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
                <li><a href="tohomeUser"><span class="glyphicon glyphicon-home"></span>&nbsp;首页</a></li>
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
                        <li><a href="#">转发&nbsp&nbsp<span id="tip1" class="badge" style="background-color: grey;font-size: 5"></span></a></li>
                        <li class="divider"></li>
                        <li><a href="#">评论&nbsp&nbsp<span id="tip2" class="badge" style="background-color: grey;font-size: 5"></span></a></li>
                        <li class="divider"></li>
                        <li><a href="#">点赞&nbsp&nbsp<span id="tip3" class="badge" style="background-color: grey;font-size: 5"></span></a></li>
                        <li class="divider"></li>
                        <li><a href="#">私信&nbsp&nbsp<span id="tip4" class="badge" style="background-color: grey;font-size: 5"></span></a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-cog"></span>
                    </a>
                    <ul class="dropdown-menu" style="font-size: 12;min-width:100%;">
                        <li><a href="#">账号设置</a></li>
                        <li class="divider"></li>
                        <li><a href="#">信息完善</a></li>
                        <li class="divider"></li>
                        <li><a href="#">隐私设置</a></li>
                        <li class="divider"></li>
                        <li><a href="#">退出</a></li>
                    </ul>
                </li>
                <li><script type="text/javascript">for(var i=1;i<=30;i++){document.write("&nbsp;");}</script></li>
            </ul>
        </div>
    </div>
</nav>
<!--主题内容-->
<div class="container" style="margin-top: 60px;">
    <div class="row clearfix">
        <div  style="position: fixed;margin-left: 120px;width: 160px;background-color:#C0C0C0;">
            <div style="padding:10px;"><span style="color: white;font-size: 16"><b>我的消息箱</b></span></div>
            <div id="td" style="padding-left:10px;padding-top: 15px;padding-bottom: 15px;cursor: pointer;"><span class="glyphicon glyphicon-ok" style="color: white;font-size: 13"> 转发</span></div>
            <div id="ct" style="padding-left:10px;padding-top: 15px;padding-bottom: 15px;cursor: pointer;"><span class="glyphicon glyphicon-minus" style="color: white;font-size: 13"> 评论</span></div>
            <div id="ae" style="padding-left:10px;padding-top: 15px;padding-bottom: 15px;cursor: pointer;"><span class="glyphicon glyphicon-minus" style="color: white;font-size: 13"> 点赞</span></div>
            <div id="pe" style="padding-left:10px;padding-top: 15px;padding-bottom: 20px;cursor: pointer;"><span class="glyphicon glyphicon-minus" style="color: white;font-size: 13"> 私信</span></div>
        </div>
        <div class="col-md-10 column" style="margin-left: 285px;">
             <div class="row">
                <!--核心内容-->
                <div class="col-md-7 column">
                    <div class="col-sm-12">
                        <div id="notification" class="row" style="padding: 5px;background-color: white;">
                        </div>
                    </div>
                </div>
                <div class="col-md-4 column" style="padding-right: 80px;">
                    <!--大家正在看-->
                    <div class="row" style="padding: 5px;background-color: white;">
                        <div class="col-sm-12">
                            <span class="pull-left" style="padding: 5px;font-size:15px;font-weight: bold;">热门话题</span>
                            <span class="pull-right" style="padding: 5px;">热度</span>
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
                        <div class="col-sm-12 text-center" style="padding: 3px"><a href="#">查看更多</a></div>
                    </div>
                    <!--使用帮助-->
                    <div class="row" style="margin-top: 10px; padding: 5px;background-color: white;">
                        <div class="col-sm-12">
                            <span class="pull-left" style="padding: 3px;font-size:15px;font-weight: bold;">使用小帮助</span>
                        </div>
                        <hr style="margin: 0;padding: 0;width: 100%">
                        <div style="padding-left:8px;padding-top:8px; font-size: 13px;">
                            <span>Q：评论太多，想分类看？</span><br>
                            <span>A：你可以通过分类筛选，只查看自己关注的人。</span>
                        </div>
                        <div style="padding-left:8px;padding-top:8px; font-size: 13px;">
                            <span>Q：不希望某些人评论自己？</span><br>
                            <span>A：可以设置仅关注的人才能给自己发评论</span>
                        </div>
                        <div style="padding-left:8px;padding-top:8px;padding-bottom: 10px;font-size: 13px;">
                            <span>Q：不希望收到某些人的评论提醒？</span><br>
                            <span>A：你可以设置评论的提醒范围</span>
                        </div>
                    </div>
                </div>
             </div>
        </div>
    </div>
</div>
<!--主题内容-->
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
                            <button id="transpondweibo" type="submit" class="btn btn-primary btn-sm btn-block">转发</button>
                        </div>
                    </div>

                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="bian">
    <header class="header">
        <a class="back"></a>
        <h5 class="tit"id="touser"></h5>
        <span id="user" style="display: none"><%=user.getUserNikename()%></span>
        <span id="pic" style="display: none"><%=user.getIcon()%></span>
        <div class="right">历史</div>
    </header>
    <div class="message" id="myDiv">
    </div>
    <div class="footer">
        <img src="images/hua.png" alt="" />
        <img src="images/face.png" alt="" />
        <input id="shu" type="text"/>
        <p>发送</p>
    </div>
</div>
</body>
<script src="js/allchat.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var i=0;
    if(i==0 && $("#param1").val()=="{param =letter}"){
        //点击私信
        $("#pe").children().removeClass("glyphicon-minus").addClass("glyphicon-ok");
        $("#td").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#ct").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#ae").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#notification").empty();
        $.ajax({
                type : "POST",  //请求方式
                url : "letterRemind",  //请求路径
                data : {},
                async:true,
                success : function(data) {  //异步请求成功执行的回调函数
                    var html='';
                    for(var d in data){
                        html="<div class=\"col-sm-12\">\n" +
                            "                               <div class=\"col-sm-2\" style=\"padding-top: 10px;padding-left: 10px;\">\n" +
                            "                                 <p onclick='Chatclick(this)' href=\"#\"><img src="+ data[d].pic +" class=\"img-circle\" width=\"50px;\"></p>\n" +
                            "                             </div>\n" +
                            "                             <div class=\"col-sm-10\" style=\"padding-top: 10px;padding-left: 0px;\">\n" +
                            "                                 <h5><b>"+data[d].name+"</b><span class=\"pull-right\" style=\"color: #999;font-size: 13;\">&nbsp;"+data[d].time+"</span></h5>\n" +
                            "                                 <h6 style=\"color: #999\">"+data[d].content+"</h6>\n" +
                            "                             </div>\n" +
                            "                            </div></hr>";
                        $("#notification").append(html);
                    }
                },//ajax引擎一般用不到；状态信息；抛出的异常信息
                error : function() {
                    alert("失败了");
                }
            });
        i=1;
    }
    else if(i==0 && $("#param1").val()=="{param =agree}"){
        $("#ae").children().removeClass("glyphicon-minus").addClass("glyphicon-ok");
        $("#pe").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#ct").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#td").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#notification").empty();
        $.ajax({
            type : "POST",  //请求方式
            url : "agreeRemind",  //请求路径
            data : {},
            async:true,
            success : function(data) {  //异步请求成功执行的回调函数
                var html='';
                for(var i in data){
                    if(data[parseInt(i)].wb.isTransponpd=="true"){
                        //如果该微博为转发微博
                        html=html+"<!--点赞的通知-->\n" +
                            "                        <div style=\"margin: 5px;\">\n" +
                            "                            <div class=\"col-sm-2\" style=\"padding-top: 20px;padding-left: 20px;\">\n" +
                            "                               <a href=\"#\"><img src=\""+
                            data[parseInt(i)].pic+"\" class=\"img-circle\" width=\"50px;\"></a>\n" +
                            "                           </div>\n" +
                            "                           <div class=\"col-sm-8\" style=\"padding-top: 20px;padding-left: 0px;\">\n" +
                            "                             <h5><b>"+
                            data[parseInt(i)].username+"</b><span style=\"color: #999;font-size: 13;\">&nbsp;赞了你的微博</span></h5>\n" +
                            "                             <h6 style=\"color: #999\">"+
                            data[parseInt(i)].agreetime+"</h6>\n" +
                            "                         </div>\n";
                        html=html+"<div class=\"col-sm-10 pull-right\" style=\"padding-bottom: 10px;padding-left: 0px;\">\n<a href='#'>@" +data[parseInt(i)].wb.nikename+":</a>"+
                            data[parseInt(i)].wb.weiboInfo;
                        for(var j in data[parseInt(i)].wb.list){
                            if(data[parseInt(i)].wb.list[parseInt(j)].message.messageType=="Transpond"){
                                html=html+"<a href='#' ><b>@"+
                                    data[parseInt(i)].wb.list[parseInt(j)].user.name+":</b></a>"+ data[parseInt(i)].wb.list[parseInt(j)].message.info;
                            }else {
                                html=html+"</div>";
                                html=html+"<div class=\"col-sm-12\" style=\"background-color:#eee;max-height: 500px;padding-top: 1rem;padding-left: 0px;\">\n" +
                                    "    <div class=\"col-sm-10 pull-right\" onclick=\"location.href='toMessage?messageID="+data[parseInt(i)].wb.list[parseInt(j)].message.id+"'\">" +
                                    "        <a href='#'><b>@"+
                                    data[parseInt(i)].wb.list[parseInt(j)].user.name+"</b></a>\n" +
                                    "        <p>"+
                                    data[parseInt(i)].wb.list[parseInt(j)].message.info+"</p>\n" +
                                    "        <br>\n" +
                                    "        <div>\n" +
                                    "            <h6 class=\"pull-left\">"+
                                    data[parseInt(i)].wb.list[parseInt(j)].message.messageTime+"</h6>\n" +
                                    "            <h6 class=\"pull-right\"><span class=\"glyphicon glyphicon-link\">"+
                                    data[parseInt(i)].wb.list[parseInt(j)].message.messageTranspondnum+"</span>&nbsp;\n" +
                                    "                <span class=\"glyphicon glyphicon-edit\">"+
                                    data[parseInt(i)].wb.list[parseInt(j)].message.messageCommentnum+"</span>&nbsp;\n" +
                                    "                <span class=\"glyphicon glyphicon-thumbs-up\">"+
                                    data[parseInt(i)].wb.list[parseInt(j)].message.messageAgreenum+"</span>\n" +
                                    "            </h6>\n" +
                                    "        </div>\n" +
                                    "    </div>\n" +
                                    "</div>";
                            }
                        }
                        html=html+ "                        <hr class=\"col-sm-12\">\n" +
                            "                        <div class=\"col-sm-12 text-center\" style=\"padding-bottom: 10px;\">\n" +
                            "                           <span class=\"glyphicon glyphicon-edit\">回复</span>\n" +
                            "                         </div>\n" +
                            "                        </div><!--点赞通知-->";
                    }else {
                        html=html+"<!--点赞的通知-->\n" +
                            "                        <div style=\"margin: 5px;\">\n" +
                            "                            <div class=\"col-sm-2\" style=\"padding-top: 20px;padding-left: 20px;\">\n" +
                            "                               <a href=\"#\"><img src=\""+
                            data[parseInt(i)].pic+"\" class=\"img-circle\" width=\"50px;\"></a>\n" +
                            "                           </div>\n" +
                            "                           <div class=\"col-sm-8\" style=\"padding-top: 20px;padding-left: 0px;\">\n" +
                            "                             <h5><b>"+
                            data[parseInt(i)].username+"</b><span style=\"color: #999;font-size: 13;\">&nbsp;赞了你的微博</span></h5>\n" +
                            "                             <h6 style=\"color: #999\">"+
                            data[parseInt(i)].agreetime+"</h6>\n" +
                            "                         </div>\n" +
                            "                         <div class=\"col-sm-10 pull-right\" style=\"padding: 7px;background-color: #eee;\">\n" +
                            "                            <a href=\"#\" style=\"color: orange;\">@"+
                            data[parseInt(i)].wb.nikename+":</a><span>"+
                            data[parseInt(i)].wb.weiboInfo+"</span>\n" +
                            "                        </div>\n" +
                            "                        <hr class=\"col-sm-12\">\n" +
                            "                        <div class=\"col-sm-12 text-center\" style=\"padding-bottom: 10px;\">\n" +
                            "                           <span class=\"glyphicon glyphicon-edit\">回复</span>\n" +
                            "                         </div>\n" +
                            "                        </div><!--点赞通知-->";
                    }
                }
                $("#notification").append(html);
            },//ajax引擎一般用不到；状态信息；抛出的异常信息
            error : function() {
                alert("获取点赞信息记录失败");
            }
        });
        i=1;
    }
    else if(i==0 && $("#param1").val()=="{param=transpond}"){
        $("#td").children().removeClass("glyphicon-minus").addClass("glyphicon-ok");
        $("#pe").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#ct").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#ae").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        //清空当前内容
        $("#notification").empty();
        $.ajax({
            type : "POST",  //请求方式
            url : "transPondRemind",  //请求路径
            data : {},
            async:true,
            success : function(data) {
                var html='';
                for(var i in data){
                    if(data[parseInt(i)].wb.isTransponpd=="true"){
                        //该微博是转发
                        //用一个用户转发微博多次
                        for(var j in data[parseInt(i)].transinfos){
                            html=html+"<!--点赞的通知-->\n" +
                                "                        <div style=\"margin: 5px;\">\n" +
                                "                            <div class=\"col-sm-2\" style=\"padding-top: 20px;padding-left: 20px;\">\n" +
                                "                               <a href=\"#\"><img src=\""+
                                data[parseInt(i)].pic+"\" class=\"img-circle\" width=\"50px;\"></a>\n" +
                                "                           </div>\n" +
                                "                           <div class=\"col-sm-8\" style=\"padding-top: 20px;padding-left: 0px;\">\n" +
                                "                             <h5><b>"+
                                data[parseInt(i)].username+"</b><span style=\"color: #999;font-size: 13;\">&nbsp;转发了你的微博</span></h5>\n" +
                                "                             <h6 style=\"color: #999\">"+
                                data[parseInt(i)].commenttime+"</h6>\n" +
                                "                         </div>\n";
                            html=html+"<div class=\"col-sm-10 pull-right\" style=\"padding-bottom: 10px;padding-left: 0px;\">" +
                                "\n转发:"+data[parseInt(i)].transinfos[parseInt(j)]+"//"+"<a href='#'>@" +data[parseInt(i)].wb.nikename+":</a>"+
                                data[parseInt(i)].wb.weiboInfo;
                            for(var k in data[parseInt(i)].wb.list){
                                if(data[parseInt(i)].wb.list[parseInt(k)].message.messageType=="Transpond"){
                                    html=html+"<a href='#' ><b>@"+
                                        data[parseInt(i)].wb.list[parseInt(k)].user.name+":</b></a>"+ data[parseInt(i)].wb.list[parseInt(k)].message.info;
                                }else {
                                    html=html+"</div>";
                                    html=html+"<div class=\"col-sm-12\" style=\"background-color:#eee;max-height: 500px;padding-top: 1rem;padding-left: 0px;\">\n" +
                                        "    <div class=\"col-sm-10 pull-right\" onclick=\"location.href='toMessage?messageID="+data[parseInt(i)].wb.list[parseInt(k)].message.id+"'\">" +
                                        "        <a href='#'><b>@"+
                                        data[parseInt(i)].wb.list[parseInt(k)].user.name+"</b></a>\n" +
                                        "        <p>"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.info+"</p>\n" +
                                        "        <br>\n" +
                                        "        <div>\n" +
                                        "            <h6 class=\"pull-left\">"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.messageTime+"</h6>\n" +
                                        "            <h6 class=\"pull-right\"><span class=\"glyphicon glyphicon-link\">"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.messageTranspondnum+"</span>&nbsp;\n" +
                                        "                <span class=\"glyphicon glyphicon-edit\">"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.messageCommentnum+"</span>&nbsp;\n" +
                                        "                <span class=\"glyphicon glyphicon-thumbs-up\">"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.messageAgreenum+"</span>\n" +
                                        "            </h6>\n" +
                                        "        </div>\n" +
                                        "    </div>\n" +
                                        "</div>";
                                }
                            }
                            html=html+ "                        <hr class=\"col-sm-12\">\n" +
                                "                        <div class=\"col-sm-12 text-center\" style=\"padding-bottom: 10px;\">\n" +
                                "                           <span class=\"glyphicon glyphicon-edit\">回复</span>\n" +
                                "                         </div>\n" +
                                "                        </div><!--点赞通知-->";
                        }
                    }else{
                        //转发的微博为原创
                        for(var j in data[parseInt(i)].transinfos){
                            html=html+"<!--点赞的通知-->\n" +
                                "                        <div style=\"margin: 5px;\">\n" +
                                "                            <div class=\"col-sm-2\" style=\"padding-top: 20px;padding-left: 20px;\">\n" +
                                "                               <a href=\"#\"><img src=\""+
                                data[parseInt(i)].pic+"\" class=\"img-circle\" width=\"50px;\"></a>\n" +
                                "                           </div>\n" +
                                "                           <div class=\"col-sm-8\" style=\"padding-top: 20px;padding-left: 0px;\">\n" +
                                "                             <h5><b>"+
                                data[parseInt(i)].username+"</b><span style=\"color: #999;font-size: 13;\">&nbsp;转发了你的微博</span></h5>\n" +
                                "                             <h6 style=\"color: #999\">"+
                                data[parseInt(i)].commenttime+"</h6>\n" +
                                "                         </div>\n" ;
                            html=html+"<div class=\"col-sm-10 pull-right\" style=\"padding-bottom: 10px;padding-left: 0px;\">" +
                                "\n转发:"+data[parseInt(i)].transinfos[parseInt(j)]+"</div>";
                            html=html+"                         <div class=\"col-sm-10 pull-right\" style=\"padding: 7px;background-color: #eee;\">\n" +
                                "                            <a href=\"#\" style=\"color: orange;\">@"+
                                data[parseInt(i)].wb.nikename+":</a><span>"+
                                data[parseInt(i)].wb.weiboInfo+"</span>\n" +
                                "                        </div>\n" +
                                "                        <hr class=\"col-sm-12\">\n" +
                                "                        <div class=\"col-sm-12 text-center\" style=\"padding-bottom: 10px;\">\n" +
                                "                           <span class=\"glyphicon glyphicon-edit\">回复</span>\n" +
                                "                         </div>\n" +
                                "                        </div><!--点赞通知-->";
                            html=html+"</div>";
                        }
                    }
                }
                $("#notification").append(html);

            },
            error:function (err) {
                alert("获取转发信息失败")
            }
        });
        i=1;
    }
    else if(i==0 && $("#param1").val()=="{param=comment}"){
        //点击评论
        $("#ct").children().removeClass("glyphicon-minus").addClass("glyphicon-ok");
        $("#pe").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#td").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#ae").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#notification").empty();
        $.ajax({
            type : "POST",  //请求方式
            url : "commentRemind",  //请求路径
            data : {},
            async:true,
            success : function(data) {  //异步请求成功执行的回调函数
                var html='';
                for(var i in data){
                    if(data[parseInt(i)].wb.isTransponpd=="true"){
                        //该微博是转发
                        //用一个用户评论了多条微博
                        for(var j in data[parseInt(i)].commentinfos){
                            html=html+"<!--点赞的通知-->\n" +
                                "                        <div style=\"margin: 5px;\">\n" +
                                "                            <div class=\"col-sm-2\" style=\"padding-top: 20px;padding-left: 20px;\">\n" +
                                "                               <a href=\"#\"><img src=\""+
                                data[parseInt(i)].pic+"\" class=\"img-circle\" width=\"50px;\"></a>\n" +
                                "                           </div>\n" +
                                "                           <div class=\"col-sm-8\" style=\"padding-top: 20px;padding-left: 0px;\">\n" +
                                "                             <h5><b>"+
                                data[parseInt(i)].username+"</b><span style=\"color: #999;font-size: 13;\">&nbsp;评论了你的微博</span></h5>\n" +
                                "                             <h6 style=\"color: #999\">"+
                                data[parseInt(i)].commenttime+"</h6>\n" +
                                "                         </div>\n";
                            html=html+"<div class=\"col-sm-10 pull-right\" style=\"padding-bottom: 10px;padding-left: 0px;\">" +
                                "\n评论:"+data[parseInt(i)].commentinfos[parseInt(j)]+"//"+"<a href='#'>@" +data[parseInt(i)].wb.nikename+":</a>"+
                                data[parseInt(i)].wb.weiboInfo;
                            for(var k in data[parseInt(i)].wb.list){
                                if(data[parseInt(i)].wb.list[parseInt(k)].message.messageType=="Transpond"){
                                    html=html+"<a href='#' ><b>@"+
                                        data[parseInt(i)].wb.list[parseInt(k)].user.name+":</b></a>"+ data[parseInt(i)].wb.list[parseInt(k)].message.info;
                                }else {
                                    html=html+"</div>";
                                    html=html+"<div class=\"col-sm-12\" style=\"background-color:#eee;max-height: 500px;padding-top: 1rem;padding-left: 0px;\">\n" +
                                        "    <div class=\"col-sm-10 pull-right\" onclick=\"location.href='toMessage?messageID="+data[parseInt(i)].wb.list[parseInt(j)].message.id+"'\">" +
                                        "        <a href='#'><b>@"+
                                        data[parseInt(i)].wb.list[parseInt(k)].user.name+"</b></a>\n" +
                                        "        <p>"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.info+"</p>\n" +
                                        "        <br>\n" +
                                        "        <div>\n" +
                                        "            <h6 class=\"pull-left\">"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.messageTime+"</h6>\n" +
                                        "            <h6 class=\"pull-right\"><span class=\"glyphicon glyphicon-link\">"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.messageTranspondnum+"</span>&nbsp;\n" +
                                        "                <span class=\"glyphicon glyphicon-edit\">"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.messageCommentnum+"</span>&nbsp;\n" +
                                        "                <span class=\"glyphicon glyphicon-thumbs-up\">"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.messageAgreenum+"</span>\n" +
                                        "            </h6>\n" +
                                        "        </div>\n" +
                                        "    </div>\n" +
                                        "</div>";
                                }
                            }
                            html=html+ "                        <hr class=\"col-sm-12\">\n" +
                                "                        <div class=\"col-sm-12 text-center\" style=\"padding-bottom: 10px;\">\n" +
                                "                           <span class=\"glyphicon glyphicon-edit\">回复</span>\n" +
                                "                         </div>\n" +
                                "                        </div><!--点赞通知-->";
                        }
                    }else{
                        //评论的微博为原创
                        for(var j in data[parseInt(i)].commentinfos){
                            html=html+"<!--点赞的通知-->\n" +
                                "                        <div style=\"margin: 5px;\">\n" +
                                "                            <div class=\"col-sm-2\" style=\"padding-top: 20px;padding-left: 20px;\">\n" +
                                "                               <a href=\"#\"><img src=\""+
                                data[parseInt(i)].pic+"\" class=\"img-circle\" width=\"50px;\"></a>\n" +
                                "                           </div>\n" +
                                "                           <div class=\"col-sm-8\" style=\"padding-top: 20px;padding-left: 0px;\">\n" +
                                "                             <h5><b>"+
                                data[parseInt(i)].username+"</b><span style=\"color: #999;font-size: 13;\">&nbsp;评论了你的微博</span></h5>\n" +
                                "                             <h6 style=\"color: #999\">"+
                                data[parseInt(i)].commenttime+"</h6>\n" +
                                "                         </div>\n" ;
                            html=html+"<div class=\"col-sm-10 pull-right\" style=\"padding-bottom: 10px;padding-left: 0px;\">" +
                                "\n评论:"+data[parseInt(i)].commentinfos[parseInt(j)]+"</div>";
                            html=html+"                         <div class=\"col-sm-10 pull-right\" style=\"padding: 7px;background-color: #eee;\">\n" +
                                "                            <a href=\"#\" style=\"color: orange;\">@"+
                                data[parseInt(i)].wb.nikename+":</a><span>"+
                                data[parseInt(i)].wb.weiboInfo+"</span>\n" +
                                "                        </div>\n" +
                                "                        <hr class=\"col-sm-12\">\n" +
                                "                        <div class=\"col-sm-12 text-center\" style=\"padding-bottom: 10px;\">\n" +
                                "                           <span class=\"glyphicon glyphicon-edit\">回复</span>\n" +
                                "                         </div>\n" +
                                "                        </div><!--点赞通知-->";
                            html=html+"</div>";

                        }
                    }
                }
                $("#notification").append(html);
            },//ajax引擎一般用不到；状态信息；抛出的异常信息
            error : function() {
                alert("获取评论信息记录失败");
            }
        });
        i=1;
    }
</script>
</body>
</html>