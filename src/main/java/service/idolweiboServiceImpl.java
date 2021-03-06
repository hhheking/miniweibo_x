package service;

import bean.transInfo;
import bean.weibo;
import com.opensymphony.xwork2.ActionContext;
import dao.*;
import pojo.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class idolweiboServiceImpl implements idolweiboService {
    messageService messageservice;
    relationService relationservice;
    userService userservice;
    messageDAO messagedao;
    agreeDAO agreedao;
    collectionDAO collectiondao;
    transpondDAO transponddao;
    userDAO userdao;

    public userDAO getUserdao() {
        return userdao;
    }

    public void setUserdao(userDAO userdao) {
        this.userdao = userdao;
    }

    public messageDAO getMessagedao() {
        return messagedao;
    }

    public void setMessagedao(messageDAO messagedao) {
        this.messagedao = messagedao;
    }

    public transpondDAO getTransponddao() {
        return transponddao;
    }

    public void setTransponddao(transpondDAO transponddao) {
        this.transponddao = transponddao;
    }

    public relationService getRelationservice() {
        return relationservice;
    }

    public void setRelationservice(relationService relationservice) {
        this.relationservice = relationservice;
    }

    public messageService getMessageservice() {
        return messageservice;
    }

    public void setMessageservice(messageService messageservice) {
        this.messageservice = messageservice;
    }

    public userService getUserservice() {
        return userservice;
    }

    public void setUserservice(userService userservice) {
        this.userservice = userservice;
    }

    public void setAgreedao(agreeDAO agreedao) {
        this.agreedao = agreedao;
    }

    public agreeDAO getAgreedao() {
        return agreedao;
    }

    public void setCollectiondao(collectionDAO collectiondao) {
        this.collectiondao = collectiondao;
    }

    public collectionDAO getCollectiondao() {
        return collectiondao;
    }

    @Override
    public String imageaddress() {
        return null;
    }

    //user指的是登录用户的对象
    @Override
    public List<User> idols(User user) {
        List<User> userList = new ArrayList<>();
        for (Relation relation : relationservice.list()) {
            if (relation.getUserByUserId().getUserId() == user.getUserId())
                userList.add(userservice.get(relation.getUserByUserByid().getUserId()));
        }
        userList.add(user);
        return userList;
    }


    @Override
    public long timeCount(Message message) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //由毫秒转化为分钟，所以除以1000*60
        //填写测试用例的时候 没有写微博发表的时间 因此这里要稍微修改
        return (timestamp.getTime() - message.getMessageTime().getTime()) / (1000 * 60);
    }

    //此处的user为登录用户的实例
    @Override
    public List<weibo> calidolweibos(User user) {
        List<weibo> weiboList = new ArrayList<>();
        for (User user1 : idols(user)) {
            for (Message message : messageservice.myMessage(user1)) {
                weibo wb = new weibo();
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
                List<Agree> agrees = agreedao.findAgree(message.getMessageId(), user.getUserId());
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

                List<Collection> collections = this.collectiondao.list(user.getUserId(), message.getMessageId());
                if (collections.isEmpty())
                    wb.setCollect_status("no");
                else
                    wb.setCollect_status("yes");
            }
        }
        //此处按照微博发布的时间先后顺序 进行排序
        weiboList.sort(new Comparator<weibo>() {
            @Override
            public int compare(weibo o1, weibo o2) {
                return Long.compare(o1.getTime(), o2.getTime());
            }
        });
        for (int i = 0; i < weiboList.size(); i++) {
            Message message = messagedao.get(weiboList.get(i).getMessid());
            List<transInfo> list = new ArrayList<>();
            while (message.getMessageType().equals("Transpond")) {
                Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
                int OrignId =0;
                if (transpond!=null)
                    OrignId = transpond.getMessageByMessageId().getMessageId();
                message = messagedao.get(OrignId);
                User u;
                if(message==null){
                    message=new Message();
                    message.setMessageInfo("转发微博被删除");
                    message.setMessageType("Orign");
                    u=new User();
                }
                else {
                    u = userdao.get(message.getUserByUserId().getUserId());
                }
                weiboList.get(i).setTranList(list);
                transInfo transinfo = new transInfo();
                transinfo.setMessage(message);
                transinfo.setUser(u);
                list.add(transinfo);
            }
        }
        return weiboList;
    }

    //计算自己的微博
    @Override
    public List<weibo> Myweibos(User user1) {
        List<weibo> weiboList = new ArrayList<>();
            for (Message message : messageservice.myMessage(user1)) {
                weibo wb = new weibo();
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
                User sessionuser=(User)ActionContext.getContext().getSession().get("user");
                //当前未登录
                if(sessionuser==null){
                    wb.setAgree_status("no");
                    wb.setCollect_status("no");
                }
                //当前已登录
                else {
                    List<Agree> agrees = agreedao.findAgree(message.getMessageId(), sessionuser.getUserId());
                    if (agrees.isEmpty())
                        wb.setAgree_status("no");
                    else
                        wb.setAgree_status("yes");

                    List<Collection> collections = this.collectiondao.list(sessionuser.getUserId(), message.getMessageId());
                    if (collections.isEmpty())
                        wb.setCollect_status("no");
                    else
                        wb.setCollect_status("yes");
                }
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
            }
        //此处按照微博发布的时间先后顺序 进行排序
        weiboList.sort(new Comparator<weibo>() {
            @Override
            public int compare(weibo o1, weibo o2) {
                return Long.compare(o1.getTime(), o2.getTime());
            }
        });
        for (int i = 0; i < weiboList.size(); i++) {
            Message message = messagedao.get(weiboList.get(i).getMessid());
            List<transInfo> list = new ArrayList<>();
            while (message.getMessageType().equals("Transpond")) {
                Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
                int OrignId =0;
                if (transpond!=null)
                    OrignId = transpond.getMessageByMessageId().getMessageId();
                message = messagedao.get(OrignId);
                User u;
                if(message==null){
                    message=new Message();
                    message.setMessageInfo("转发微博被删除");
                    message.setMessageType("Orign");
                    u=new User();
                }
                else {
                    u = userdao.get(message.getUserByUserId().getUserId());
                }
                transInfo transinfo = new transInfo();
                transinfo.setMessage(message);
                transinfo.setUser(u);
                list.add(transinfo);
            }
            weiboList.get(i).setTranList(list);
        }
        return weiboList;
    }
    @Override
    public List<weibo> weiboList(){
        int count=0;
        List<weibo> weiboList = new ArrayList<>();
        List<Message> messageList=messageservice.list();
        messageList.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return Long.compare(o2.getMessageAgreenum()+o2.getMessageCommentnum()+o2.getMessageCollectnum()+o2.getMessageTranspondnum(),
                        o1.getMessageAgreenum()+o1.getMessageCommentnum()+o1.getMessageCollectnum()+o1.getMessageTranspondnum());
            }
        });
        User user1;
        for(Message message:messageList){
            weibo wb = new weibo();
            user1=userservice.get(message.getUserByUserId().getUserId());
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
            User user=(User)ActionContext.getContext().getSession().get("user");
            List<Agree> agrees = agreedao.findAgree(message.getMessageId(), user.getUserId());
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
            }
            else {
                wb.setIsTransponpd("false");
            }
            List<Collection> collections = this.collectiondao.list(user.getUserId(), message.getMessageId());
            if (collections.isEmpty())
                wb.setCollect_status("no");
            else
                wb.setCollect_status("yes");
            count++;
            if(count>=7){
                break;
            }
            weiboList.add(wb);
        }
        //weibos要重新赋值 weibos;
        for (int i = 0; i < weiboList.size(); i++) {
            Message message = messageservice.get(weiboList.get(i).getMessid());
            List<transInfo> list = new ArrayList<>();
            while (message.getMessageType().equals("Transpond")) {
                Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
                int OrignId =0;
                if (transpond!=null)
                    OrignId = transpond.getMessageByMessageId().getMessageId();
                message = messagedao.get(OrignId);
                User u;
                if(message==null){
                    message=new Message();
                    message.setMessageInfo("转发微博被删除");
                    message.setMessageType("Orign");
                    u=new User();
                }
                else {
                    u = userdao.get(message.getUserByUserId().getUserId());
                }
                weiboList.get(i).setTranList(list);
                transInfo transinfo = new transInfo();
                transinfo.setMessage(message);
                transinfo.setUser(u);
                list.add(transinfo);
            }
        }
        return weiboList;
    }

    @Override
    public List<weibo> mycollections() {
        User user=(User)ActionContext.getContext().getSession().get("user");
        List<weibo> weiboList=new ArrayList<>();
        List<Collection> collections=collectiondao.listMycollections(user.getUserId());
        for(Collection collection:collections){
            Message message=messagedao.get(collection.getMessageByMessageId().getMessageId());
            weibo wb=new weibo();
            wb.setImage(user.getIcon());
            wb.setNikename(user.getUserNikename());
            wb.setId(user.getUserId());
            wb.setTime(timeCount(message));
            wb.setWeiboInfo(message.getMessageInfo());
            wb.setTranspond(message.getMessageTranspondnum());
            wb.setAgree(message.getMessageAgreenum());
            wb.setComment(message.getMessageCommentnum());
            wb.setCollect(message.getMessageCollectnum());
            wb.setMessid(message.getMessageId());
            wb.setCollect_status("yes");
            List<Agree> agrees = agreedao.findAgree(message.getMessageId(), user.getUserId());
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
            }
            else {
                wb.setIsTransponpd("false");
            }
            weiboList.add(wb);
        }
        for (int i = 0; i < weiboList.size(); i++) {
            Message message = messageservice.get(weiboList.get(i).getMessid());
            List<transInfo> list = new ArrayList<>();
            while (message.getMessageType().equals("Transpond")) {
                Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
                int OrignId =0;
                if (transpond!=null)
                    OrignId = transpond.getMessageByMessageId().getMessageId();
                message = messagedao.get(OrignId);
                User u;
                if(message==null){
                    message=new Message();
                    message.setMessageInfo("转发微博被删除");
                    message.setMessageType("Orign");
                    u=new User();
                }
                else {
                    u = userdao.get(message.getUserByUserId().getUserId());
                }
                transInfo transinfo = new transInfo();
                transinfo.setMessage(message);
                transinfo.setUser(u);
                list.add(transinfo);
            }
            weiboList.get(i).setTranList(list);
        }
        return weiboList;
    }

    @Override
    public List<weibo> myagrees() {
        User user=(User)ActionContext.getContext().getSession().get("user");
        List<weibo> weiboList=new ArrayList<>();
        List<Agree> agrees=agreedao.myagrees(user.getUserId());
        for(Agree agree:agrees){
            Message message=messagedao.get(agree.getMessageByMessageId().getMessageId());
            weibo wb=new weibo();
            wb.setImage(user.getIcon());
            wb.setNikename(user.getUserNikename());
            wb.setId(user.getUserId());
            wb.setTime(timeCount(message));
            wb.setWeiboInfo(message.getMessageInfo());
            wb.setTranspond(message.getMessageTranspondnum());
            wb.setAgree(message.getMessageAgreenum());
            wb.setComment(message.getMessageCommentnum());
            wb.setCollect(message.getMessageCollectnum());
            wb.setMessid(message.getMessageId());
            wb.setAgree_status("yes");
            List<Collection> collections = this.collectiondao.list(user.getUserId(), message.getMessageId());
            if (collections.isEmpty())
                wb.setCollect_status("no");
            else
                wb.setCollect_status("yes");
            if (message.getMessageType().equals("Transpond")) {
                wb.setIsTransponpd("true");
                Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
                int OrignId =0;
                if (transpond!=null)
                    OrignId = transpond.getMessageByMessageId().getMessageId();
                wb.setTranfrommessid(OrignId);
            }
            else {
                wb.setIsTransponpd("false");
            }
            weiboList.add(wb);
        }
        for (int i = 0; i < weiboList.size(); i++) {
            Message message = messageservice.get(weiboList.get(i).getMessid());
            List<transInfo> list = new ArrayList<>();
            while (message.getMessageType().equals("Transpond")) {
                Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
                int OrignId =0;
                if (transpond!=null)
                    OrignId = transpond.getMessageByMessageId().getMessageId();
                message = messagedao.get(OrignId);
                User u;
                if(message==null){
                    message=new Message();
                    message.setMessageInfo("转发微博被删除");
                    message.setMessageType("Orign");
                    u=new User();
                }
                else {
                    u = userdao.get(message.getUserByUserId().getUserId());
                }
                transInfo transinfo = new transInfo();
                transinfo.setMessage(message);
                transinfo.setUser(u);
                list.add(transinfo);
            }
            weiboList.get(i).setTranList(list);
        }
        return weiboList;
    }

    @Override
    public List<weibo> index() {
        int count=0;
        List<weibo> weiboList = new ArrayList<>();
        List<Message> messageList=messageservice.list();
        messageList.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return Long.compare(o2.getMessageAgreenum()+o2.getMessageCommentnum()+o2.getMessageCollectnum()+o2.getMessageTranspondnum(),
                        o1.getMessageAgreenum()+o1.getMessageCommentnum()+o1.getMessageCollectnum()+o1.getMessageTranspondnum());
            }
        });
        User user1;
        for(Message message:messageList){
            weibo wb = new weibo();
            user1=userservice.get(message.getUserByUserId().getUserId());
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
            wb.setAgree_status("no");
            wb.setCollect_status("no");
            if (message.getMessageType().equals("Transpond")) {
                wb.setIsTransponpd("true");
                Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
                int OrignId =0;
                if (transpond!=null)
                    OrignId = transpond.getMessageByMessageId().getMessageId();
                wb.setTranfrommessid(OrignId);
            }
            else {
                wb.setIsTransponpd("false");
            }
            count++;
            if(count>=10){
                break;
            }
            weiboList.add(wb);
        }
        //weibos要重新赋值 weibos;
        for (int i = 0; i < weiboList.size(); i++) {
            Message message = messageservice.get(weiboList.get(i).getMessid());
            List<transInfo> list = new ArrayList<>();
            while (message.getMessageType().equals("Transpond")) {
                Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
                int OrignId =0;
                if (transpond!=null)
                    OrignId = transpond.getMessageByMessageId().getMessageId();
                message = messagedao.get(OrignId);
                User u;
                if(message==null){
                    message=new Message();
                    message.setMessageInfo("转发微博被删除");
                    message.setMessageType("Orign");
                    u=new User();
                }
                else {
                    u = userdao.get(message.getUserByUserId().getUserId());
                }
                weiboList.get(i).setTranList(list);
                transInfo transinfo = new transInfo();
                transinfo.setMessage(message);
                transinfo.setUser(u);
                list.add(transinfo);
            }
        }
        return weiboList;
    }

}
