package bean;

import pojo.Message;
import pojo.User;

import java.util.List;

public class searchResult {
    List<search_user> users;
    List<search_message> messages;

    public List<search_user> getUsers() {
        return users;
    }

    public void setUsers(List<search_user> users) {
        this.users = users;
    }

    public List<search_message> getMessages() {
        return messages;
    }

    public void setMessages(List<search_message> messages) {
        this.messages = messages;
    }
}
