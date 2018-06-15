package service;

import bean.UserInformation;
import dao.userDAO;
import pojo.User;
import pojo.Userinfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2018/6/12.
 */
public class manageUserListImpl implements manageUserList {
    relationService relationservice;
    userService userservice;
    messageService messageservice;
    @Override
    public List<UserInformation> getUserList() {
        List<UserInformation> userinfos=new ArrayList<>();
        for(User u:userservice.list()){
            UserInformation userinformation=new UserInformation();
            userinformation.setUser(u);
            userinformation.setFansnum(relationservice.calfans(u));
            userinformation.setIdolnum(relationservice.calidols(u));
            userinformation.setMessagenum(messageservice.myMessage(u).size());
            userinfos.add(userinformation);
        }
        return userinfos;
    }

    public messageService getMessageservice() {
        return messageservice;
    }

    public relationService getRelationservice() {
        return relationservice;
    }

    public userService getUserservice() {
        return userservice;
    }

    public void setMessageservice(messageService messageservice) {
        this.messageservice = messageservice;
    }

    public void setRelationservice(relationService relationservice) {
        this.relationservice = relationservice;
    }

    public void setUserservice(userService userservice) {
        this.userservice = userservice;
    }
}
