package dao;

import pojo.Collection;
import pojo.Message;

import java.util.List;

public interface collectionDAO {
    public void addCollection(Collection collection);
    public void deleteCollection(Collection collection);
    public List<Collection> list(int user_id,int message_id);
    public void updateMessage(Message message);
    public Message getMessage(int id);
}
