package dao;

import pojo.Remind;

import java.util.List;

public interface remindDAO {
    public void addRemind(Remind remind);
    public List<Remind> getRemind(int touser_id,int user_id,int message_id,String type);
    public void updateRemind(Remind remind);
    public void deleteRemind(Remind remind);
    public List<Remind> findnew(int touser_id);
    public List<Remind> list(int touser_id);
    public List<Remind> list(int touser_id,String type);
}
