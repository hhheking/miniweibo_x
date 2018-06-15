$(function () {
    //点击转发
    $("#td").click(function(){
        $("#td").children().removeClass("glyphicon-minus").addClass("glyphicon-ok");
        $("#pe").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#ct").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#ae").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        //清空当前内容
        $("#notification").empty();
        $.ajax({
            type : "POST",  //请求方式
            url : "transPondRemind",  //请求路径
            data : {},
            async:true,
            success : function(data) {
                var html;
                for(var i in data){
                    if(data[parseInt(i)].wb.isTransponpd=="true"){
                        //该微博是转发
                        //用一个用户转发微博多次
                        for(var j in data[parseInt(i)].transinfos){
                            html=html+"<!--点赞的通知-->\n" +
                                "                        <div style=\"margin-top: 5px;\">\n" +
                                "                            <div class=\"col-sm-2\" style=\"padding-top: 20px;padding-left: 20px;\">\n" +
                                "                               <a href=\"#\"><img src=\""+
                                data[parseInt(i)].pic+"\" class=\"img-circle\" width=\"50px;\"></a>\n" +
                                "                           </div>\n" +
                                "                           <div class=\"col-sm-8\" style=\"padding-top: 20px;padding-left: 0px;\">\n" +
                                "                             <h5><b>"+
                                data[parseInt(i)].username+"</b><span style=\"color: #999;font-size: 13;\">&nbsp;转发了你的微博</span></h5>\n" +
                                "                             <h6 style=\"color: #999\">"+
                                data[parseInt(i)].commenttime+"</h6>\n" +
                                "                         </div>\n";
                            html=html+"<div class=\"col-sm-10 pull-right\" style=\"padding-bottom: 10px;padding-left: 0px;\">" +
                                "\n转发:"+data[parseInt(i)].transinfos[parseInt(j)]+"//"+"<a href='#'>@" +data[parseInt(i)].wb.nikename+":</a>"+
                                data[parseInt(i)].wb.weiboInfo+"</div>";
                            html=html+"<div class=\"col-sm-10 pull-right\" style=\"padding: 7px;background-color: #eee;\">";
                            for(var k in data[parseInt(i)].wb.list){
                                if(data[parseInt(i)].wb.list[parseInt(k)].message.messageType=="Transpond"){
                                    html=html+"<a href='#' ><b>@"+
                                        data[parseInt(i)].wb.list[parseInt(k)].user.name+":</b></a>"+ data[parseInt(i)].wb.list[parseInt(k)].message.info;
                                }else {
                                    html=html+"<div class=\"col-sm-12\" style=\"background-color:#eee;max-height: 500px;padding-top: 1rem;padding-left: 0px;\">\n" +
                                        "    <div class=\"col-sm-12 pull-right\">\n" +
                                        "        <a href='#'><b>@"+
                                        data[parseInt(i)].wb.list[parseInt(k)].user.name+"</b></a>\n" +
                                        "        <p>"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.info+"</p>\n" +
                                        "        <br>\n" +
                                        "        <div>\n" +
                                        "            <h6 class=\"pull-left\">"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.messageTime+"</h6>\n" +
                                        "            <h6 class=\"pull-right\"><span class=\"glyphicon glyphicon-link\">"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.messageTranspondnum+"</span>&nbsp;\n" +
                                        "                <span class=\"glyphicon glyphicon-edit\">"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.messageCommentnum+"</span>&nbsp;\n" +
                                        "                <span class=\"glyphicon glyphicon-thumbs-up\">"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.messageAgreenum+"</span>\n" +
                                        "            </h6>\n" +
                                        "        </div>\n" +
                                        "    </div>\n" +
                                        "</div>";
                                }
                            }
                            html=html+"</div>";
                            html=html+ "                        <hr class=\"col-sm-12\">\n" +
                                "                        <div class=\"col-sm-12 text-center\" style=\"padding-bottom: 10px;\">\n" +
                                "                           <span class=\"glyphicon glyphicon-edit\">回复</span>\n" +
                                "                         </div>\n" +
                                "                        </div><!--点赞通知-->";
                        }
                    }else{
                        //转发的微博为原创
                        for(var j in data[parseInt(i)].transinfos){
                            html=html+"<!--点赞的通知-->\n" +
                                "                        <div style=\"margin-top: 5px;\">\n" +
                                "                            <div class=\"col-sm-2\" style=\"padding-top: 20px;padding-left: 20px;\">\n" +
                                "                               <a href=\"#\"><img src=\""+
                                data[parseInt(i)].pic+"\" class=\"img-circle\" width=\"50px;\"></a>\n" +
                                "                           </div>\n" +
                                "                           <div class=\"col-sm-8\" style=\"padding-top: 20px;padding-left: 0px;\">\n" +
                                "                             <h5><b>"+
                                data[parseInt(i)].username+"</b><span style=\"color: #999;font-size: 13;\">&nbsp;转发了你的微博</span></h5>\n" +
                                "                             <h6 style=\"color: #999\">"+
                                data[parseInt(i)].commenttime+"</h6>\n" +
                                "                         </div>\n" ;
                            html=html+"<div class=\"col-sm-10 pull-right\" style=\"padding-bottom: 10px;padding-left: 0px;\">" +
                                "\n转发:"+data[parseInt(i)].transinfos[parseInt(j)]+"</div>";
                            html=html+"                         <div class=\"col-sm-10 pull-right\" style=\"padding: 7px;background-color: #eee;\">\n" +
                                "                            <a href=\"#\" style=\"color: orange;\">@"+
                                data[parseInt(i)].wb.nikename+":</a><span>"+
                                data[parseInt(i)].wb.weiboInfo+"</span>\n" +
                                "                        </div>\n" +
                                "                        <hr class=\"col-sm-12\">\n" +
                                "                        <div class=\"col-sm-12 text-center\" style=\"padding-bottom: 10px;\">\n" +
                                "                           <span class=\"glyphicon glyphicon-edit\">回复</span>\n" +
                                "                         </div>\n" +
                                "                        </div><!--点赞通知-->";
                            html=html+"</div>";
                        }
                    }
                }
                $("#notification").append(html);

            },
            error:function (err) {
                alert("获取转发信息失败")
            }
        });
    });
    //点击私信
    $("#pe").click(function(){
        $("#pe").children().removeClass("glyphicon-minus").addClass("glyphicon-ok");
        $("#td").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#ct").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#ae").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#notification").empty();
        $.ajax({
            type : "POST",  //请求方式
            url : "letterRemind",  //请求路径
            data : {},
            async:true,
            success : function(data) {  //异步请求成功执行的回调函数
                var html;
                for(var d in data){
                    html="<div class=\"col-sm-12\">\n" +
                        "                               <div class=\"col-sm-2\" style=\"padding-top: 10px;padding-left: 10px;\">\n" +
                        "                                 <p onclick='Chatclick(this)' href=\"#\"><img src="+ data[d].pic +" class=\"img-circle\" width=\"50px;\"></p>\n" +
                        "                             </div>\n" +
                        "                             <div class=\"col-sm-10\" style=\"padding-top: 10px;padding-left: 0px;\">\n" +
                        "                                 <h5><b>"+data[d].name+"</b><span class=\"pull-right\" style=\"color: #999;font-size: 13;\">&nbsp;"+data[d].time+"</span></h5>\n" +
                        "                                 <h6 style=\"color: #999\">"+data[d].content+"</h6>\n" +
                        "                             </div>\n" +
                        "                            </div></hr>";
                    $("#notification").append(html);
                }
            },//ajax引擎一般用不到；状态信息；抛出的异常信息
            error : function() {
                alert("失败了");
            }
        })
    });

    //点击评论
    $("#ct").click(function(){
        $("#ct").children().removeClass("glyphicon-minus").addClass("glyphicon-ok");
        $("#pe").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#td").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#ae").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#notification").empty();
        $.ajax({
            type : "POST",  //请求方式
            url : "commentRemind",  //请求路径
            data : {},
            async:true,
            success : function(data) {  //异步请求成功执行的回调函数
                var html;
                for(var i in data){
                    if(data[parseInt(i)].wb.isTransponpd=="true"){
                        //该微博是转发
                        //用一个用户评论了多条微博
                        for(var j in data[parseInt(i)].commentinfos){
                            html=html+"<!--点赞的通知-->\n" +
                                "                        <div style=\"margin-top: 5px;\">\n" +
                                "                            <div class=\"col-sm-2\" style=\"padding-top: 20px;padding-left: 20px;\">\n" +
                                "                               <a href=\"#\"><img src=\""+
                                data[parseInt(i)].pic+"\" class=\"img-circle\" width=\"50px;\"></a>\n" +
                                "                           </div>\n" +
                                "                           <div class=\"col-sm-8\" style=\"padding-top: 20px;padding-left: 0px;\">\n" +
                                "                             <h5><b>"+
                                data[parseInt(i)].username+"</b><span style=\"color: #999;font-size: 13;\">&nbsp;评论了你的微博</span></h5>\n" +
                                "                             <h6 style=\"color: #999\">"+
                                data[parseInt(i)].commenttime+"</h6>\n" +
                                "                         </div>\n";
                            html=html+"<div class=\"col-sm-10 pull-right\" style=\"padding-bottom: 10px;padding-left: 0px;\">" +
                                "\n评论:"+data[parseInt(i)].commentinfos[parseInt(j)]+"//"+"<a href='#'>@" +data[parseInt(i)].wb.nikename+":</a>"+
                                data[parseInt(i)].wb.weiboInfo+"</div>";
                            html=html+"<div class=\"col-sm-10 pull-right\" style=\"padding: 7px;background-color: #eee;\">";
                            for(var k in data[parseInt(i)].wb.list){
                                if(data[parseInt(i)].wb.list[parseInt(k)].message.messageType=="Transpond"){
                                    html=html+"<a href='#' ><b>@"+
                                        data[parseInt(i)].wb.list[parseInt(k)].user.name+":</b></a>"+ data[parseInt(i)].wb.list[parseInt(k)].message.info;
                                }else {
                                    html=html+"<div class=\"col-sm-12\" style=\"background-color:#eee;max-height: 500px;padding-top: 1rem;padding-left: 0px;\">\n" +
                                        "    <div class=\"col-sm-12 pull-right\">\n" +
                                        "        <a href='#'><b>@"+
                                        data[parseInt(i)].wb.list[parseInt(k)].user.name+"</b></a>\n" +
                                        "        <p>"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.info+"</p>\n" +
                                        "        <br>\n" +
                                        "        <div>\n" +
                                        "            <h6 class=\"pull-left\">"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.messageTime+"</h6>\n" +
                                        "            <h6 class=\"pull-right\"><span class=\"glyphicon glyphicon-link\">"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.messageTranspondnum+"</span>&nbsp;\n" +
                                        "                <span class=\"glyphicon glyphicon-edit\">"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.messageCommentnum+"</span>&nbsp;\n" +
                                        "                <span class=\"glyphicon glyphicon-thumbs-up\">"+
                                        data[parseInt(i)].wb.list[parseInt(k)].message.messageAgreenum+"</span>\n" +
                                        "            </h6>\n" +
                                        "        </div>\n" +
                                        "    </div>\n" +
                                        "</div>";
                                }
                            }
                            html=html+"</div>";
                            html=html+ "                        <hr class=\"col-sm-12\">\n" +
                                "                        <div class=\"col-sm-12 text-center\" style=\"padding-bottom: 10px;\">\n" +
                                "                           <span class=\"glyphicon glyphicon-edit\">回复</span>\n" +
                                "                         </div>\n" +
                                "                        </div><!--点赞通知-->";
                        }
                    }else{
                        //评论的微博为原创
                        for(var j in data[parseInt(i)].commentinfos){
                            html=html+"<!--点赞的通知-->\n" +
                                "                        <div style=\"margin-top: 5px;\">\n" +
                                "                            <div class=\"col-sm-2\" style=\"padding-top: 20px;padding-left: 20px;\">\n" +
                                "                               <a href=\"#\"><img src=\""+
                                data[parseInt(i)].pic+"\" class=\"img-circle\" width=\"50px;\"></a>\n" +
                                "                           </div>\n" +
                                "                           <div class=\"col-sm-8\" style=\"padding-top: 20px;padding-left: 0px;\">\n" +
                                "                             <h5><b>"+
                                data[parseInt(i)].username+"</b><span style=\"color: #999;font-size: 13;\">&nbsp;评论了你的微博</span></h5>\n" +
                                "                             <h6 style=\"color: #999\">"+
                                data[parseInt(i)].commenttime+"</h6>\n" +
                                "                         </div>\n" ;
                            html=html+"<div class=\"col-sm-10 pull-right\" style=\"padding-bottom: 10px;padding-left: 0px;\">" +
                                "\n评论:"+data[parseInt(i)].commentinfos[parseInt(j)]+"</div>";
                            html=html+"                         <div class=\"col-sm-10 pull-right\" style=\"padding: 7px;background-color: #eee;\">\n" +
                                "                            <a href=\"#\" style=\"color: orange;\">@"+
                                data[parseInt(i)].wb.nikename+":</a><span>"+
                                data[parseInt(i)].wb.weiboInfo+"</span>\n" +
                                "                        </div>\n" +
                                "                        <hr class=\"col-sm-12\">\n" +
                                "                        <div class=\"col-sm-12 text-center\" style=\"padding-bottom: 10px;\">\n" +
                                "                           <span class=\"glyphicon glyphicon-edit\">回复</span>\n" +
                                "                         </div>\n" +
                                "                        </div><!--点赞通知-->";
                            html=html+"</div>";

                        }
                    }
                }
                $("#notification").append(html);
            },//ajax引擎一般用不到；状态信息；抛出的异常信息
            error : function() {
                alert("获取评论信息记录失败");
            }
        })
    });
    //点击点赞
    $("#ae").click(function(){
        $("#ae").children().removeClass("glyphicon-minus").addClass("glyphicon-ok");
        $("#pe").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#ct").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#td").children().removeClass("glyphicon-ok").addClass("glyphicon-minus");
        $("#notification").empty();
        $.ajax({
            type : "POST",  //请求方式
            url : "agreeRemind",  //请求路径
            data : {},
            async:true,
            success : function(data) {  //异步请求成功执行的回调函数
                var html;
                for(var i in data){
                    if(data[parseInt(i)].wb.isTransponpd=="true"){
                        //如果该微博为转发微博
                        html=html+"<!--点赞的通知-->\n" +
                            "                        <div style=\"margin-top: 5px;\">\n" +
                            "                            <div class=\"col-sm-2\" style=\"padding-top: 20px;padding-left: 20px;\">\n" +
                            "                               <a href=\"#\"><img src=\""+
                            data[parseInt(i)].pic+"\" class=\"img-circle\" width=\"50px;\"></a>\n" +
                            "                           </div>\n" +
                            "                           <div class=\"col-sm-8\" style=\"padding-top: 20px;padding-left: 0px;\">\n" +
                            "                             <h5><b>"+
                            data[parseInt(i)].username+"</b><span style=\"color: #999;font-size: 13;\">&nbsp;赞了你的微博</span></h5>\n" +
                            "                             <h6 style=\"color: #999\">"+
                            data[parseInt(i)].agreetime+"</h6>\n" +
                            "                         </div>\n";
                        html=html+"<div class=\"col-sm-10 pull-right\" style=\"padding-bottom: 10px;padding-left: 0px;\">\n<a href='#'>@" +data[parseInt(i)].wb.nikename+":</a>"+
                            data[parseInt(i)].wb.weiboInfo+ "</div>\n";
                        html=html+"<div class=\"col-sm-10 pull-right\" style=\"padding: 7px;background-color: #eee;\">";
                        for(var j in data[parseInt(i)].wb.list){
                            if(data[parseInt(i)].wb.list[parseInt(j)].message.messageType=="Transpond"){
                                html=html+"<a href='#' ><b>@"+
                                    data[parseInt(i)].wb.list[parseInt(j)].user.name+":</b></a>"+ data[parseInt(i)].wb.list[parseInt(j)].message.info;
                            }else {
                                html=html+"<div class=\"col-sm-12\" style=\"background-color:#eee;max-height: 500px;padding-top: 1rem;padding-left: 0px;\">\n" +
                                    "    <div class=\"col-sm-12 pull-right\">\n" +
                                    "        <a href='#'><b>@"+
                                    data[parseInt(i)].wb.list[parseInt(j)].user.name+"</b></a>\n" +
                                    "        <p>"+
                                    data[parseInt(i)].wb.list[parseInt(j)].message.info+"</p>\n" +
                                    "        <br>\n" +
                                    "        <div>\n" +
                                    "            <h6 class=\"pull-left\">"+
                                    data[parseInt(i)].wb.list[parseInt(j)].message.messageTime+"</h6>\n" +
                                    "            <h6 class=\"pull-right\"><span class=\"glyphicon glyphicon-link\">"+
                                    data[parseInt(i)].wb.list[parseInt(j)].message.messageTranspondnum+"</span>&nbsp;\n" +
                                    "                <span class=\"glyphicon glyphicon-edit\">"+
                                    data[parseInt(i)].wb.list[parseInt(j)].message.messageCommentnum+"</span>&nbsp;\n" +
                                    "                <span class=\"glyphicon glyphicon-thumbs-up\">"+
                                    data[parseInt(i)].wb.list[parseInt(j)].message.messageAgreenum+"</span>\n" +
                                    "            </h6>\n" +
                                    "        </div>\n" +
                                    "    </div>\n" +
                                    "</div>";
                            }
                        }
                        html=html+"</div>";
                        html=html+ "                        <hr class=\"col-sm-12\">\n" +
                            "                        <div class=\"col-sm-12 text-center\" style=\"padding-bottom: 10px;\">\n" +
                            "                           <span class=\"glyphicon glyphicon-edit\">回复</span>\n" +
                            "                         </div>\n" +
                            "                        </div><!--点赞通知-->";
                    }else {
                        html=html+"<!--点赞的通知-->\n" +
                            "                        <div style=\"margin-top: 5px;\">\n" +
                            "                            <div class=\"col-sm-2\" style=\"padding-top: 20px;padding-left: 20px;\">\n" +
                            "                               <a href=\"#\"><img src=\""+
                            data[parseInt(i)].pic+"\" class=\"img-circle\" width=\"50px;\"></a>\n" +
                            "                           </div>\n" +
                            "                           <div class=\"col-sm-8\" style=\"padding-top: 20px;padding-left: 0px;\">\n" +
                            "                             <h5><b>"+
                            data[parseInt(i)].username+"</b><span style=\"color: #999;font-size: 13;\">&nbsp;赞了你的微博</span></h5>\n" +
                            "                             <h6 style=\"color: #999\">"+
                            data[parseInt(i)].agreetime+"</h6>\n" +
                            "                         </div>\n" +
                            "                         <div class=\"col-sm-10 pull-right\" style=\"padding: 7px;background-color: #eee;\">\n" +
                            "                            <a href=\"#\" style=\"color: orange;\">@"+
                            data[parseInt(i)].wb.nikename+":</a><span>"+
                            data[parseInt(i)].wb.weiboInfo+"</span>\n" +
                            "                        </div>\n" +
                            "                        <hr class=\"col-sm-12\">\n" +
                            "                        <div class=\"col-sm-12 text-center\" style=\"padding-bottom: 10px;\">\n" +
                            "                           <span class=\"glyphicon glyphicon-edit\">回复</span>\n" +
                            "                         </div>\n" +
                            "                        </div><!--点赞通知-->";
                    }
                }
                $("#notification").append(html);
            },//ajax引擎一般用不到；状态信息；抛出的异常信息
            error : function() {
                alert("获取点赞信息记录失败");
            }
        })
    });
});