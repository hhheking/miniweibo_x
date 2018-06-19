package action;

import bean.*;
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
    List<agreeWB> refreshweibos;
    relationService relationservice;
    private agreeWB wb;

    public agreeWB getWb() {
        return wb;
    }

    public void setWb(agreeWB wb) {
        this.wb = wb;
    }

    public void setRelationservice(relationService relationservice) {
        this.relationservice = relationservice;
    }

    public relationService getRelationservice() {
        return relationservice;
    }

    public void setRefreshweibos(List<agreeWB> refreshweibos) {
        this.refreshweibos = refreshweibos;
    }

    public List<agreeWB> getRefreshweibos() {
        return refreshweibos;
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
        wb=new agreeWB();
        //得到被转发的微博对象
        Message message1=messageservice.get(messageID);
        User user1=userservice.get(message1.getUserByUserId().getUserId());
        wb.setTimestamp(message1.getMessageTime());
        wb.setNikename(user1.getUserNikename());
        wb.setId(user1.getUserId());
        wb.setWeiboInfo(message1.getMessageInfo());
        wb.setTranspond(message1.getMessageTranspondnum());
        wb.setAgree(message1.getMessageAgreenum());
        wb.setComment(message1.getMessageCommentnum());
        wb.setMessid(message1.getMessageId());
        wb.setCollect(message1.getMessageCollectnum());
        if(message1.getMessageType().equals("Transpond")){
            wb.setIsTransponpd("true");
        }
        else {
            wb.setIsTransponpd("false");
        }
        Message message2 = messageservice.get(wb.getMessid());
        List<transweibo> transweibos=new ArrayList<>();
        while (message2.getMessageType().equals("Transpond")){
            search_message searchMessage=new search_message();
            Transpond transpond=transponddao.findTranspondFrom(message2.getMessageId());
            int OrignId=0;
            if(transpond!=null)
                OrignId=transpond.getMessageByMessageId().getMessageId();
            message2=messageservice.get(OrignId);
            User u;
            search_user searchUser=new search_user();
            if(message2==null){
                message2=new Message();
                searchMessage.setInfo("转发微博已删除");
                message2.setMessageType("Orign");
                searchMessage.setMessageType("Orign");
                u=new User();
            }else {
                searchMessage.setMessageType(message2.getMessageType());
                searchMessage.setMessageTime(message2.getMessageTime());
                searchMessage.setMessageTranspondnum(message2.getMessageTranspondnum());
                searchMessage.setMessageAgreenum(message2.getMessageAgreenum());
                searchMessage.setMessageCommentnum(message2.getMessageCommentnum());
                searchMessage.setMessageCollectnum(message2.getMessageCollectnum());
                searchMessage.setInfo(message2.getMessageInfo());
                searchMessage.setId(message2.getMessageId());
                u=userservice.get(message2.getUserByUserId().getUserId());
                searchUser.setName(u.getUserNikename());
                searchUser.setImageurl(u.getIcon());
                searchUser.setId(u.getUserId());
            }
            transweibo transweibo=new transweibo();
            transweibo.setUser(searchUser);
            transweibo.setMessage(searchMessage);
            transweibos.add(transweibo);
        }
        wb.setList(transweibos);
        return "success1";
    }

    public String refresh(){
        refreshweibos=new ArrayList<>();
        int count=0;
        User user1=(User) ActionContext.getContext().getSession().get("user");
        //userList为所有的用户
        List<User> userList=userservice.list();
        //搜索我没有关注的人微博
        //当前用户没有登录
        if(user1==null){

        }else{
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
            //移除登录用户自己
            userList.removeIf(new Predicate<User>() {
                @Override
                public boolean test(User user) {
                    if(user1.getUserId()==user.getUserId()){
                        return true;
                    }else{
                        return false;
                    }
                }
            });
        }
        //userList为自己未关注的人的名单
        //推荐微博选择原创微博
        for(User user:userList){
            agreeWB wb=new agreeWB();
            messageList=messageservice.myMessage(user);
            //如果该用户没有法国微博，则找到下一个用户
            int flag=0;
            if(messageList.isEmpty()){
                continue;
            }
            else {
                //选择一条用户的原创微博
                for(Message message1:messageList){
                    if(message1.getMessageType().equals("Orign")){
                        message=message1;
                        flag=1;
                        break;
                    }
                }
                //当前用户没有原创微博
                if(flag==0){
                    continue;
                }
                //设置用户的头像
                wb.setImage(user.getIcon());
                wb.setNikename(user.getUserNikename());
                wb.setTime(timeCount(message));
                wb.setWeiboInfo(message.getMessageInfo());
                wb.setAgree(message.getMessageAgreenum());
                wb.setComment(message.getMessageCommentnum());
                wb.setCollect(message.getMessageCollectnum());
                wb.setMessid(message.getMessageId());
                wb.setId(user.getUserId());
                //刷新部分最多显示5条微博
                if(count==5){
                    break;
                }
            }
            refreshweibos.add(wb);
            count++;
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
        //得到需要显示具体信息的微博对象
        message=messageservice.get(messageID);
        //得到发微博的用户
        user=userservice.get(message.getUserByUserId().getUserId());
        //设置用户的头像
        //
        weibo.setImage(user.getIcon());
        weibo.setNikename(user.getUserNikename());
        weibo.setTime(timeCount(message));
        weibo.setWeiboInfo(message.getMessageInfo());
        weibo.setTranspond(message.getMessageTranspondnum());
        weibo.setAgree(message.getMessageAgreenum());
        weibo.setComment(message.getMessageCommentnum());
        weibo.setCollect(message.getMessageCollectnum());
        weibo.setMessid(message.getMessageId());
        weibo.setId(user.getUserId());
        User user1=(User)ActionContext.getContext().getSession().get("user");
        if(user1==null){
            //当前用户没有登录
            weibo.setAgree_status("no");
            weibo.setCollect_status("no");
        }else{
            List<Agree> agrees = agreedao.findAgree(message.getMessageId(), user1.getUserId());
            if (agrees.isEmpty())
                weibo.setAgree_status("no");
            else
                weibo.setAgree_status("yes");
            List<Collection> collections = this.collectiondao.list(user1.getUserId(), message.getMessageId());
            if (collections.isEmpty())
                weibo.setCollect_status("no");
            else
                weibo.setCollect_status("yes");
        }
        if (message.getMessageType().equals("Transpond")) {
            weibo.setIsTransponpd("true");
            Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
            int OrignId =0;
            if (transpond!=null)
                OrignId = transpond.getMessageByMessageId().getMessageId();
            weibo.setTranfrommessid(OrignId);
        }
        else {
            weibo.setIsTransponpd("false");
        }
        Message message1 = messageservice.get(message.getMessageId());
        List<transInfo> list = new ArrayList<>();
        while (message1.getMessageType().equals("Transpond")) {
            Transpond transpond=transponddao.findTranspondFrom(message1.getMessageId());
            int OrignId =0;
            if (transpond!=null)
                OrignId = transpond.getMessageByMessageId().getMessageId();
            message1 = messageservice.get(OrignId);
            User u;
            if(message1==null){
                message1=new Message();
                message1.setMessageInfo("转发微博被删除");
                message1.setMessageType("Orign");
                u=new User();
            }
            else {
                u = userservice.get(message1.getUserByUserId().getUserId());
            }
            transInfo transinfo = new transInfo();
            transinfo.setMessage(message1);
            transinfo.setUser(u);
            list.add(transinfo);
        }
        weibo.setTranList(list);
        //刷新右侧推荐的微博
        refresh();
        return "messagejsp";
    }
}
