package dao;

import pojo.Agree;
import pojo.Message;

import java.util.List;

public interface agreeDAO {
    public void addAgree(Agree agree);
    public void deleteAgree(Agree agree);
    public List<Agree> findAgree(int message_id,int user_id);
    public void updateMessage(Message message);
    public Message getMessage(int id);
    public List<Agree> myagrees(int id);
}
