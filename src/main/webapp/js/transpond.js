$(function () {
    var str;
    var present;
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
    $(".glyphicon.glyphicon-link").click(function () {
        var parentdiv=$(this).parent().parent().prev().children().eq(0).children().eq(1);
        $("#transpond_info").text(parentdiv.children().eq(2).text());
        $("#transpond_username").text(parentdiv.children().eq(0).text());
        $("#messID").val($(this).parent().next().next().children("#MessageId").val());
        str="转发"+(parseInt($(this).text().substring(2)) + 1);
        present=$(this);
    });
})