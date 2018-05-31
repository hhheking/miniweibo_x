package dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import pojo.Privateletter;
import pojo.User;

import java.util.List;

public class PrivateletterDAOImpl extends HibernateTemplate implements PrivateletterDAO {
    @Override
    public List<Privateletter> list(int user1_id, int user2_id) {
        return find("from Privateletter p where p.userByUserId = "+user1_id + "and p.userByTouserId = "+ user2_id);
    }

    @Override
    public void add(Privateletter privateletter) {
        save(privateletter);
    }

    @Override
    public void delete(Privateletter privateletter) {
        delete(privateletter);
    }

    @Override
    public List<User> getID(String name){
        return find("from User u where u.userNikename = ?",name);
    }

    @Override
    public List<String> getName(int id){
        return find("select u.userNikename from User u where u.userId = ?",id);
    }
}
