$(function () {
    $("#refresh").click(function () {
        $.ajax({
            type:'Post',
            url:'refreshMessage',
            data:{},
            dataType: 'json',
            async:true,
            success:function (data) {
                $("#right").html("");
                var layout="<div style=\"padding-top: 15px;\">\n" +
                    "            <span><b>相关推荐</b></span>\n" +
                    "            <span id=\"refresh\" class=\"glyphicon glyphicon-refresh pull-right\">刷新</span>\n" +
                    "        </div><hr>";
                for(var i in data){
                    layout=layout+"<div class=\"row\">\n" +
                        "                <!--头像-->\n" +
                        "                <div class=\"col-md-1 column\" style=\"padding-top: 8px;\">\n" +
                        "                    <a href=\"#\"><img src=\""+
                        data[parseInt(i)].image+
                        "\" class=\"img-circle\" width=\"40px;\"></a>\n" +
                        "                </div>\n" +
                        "                <div class=\"col-md-5 column\" style=\"padding-left: 30px;\">\n" +
                        "                    <!--昵称-->\n" +
                        "                    <h5><b>"+
                        data[parseInt(i)].nikename+"</b></h5>\n" +
                        "                    <h6>"+
                        data[parseInt(i)].time+"分钟前</h6>\n" +
                        "                </div>\n" +
                        "                <div class=\"col-md-6 column\">\n" +
                        "                    <!--关注按钮-->\n" +
                        "                    <div class=\"pull-right\" style=\"margin-top: 5px;\">\n" +
                        "                        <div  style=\"text-align: center;padding:3px;background-color: orange;\n" +
                        "                    cursor: pointer;\"><span style=\"color: white; \">+关注</span></div>\n" +
                        "                    </div>\n" +
                        "                </div>\n" +
                        "                <!--微博具体内容-->\n" +
                        "                <div class=\"col-md-12 column\">\n" +
                        "                    <div class=\"col-md-1 column\">\n" +
                        "                    </div>\n" +
                        "                    <div class=\"col-md-11 column\">\n" +
                        data[parseInt(i)].weiboInfo +
                        "                    </div>\n" +
                        "                </div>\n" +
                        "                <div>\n" +
                        "                    <!--点赞、评论、转发-->\n" +
                        "                    <h6 class=\"pull-right\" style=\"padding-right: 10px;\">\n" +
                        "                        <span class=\"glyphicon glyphicon-link\">"+
                        data[parseInt(i)].transpond+"</span>&nbsp;\n" +
                        "                        <span class=\"glyphicon glyphicon-edit\">"+
                        data[parseInt(i)].comment+"</span>&nbsp;\n" +
                        "                        <span class=\"glyphicon glyphicon-thumbs-up\">"+
                        data[parseInt(i)].agree+"</span>\n" +
                        "                    </h6>\n" +
                        "                </div>\n" +
                        "            </div>\n" +
                        "            <hr>";
                }
                $("#right").append(layout);
            },
            error:function (err) {

            }
        });
    });
});