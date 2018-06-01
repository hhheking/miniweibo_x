package service;

public interface agreeService {
    public void addAgree(int user_id,int message_id);
    public void deleteAgree(int user_id,int message_id);
    public void updateMessage(int message_id,int add);
}
