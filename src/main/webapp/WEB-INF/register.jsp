<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2018/5/19
  Time: 0:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>mini微博</title>

    <link rel="stylesheet" href="css/style.css">

    <script type="text/javascript" src="js/jquery-2.1.0.min.js"></script>
    <script type="text/javascript" src="js/easyform.js"></script>

</head>
<body>
<br>
<div class="form-div"  style="margin-top: 150px;">
    <form id="reg-form" action="registerUser" method="post">
        <table>
            <tr>
                <td>昵称</td>
                <td><input name="user.userNikename" type="text" id="nickname" easyform="length:2-16" message="昵称必须为2—16位" easytip="disappear:lost-focus;theme:blue;"></td>
            </tr>
            <tr>
                <td>email</td>
                <td><input name="user.userEmail" type="text" id="email" easyform="email;real-time;" message="Email格式要正确" easytip="disappear:lost-focus;theme:blue;" ajax-message="这个Email地址已经被注册过，请换一个吧!"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input name="user.userPassword" type="password" id="psw1" easyform="length:6-16;" message="密码必须为6—16位" easytip="disappear:lost-focus;theme:blue;"></td>
            </tr>
            <tr>
                <td>确认密码</td>
                <td><input type="password" id="psw2" easyform="length:6-16;equal:#psw1;" message="两次密码输入要一致" easytip="disappear:lost-focus;theme:blue;"></td>
            </tr>
        </table>

        <div class="buttons">
            <input value="注 册" type="submit" style="margin-right:20px; margin-top:20px;">
            <input value="已有账号 前去登录" type="submit" style="margin-right:55px; margin-top:20px;">
        </div>

        <br class="clear">
    </form>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        $('#reg-form').easyform();
    });
</script>
</body>
</html>

