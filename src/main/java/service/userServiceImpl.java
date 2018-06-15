package service;

import com.opensymphony.xwork2.ActionContext;
import dao.userDAO;
import pojo.User;

import java.util.List;
import java.util.Map;

public class userServiceImpl implements userService {
    userDAO userdao;

    public void setUserdao(userDAO userdao) {
        this.userdao = userdao;
    }

    public userDAO getUserdao() {
        return userdao;
    }

    @Override
    //列出用户列表
    public List<User> list() {
        return userdao.list();
    }

    @Override
    //用户注册
    public void register(User user) {
        userdao.add(user);
    }

    @Override
    //用户更新个人资料
    public void update(User user) {
        userdao.update(user);
    }

    @Override
    //用户登录
    public boolean login(User user) {
        for(User user1:userdao.list()){
            if(user1.getUserNikename().equals(user.getUserNikename()) && user1.getUserPassword().equals(user.getUserPassword())){
                Map<String, Object> session = ActionContext.getContext().getSession();
                session.put("user",user1);
                return true;
            }
        }
        return false;
    }

    @Override
    //用户后台管理时，得到某位用户
    public User get(int id) {
        return userdao.get(id);
    }

    @Override
    public void delete(int id) {
        User user=userdao.get(id);
        userdao.delete(user);
    }
}
