package bean;

import java.sql.Timestamp;
import java.util.List;

public class remindTranspond {
    //转发的用户的用户名
    private String username;
    //转发的用户的头像
    private String pic;
    //转发时间
    private Timestamp commenttime;
    //转发内容
    private List<String> transinfos;
    //原微博
    private agreeWB wb;

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

    public Timestamp getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(Timestamp commenttime) {
        this.commenttime = commenttime;
    }


    public agreeWB getWb() {
        return wb;
    }

    public void setWb(agreeWB wb) {
        this.wb = wb;
    }

    public List<String> getTransinfos() {
        return transinfos;
    }

    public void setTransinfos(List<String> transinfos) {
        this.transinfos = transinfos;
    }
}
