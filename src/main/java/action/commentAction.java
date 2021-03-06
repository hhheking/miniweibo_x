package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.userDAO;
import org.apache.struts2.ServletActionContext;
import pojo.Comment;
import service.commentService;
import service.commentServiceImpl;
import service.messageService;
import sun.plugin2.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2018/5/29.
 */
public class commentAction extends ActionSupport{
    String [][]result;
    String messid;
    String userid;
    String commentinfo;
    commentService commentservice;
    int commentid;

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    @Override

    public String execute() throws Exception {
        result=commentservice.commentInformation(Integer.parseInt(messid));
        return SUCCESS;
    }
    public String add(){
        commentid=commentservice.addComment(Integer.parseInt(messid),commentinfo,Integer.parseInt(userid));
        return "add";
    }
    public String getCommentinfo() {
        return commentinfo;
    }

    public String getUserid() {
        return userid;
    }

    public void setCommentinfo(String commentinfo) {
        this.commentinfo = commentinfo;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String[][] getResult() {
        return result;
    }

    public void setResult(String[][] result) {
        this.result = result;
    }
    public String getMessid() {
        return messid;
    }

    public void setMessid(String messid) {
        this.messid = messid;
    }

    public commentService getCommentservice() {
        return commentservice;
    }

    public void setCommentservice(commentService commentservice) {
        this.commentservice = commentservice;
    }

    public void delete(){
        commentservice.deleteComment(commentid);
    }

}
