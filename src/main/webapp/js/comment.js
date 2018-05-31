$(function () {
    $(".glyphicon.glyphicon-edit").click(function () {
        var parentdiv=$(this).parent().parent();
        commentdiv=parentdiv.next();
        commentdiv.toggle();
        var messid1=$(this).next().val();
        if(commentdiv.is(":visible")==true){
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
                },//ajax引擎一般用不到；状态信息；抛出的异常信息
                error : function() {
                    alert("失败了");
                }
            });
        }
    })
})
$(function(){
    $(".btn.btn-default.pull-right").click(
        function(){
            var commentinfo=$(this).parent().prev().children().val();
            var userid=$(this).next().next().val();
            var messid=$(this).next().val();
            $.ajax({
                type : "POST",  //请求方式
                url : "addcommentAction",  //请求路径
                data : {
                    'messid' :messid,
                    'userid':userid,
                    'commentinfo':commentinfo
                },
                async:true,
                success : function(data){
                    ;
                },
                error:function(){
                    alert("ajax失败了")
                }
            });

        }
    )
}
)
