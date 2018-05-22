package dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import pojo.Message;

import java.util.List;

public class messageDAOImpl extends HibernateTemplate implements messageDAO {
    @Override
    public List<Message> list() {
        return find("from Message ");
    }

    @Override
    public void add(Message message) {
        save(message);
    }

    @Override
    public void delete(Message message) {
        super.delete(message);
    }

    @Override
    public void updata(Message message) {
        super.update(message);
    }

    @Override
    public Message get(int id) {
        return (Message)get(Message.class,id);
    }
}
