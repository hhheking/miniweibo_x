<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2018/5/24
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="java.util.Map" %>
<%@ page import="pojo.User" %>
<%
    Map<String, Object> s= ActionContext.getContext().getSession();
    User user=(User)s.get("user");
    if(user==null){
        //如果当前用户未登录
        user=new User();
        user.setUserId(0);
        user.setUserNikename("未登录");
    }
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
    <!-- 引入 Bootstrap -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/chat.css" />
    <link rel="stylesheet" type="text/css" href="css/dialog.css">
    <script src="js/zepto.min.js"></script>
    <script type="text/javascript" src="js/dialog.min.js"></script>
    <script src="js/hotSearch.js"></script>
    <script src="js/remind.js"></script>
    <script src="js/flexible.js"></script>
    <script src="js/message.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#focus").click(function () {
                $.ajax({
                    url:'judgeRelation',
                    data:{'user_id':$("#span").html()},
                    dataType: 'json',
                    async:true,
                    success:function (data) {
                        $("#focus").html(data);
                        //粉丝数加1
                        if(data=="+关注"){
                            $("#fansnum").text(parseInt($("#fansnum").text())-1);
                        }else{
                            $("#fansnum").text(parseInt($("#fansnum").text())+1);
                        }

                    },
                    error:function (err) {
                        popup({type:'error',msg:"关注失败，请先登陆！",delay:2000,bg:true,clickDomCancel:true});
                    }
                });
            });
        });
    </script>
    <style type="text/css">
        body{
            background: url("/images/bg.jpg") center top;
        }
        #index_panel{
            position: fixed;
            top: 45px;
            left: 260px;
            width: 400px;
        }
        .face{
            background: url("/images/face.png")  no-repeat;
            padding:1px 0 10px 25px;
            cursor: pointer;
            font-size: 15px;
        }
        .pic{
            background: url("/images/pic.png")  no-repeat;
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
                <li><a href="tohotUser"><span class="glyphicon glyphicon-home"></span>&nbsp;首页</a></li>
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
<!--左侧空白div-->
<div class="col-md-2 column"></div>
<!--网站主题内容-->
<div class="col-md-8 column" style="margin-top: 70px;">
    <!--包含昵称和头像的div-->
    <div style="height: 280px;background-color: white; background: url('/images/bg2.jpg');background-repeat: no-repeat">
        <div class="row text-center inform" style="margin-top: 5px;padding: 50px;">
            <img id="topic" src="${user.getIcon()}" class="img-circle" width="110px;">
            <h4 style="font-weight: bold;">${user.getUserNikename()}</h4>
            <div class="col-sm-12" >
                <p>一句话介绍一下自己吧，让别人更了解你</p>
                <span id="span" style="display: none">${id}</span>
            </div>
            <!--关注和私信的按钮-->
            <div class="row clearfix">
                <div class="col-md-5"></div>
                <div id="focus" class="col-md-1 column" style="text-align: center;padding:8px;background-color: orange;border-radius: 0.5rem;margin-right:4px;cursor: pointer;"><span id="status" style="color: white; ">${status}</span></div>
                <div class="col-md-1 column" style="text-align: center;padding:8px;background-color: gray;border-radius: 0.5rem;margin-left: 4px;cursor: pointer;"><span style="color: white; " id="chat">私信</span></div>
                <div class="col-md-5"></div>
            </div>
        </div>
    </div>
    <!--选项卡div-->
    <div class="container-fluid">
        <div class="row clearfix" style="background-color: white;padding: 7px;">
            <div class="col-md-6 column" style="text-align: right;padding-right: 95px;">
                <div>他的主页</div>
            </div>
            <div class="col-md-6 column" style="text-align: left;padding-left: 95px;">
                <div>他的相册</div>
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
                    <div><b id="fansnum">${fans}</b></div>
                    <div>粉丝</div>
                </div>
                <div class="col-sm-4" style="text-align: center;">
                    <div><b>${mymessageList.size()}</b></div>
                    <div>微博</div>
                </div>
            </div>
            <!--资料完成度-->
            <div class="col-sm-12" style="background-color: white;margin-top: 5px;padding: 10px;">
                <h5>等级:<strong>&nbsp;lv9</strong></h5>
                <!--横线-->
                <hr>
                <div>个人资料完成度<strong>40%</strong></div>
                <div class="progress" style="margin-top: 10px;">
                    <div class="progress-bar" role="progressbar" aria-valuenow="60"
                         aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
                        <span class="sr-only">40% 完成</span>
                    </div>
                </div>
                <!--横线-->
                <hr>
                <div style="text-align: center;">查看更多></div>
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
            <!--tab选项卡-->
            <s:iterator value="weibos" var="weibo">
                <div style="background-color: white;margin: 5px;">
                    <!--上层div-->
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                        <div class="col-md-2 column" style="padding-left: 25px;padding-top: 10px;">
                            <!--点击头像 进入用户空间-->
                            <a href="toUser?userid=${weibo.getId()}"><img src="${weibo.getImage()}" class="img-circle" width="70px;"></a>
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
                        <div class="col-md-12 column" style="background-color:#eee;max-height: 500px;padding-top: 10px;padding-left: 0px;">
                            <div class="col-md-10 column pull-right">
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
                        <div class="col-md-4 column" style="text-align: center;padding: 10px;border-right: 1px solid #ddd;">
                            <span class="glyphicon glyphicon-link" data-toggle="modal" data-target="#TransPondModal" onclick="transponds(this)">转发${weibo.getTranspond()}</span>
                        </div>
                        <div class="col-md-3 column" style="text-align: center;padding: 10px;border-right: 1px solid #ddd;display: none;"  >
                            <!--得到微博的收藏状态和收藏的次数-->
                            <s:if test="#weibo.collect_status == \"no\""><span class="glyphicon glyphicon-star-empty" onclick="collection(this)">收藏</span></s:if>
                            <s:else>
                                <span class="glyphicon glyphicon-star-empty" style="color: coral" onclick="collection(this)">已收藏</span>
                            </s:else>
                            <input value="${weibo.getMessid()}" style="display: none">
                            <input value="<%=user.getUserId()%>" style="display: none;">
                        </div>
                        <div class="col-md-4 column" style="text-align: center;padding: 10px;border-right: 1px solid #ddd;">
                            <span id="showcomment" class="glyphicon glyphicon-edit" onclick="comment(this)">评价${weibo.getComment()}</span>
                            <input id="MessageId" value="${weibo.getMessid()}" style="display: none">
                        </div>
                        <div class="col-md-4 column" style="text-align: center;padding: 10px;">
                            <!--得到微博的赞同状态和赞同次数-->
                            <s:if test="#weibo.agree_status == \"no\""><span class="glyphicon glyphicon-thumbs-up" onclick="agree(this)">${weibo.getAgree()}</span></s:if>
                            <s:else>
                                <span class="glyphicon glyphicon-thumbs-up" style="color: coral" onclick="agree(this)">${weibo.getAgree()}</span>
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
                                        <button type="submit" class="btn btn-default pull-right" style="background-color: orange;height: 30px;" onclick="pinlun(this)">评论</button>
                                        <input value="${weibo.getMessid()}" style="display: none">
                                        <input id="sessionuserid" value="<%=user.getUserId()%>" style="display: none">
                                        <input id="sessionusername" value="<%=user.getUserNikename()%>" style="display: none">
                                    </div>
                                </form>
                                <!--分割线-->
                                <hr>
                            </div>
                        </div>
                    </div>
                </div>
            </s:iterator>
        </div>
    </div>
</div>
<!--右侧空白div-->
<div class="col-md-2 column"></div>
<!--标题搜索栏获得焦点后，显示下拉搜索列表-->
<div id="index_panel" style="display:none; ">
    <a href="#" class="list-group-item"> <span class="badge" style="background-color: #FF4500;">爆</span>1.第一条热搜第一条热搜第一条热搜</a>
    <a href="#" class="list-group-item"><span class="badge" style="background-color: #FF0000;">热</span>2.第二条热搜第二条热搜第二条热搜</a>
    <a href="#" class="list-group-item"><span class="badge" style="background-color: #DC143C;">新</span>3.第三条热搜第三条热搜第三条热搜</a>
</div>
</div>
<div class="bian">
    <header class="header">
        <a class="back"></a>
        <h5 class="tit"id="touser">${user.getUserNikename()}</h5>
        <span id="user" style="display: none"><%=user.getUserNikename()%></span>
        <span id="pic" style="display: none"><%=user.getIcon()%></span>
        <div class="right">历史</div>
    </header>
    <div class="message" id="myDiv">
    </div>
    <div class="footer">
        <img src="images/hua.png" alt="" />
        <input id="shu" type="text"/>
        <p>发送</p>
    </div>
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
<script src="js/chat.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
