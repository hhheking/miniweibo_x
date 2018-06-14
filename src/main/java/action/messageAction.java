package action;

import bean.weibo;
import com.opensymphony.xwork2.ActionContext;
import dao.agreeDAO;
import dao.collectionDAO;
import dao.transpondDAO;
import pojo.*;
import service.messageService;
import service.relationService;
import service.transpondService;
import service.userService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
    List<weibo> refreshweibos=new ArrayList<>();
    relationService relationservice;

    public void setRelationservice(relationService relationservice) {
        this.relationservice = relationservice;
    }

    public relationService getRelationservice() {
        return relationservice;
    }

    public List<weibo> getRefreshweibos() {
        return refreshweibos;
    }

    public void setRefreshweibos(List<weibo> refreshweibos) {
        this.refreshweibos = refreshweibos;
    }

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
        messageID = message.getMessageId();
        return "success";
    }
    public String trans(){
        message=messageservice.transmessage(messagrReason,messageID);
        messageservice.add(message);
        transpondservice.add(messageID,message.getMessageId());
        return "success1";
    }

    public String refresh(){
        int count=0;
        User user1=(User) ActionContext.getContext().getSession().get("user");
        //userList为所有的用户
        List<User> userList=userservice.list();
        //搜索我没有关注的人微博
        for(Relation relation:relationservice.myIdols(user1)){
            //user2为我关注的用户
            User user2=userservice.get(relation.getUserByUserByid().getUserId());
            //userList去掉了所有登录用户关注的人
            userList.removeIf(new Predicate<User>() {
                @Override
                public boolean test(User user) {
                    if(user2.getUserId()==user.getUserId())
                        return true;
                    else
                        return false;
                }
            });
        }
        for(User user:userList){
            weibo wb=new weibo();
            List<weibo> weiboList = new ArrayList<>();
            messageList=messageservice.myMessage(user);
            //如果该用户没有法国微博，则找到下一个用户
            if(messageList.isEmpty()){
                continue;
            }
            else {
                //设置用户的头像
                wb.setImage(user1.getIcon());
                wb.setNikename(user1.getUserNikename());
                wb.setTime(timeCount(message));
                wb.setWeiboInfo(message.getMessageInfo());
                wb.setTranspond(message.getMessageTranspondnum());
                wb.setAgree(message.getMessageAgreenum());
                wb.setComment(message.getMessageCommentnum());
                wb.setCollect(message.getMessageCollectnum());
                wb.setMessid(message.getMessageId());
                wb.setId(user1.getUserId());
                List<Agree> agrees = agreedao.findAgree(message.getMessageId(), user1.getUserId());
                if (agrees.isEmpty())
                    wb.setAgree_status("no");
                else
                    wb.setAgree_status("yes");
                if (message.getMessageType().equals("Transpond")) {
                    wb.setIsTransponpd("true");
                    Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
                    int OrignId =0;
                    if (transpond!=null)
                        OrignId = transpond.getMessageByMessageId().getMessageId();
                    wb.setTranfrommessid(OrignId);
                    weiboList.add(wb);
                }
                else {
                    wb.setIsTransponpd("false");
                    weiboList.add(wb);
                }

                List<Collection> collections = this.collectiondao.list(user1.getUserId(), message.getMessageId());
                if (collections.isEmpty())
                    wb.setCollect_status("no");
                else
                    wb.setCollect_status("yes");
                refreshweibos.add(wb);
                count++;
                //刷新部分只显示5条微博
                if(count==5){
                    break;
                }
            }
        }
        return "success2";
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
        //得到发微博的用户
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
        //刷新右侧推荐的微博
        refresh();
        return "messagejsp";
    }
}
