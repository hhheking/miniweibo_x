package dao;

import pojo.Privateletter;
import pojo.User;

import java.util.List;

public interface PrivateletterDAO {
    public List<Privateletter> list(int user1_id, int user2_id);
    public void add(Privateletter privateletter);
    public void delete(Privateletter privateletter);
    public List<User> getID(String name);
    public List<String> getName(int id);
}
