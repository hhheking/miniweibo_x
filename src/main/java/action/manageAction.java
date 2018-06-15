package action;

import bean.UserInformation;
import bean.UsingCondition;
import com.opensymphony.xwork2.ActionSupport;
import dao.userDAO;
import pojo.Message;
import service.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by apple on 2018/6/12.
 */
public class manageAction extends ActionSupport{
    manageUserList manageuserList;
    messageService messageserviceImpl;
    userService userserviceImpl;
    List<UserInformation> UserList;
    List<Message> messageList;
    String userid;
    String messid;
    usingDetaiLManage usingDetailManageImpl;
    UsingCondition usingCondition;
    public String execute() throws Exception {
        UserList=manageuserList.getUserList();
        return SUCCESS;
    }
    public String delete(){
        userserviceImpl.delete(Integer.parseInt(userid));
        return "manage";
    }
    public String messdetail(){
        messageList=messageserviceImpl.myMessage(Integer.parseInt(userid));
        return "messManage";
    }
    public String deletemess(){
        messageserviceImpl.delete(Integer.parseInt(messid));
        return "messdetail";
    }

    public String toUser(){
        return "usermanage";
    }
    public String UsingDetail(){
        usingCondition=usingDetailManageImpl.get();
        return "usingdetail";
    }

    //get set方法


    public usingDetaiLManage getUsingDetailManageImpl() {
        return usingDetailManageImpl;
    }

    public void setUsingDetailManageImpl(usingDetaiLManage usingDetailManageImpl) {
        this.usingDetailManageImpl = usingDetailManageImpl;
    }

    public UsingCondition getUsingCondition() {
        return usingCondition;
    }


    public void setUsingCondition(UsingCondition usingCondition) {
        this.usingCondition = usingCondition;
    }


    public List<Message> getMessageList() {
        return messageList;
    }

    public String getMessid() {
        return messid;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public void setMessid(String messid) {
        this.messid = messid;
    }

    public userService getUserserviceImpl() {
        return userserviceImpl;
    }

    public void setUserserviceImpl(userService userserviceImpl) {
        this.userserviceImpl = userserviceImpl;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public messageService getMessageserviceImpl() {
        return messageserviceImpl;
    }

    public void setMessageserviceImpl(messageService messageserviceImpl) {
        this.messageserviceImpl = messageserviceImpl;
    }
    public manageUserList getManageuserList() {
        return manageuserList;
    }

    public List<UserInformation> getUserList() {
        return UserList;
    }

    public void setManageuserList(manageUserList manageuserList) {
        this.manageuserList = manageuserList;
    }

    public void setUserList(List<UserInformation> userList) {
        UserList = userList;
    }
}
