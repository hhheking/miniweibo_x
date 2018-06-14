package bean;

import java.sql.Timestamp;

public class remindagree {
    //点赞的用户的用户名
    private String username;
    //点赞的用户的头像
    private String pic;
    //点赞时间
    private Timestamp agreetime;
    private agreeWB wb;

    public agreeWB getWb() {
        return wb;
    }

    public void setWb(agreeWB wb) {
        this.wb = wb;
    }

    public void setAgreetime(Timestamp agreetime) {
        this.agreetime = agreetime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Timestamp getAgreetime() {
        return agreetime;
    }

}
