package service;

import dao.relationDAO;
import pojo.Relation;

import java.util.List;

public class relationServiceImpl implements relationService {
    relationDAO relationdao;

    public relationDAO getRelationdao() {
        return relationdao;
    }

    public void setRelationdao(relationDAO relationdao) {
        this.relationdao = relationdao;
    }

    @Override
    public List<Relation> list() {
        return relationdao.list();
    }

    @Override
    public void add(Relation relation) {
        relationdao.add(relation);
    }

    @Override
    public void delete(Relation relation) {
        relationdao.delete(relation);
    }

    @Override
    public void update(Relation relation) {
        relationdao.update(relation);
    }

    @Override
    public Relation get(int id) {
        return relationdao.get(id);
    }
}
