$(function () {
    //收藏功能
    $(".glyphicon.glyphicon-star-empty").click(function () {
        var message_id = $(this).next().val();
        var user_id = $(this).next().next().val();
        var add=1;
        var status = $(this).html();
        var yan = $(this).next().css("color");
        if($(this).css("color") != yan) {
            add = 0;
            $(this).css("color",yan);
            $(this).html("收藏");
        }
        else {
            $(this).css("color", "coral");
            $(this).html("已收藏");
        }

        $.ajax({
            type : "POST",  //请求方式
            url : "collectionAction",  //请求路径
            data : {
                'message_id' : message_id,
                'user_id' : user_id,
                'add' : add
            },
            async:true,
            success : function(data) {  //异步请求成功执行的回调函数

            },//ajax引擎一般用不到；状态信息；抛出的异常信息
            error : function() {
                alert("失败了")
            }
        });


    });
    //点赞功能
    $(".glyphicon.glyphicon-thumbs-up").click(function () {
        var message_id = $(this).next().val();
        var user_id = $(this).next().next().val();
        var add=1;
        var number = $(this).html();
        var yan = $(this).next().css("color");
        if($(this).css("color") != yan) {
            add = 0;
            $(this).css("color",yan);
            $(this).html((parseInt(number) - 1));
        }
        else {
            $(this).css("color", "coral");
            $(this).html((parseInt(number) + 1));
        }

        $.ajax({
            type : "POST",  //请求方式
            url : "agreeAction",  //请求路径
            data : {
                'message_id' : message_id,
                'user_id' : user_id,
                'add' : add
            },
            async:true,
            success : function(data) {  //异步请求成功执行的回调函数

            },//ajax引擎一般用不到；状态信息；抛出的异常信息
            error : function() {
                alert("点赞失败了")
            }
        });


    });
    // 评价功能
    $(".glyphicon.glyphicon-edit").click(function () {
        var parentdiv=$(this).parent().parent();
        commentdiv=parentdiv.next();
        commentdiv.toggle();
        var messid1=$(this).next().val();
        var elementnum=commentdiv.children().length;//第一次点击评论时,评论框里的元素只有2,执行下面函数后增加了评论,评论框的孩子元素>2,用此判断是否第一次点击
        if(commentdiv.is(":visible")==true && elementnum==2){//当评论框是可见的且是第一次点击
            $.ajax({
                type : "POST",  //请求方式
                url : "commentAction",  //请求路径
                data : {
                    'messid' :messid1
                },
                async:true,
                success : function(data) {  //异步请求成功执行的回调函数
                    var i,j;

                    for( i=0;i<data.length;i++){
                        if(i>=5){
                            break;
                        }
                        var com="<div class=\"row clearfix\" style=\"border-bottom: 1px solid #ddd;margin: 5px;\">\n" +
                            "                                    <div class=\"col-md-1 column\">\n" +
                            "                                       <a href=\"toUser?userid="+data[i][4]+"\"><img src=\""+data[i][1]+"\" width=\"30px;\"></a>\n" +
                            "                                    </div>\n" +
                            "                                    <div class=\"col-md-11 column\">\n" +
                            "                                        <a href=\"toUser?userid="+data[i][4]+"\"><span>"+data[i][0]+"</span></a>\n" +
                            "                                        <span>"+data[i][3]+"</span>\n" +
                            "                                        <h6 style=\"margin-top: 1px;\">"+data[i][2]+"分钟前"+"</h6>\n" +
                            "                                    </div>\n" +
                            "                                </div>"
                        commentdiv.append(com);
                    }
                    if(i==5){
                        var com="<div style=\'text-align: center\'><a href=\"#\">查看更多</a></div>"
                        commentdiv.append(com);
                    }
                },//ajax引擎一般用不到；状态信息；抛出的异常信息
                error : function() {
                    alert("失败了")
                }
            });
        }
    });
    $(".btn.btn-default.pull-right").click(function() {
            if ($(this).html() == "评论") {
                var commentinfo = $(this).parent().prev().children().val();
                var userid = $(this).next().next().val();
                var messid = $(this).next().val();
                var nikename = $(this).next().next().next().val();
                $.ajax({
                    type: "POST",  //请求方式
                    url: "addcommentAction",  //请求路径
                    data: {
                        'messid': messid,
                        'userid': userid,
                        'commentinfo': commentinfo
                    },
                    async: true,
                    success: function (data) {
                        ;
                    },
                    error: function () {
                        alert("ajax失败了")
                    }
                });
                var div = $(this).parent().parent().parent().parent();
                var mycom = "<div class=\"row clearfix\" style=\"border-bottom: 1px solid #ddd;margin: 5px;\">\n" +
                    "                                    <div class=\"col-md-1 column\">\n" +
                    "                                       <a href=\"toUser?userid=" + userid + "\"><img src=\""+$("#touxiang").html()+"\" width=\"30px;\"></a>\n" +
                    "                                    </div>\n" +
                    "                                    <div class=\"col-md-11 column\">\n" +
                    "                                        <a href=\"toUser?userid=" + userid + "\"><span>" + nikename + "</span></a>\n" +
                    "                                        <span>" + commentinfo + "</span>\n" +
                    "                                        <h6 style=\"margin-top: 1px;\">" + "10秒钟前" + "</h6>\n" +
                    "                                    </div>\n" +
                    "                                </div>"
                div.after(mycom);
                $(this).parent().prev().children().val("");
                var commentdiv = $(this).parent().parent().parent().parent().parent().prev().children("div").eq(2).children("span").html();
                commentdiv = "评价" + (parseInt(commentdiv.substring(2)) + 1);
                $(this).parent().parent().parent().parent().parent().prev().children("div").eq(2).children("span").html(commentdiv);
            }
        });
})
