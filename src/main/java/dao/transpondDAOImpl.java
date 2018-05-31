package dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import pojo.Transpond;

import java.util.List;

public class transpondDAOImpl extends HibernateTemplate implements transpondDAO {
    @Override
    public List<Transpond> list() {
        return find("from Transpond ");
    }

    @Override
    public void add(Transpond transpond) {
        save(transpond);
    }

    @Override
    public void delete(Transpond transpond) {
        super.delete(transpond);
    }

    @Override
    public Transpond get(int id) {
        return (Transpond)get(Transpond.class,id);
    }
}
