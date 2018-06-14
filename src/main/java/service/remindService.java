package service;

import pojo.Remind;
import pojo.User;

import java.util.List;

public interface remindService {
    public User getUser(int id);
    public void addRemind(Remind remind);
    public void updateRemind(int touser_id,int user_id,int message_id,String type);
    public void updateRemind(Remind remind);
    public List<Remind> findnew(int touser_id);
    public List<Remind> list(int touser_id);
    public List<Remind> list(int touser_id,String type);
}
