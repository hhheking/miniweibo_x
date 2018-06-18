<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         isELIgnored="false" %>
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
    <link rel="stylesheet" href="js/chartist.min.css">
    <!-- Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

    <link rel="stylesheet" href="js/assets/js/Lightweight-Chart/cssCharts.css">
    <script src="js/assets/js/jquery-1.10.2.js"></script>

    <!-- Bootstrap Js -->
    <script src="js/chartist.min.js"></script>
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
    <!-- Custom Js -->
    <script src="js/assets/js/custom-scripts.js"></script>
    <script type="text/javascript">
        window.onload=function(){
            var data = {
                // A labels array that can contain any sort of values
                labels: ['六天前', '五天前', '四天前', '三天前', '两天前','昨天','今天'],
                // Our series array that contains series objects or in this case series data arrays
                series: [
                    [${usingCondition.getNewuser()[0]}, ${usingCondition.getNewuser()[1]}, ${usingCondition.getNewuser()[2]}, ${usingCondition.getNewuser()[3]}, ${usingCondition.getNewuser()[4]},${usingCondition.getNewuser()[5]},${usingCondition.getNewuser()[6]}]
                ]
            };

// Create a new line chart object where as first parameter we pass in a selector
// that is resolving to our chart container element. The Second parameter
// is the actual data object.
            new Chartist.Line('.ct-chart', data);
            new Chartist.Pie('#online', {
               series: [${usingCondition.getOnlinenum()}]
            }, {
                donut: true,
                donutWidth: 60,
                donutSolid: true,
                startAngle: 270,
                total: ${usingCondition.getUsernum()},
                showLabel: true
            });
            var onlinepercent =parseFloat(${usingCondition.getOnlinenum()} /${usingCondition.getUsernum()});
            onlinepercent=Math.round(onlinepercent*100)+"%";
            $("#onlinepercent").html(onlinepercent);
            $("#activepercent").html(onlinepercent);
            new Chartist.Pie('#active', {
                series: [${usingCondition.getOnlinenum()}]
            }, {
                donut: true,
                donutWidth: 60,
                donutSolid: true,
                startAngle: 0,
                total: ${usingCondition.getUsernum()},
                showLabel: true
            });
            new Chartist.Pie('#addToday', {
                series: [${usingCondition.getNewuser()[6]}]
            }, {
                donut: true,
                donutWidth: 60,
                donutSolid: true,
                startAngle: 45,
                total: ${usingCondition.getUsernum()},
                showLabel: true
            });
            var addnumpercent=parseFloat(${usingCondition.getNewuser()[6]}/${usingCondition.getUsernum()});
            addnumpercent=Math.round(addnumpercent*100)+"%";
            $("#addpercent").html(addnumpercent);
        };
    </script>
</head>

<body>
<div id="wrapper">
    <nav class="navbar navbar-default top-navbar" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle waves-effect waves-dark" data-toggle="collapse" data-target=".sidebar-collapse">
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
            <li><a class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown1"><i class="fa fa-user fa-fw"></i> <b>John Doe</b> <i class="material-icons right">arrow_drop_down</i></a></li>
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
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">

                <li>
                    <a class="active-menu waves-effect waves-dark" href="UsingDetailManage"><i class="fa fa-dashboard"></i>使用情况</a>
                </li>

                <li>
                    <a href="Manage" class="waves-effect waves-dark"><i class="fa fa-table"></i>用户管理</a>
                </li>

            </ul>

        </div>

    </nav>
    <!-- /. NAV SIDE  -->

    <div id="page-wrapper">
        <div class="header">
            <h1 class="page-header">
                Mini微博使用情况
            </h1>
        </div>
        <div id="page-inner">

            <div class="dashboard-cards">
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-3">

                        <div class="card horizontal cardIcon waves-effect waves-dark">
                            <div class="card-image red">
                                <i class="material-icons dp48">import_export</i>
                            </div>
                            <div class="card-stacked red">
                                <div class="card-content">
                                    <h3>${usingCondition.getOnlinenum()}</h3>
                                </div>
                                <div class="card-action">
                                    <strong>在线人数</strong>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-3">

                        <div class="card horizontal cardIcon waves-effect waves-dark">
                            <div class="card-image orange">
                                <i class="material-icons dp48">supervisor_account</i>
                            </div>
                            <div class="card-stacked orange">
                                <div class="card-content">
                                    <h3>${usingCondition.getTodaynum()}</h3>
                                </div>
                                <div class="card-action">
                                    <strong>今日活跃</strong>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-3">

                        <div class="card horizontal cardIcon waves-effect waves-dark">
                            <div class="card-image blue">
                                <i class="material-icons dp48">comment</i>
                            </div>
                            <div class="card-stacked blue">
                                <div class="card-content">
                                    <h3>${usingCondition.getTotalmessnum() }</h3>
                                </div>
                                <div class="card-action">
                                    <strong>微博总数</strong>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-3">

                        <div class="card horizontal cardIcon waves-effect waves-dark">
                            <div class="card-image green">
                                <i class="material-icons dp48">supervisor_account</i>
                            </div>
                            <div class="card-stacked green">
                                <div class="card-content">
                                    <h3>${usingCondition.getUsernum()}</h3>
                                </div>
                                <div class="card-action">
                                    <strong>用户总数</strong>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <!-- /. ROW  -->
            <div class="row">
                <div class="col-xs-10 col-sm-10 col-md-6">
                    <div class="cirStats">
                        <div class="row">
                            <div class="col-xs-10 col-sm-5 col-md-6">
                                <div class="card-panel text-center" id="bluediv" style="background-color: #9acfea">
                                    <h4>今日活跃人数占比</h4>
                                    <div id="online" class="easypiechart"><span id="onlinepercent">10%</span></div>
                                </div>
                            </div>
                            <div class="col-xs-10 col-sm-5 col-md-6">
                                <div class="card-panel text-center" style="background-color: #00E676">
                                    <h4>在线人数占比</h4>
                                    <div id="active" class="easypiechart"><span id="activepercent">10%</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-10 col-sm-5 col-md-6">
                                <div class="card-panel text-center" style="background-color:yellow">
                                    <h4>发过微博的人数占比</h4>
                                    <div class="easypiechart" id="easypiechart-teal" data-percent="84" ><span class="percent" id="teal">84%</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-10 col-sm-5 col-md-6">
                                <div class="card-panel text-center" style="background-color: #9F9F9F">
                                    <h4>今日新增人数占比</h4>
                                    <div id="addToday" class="easypiechart"><span id="addpercent">10%</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                <div class="col-xs-12 col-sm-12 col-md-5">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="card">
                                <div class="card-image donutpad">
                                    <div class="ct-chart ct-perfect-fourth"></div>
                                </div>
                                <div class="card-action" style="text-align:center">
                                    <b>近一周增加的粉丝数</b>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                    </div>
 ><!--/.row-->
                </div><!--/.row-->




</body>

</html>