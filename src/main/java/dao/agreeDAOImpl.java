package dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import pojo.Agree;
import pojo.Message;

import java.util.List;

public class agreeDAOImpl extends HibernateTemplate implements agreeDAO {
    @Override
    public void addAgree(Agree agree) {
        save(agree);
    }

    @Override
    public void deleteAgree(Agree agree) {
        super.delete(agree);
    }

    @Override
    public List<Agree> findAgree(int message_id, int user_id) {
        return find("from Agree a where a.userByUserId=" + user_id +"and a.messageByMessageId =" + message_id);
    }

    @Override
    public void updateMessage(Message message) {
            super.update(message);
    }

    @Override
    public Message getMessage(int id) {
        return get(Message.class,id);
    }

}
