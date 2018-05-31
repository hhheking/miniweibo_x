package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.userDAO;
import org.apache.struts2.ServletActionContext;
import pojo.Comment;
import service.commentService;
import service.commentServiceImpl;
import service.messageService;

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
    @Override
    public String execute() throws Exception {
        result=commentservice.commentInformation(Integer.parseInt(messid));
        return SUCCESS;
    }
    public String add(){
        return SUCCESS;
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

}
