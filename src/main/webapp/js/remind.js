$(function () {
    setInterval(function () {
        $.ajax({
            type : "POST",  //请求方式
            url : "rootRemind",  //请求路径
            data : {},
            async:true,
            success : function(data) {  //异步请求成功执行的回调函数
                    $("#tip").html(data[4]);
                    $("#tip1").html(data[0]);
                    $("#tip2").html(data[1]);
                    $("#tip3").html(data[2]);
                    $("#tip4").html(data[3]);
            },//ajax引擎一般用不到；状态信息；抛出的异常信息
            error : function() {
                alert("失败了");
            }
        })
   },5000);
});