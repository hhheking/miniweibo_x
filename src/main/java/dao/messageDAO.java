package dao;

import pojo.Message;

import java.util.List;

public interface messageDAO {
    public List<Message> list();
    public void add(Message message);
    public void delete(Message message);
    public  void updata(Message message);
    public Message get(int id);
}
