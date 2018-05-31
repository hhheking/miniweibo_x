package dao;

import pojo.Comment;

import java.util.List;

/**
 * Created by apple on 2018/5/29.
 */
public interface commentDAO {
    public List<Comment> list();
    public void add(Comment comment);
    public void delete(Comment comment);
    public  void updata(Comment comment);
    public Comment get(int id);
    public List<Comment> list(int messid);
}

