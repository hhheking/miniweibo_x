package service;

import dao.relationDAO;
import dao.userDAO;
import pojo.Relation;
import pojo.User;

import java.util.ArrayList;
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

    @Override
    public int calfans(User user) {
        int count = 0;
        for(Relation relation:relationdao.list()){
            if(relation.getUserByUserByid().getUserId()==user.getUserId()){
                count++;
            }
        }
        return count;
    }

    @Override
    public int calidols(User user1) {
        int count=0;
        for(Relation relation:relationdao.list()){
            if(relation.getUserByUserId().getUserId()==user1.getUserId()){
                count++;
            }
        }
        return count;
    }

    @Override
    public List<User> myIdols(User user) {
        List<User> userList=new ArrayList<>();
        for(Relation relation:relationdao.list()){
            if(relation.getUserByUserId().getUserId()==user.getUserId()){
                //注意这里可能会出现的问题，relation表中user对象仅有一个user_id属性
                userList.add(relation.getUserByUserByid());
                //System.out.println(relation.getUserByUserByid().getUserNikename());
            }
        }
        return userList;
    }
}
