//收藏功能
function collection(c) {
    var message_id = $(c).next().val();
    var user_id = $(c).next().next().val();
    var add=1;
    var status = $(c).html();
    var yan = $(c).next().css("color");
    if($(c).css("color") != yan) {
        add = 0;
        $(c).css("color",yan);
        $(c).html("收藏");
    }
    else {
        $(c).css("color", "coral");
        $(c).html("已收藏");
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
};

//点赞功能
function agree(c) {
    var message_id = $(c).next().val();
    var user_id = $(c).next().next().val();
    var add = 1;
    var number = $(c).html();
    var yan = $(c).next().css("color");
    if ($(c).css("color") != yan) {
        add = 0;
        $(c).css("color", yan);
        $(c).html((parseInt(number) - 1));
    }
    else {
        $(c).css("color", "coral");
        $(c).html((parseInt(number) + 1));
    }

    $.ajax({
        type: "POST",  //请求方式
        url: "agreeAction",  //请求路径
        data: {
            'message_id': message_id,
            'user_id': user_id,
            'add': add
        },
        async: true,
        success: function (data) {  //异步请求成功执行的回调函数

        },//ajax引擎一般用不到；状态信息；抛出的异常信息
        error: function () {
            alert("点赞失败了")
        }
    });
}

// 评价功能
function comment(c) {
    var parentdiv=$(c).parent().parent();
    var messageid=$(c).next().val();
    commentdiv=parentdiv.next();
    commentdiv.toggle();
    var messid1=$(c).next().val();
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
                        "                                       <a href=\"toUser?userid="+data[i][4]+"\"><img src=\"images/icon.png\" width=\"30px;\"></a>\n" +
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
                    var com="<div style='text-align: center'><a href='toMessage?messageID="+messageid+"'>查看更多</a></div>";
                    commentdiv.append(com);
                }
            },//ajax引擎一般用不到；状态信息；抛出的异常信息
            error : function() {
                alert("失败了")
            }
        });
    }
};

function pinlun(c) {
    if ($(c).html() == "评论") {
        var commentinfo = $(c).parent().prev().children().val();
        var userid = $(c).next().next().val();
        var messid = $(c).next().val();
        var nikename = $(c).next().next().next().val();
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
        var div = $(c).parent().parent().parent().parent();
        var mycom = "<div class=\"row clearfix\" style=\"border-bottom: 1px solid #ddd;margin: 5px;\">\n" +
            "                                    <div class=\"col-md-1 column\">\n" +
            "                                       <a href=\"toUser?userid=" + userid + "\"><img src=\"images/icon.png\" width=\"30px;\"></a>\n" +
            "                                    </div>\n" +
            "                                    <div class=\"col-md-11 column\">\n" +
            "                                        <a href=\"toUser?userid=" + userid + "\"><span>" + nikename + "</span></a>\n" +
            "                                        <span>" + commentinfo + "</span>\n" +
            "                                        <h6 style=\"margin-top: 1px;\">" + "10秒钟前" + "</h6>\n" +
            "                                    </div>\n" +
            "                                </div>"
        div.after(mycom);
        $(c).parent().prev().children().val("");
        var commentdiv = $(c).parent().parent().parent().parent().parent().prev().children("div").eq(2).children("span").html();
        commentdiv = "评价" + (parseInt(commentdiv.substring(2)) + 1);
        $(c).parent().parent().parent().parent().parent().prev().children("div").eq(2).children("span").html(commentdiv);
    }
};

//转发功能
var str;
var present;
function transponds(c) {
    var parentdiv=$(c).parent().parent().prev().children().eq(0).children().eq(1);
    $("#transpond_info").text(parentdiv.children().eq(2).text());
    $("#transpond_username").text(parentdiv.children().eq(0).text());
    $("#messID").val($(c).parent().next().next().children("#MessageId").val());
    str="转发"+(parseInt($(c).text().substring(2)) + 1);
    present=$(c);
};

//转发的模态框显示在界面上
$(function () {
        // 执行一些动作..
        $("#transpondweibo").click(function () {
            $.ajax({
                type:'Post',
                url:'transMessage',
                data:{
                    'messageInfo':$("#transpond_info").text(),
                    'messagrReason':$("#transpond_reason").val(),
                    'messageID':$("#messID").val(),
                    'message_username':$("#transpond_username").text()
                },
                dataType: 'json',
                async:true,
                success:function(data){
                    //消除模态框
                    $('#TransPondModal').hide();
                    $(".modal-backdrop").remove();
                    //成功发布微博,消除文本框中的内容
                    $('#TransPondModal').modal('hide');
                    var info=$("#transpond_info").text();
                    var reason=$("#transpond_reason").val();
                    var name=$("#transpond_username").text();
                    var userid=$("#sessionuserid").val();
                    var username=$("#sessionusername").val();
                    var myweibo="<div style=\"background-color: white;margin: 5px;\">\n" +
                        "                        <!--上层div-->\n" +
                        "                        <div class=\"row clearfix\">\n" +
                        "                            <div class=\"col-md-2 column\" style=\"padding-left: 25px;padding-top: 10px;\">\n" +
                        "                                <!--点击头像 进入用户空间-->\n" +
                        "                                <a href=\"toUser?userid=" +userid+
                        "<%=user.getUserId()%>>\"><img src=\"images/icon.png\" class=\"img-circle\" width=\"70px;\"></a>\n" +
                        "                            </div>\n" +
                        "                            <div class=\"col-md-10 column\">\n" +
                        "                                <h4 style=\"font-weight: bold;\">" +username+
                        "</h4>\n" +
                        "                                <h6>0分钟前 来自miniweibo.com</h6>\n" +
                        "                                <p>"+"转发微博："+reason+"//@"+name+"："+info+
                        "                                   </p>\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                        <!--下层div-->\n" +
                        "                        <div class=\"row clearfix\">\n" +
                        "                            <div class=\"col-md-3 column\" style=\"text-align: center;padding: 5px;\">\n" +
                        "                                <span class=\"glyphicon glyphicon-link\">转发0</span>\n" +
                        "                            </div>\n" +
                        "                            <div class=\"col-md-3 column\" style=\"text-align: center;\">\n" +
                        "                                <span class=\"glyphicon glyphicon-star-empty\">收藏0</span>\n" +
                        "                            </div>\n" +
                        "                            <div class=\"col-md-3 column\" style=\"text-align: center;\">\n" +
                        "                                <span id=\"showcomment\" class=\"glyphicon glyphicon-edit\">评价0</span>\n" +
                        "                            </div>\n" +
                        "                            <div class=\"col-md-3 column\" style=\"text-align: center;\">\n" +
                        "                                <span class=\"glyphicon glyphicon-thumbs-up\">点赞0</span>\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                        <!--点击评价显示出来的div-->\n" +
                        "                        <div id=\"comment\" style=\"height: 100px;background: red;display: none\">\n" +
                        "\n" +
                        "                        </div>\n" +
                        "                    </div>"
                    $("#myWeibo").prepend(myweibo);
                    //转发成功 原创微博数目加1
                    present.text(str);
                    $("#mymessagenum").text((parseInt($("#mymessagenum").text())+1));
                },
                error:function (err) {
                    //成功发布微博
                    alert("转发微博失败!");
                }
            });
        })
})
