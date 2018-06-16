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
                    $('#TransPondModal').modal('hide');
                    var info=$("#transpond_info").text();
                    var reason=$("#transpond_reason").val();
                    var name=$("#transpond_username").text();
                    var userid=$("#sessionuserid").val();
                    var username=$("#sessionusername").val();
                    var myweibo="<div style=\"background-color: white;margin: 5px;\">\n" +
                        "    <!--上层div-->\n" +
                        "    <div class=\"row clearfix\" style=\"padding-bottom: 1.5rem;\">\n" +
                        "        <div class=\"col-md-12 column\">\n" +
                        "            <div class=\"col-md-2 column\" style=\"padding-left: 25px;padding-top: 10px;\">\n" +
                        "                <!--点击头像 进入用户空间-->\n" +
                        "                <a href='toUser?userid="+userid+"'>"+
                        "<img src='images/icon.png' class=\"img-circle\" width=\"60px;\"></a>\n" +
                        "            </div>\n" +
                        "            <div class=\"col-md-10 column\">\n" +
                        "                <h4 style=\"font-weight: bold;\">"+username+"</h4>\n" +
                        "                <h6>0分钟前 来自miniweibo.com</h6>\n" + reason;
                    var count=data.list.length;
                    if(count!=0){
                        //微博不为原创
                        myweibo=myweibo+"<a href='#'><b>@"+data.nikename+":</b></a>"+data.weiboInfo;
                        for(var k in data.list){
                            if(data.list[parseInt(k)].message.messageType=="Transpond"){
                                //如果该微博是转发后的微博
                                myweibo=myweibo+"<a href='#' ><b>@"+
                                    data.list[parseInt(k)].user.name+":</b></a>"+ data.list[parseInt(k)].message.info;
                            }
                        }
                        //微博bu为原创
                        myweibo=myweibo+"</div></div>";
                        myweibo=myweibo+"<div class=\"col-md-12\" style=\"max-height: 500px;padding-top: 1rem;\">\n" +"<div class=\"col-sm-12\" style='background-color:#eee;'>"+
                            "                <div class=\"col-md-10 column pull-right\" style='padding-left: 0px;'>\n" +
                            "                    <a href='#'>" +
                            "                        <b>@"+
                            data.list[count-1].user.name+":</b></a>\n" +
                            "                        <p>"+
                            data.list[count-1].message.info+"</p>\n" +
                            "                        <br>\n" +
                            "                        <div>\n" +
                            "                            <h6 class=\"pull-left\">"+data.list[count-1].message.messageTime+"</h6>\n" +
                            "                            <h6 class=\"pull-right\"><span class=\"glyphicon glyphicon-link\">"+data.list[count-1].message.messageTranspondnum+"</span>&nbsp;\n" +
                            "                                <span class=\"glyphicon glyphicon-edit\">"+data.list[count-1].message.messageCollectnum+"</span>&nbsp;\n" +
                            "                                <span class=\"glyphicon glyphicon-thumbs-up\">"+data.list[count-1].message.messageAgreenum+"</span>\n" +
                            "                            </h6>\n" +
                            "                        </div>\n" +
                            "                    </div>" +
                            "                </div>"+"</div>"+"</div>"+
                            "<div class=\"row\" style=\"border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;\">\n" +
                            "    <div class=\"col-md-3 column\" style=\"text-align: center;padding: 10px;border-right: 1px solid #ddd;\">\n" +
                            "        <span class=\"glyphicon glyphicon-link\" onclick=\"transponds(this)\" data-toggle=\"modal\" data-target=\"#TransPondModal\">转发0</span>\n" +
                            "    </div>\n" +
                            "    <div class=\"col-md-3 column\" style=\"text-align: center;padding: 10px;border-right: 1px solid #ddd;\">\n" +
                            "        <!--得到微博的收藏状态和收藏的次数-->\n" +
                            "        <span class=\"glyphicon glyphicon-star-empty\" onclick=\"collection(this)\">收藏</span>\n" +
                            "</div>\n" +
                            "<div class=\"col-md-3 column\" style=\"text-align: center;padding: 10px;border-right: 1px solid #ddd;\">\n" +
                            "    <span id=\"showcomment\" class=\"glyphicon glyphicon-edit\" onclick=\"comment(this)\">评价0</span>\n" +
                            "</div>\n" +
                            "<div class=\"col-md-3 column\" style=\"text-align: center;padding: 10px;\">\n" +
                            "    <!--得到微博的赞同状态和赞同次数-->\n" +
                            "    <span class=\"glyphicon glyphicon-thumbs-up\" onclick=\"agree(this)\">0</span>\n" +
                            "</div>\n" +
                            "</div>" +
                            "</div>";
                    }else{
                        //微博为原创
                        myweibo=myweibo+"</div></div>";
                        myweibo=myweibo+"<div class=\"col-md-12\" style=\"max-height: 500px;padding-top: 1rem;\">\n" +"<div class=\"col-sm-12\" style='background-color:#eee;'>"+
                            "                <div class=\"col-md-10 column pull-right\" style='padding-left: 0px;'>\n" +
                            "                    <a href='#'>" +
                            "                        <b>@"+
                            data.nikename+":</b></a>\n" +
                            "                        <p>"+
                            data.weiboInfo+"</p>\n" +
                            "                        <br>\n" +
                            "                        <div>\n" +
                            "                            <h6 class=\"pull-left\">"+data.timestamp+"</h6>\n" +
                            "                            <h6 class=\"pull-right\"><span class=\"glyphicon glyphicon-link\">"+data.transpond+"</span>&nbsp;\n" +
                            "                                <span class=\"glyphicon glyphicon-edit\">"+data.comment+"</span>&nbsp;\n" +
                            "                                <span class=\"glyphicon glyphicon-thumbs-up\">"+data.agree+"</span>\n" +
                            "                            </h6>\n" +
                            "                        </div>\n" +
                            "                    </div>" +
                            "                </div>"+"</div>"+"</div>"+
                            "<div class=\"row\" style=\"border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;\">\n" +
                            "    <div class=\"col-md-3 column\" style=\"text-align: center;padding: 10px;border-right: 1px solid #ddd;\">\n" +
                            "        <span class=\"glyphicon glyphicon-link\" onclick=\"transponds(this)\" data-toggle=\"modal\" data-target=\"#TransPondModal\">转发0</span>\n" +
                            "    </div>\n" +
                            "    <div class=\"col-md-3 column\" style=\"text-align: center;padding: 10px;border-right: 1px solid #ddd;\">\n" +
                            "        <!--得到微博的收藏状态和收藏的次数-->\n" +
                            "        <span class=\"glyphicon glyphicon-star-empty\" onclick=\"collection(this)\">收藏</span>\n" +
                            "</div>\n" +
                            "<div class=\"col-md-3 column\" style=\"text-align: center;padding: 10px;border-right: 1px solid #ddd;\">\n" +
                            "    <span id=\"showcomment\" class=\"glyphicon glyphicon-edit\" onclick=\"comment(this)\">评价0</span>\n" +
                            "</div>\n" +
                            "<div class=\"col-md-3 column\" style=\"text-align: center;padding: 10px;\">\n" +
                            "    <!--得到微博的赞同状态和赞同次数-->\n" +
                            "    <span class=\"glyphicon glyphicon-thumbs-up\" onclick=\"agree(this)\">0</span>\n" +
                            "</div>\n" +
                            "</div>" +
                            "</div>";
                    }
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
        });
    $(".glyphicon.glyphicon-link").click(function () {
        var parentdiv=$(this).parent().parent().prev().children().eq(0).children().eq(1);
        $("#transpond_info").text(parentdiv.children().eq(2).text());
        $("#transpond_username").text(parentdiv.children().eq(0).text());
        $("#messID").val($(this).parent().next().next().children("#MessageId").val());
        str="转发"+(parseInt($(this).text().substring(2)) + 1);
        present=$(this);
    });
})