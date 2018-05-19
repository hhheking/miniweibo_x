<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2018/5/19
  Time: 9:48
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
    <form id="reg-form" action="loginUser" method="post">
        <table>
            <tr>
                <td>昵称</td>
                <td><input name="user.userNikename" type="text" id="nickname" easyform="length:2-16" message="昵称必须为2—16位" easytip="disappear:lost-focus;theme:blue;"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input name="user.userPassword" type="password" id="psw1" easyform="length:6-16;" message="密码必须为6—16位" easytip="disappear:lost-focus;theme:blue;"></td>
            </tr>
        </table>

        <div class="buttons">
            <input value="登 录" type="submit" style="margin-right:50px; margin-top:20px;">
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
