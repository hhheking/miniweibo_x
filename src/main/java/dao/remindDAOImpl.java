package dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import pojo.Remind;
import pojo.User;

import java.util.List;

public class remindDAOImpl extends HibernateTemplate implements remindDAO {
    @Override
    public User getUser(int id) {
        return (User) find("from User u where u.userId ="+ id).get(0);
    }

    @Override
    public void addRemind(Remind remind) {
        save(remind);
    }

    @Override
    public List<Remind> getRemind(int touser_id, int user_id, int message_id,String type) {
        return find("from Remind r where r.touserId=" + touser_id +"and r.usreId=" + user_id + "and r.messageId=" + message_id +"and r.type=?",type);
    }


    @Override
    public void updateRemind(Remind remind) { super.update(remind); }

    @Override
    public void deleteRemind(Remind remind) {
        super.delete(remind);
    }

    @Override
    public List<Remind> findnew(int touser_id) {
        return find("from Remind r where r.isnew = false and r.touserId =" + touser_id);
    }

    @Override
    public List<Remind> list(int touser_id) {
        return find("from Remind r where r.touserId =" + touser_id);
    }

    @Override
    public List<Remind> list(int touser_id, String type) {
        return find("from Remind r where r.touserId =" + touser_id + "and r.type=?",type);
    }

}
