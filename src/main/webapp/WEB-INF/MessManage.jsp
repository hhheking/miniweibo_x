<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2018/6/12
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Mini微博后台管理</title>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="js/assets/materialize/css/materialize.min.css" media="screen,projection" />
    <!-- Bootstrap Styles-->
    <link href="js/assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FontAwesome Styles-->
    <link href="js/assets/css/font-awesome.css" rel="stylesheet" />
    <!-- Morris Chart Styles-->
    <link href="js/assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
    <!-- Custom Styles-->
    <link href="js/assets/css/custom-styles.css" rel="stylesheet" />
    <!-- Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <link rel="stylesheet" href="js/assets/js/Lightweight-Chart/cssCharts.css">
</head>
<body>
<div id="wrapper">
    <nav class="navbar navbar-default top-navbar" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand waves-effect waves-dark" href="index.html"><i class="large material-icons">track_changes</i> <strong>Mini微博后台</strong></a>

            <div id="sideNav" href=""><i class="material-icons dp48">toc</i></div>
        </div>

        <ul class="nav navbar-top-links navbar-right">
            <li><a class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown4"><i class="fa fa-envelope fa-fw"></i> <i class="material-icons right">arrow_drop_down</i></a></li>
            <li><a class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown3"><i class="fa fa-tasks fa-fw"></i> <i class="material-icons right">arrow_drop_down</i></a></li>
            <li><a class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown2"><i class="fa fa-bell fa-fw"></i> <i class="material-icons right">arrow_drop_down</i></a></li>
            <li><a class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown1"><i class="fa fa-user fa-fw"></i> <b>Admin</b> <i class="material-icons right">arrow_drop_down</i></a></li>
        </ul>
    </nav>
    <!-- Dropdown Structure -->
    <ul id="dropdown1" class="dropdown-content">
        <li><a href="#"><i class="fa fa-user fa-fw"></i> My Profile</a>
        </li>
        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
        </li>
        <li><a href="#"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
        </li>
    </ul>
    <!--/. NAV TOP  -->
    <!--/. NAV TOP  -->
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">

                <li>
                    <a class="waves-effect waves-dark" href="UsingDetailManage"><i class="fa fa-dashboard"></i>使用情况</a>
                </li>

                <li>
                    <a href="toUserManage" class="active-menu waves-effect waves-dark"><i class="fa fa-table"></i>用户管理</a>
                </li>

            </ul>

        </div>

    </nav>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper" >
        <div class="header">
            <h1 class="page-header">
                用户管理
            </h1>

        </div>

        <div id="page-inner">

            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="card">
                        <div class="card-action">
                            微博列表
                        </div>
                        <div class="card-content">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                    <tr>
                                        <th>微博内容</th>
                                        <th>转发数</th>
                                        <th>评论数</th>
                                        <th>点赞数</th>
                                        <th>撤销该微博</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <s:iterator value="messageList" var="message">
                                        <tr class="gradeU">
                                            <td>${message.getMessageInfo()}</td>
                                            <td class="center">${message.getMessageTranspondnum()}</td>
                                            <td class="center">${message.getMessageCommentnum()}</td>
                                            <td class="center">${message.getMessageAgreenum()}</td>
                                            <td class="center"><a href="deletemessManage?messid=${message.getMessageId()}&userid=${message.getUserByUserId().getUserId()}">删除微博</a></td>
                                        </tr>
                                    </s:iterator>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
            <!-- /. ROW  -->


        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
    <!-- /. WRAPPER  -->
    <!-- JS Scripts-->


    <!-- jQuery Js -->
    <script src="js/assets/js/jquery-1.10.2.js"></script>

    <!-- Bootstrap Js -->
    <script src="js/assets/js/bootstrap.min.js"></script>

    <script src="js/assets/materialize/js/materialize.min.js"></script>

    <!-- Metis Menu Js -->
    <script src="js/assets/js/jquery.metisMenu.js"></script>
    <!-- Morris Chart Js -->
    <script src="js/assets/js/morris/raphael-2.1.0.min.js"></script>
    <script src="js/assets/js/morris/morris.js"></script>


    <script src="js/assets/js/easypiechart.js"></script>
    <script src="js/assets/js/easypiechart-data.js"></script>

    <script src="js/assets/js/Lightweight-Chart/jquery.chart.js"></script>
    <!-- DATA TABLE SCRIPTS -->
    <script src="js/assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="js/assets/js/dataTables/dataTables.bootstrap.js"></script>
    <script>
        $(document).ready(function () {
            $('#dataTables-example').dataTable();
        });
    </script>
    <!-- Custom Js -->
    <script src="js/assets/js/custom-scripts.js"></script>


</body>

</html>

