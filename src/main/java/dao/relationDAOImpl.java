package dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import pojo.Relation;
import pojo.User;

import java.util.List;

public class relationDAOImpl extends HibernateTemplate implements relationDAO {
    @Override
    public List<Relation> list() {
        //将用户成功登录的javabean对象存入session中
        return find("from Relation ");
    }

    @Override
    public void add(Relation relation) {
        save(relation);
    }

    @Override
    public void delete(Relation relation) {
        super.delete(relation);
    }

    @Override
    public void update(Relation relation) {
        super.update(relation);
    }

    @Override
    public Relation get(int id) {
        return (Relation)get(Relation.class,id);
    }

    @Override
    public List<Relation> listFans(User user) {
        return null;
    }

    @Override
    public List<Relation> listIdols(User user) {
        return null;
    }
}
