package action;

import com.opensymphony.xwork2.ActionContext;
import pojo.Message;
import pojo.User;
import service.messageService;

import java.util.List;
import java.util.Map;

public class messageAction {
    Message message;
    List<Message> messageList;
    messageService messageservice;
    int weibo_num;

    public List<Message> getMessageList() {
        return messageList;
    }

    public Message getMessage() {
        return message;
    }

    public messageService getMessageservice() {
        return messageservice;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public void setMessageservice(messageService messageservice) {
        this.messageservice = messageservice;
    }
    public String count(){
        messageList=messageservice.list();
        //获取登录用户的javabean
        Map<String, Object> session = ActionContext.getContext().getSession();
        User u=(User)session.get("user");
        for(Message message:messageList){
            if(message.getUserByUserId().getUserId()==u.getUserId()){
                weibo_num++;
            }
        }
        return "home";
    }
}
