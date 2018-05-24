package dao;

import pojo.Relation;
import pojo.User;

import java.util.List;

public interface relationDAO {
    public List<Relation> list();
    public void add(Relation relation);
    public void delete(Relation relation);
    public void update(Relation relation);
    public Relation get(int id);
    public  List<Relation> listFans(User user);
    public  List<Relation> listIdols(User user);
}
