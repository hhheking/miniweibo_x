package service;

import pojo.Remind;
import dao.remindDAO;
import pojo.User;

import java.util.List;

public class remindServiceImpl implements remindService {
    remindDAO reminddao;

    public void setReminddao(remindDAO reminddao) {
        this.reminddao = reminddao;
    }

    public remindDAO getReminddao() {
        return reminddao;
    }

    @Override
    public User getUser(int id) {
        return reminddao.getUser(id);
    }

    @Override
    public void addRemind(Remind remind) {
        reminddao.addRemind(remind);
    }

    @Override
    public void updateRemind(int touser_id,int user_id,int message_id,String type) {
        Remind remind = reminddao.getRemind(touser_id,user_id,message_id,type).get(0);
        remind.setIsnew(true);
        reminddao.updateRemind(remind);
    }

    @Override
    public List<Remind> findnew(int touser_id) {
        return reminddao.findnew(touser_id);
    }

    @Override
    public List<Remind> list(int touser_id) {
        return reminddao.list(touser_id);
    }

    @Override
    public List<Remind> list(int touser_id, String type) {
        return reminddao.list(touser_id,type);
    }
}
