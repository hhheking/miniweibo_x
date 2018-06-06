package service;

import pojo.Remind;

import java.util.List;

public interface remindService {
    public void addRemind(Remind remind);
    public void updateRemind(int touser_id,int user_id,int message_id,String type);
    public List<Remind> findnew(int touser_id);
    public List<Remind> list(int touser_id);
    public List<Remind> list(int touser_id,String type);
}
