package bean;

/**
 * Created by apple on 2018/6/14.
 */
public class UsingCondition {
    int onlinenum;
    int usernum;
    int todaynum;
    int totalmessnum;
    int []newuser;

    public int getTotalmessnum() {
        return totalmessnum;
    }

    public int getOnlinenum() {
        return onlinenum;
    }

    public int getTodaynum() {
        return todaynum;
    }

    public int getUsernum() {
        return usernum;
    }

    public int[] getNewuser() {
        return newuser;
    }

    public void setTotalmessnum(int totalmessnum) {
        this.totalmessnum = totalmessnum;
    }
    public void setNewuser(int[] newuser) {
        this.newuser = newuser;
    }

    public void setOnlinenum(int onlinenum) {
        this.onlinenum = onlinenum;
    }

    public void setTodaynum(int todaynum) {
        this.todaynum = todaynum;
    }

    public void setUsernum(int usernum) {
        this.usernum = usernum;
    }
}
