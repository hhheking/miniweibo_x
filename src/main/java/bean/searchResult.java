package bean;

import pojo.Message;
import pojo.User;

import java.util.List;

public class searchResult {
    List<User> userListBySearch;
    List<Message> messageListBySearch;

    public List<User> getUserListBySearch() {
        return userListBySearch;
    }

    public void setUserListBySearch(List<User> userListBySearch) {
        this.userListBySearch = userListBySearch;
    }

    public List<Message> getMessageListBySearch() {
        return messageListBySearch;
    }

    public void setMessageListBySearch(List<Message> messageListBySearch) {
        this.messageListBySearch = messageListBySearch;
    }
}
