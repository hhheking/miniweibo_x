package bean;

import pojo.User;

/**
 * Created by apple on 2018/6/12.
 */
public class UserInformation {
    private User user;
    int idolnum;
    int fansnum;
    int messagenum;

    public int getFansnum() {
        return fansnum;
    }

    public int getIdolnum() {
        return idolnum;
    }

    public int getMessagenum() {
        return messagenum;
    }

    public User getUser() {
        return user;
    }

    public void setFansnum(int fansnum) {
        this.fansnum = fansnum;
    }

    public void setIdolnum(int idolnum) {
        this.idolnum = idolnum;
    }

    public void setMessagenum(int messagenum) {
        this.messagenum = messagenum;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
