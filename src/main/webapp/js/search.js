$(function(){
    $('#index_sousuo').bind('input propertychange', function(){
        //输入框内容改变后，先改变热搜框的可见属性
        $("#index_panel").css("display","none");
        //执行ajax请求，请求查找的数据
        var str=$("#index_sousuo").val();
        if(str==""){
            $("#searchResult").html("");
            $("#index_panel").css("display","");
            return ;
        }
        $.ajax({
            type:'Post',
            url:'search',
            data:{'keywords':$("#index_sousuo").val()},
            dataType:'json',
            async:true,
            success:function (data) {
                $("#searchResult").html("");
                //解析json数据,拼接成html代码
                var default_result="<div>\n" +
                    "<a href='#' class=\"list-group-item\">搜\"<span id=\"default_weibo\" style=\"color: orange;\">"
                    +str+
                    "</span>\"相关微博</a>";
                var count=0;
                for(var i in data.messages){
                    //i是一个Message对象，要考虑messageInfo过长的问题
                    default_result=default_result+"<a href='toMessage?messageID="+data.messages[parseInt(i)].id+"' class=\"list-group-item\">"
                        +data.messages[parseInt(i)].info+
                        "</a>";
                    count=count+1;
                    //下拉列表最多显示5个
                    if(count>=5){
                        break;
                    }
                }
                default_result=default_result+"<a href=\"#\" class=\"list-group-item\">搜\"<span  style=\"color: orange;\">"
                    +str+
                    "</span>\"相关用户</a>";
                count = 0;
                for(var i in data.users){
                    //i是一个User对象，一般而言，userNikename过长的问题不用考虑
                    default_result=default_result+"<a href='toUser?userid="+data.users[parseInt(i)].id+"' class=\"list-group-item\">\n" +
                        "        <div class=\"row clearfix\">\n" +
                        "            <div class=\"col-sm-1\">\n" +
                        "<img src=\"images/icon.png\" class=\"img-circle\" width=\"38px;\">\n" +
                        "            </div>\n" +
                        "            <div class=\"col-sm-11\" style='padding-left: 30px;'>\n" +
                        "<b>"+data.users[parseInt(i)].name +"</b>"+
                        "                <div>\n" +
                        "粉丝数："+"<span style='color: orange'>"+data.users[parseInt(i)].fans+"</span>"+
                        "                </div>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </a>";
                    count=count+1;
                    //下拉列表最多显示5个
                    if(count>=5){
                        break;
                    }
                }
                default_result=default_result+"</div>";
                $("#searchResult").append(default_result);
                $("#searchResult").slideDown();
            },
            error:function (error) {
                //找不到搜索值的时候也会导致失败
                //清空内容，然后添加div进去
                $("#searchResult").html("");
                var default_result="<div>\n" +
                    "<a href=\"#\" class=\"list-group-item\">搜\"<span id=\"default_weibo\" style=\"color: orange;\">"
                    +str+
                    "</span>\"相关微博</a>\n" +
                    "<a href=\"#\" class=\"list-group-item\">搜\"<span id=\"default_user\"  style=\"color: orange;\">"
                    +str+
                    "</span>\"相关用户</a>\n" +
                    "</div>";
                $("#searchResult").append(default_result);
                $("#searchResult").slideDown();
            }
        });
    });
});