package action;

import com.opensymphony.xwork2.ActionContext;
import pojo.Relation;
import pojo.User;
import service.relationService;
import service.userService;

import java.util.List;
import java.util.Map;

public class relationAction {
    List<Relation> relationList;
    relationService relationservice;
    userService userservice;
    User user;
    int user_id;

    public void setUserservice(userService userservice) {
        this.userservice = userservice;
    }

    public userService getUserservice() {
        return userservice;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public relationService getRelationservice() {
        return relationservice;
    }

    public void setRelationservice(relationService relationservice) {
        this.relationservice = relationservice;
    }

    public List<Relation> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<Relation> relationList) {
        this.relationList = relationList;
    }
    public String list(){
        relationList=relationservice.list();
        return "listrelation";

    }
    public String add(){
        Map<String, Object> session = ActionContext.getContext().getSession();
        User user1=(User)session.get("user");
        System.out.println("登录用户的数据");
        System.out.println(user1.getUserId());
        System.out.println(user1.getUserNikename());
        user=userservice.get(user_id);
        System.out.println("偶像的数据");
        System.out.println(user.getUserId());
        System.out.println(user.getUserNikename());
        Relation relation=new Relation();
        relation.setUserByUserId(user1);
        relation.setUserByUserByid(user);
        relationservice.add(relation);
        return "success";
    }
}
