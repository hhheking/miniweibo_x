package action;

import bean.searchResult;
import dao.messageDAO;
import dao.userDAO;

//搜索的逻辑实现
public class searchAction {
    userDAO userdao;
    messageDAO messagedao;
    String keywords;
    searchResult searcresult=new searchResult();

    public searchResult getSearcresult() {
        return searcresult;
    }

    public void setSearcresult(searchResult searcresult) {
        this.searcresult = searcresult;
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
        searcresult.setMessageListBySearch(messagedao.searchByInfo(keywords));
        searcresult.setUserListBySearch(userdao.searchByName(keywords));
        return "success";
    }
}
