package dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import pojo.Relation;

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
}
