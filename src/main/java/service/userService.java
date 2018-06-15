package service;

import pojo.User;

import java.util.List;

public interface userService {
    public List<User> list();
    public void register(User user);
    public void update(User user);
    public boolean login(User user);
    public User get(int id);
    public void delete(int id);
}
