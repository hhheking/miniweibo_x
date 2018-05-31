package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import pojo.Comment;

import java.util.List;

/**
 * Created by apple on 2018/5/29.
 */
public class commentDAOImpl extends HibernateTemplate implements commentDAO{
    @Override
    public List<Comment> list() {
        return  find("from Comment ");
    }
    @Override
    public void add(Comment comment) {
        save(comment);
    }

    @Override
    public void delete(Comment comment) {
        super.delete(comment);
    }

    @Override
    public void updata(Comment comment) {
        super.update(comment);
    }

    @Override
    public Comment get(int id) {
        return (Comment)get(Comment.class,id);
    }

    @Override
    public List<Comment> list(int messid) {
        String s="from Comment c where c.messageByMessageId="+messid;
        return find(s);
    }

}