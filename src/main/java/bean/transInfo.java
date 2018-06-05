package bean;

import pojo.Message;
import pojo.User;

/**
 * Created by apple on 2018/6/3.
 */
public class transInfo {
    private Message message;
    private User user;

    public Message getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
