package action;

import bean.fan;
import bean.transInfo;
import bean.weibo;
import com.opensymphony.xwork2.ActionContext;
import dao.agreeDAO;
import dao.collectionDAO;
import dao.transpondDAO;
import pojo.*;
import service.idolweiboService;
import service.messageService;
import service.relationService;
import service.userService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class userAction {
    User user;
    List<User> userList;
    userService userservice;
    messageService messageservice;
    relationService relationservice;
    idolweiboService idolweiboservice;
    List<Message> mymessageList;
    int fans;
    int idols;
    List<weibo> weibos;
    String status;
    int id;
    List<User> idolsuser;
    List<fan> fanList;

    public List<fan> getFanList() {
        return fanList;
    }

    public void setFanList(List<fan> fanList) {
        this.fanList = fanList;
    }

    public List<User> getIdolsuser() {
        return idolsuser;
    }

    public void setIdolsuser(List<User> idolsuser) {
        this.idolsuser = idolsuser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public idolweiboService getIdolweiboservice() {
        return idolweiboservice;
    }

    public void setIdolweiboservice(idolweiboService idolweiboservice) {
        this.idolweiboservice = idolweiboservice;
    }

    public List<weibo> getWeibos() {
        return weibos;
    }

    public void setWeibos(List<weibo> weibos) {
        this.weibos = weibos;
    }

    public List<Message> getMymessageList() {
        return mymessageList;
    }

    public void setMymessageList(List<Message> mymessageList) {
        this.mymessageList = mymessageList;
    }

    public void setMessageservice(messageService messageservice) {
        this.messageservice = messageservice;
    }

    public messageService getMessageservice() {
        return messageservice;
    }


    public void setIdols(int idols) {
        this.idols = idols;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getIdols() {
        return idols;
    }

    public int getFans() {
        return fans;
    }

    public void setRelationservice(relationService relationservice) {
        this.relationservice = relationservice;
    }

    public relationService getRelationservice() {
        return relationservice;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserservice(userService userservice) {
        this.userservice = userservice;
    }

    public userService getUserservice() {
        return userservice;
    }
    public String list(){
        userList=userservice.list();
        return "listuser";
    }

    public String login(){
        if(user.getUserNikename().equals("admin")){
            return "Manage";
        }
        if(userservice.login(user)){
            return tohot();
        }else {
            //登录失败
            return "loginfail";
        }
    }

    public String register(){
        //用户注册的时间
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        user.setUserTime(timestamp);
        userservice.register(user);
        return "loginuser";
    }

    public String personspace(){
        //进入自己的空间
        Map<String, Object> session = ActionContext.getContext().getSession();
        user=(User)session.get("user");
        if(user==null){
            return "loginuser";
        }
        fans=relationservice.calfans(user);
        idols=relationservice.calidols(user);
        mymessageList=messageservice.myMessage(user);
        weibos=idolweiboservice.Myweibos(user);
        return "personspace";
    }
    public String to(){
        //得到session中的user实例
        Map<String, Object> session = ActionContext.getContext().getSession();
        User user1=(User)session.get("user");
        Map Getid=ActionContext.getContext().getParameters();
        String [] userid= (String[]) Getid.get("userid");
        id=Integer.parseInt(userid[0]);
        user=userservice.get(id);
        status="+关注";
        if(user1==null){
            //用户未登录
        }else{
            //得到登录用户的所有关注的人
            if(user1.getUserNikename().equals(user.getUserNikename())){
                //点击的头像为本人,根据用户名来判断
                return personspace();
            }
            //点击的头像不为本人
            for(Relation relation:relationservice.myIdols(user1)){
                if(relation.getUserByUserByid().getUserId()==user.getUserId()){
                    status="已关注";
                }
            }
        }
        fans=relationservice.calfans(user);
        idols=relationservice.calidols(user);
        mymessageList=messageservice.myMessage(user);
        weibos=idolweiboservice.Myweibos(user);
        return "other_person";
    }

    public String idol(){
        //进入个人主页 显示所有关注的人
        Map<String, Object> session = ActionContext.getContext().getSession();
        User user1=(User)session.get("user");
        idolsuser=new ArrayList<>();
        for(Relation relation:relationservice.myIdols(user1)){
            idolsuser.add(userservice.get(relation.getUserByUserByid().getUserId()));
        }
        return "personspace_idols";
    }

    public String fan(){
        //进入个人主页 显示所有关注“我”的人
        //得到当前登录的用户
        Map<String, Object> session = ActionContext.getContext().getSession();
        User user1=(User)session.get("user");
        //fanList为所有关注我的人
        fanList=new ArrayList<>();
        //idolsuer为所有我关注的人
        idolsuser=new ArrayList<>();
        for(Relation relation:relationservice.myIdols(user1)){
            idolsuser.add(userservice.get(relation.getUserByUserByid().getUserId()));
        }
        for(Relation relation:relationservice.myFans(user1)){
           user=userservice.get(relation.getUserByUserId().getUserId());
           fan fan1=new fan();
           fan1.setImageurl(user.getIcon());
           fan1.setStatus("+关注");
           fan1.setName(user.getUserNikename());
           fan1.setUserid(user.getUserId());
           for(User user2:idolsuser){
               if(user2.getUserId()==user.getUserId()){
                   fan1.setStatus("互相关注");
               }
           }
           fan1.setFans(relationservice.calfans(user));
           fan1.setIdols(relationservice.calidols(user));
           fan1.setWeibos(messageservice.myMessage(user).size());
           fanList.add(fan1);
        }
        return "personspace_fans";
    }

    public String tohome(){
        Map<String, Object> session = ActionContext.getContext().getSession();
        user=(User)session.get("user");
        if(user==null){
            return "loginuser";
        }
        fans=relationservice.calfans(user);
        idols=relationservice.calidols(user);
        mymessageList=messageservice.myMessage(user);
        weibos=idolweiboservice.calidolweibos(user);
        return "home";
    }
    public String exit(){
        if(ActionContext.getContext().getSession().get("user")!=null){
            ActionContext.getContext().getSession().clear();
        }
        return "loginfail";
    }
    public String tohot(){
        Map<String, Object> session = ActionContext.getContext().getSession();
        user=(User)session.get("user");
        fans=relationservice.calfans(user);
        idols=relationservice.calidols(user);
        mymessageList=messageservice.myMessage(user);
        weibos=idolweiboservice.weiboList();
        return "hotweibos";
    }

    public long timeCount(Message message) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //由毫秒转化为分钟，所以除以1000*60
        //填写测试用例的时候 没有写微博发表的时间 因此这里要稍微修改
        return (timestamp.getTime() - message.getMessageTime().getTime()) / (1000 * 60);
    }

    public String toMycollect(){
        Map<String, Object> session = ActionContext.getContext().getSession();
        user=(User)session.get("user");
        fans=relationservice.calfans(user);
        idols=relationservice.calidols(user);
        mymessageList=messageservice.myMessage(user);
        weibos=idolweiboservice.mycollections();
        return "mycollections";
    }

    public String toMyagree(){
        Map<String, Object> session = ActionContext.getContext().getSession();
        user=(User)session.get("user");
        fans=relationservice.calfans(user);
        idols=relationservice.calidols(user);
        mymessageList=messageservice.myMessage(user);
        weibos=idolweiboservice.myagrees();
        return "myagrees";
    }


}
