package service;

import pojo.Relation;
import pojo.User;

import java.util.List;

public interface relationService {
    public List<Relation> list();
    public void add(Relation relation);
    public void delete(Relation relation);
    public void update(Relation relation);
    public Relation get(int id);
    public int calfans(User user);
    public int calidols(User user);
    public List<User> myIdols(User user);
}
