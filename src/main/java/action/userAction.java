package action;

import com.opensymphony.xwork2.ActionContext;
import pojo.User;
import service.relationService;
import service.userService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class userAction {
    User user;
    List<User> userList;
    userService userservice;

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
    public String edit(){
        user=userservice.get(user.getUserId());
        return "edituser";
    }
    public String add(){
        userservice.add(user);
        return "listuseraction";
    }
    public String delete(){
        userservice.delete(user);
        return "listuseraction";
    }
    public String update(){
        userservice.update(user);
        return "listuseraction";
    }
    public String login(){
        int flag = 0;
        for (User u:userservice.list()) {
            if(u.getUserNikename().equals(user.getUserNikename()) && u.getUserPassword().equals(user.getUserPassword())){
                System.out.println("登录成功");
                user.setUserId(u.getUserId());
                //将用户成功登录的javabean对象存入session中
                Map<String, Object> session = ActionContext.getContext().getSession();
                session.put("user",user);
                flag = 1;
                return "calfans";
            }
        }
        if(flag == 0){
            System.out.println("用户名或密码错误");
        }
        return "loginfail";
    }

    public String register(){
        //用户注册的时间
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        user.setUserTime(timestamp);
        userservice.add(user);
        return "loginuser";
    }
}
