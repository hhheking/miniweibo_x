package service;

import dao.userDAO;
import pojo.User;

import java.util.List;

public class userServiceImpl implements userService {
    userDAO userdao;

    public void setUserdao(userDAO userdao) {
        this.userdao = userdao;
    }

    public userDAO getUserdao() {
        return userdao;
    }

    @Override
    public List<User> list() {
        List<User> list=userdao.list();
        return list;
    }

    @Override
    public void add(User user) {
        userdao.add(user);
    }

    @Override
    public void delete(User user) {
        userdao.delete(user);
    }

    @Override
    public void update(User user) {
        userdao.update(user);
    }

    @Override
    public User get(int id) {
        return userdao.get(id);
    }
}
