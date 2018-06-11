package action;

import bean.weibo;
import com.opensymphony.xwork2.ActionContext;
import dao.agreeDAO;
import dao.collectionDAO;
import dao.transpondDAO;
import pojo.*;
import service.messageService;
import service.transpondService;
import service.userService;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    userService userservice;
    agreeDAO agreedao;
    weibo weibo;
    User user;
    transpondDAO transponddao;
    collectionDAO collectiondao;

    public agreeDAO getAgreedao() {
        return agreedao;
    }

    public void setAgreedao(agreeDAO agreedao) {
        this.agreedao = agreedao;
    }

    public transpondDAO getTransponddao() {
        return transponddao;
    }

    public void setTransponddao(transpondDAO transponddao) {
        this.transponddao = transponddao;
    }

    public collectionDAO getCollectiondao() {
        return collectiondao;
    }

    public void setCollectiondao(collectionDAO collectiondao) {
        this.collectiondao = collectiondao;
    }

    public userService getUserservice() {
        return userservice;
    }

    public void setUserservice(userService userservice) {
        this.userservice = userservice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public bean.weibo getWeibo() {
        return weibo;
    }

    public void setWeibo(bean.weibo weibo) {
        this.weibo = weibo;
    }

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

    public long timeCount(Message message) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //由毫秒转化为分钟，所以除以1000*60
        //填写测试用例的时候 没有写微博发表的时间 因此这里要稍微修改
        return (timestamp.getTime() - message.getMessageTime().getTime()) / (1000 * 60);
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
    public String delete(){
        messageservice.delete(messageID);
        return "success";
    }
    //跳转到微博的详细内容
    //判断给微博是否为自己发布过的微博
    public String to(){
        weibo=new weibo();
        List<weibo> weiboList = new ArrayList<>();
        //得到需要显示具体信息的微博对象
        message=messageservice.get(messageID);
        //得到发微博的与用户
        user=userservice.get(message.getUserByUserId().getUserId());
        //设置用户的头像
        //
        weibo.setNikename(user.getUserNikename());
        weibo.setTime(timeCount(message));
        weibo.setWeiboInfo(message.getMessageInfo());
        weibo.setTranspond(message.getMessageTranspondnum());
        weibo.setAgree(message.getMessageAgreenum());
        weibo.setComment(message.getMessageCommentnum());
        weibo.setCollect(message.getMessageCollectnum());
        weibo.setMessid(message.getMessageId());
        weibo.setId(user.getUserId());
        List<Agree> agrees = agreedao.findAgree(message.getMessageId(), user.getUserId());
        if (agrees.isEmpty())
            weibo.setAgree_status("no");
        else
            weibo.setAgree_status("yes");
        if (message.getMessageType().equals("Transpond")) {
            weibo.setIsTransponpd("true");
            Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
            int OrignId =0;
            if (transpond!=null)
                OrignId = transpond.getMessageByMessageId().getMessageId();
            weibo.setTranfrommessid(OrignId);
            weiboList.add(weibo);
        }
        else {
            weibo.setIsTransponpd("false");
            weiboList.add(weibo);
        }

        List<Collection> collections = this.collectiondao.list(user.getUserId(), message.getMessageId());
        if (collections.isEmpty())
            weibo.setCollect_status("no");
        else
            weibo.setCollect_status("yes");
        return "messagejsp";
    }
}
