package service;

import pojo.Privateletter;
import pojo.User;

import java.util.List;

public interface PrivateletterService {
    public List<Privateletter> list(int user1_id, int user2_id);
    public void add(String user,String touser,String content);
    public void delete(Privateletter privateletter);
    public List<User> getID(String name);
    public List<String> getName(int id);
}
