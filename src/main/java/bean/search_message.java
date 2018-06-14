package bean;

import java.sql.Timestamp;

public class search_message {
    int id;
    String info;
    String messageType;
    Integer messageCollectnum;
    Integer messageCommentnum;
    Integer messageTranspondnum;
    Integer messageAgreenum;
    Timestamp messageTime;

    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Integer getMessageCollectnum() {
        return messageCollectnum;
    }

    public void setMessageCollectnum(Integer messageCollectnum) {
        this.messageCollectnum = messageCollectnum;
    }

    public Integer getMessageCommentnum() {
        return messageCommentnum;
    }

    public void setMessageCommentnum(Integer messageCommentnum) {
        this.messageCommentnum = messageCommentnum;
    }

    public Integer getMessageTranspondnum() {
        return messageTranspondnum;
    }

    public void setMessageTranspondnum(Integer messageTranspondnum) {
        this.messageTranspondnum = messageTranspondnum;
    }

    public Integer getMessageAgreenum() {
        return messageAgreenum;
    }

    public void setMessageAgreenum(Integer messageAgreenum) {
        this.messageAgreenum = messageAgreenum;
    }
}
