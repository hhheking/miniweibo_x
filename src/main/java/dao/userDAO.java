package dao;

import pojo.User;

import java.util.List;

public interface userDAO {
    public List<User> list();
    public void add(User user);
    public void delete(User user);
    public void update(User user);
    public User get(int id);
}
