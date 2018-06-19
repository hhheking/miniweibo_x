package dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import pojo.Collection;
import pojo.Message;

import java.util.List;

public class collectionDAOImpl extends HibernateTemplate implements collectionDAO {

    @Override
    public void addCollection(Collection collection) {
        save(collection);
    }

    @Override
    public void deleteCollection(Collection collection) {
        super.delete(collection);
    }

    @Override
    public List<Collection> list(int user_id, int message_id) {
        return find("from Collection c where c.userByUserId ="+ user_id +"and c.messageByMessageId =" + message_id);
    }

    @Override
    public void updateMessage(Message message) {
        super.update(message);
    }

    @Override
    public Message getMessage(int id) {
        return get(Message.class,id);
    }

    @Override
    public List<Collection> listMycollections(int userid) {
        return find("from Collection c where c.userByUserId="+userid);
    }
}
