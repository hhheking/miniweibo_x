package service;

import pojo.Message;
import pojo.User;

import java.util.List;

public interface messageService {
    public List<Message> list();
    public int add(Message message);
    public void delete(int messageid);
    public Message get(int id);
    public List<Message> myMessage(User user);
    public List<Message> idols_messages(List<User> users);
    public Message message(String info);
    public Message transmessage(String resaon,int id);
    public List<Message> myMessage(int userid);
}
