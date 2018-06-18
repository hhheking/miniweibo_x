$(function () {
    $("#index_sousuo").focus(function(){
        //执行ajax请求,将数据显示在div中
        $.ajax({
            type:'Post',
            url:'hotSearch',
            data:{},
            dataType:'json',
            async:true,
            success:function (data) {
                $("#index_panel").html("");
                var hotsearch="";
                for(var i in data){
                    hotsearch=hotsearch+"<a href=\"toMessage?messageID="+
                        data[parseInt(i)].id+"\" " +
                        "class=\"list-group-item\">";
                    if(parseInt(i)==0){
                        hotsearch=hotsearch+"<span class=\"badge\" style=\"background-color: #FF4500;\">爆</span>" +
                            (parseInt(i)+1)+"."+data[parseInt(i)].info+"</a>";
                    }else if(parseInt(i)==1){
                        hotsearch=hotsearch+"<span class=\"badge\" style=\"background-color: #FF0000;\">热</span>" +
                            (parseInt(i)+1)+"."+data[parseInt(i)].info+"</a>";
                    }else if(parseInt(i)==2){
                        hotsearch=hotsearch+"<span class=\"badge\" style=\"background-color: #DC143C;\">新</span>" +
                            (parseInt(i)+1)+"."+data[parseInt(i)].info+"</a>";
                    }else {
                        hotsearch=hotsearch+ (parseInt(i)+1)+"."+data[parseInt(i)].info+"</a>";
                    }

                }
                $("#index_panel").append(hotsearch);
                $("#index_panel").slideDown();
            },
            error:function (err) {
                alert("热搜失败");
            }
        });
    });
    $("#index_sousuo").blur(function(){
        setTimeout(function(){
            $("#index_panel").slideUp();
        }, 300);
        setTimeout(function(){
            $("#searchResult").slideUp();
        }, 300);
    });
});