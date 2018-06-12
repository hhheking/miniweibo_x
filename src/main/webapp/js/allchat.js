
function send(headSrc, str) {
    var html="<div class='send'><div class='msg'><img src="+headSrc+" />"+
        "<p class='msg_input'>"+str+"</p></div></div>";
    upView(html);
}
/*接受消息*/
function show(headSrc,str){
    var html="<div class='show'><div class='msg'><img src="+headSrc+" />"+
        "<p class='msg_input'>"+str+"</p></div></div>";
    upView(html);
}
/*发送时间*/
function addtime(time) {
    var html="<div class=\"time\">"+time+"</div>";
    upView(html);
}
/*得到天*/
function getday(time) {
    var day = time.split(/[- ]/);
    return day[2];
}
/*更新视图*/
function upView(html){
	$('.message').append(html);
    var myDiv =document.getElementById('myDiv');
    myDiv.scrollTop = myDiv.scrollHeight;
}
function sj(){
	return parseInt(Math.random()*10)
}
/*接受消息*/
function open(topic) {
	websocket.send("$$$#"+$("#user").html()+"#"+$("#pic").html());
    $.ajax({
        type:'post',
        url:'chat',
        data:{'user':$("#user").html(),'touser':$("#touser").html()},
        dataType: 'json',
        async:true,

        success: function(data){
            var first = null;
            var day;
            var infos;
           for(i=0;i < data.length;i++){
                infos = data[i].split("#");
                day = getday(infos[1]);
                if(day != first){
                    addtime(infos[1]);
                    first = day;
                }
                if(infos[2] == "show")
                    show($("#pic").html(),infos[0]);
                else
                    send(topic,infos[0]);
           }
            addtime("----以上为历史记录----");
        },

        error:function(){
            alert('ajax 失败');

        }
    });
    }
function message(event) {
    var pic = event.data.split("#");
    send(pic[1],pic[0]);
};
function Chatclick(c){
    websocket = new WebSocket("ws://localhost:8080/websocket");
    var touser = $(c).parent().next().children().eq(0).children().eq(0).html();
    var topic = $(c).children().eq(0).attr("src");
    websocket.onopen = function () {open(topic);}
    websocket.onmessage = function (ev) { message(ev); }
    $("#touser").html(touser);
    $(".bian").show();
    var myDiv =document.getElementById('myDiv');
    myDiv.scrollTop = myDiv.scrollHeight;
}

$(function(){

	$('.footer').on('keyup','input',function(){
		if($(this).val().length>0){
			$(this).next().css('background','#114F8E').prop('disabled',true);
		
		}else{
			$(this).next().css('background','#ddd').prop('disabled',false);
		}
	})
	$('.footer p').click(function(){
        var msg = $("#shu").val();
        websocket.send(msg+"#"+$("#touser").html());
        show($("#pic").html(),msg);
        $("#shu").val("");
	})
    $(".back").click(function(){
        $(".bian").hide();
        websocket.close();
        $(".message").html("");
    });
    $("#chat").click(function(){
       Chatclick();
    });
})
