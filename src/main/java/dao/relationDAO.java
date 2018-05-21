package dao;

import pojo.Relation;

import java.util.List;

public interface relationDAO {
    public List<Relation> list();
    public void add(Relation relation);
    public void delete(Relation relation);
    public void update(Relation relation);
    public Relation get(int id);

}
