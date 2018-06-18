<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2018/6/13
  Time: 1:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>mini微博-完善信息</title>
    <link rel="stylesheet" type="text/css" href="css/main.css" />
    <style type="text/css">
        #wizard {border:5px solid #789;font-size:12px;height:400px;margin:20px auto;width:570px;overflow:hidden;position:relative;-moz-border-radius:5px;-webkit-border-radius:5px;}
        #wizard .items{width:20000px; clear:both; position:absolute;}
        #wizard .right{float:right;}
        #wizard #status{height:35px;background:#123;padding-left:25px !important;}
        #status li{float:left;color:#fff;padding:10px 30px;}
        #status li.active{background-color:#369;font-weight:normal;}
        .input{width:200px; height:25px; margin:10px auto; line-height:20px; border:1px solid #d3d3d3; padding:2px}
        .page{padding:20px 30px;width:500px;float:left;}
        .page h3{height:42px; font-size:16px; border-bottom:1px dotted #ccc; margin-bottom:20px; padding-bottom:5px}
        .page h3 em{font-size:12px; font-weight:500; font-style:normal}
        .page p{line-height:24px;}
        .page p label{font-size:14px; display:block;}
        .btn_nav{height:36px; line-height:36px; margin:20px auto;}
        .prev,.next{width:100px; height:32px; line-height:32px; background:url(btn_bg.gif) repeat-x bottom; border:1px solid #d3d3d3; cursor:pointer}
    </style>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/scrollable.js"></script>
</head>

<body style="background: url(/images/bg.jpg) center top;">
<div id="main">
    <h2 class="top_title">mini微博-请完善你的信息</h2>
    <p style="height:24px; line-height:24px; margin:16px">我们不会将您的信息泄露给第三方机构或者个人，请放心使用！</p>
    <form action="addUserinfo" method="post">
        <div id="wizard">
            <ul id="status">
                <li class="active"><strong>1.</strong>填写账户信息</li>
                <li><strong>2.</strong>填写联系信息</li>
                <li><strong>3.</strong>完成</li>
            </ul>

            <div class="items">
                <div class="page">
                    <h3>完善账户信息<br/><em>请完善好您的信息，便于我们更好的服务你</em></h3>
                    <p><label>姓名：</label><input type="text" class="input" id="user" name="userinfo.userinfoTruename" /></p>
                    <p><label>地址：</label><input type="text" class="input" id="address" name="userinfo.userinfoAddress" /></p>
                    <p><label>性别：</label><select name="userinfo.userinfoSex" class="input">
                        <option value="man">男</option>
                        <option value="woman">女</option>
                        <option value="secret" selected="selected">请选择性别</option>
                    </select></p>
                    <div class="btn_nav">
                        <input type="button" class="next right" value="下一步&raquo;" />
                    </div>
                </div>
                <div class="page">
                    <h3>填写信息<br/><em>请告诉我们您其他的信息。</em></h3>
                    <p><label>生日：</label><input type="text" class="input" name="userinfo.userinfoBirthday"/></p>
                    <p><label>QQ：</label><input type="text" class="input" name="userinfo.userinfoQqnumber"/></p>
                    <p><label>个人简介：</label><input type="text" class="input" name="userinfo.userinfoIntro" placeholder="广交天下好友" /></p>
                    <div class="btn_nav">
                        <input type="button" class="prev" style="float:left" value="&laquo;上一步" />
                        <input type="button" class="next right" value="下一步&raquo;" />
                    </div>
                </div>
                <div class="page">
                    <h3>完成<br/><em>点击确定完成。</em></h3>
                    <h4>mini微博欢迎您！</h4>
                    <p>请点击“确定”按钮继续浏览其它精彩内容吧。</p>
                    <br/>
                    <br/>
                    <br/>
                    <div class="btn_nav">
                        <input type="button" class="prev" style="float:left" value="&laquo;上一步" />
                        <input type="submit" class="next right" value="确定" />
                    </div>
                </div>
            </div>
        </div>
    </form><br />
</div>

<script type="text/javascript">
    $(function(){
        $("#wizard").scrollable({
            onSeek: function(event,i){
                $("#status li").removeClass("active").eq(i).addClass("active");
            },
            onBeforeSeek:function(event,i){
                if(i==1){
                    var user = $("#user").val();
                    if(user==""){
                        alert("请输入姓名！");
                        return false;
                    }
                    var add = $("#address").val();
                    if(add==""){
                        alert("请输入地址！");
                        return false;
                    }
                }
            }
        });
    });
</script>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
    <p>mini微博——发现更多好玩的事</p>
</div>
</body>
</html>
