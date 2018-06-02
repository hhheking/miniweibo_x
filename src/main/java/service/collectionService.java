package service;

public interface collectionService {
    public void addCollection(int user_id,int message_id);
    public void deleteCollection(int user_id,int message_id);
    public void updateMessage(int message_id,int add);
}
