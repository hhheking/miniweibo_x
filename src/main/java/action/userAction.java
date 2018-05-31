package action;

import bean.weibo;
import com.opensymphony.xwork2.ActionContext;
import pojo.Message;
import pojo.Relation;
import pojo.User;
import service.idolweiboService;
import service.messageService;
import service.relationService;
import service.userService;

import java.sql.Timestamp;
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
        if(userservice.login(user)){
            //用户登录成功
            //此时user对象仅有nikename和password属性，无法使用user.getuser_id()的语法,需要取出session中的user值
            Map<String, Object> session = ActionContext.getContext().getSession();
            user=(User)session.get("user");
            fans=relationservice.calfans(user);
            idols=relationservice.calidols(user);
            mymessageList=messageservice.myMessage(user);
            weibos=idolweiboservice.calidolweibos(user);
        }else {
            //登录失败
            return "loginfail";
        }
        return "home";
    }

    public String register(){
        //用户注册的时间
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        user.setUserTime(timestamp);
        userservice.register(user);
        return "loginuser";
    }

    public String personspace(){
        //得到session中的user实例
        Map<String, Object> session = ActionContext.getContext().getSession();
        user=(User)session.get("user");
        fans=relationservice.calfans(user);
        idols=relationservice.calidols(user);
        mymessageList=messageservice.myMessage(user);
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
        //得到登录用户的所有关注的人
        status="+关注";
        for(Relation relation:relationservice.myIdols(user1)){
            if(relation.getUserByUserByid().getUserId()==user.getUserId()){
                status="已关注";
            }
        }
       if(user1.getUserNikename().equals(user.getUserNikename())){
            //点击的头像为本人,根据用户名来判断
            return personspace();
       }else {
            //点击的头像不为本人
            fans=relationservice.calfans(user);
            idols=relationservice.calidols(user);
            mymessageList=messageservice.myMessage(user);
            return "other_person";
       }
    }
}
