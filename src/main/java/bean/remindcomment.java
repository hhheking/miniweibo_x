package bean;

import java.sql.Timestamp;
import java.util.List;

public class remindcomment {
    //评论的用户的用户名
    private String username;
    //评论的用户的头像
    private String pic;
    //评论时间
    private Timestamp commenttime;
    //评论内容
    private List<String> commentinfos;
    private agreeWB wb;

    public List<String> getCommentinfos() {
        return commentinfos;
    }

    public void setCommentinfos(List<String> commentinfos) {
        this.commentinfos = commentinfos;
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
}
