package dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import pojo.User;

import java.util.List;

public class userDAOImpl extends HibernateTemplate implements userDAO {
    @Override
    public List<User> list() {
        return find("from User");
    }

    @Override
    public void add(User user) {
        save(user);
    }

    @Override
    public void delete(User user) {
        super.delete(user);
    }

    @Override
    public void update(User user) {
        super.update(user);
    }

    @Override
    public User get(int id) {
        return (User)get(User.class,id);
    }
}
