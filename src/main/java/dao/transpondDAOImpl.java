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

    @Override
    public Transpond findTranspondFrom(int result_id) {
        List<Transpond> list= (List<Transpond>) find("from Transpond t where t.resultmessid="+result_id);
        if(list.size()>0){
            return list.get(0);
        }
        return null;

    }

    @Override
    public List<Transpond> getTranspondByuseridAndmessageid(int userid, int messageid) {
        return find("from Transpond  t where t.userByUserId="+userid+"and t.messageByMessageId.messageId="+messageid);
    }
}
