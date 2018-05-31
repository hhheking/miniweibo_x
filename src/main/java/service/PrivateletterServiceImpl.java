package service;

import dao.PrivateletterDAO;
import pojo.Privateletter;
import pojo.User;

import java.util.List;

public class PrivateletterServiceImpl implements PrivateletterService {
    PrivateletterDAO privateletterDAO;

    public void setPrivateletterDAO(PrivateletterDAO privateletterDAO) {
        this.privateletterDAO = privateletterDAO;
    }

    public PrivateletterDAO getPrivateletterDAO() {
        return privateletterDAO;
    }

    @Override
    public List<Privateletter> list(int user1_id, int user2_id) {
        List<Privateletter> list1 = privateletterDAO.list(user2_id,user1_id);
        List<Privateletter> list2 = privateletterDAO.list(user1_id,user2_id);
        list1.addAll(list2);
        return list1;
    }

    @Override
    public void add(Privateletter privateletter) {
        privateletterDAO.add(privateletter);
    }

    @Override
    public void delete(Privateletter privateletter) {
        privateletterDAO.delete(privateletter);
    }
    @Override
    public List<User> getID(String name){
        return privateletterDAO.getID(name);
    }

    @Override
    public List<String> getName(int id) {
        return privateletterDAO.getName(id);
    }
}
