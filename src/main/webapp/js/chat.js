var webSocket = new WebSocket("ws://localhost:8080/other_person.jsp");

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
webSocket.onopen = function () {
	webSocket.send("$$$#"+$("#user").html()+"#"+$("#pic").html()+"#"+$("#touser").html());

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
                    show("images/"+$("#pic").html()+".png",infos[0]);
                else
                    send("images/"+$("#pic").html()+".png",infos[0]);
           }
        },

        error:function(){
            alert('ajax 失败');

        }
    });
    }
webSocket.onmessage = function (event) {
    var pic = event.data.split("#");
    send("/images/"+pic[1]+".png",pic[0]);
};
$(function(){

	$('.footer').on('keyup','input',function(){
		if($(this).val().length>0){
			$(this).next().css('background','#114F8E').prop('disabled',true);
		
		}else{
			$(this).next().css('background','#ddd').prop('disabled',false);
		}
	})
	$('.footer p').click(function(){
		var message = $(this).prev().val();
		webSocket.send(message+"#"+$("#touser").html());
		show("images/"+$("#pic").html()+".png",message);
		$(this).prev().val("");
	})
    $(".back").click(function(){
        $(".bian").hide();
    });
    $("#chat").click(function(){
        $(".bian").show();
    });
})
