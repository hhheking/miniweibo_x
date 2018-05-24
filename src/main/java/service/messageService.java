package service;

import pojo.Message;
import pojo.User;

import java.util.List;

public interface messageService {
    public List<Message> list();
    public void add(Message message);
    public void delete(Message message);
    public Message get(int id);
    public List<Message> myMessage(User user);
    public List<Message> idols_messages(List<User> users);

}
