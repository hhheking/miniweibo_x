package service;

import bean.weibo;
import pojo.Message;
import pojo.User;

import java.sql.Timestamp;
import java.util.List;

public interface idolweiboService {
    public String imageaddress();
    public List<User> idols(User user);
    public long timeCount(Message message);
    public List<weibo> calidolweibos(User user);
}
