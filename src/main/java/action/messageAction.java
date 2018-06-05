package action;

import com.opensymphony.xwork2.ActionContext;
import pojo.Message;
import pojo.User;
import service.messageService;
import service.transpondService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class messageAction {
    Message message;
    List<Message> messageList;
    messageService messageservice;
    String messageInfo;
    String messagrReason;
    int messageID;
    String message_username;
    transpondService transpondservice;

    public String getMessage_username() {
        return message_username;
    }

    public void setMessage_username(String message_username) {
        this.message_username = message_username;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getMessagrReason() {
        return messagrReason;
    }

    public void setMessagrReason(String messagrReason) {
        this.messagrReason = messagrReason;
    }


    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

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

    public transpondService getTranspondservice() {
        return transpondservice;
    }

    public void setTranspondservice(transpondService transpondservice) {
        this.transpondservice = transpondservice;
    }

    public String send(){
        message=messageservice.message(messageInfo);
        messageservice.add(message);
        return "success";
    }
    public String trans(){
        message=messageservice.transmessage(messagrReason,messageID);
        messageservice.add(message);
        transpondservice.add(messageID,message.getMessageId());
        return "success1";
    }
}
