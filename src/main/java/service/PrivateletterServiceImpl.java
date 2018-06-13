package service;

import dao.PrivateletterDAO;
import pojo.Privateletter;
import pojo.Remind;
import pojo.User;

import java.sql.Timestamp;
import java.util.List;
import dao.remindDAO;
public class PrivateletterServiceImpl implements PrivateletterService {
    PrivateletterDAO privateletterDAO;
    remindDAO reminddao;

    public void setPrivateletterDAO(PrivateletterDAO privateletterDAO) {
        this.privateletterDAO = privateletterDAO;
    }

    public PrivateletterDAO getPrivateletterDAO() {
        return privateletterDAO;
    }

    public void setReminddao(remindDAO reminddao) {
        this.reminddao = reminddao;
    }

    public remindDAO getReminddao() {
        return reminddao;
    }

    @Override
    public List<Privateletter> list(int user1_id, int user2_id) {
        List<Privateletter> list1 = privateletterDAO.list(user2_id,user1_id);
        List<Privateletter> list2 = privateletterDAO.list(user1_id,user2_id);
        list1.addAll(list2);
        return list1;
    }

    @Override
    public void add(String user,String touser,String content,int flag) {
        Privateletter pri = new Privateletter();
        User user1 = getID(user).get(0);
        User user2 = getID(touser).get(0);
        pri.setUserByUserId(user1);
        pri.setUserByTouserId(user2);
        pri.setPrivateletterInfo(content);
        pri.setPrivateletterTime(new Timestamp(System.currentTimeMillis()));
        privateletterDAO.add(pri);
        //添加聊天提醒
        if(flag == 1)
        {return;}
        int user_id = user1.getUserId();
        int touser_id = user2.getUserId();
        if(user_id!= touser_id) {
            Remind remind = new Remind();
            remind.setIsnew(false);
            remind.setTouserId(touser_id);
            remind.setUsreId(user_id);
            remind.setType("letter");
            remind.setContent(content);
            remind.setTime(new Timestamp(System.currentTimeMillis()));
            this.reminddao.addRemind(remind);
        }
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
