package action;

import bean.searchResult;
import bean.search_message;
import bean.search_user;
import dao.messageDAO;
import dao.userDAO;
import pojo.Message;
import pojo.User;
import service.relationService;

import java.util.ArrayList;
import java.util.List;

//搜索的逻辑实现
public class searchAction {
    userDAO userdao;
    messageDAO messagedao;
    String keywords;
    searchResult searchresult;
    relationService service;

    public searchResult getSearchresult() {
        return searchresult;
    }

    public relationService getService() {
        return service;
    }

    public void setService(relationService service) {
        this.service = service;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

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

    public String search(){
        searchresult=new searchResult();
        List<search_user> list=new ArrayList<>();
        List<search_message> list1=new ArrayList<>();
        for(User u:userdao.searchByName(keywords)){
            search_user searchUser=new search_user();
            searchUser.setId(u.getUserId());
            searchUser.setImageurl("");
            searchUser.setFans(service.calfans(u));
            searchUser.setName(u.getUserNikename());
            list.add(searchUser);
        }
        for(Message u:messagedao.searchByInfo(keywords)){
            search_message searchUser=new search_message();
            searchUser.setId(u.getMessageId());
            searchUser.setInfo(u.getMessageInfo());
            list1.add(searchUser);
        }
        searchresult.setUsers(list);
        searchresult.setMessages(list1);
        return "success";
    }
}
