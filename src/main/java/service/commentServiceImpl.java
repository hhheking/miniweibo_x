package service;

import com.opensymphony.xwork2.ActionContext;
import dao.*;
import pojo.Comment;
import pojo.Message;
import pojo.Remind;
import pojo.User;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2018/5/29.
 */
public class commentServiceImpl implements commentService {
    private userDAO userdao;
    private messageDAO messagedao;
    private commentDAO commentdao;
    private remindDAO reminddao;

    public void setCommentdao(commentDAO commentdao) {
        this.commentdao = commentdao;
    }

    public commentDAO getCommentdao() {
        return commentdao;
    }
    public messageDAO getMessagedao() {
        return messagedao;
    }

    public userDAO getUserdao() {
        return userdao;
    }

    public void setMessagedao(messageDAO messagedao) {
        this.messagedao = messagedao;
    }

    public void setUserdao(userDAO userdao) {
        this.userdao = userdao;
    }

    public remindDAO getReminddao() {
        return reminddao;
    }

    public void setReminddao(remindDAO reminddao) {
        this.reminddao = reminddao;
    }

    @Override
    public List<Comment> commentList(int messid) {
        return commentdao.list(messid);
    }

    @Override
    public void addComment(int messid, String commentinfo, int userid) {
        Comment com=new Comment();
        com.setCommentInfo(commentinfo);
        com.setCommentTime(new Timestamp(System.currentTimeMillis()));
        User user=userdao.get(userid);
        Message message=messagedao.get(messid);
        message.setMessageCommentnum(message.getMessageCommentnum()+1);
        messagedao.updata(message);
        com.setUserByUserId(user);
        com.setMessageByMessageId(message);
        commentdao.add(com);
        int user_id =message.getUserByUserId().getUserId();
        if(userid!= user_id) {
            Remind remind = new Remind();
            remind.setIsnew(false);
            remind.setMessageId(message);
            remind.setTouserId(user_id);
            remind.setUsreId(userid);
            remind.setType("comment");
            remind.setTime(new Timestamp(System.currentTimeMillis()));
            this.reminddao.addRemind(remind);
        }
    }

    @Override
    public void deleteComment(int commentid) {
        Comment comment = commentdao.get(commentid);
        Message message = messagedao.get(comment.getCommentId());
        commentdao.delete(comment);
        User u = (User) ActionContext.getContext().getSession().get("user");
        int user_id = u.getUserId();
        int touser_id = message.getUserByUserId().getUserId();
        int message_id = message.getMessageId();
        if(touser_id != user_id) {
            Remind remind = reminddao.getRemind(touser_id, user_id, message_id, "comment").get(0);
            reminddao.deleteRemind(remind);
        }
    }

    @Override
    public int countCommentNum(int messid) {
        return  commentdao.list(messid).size();
    }

    @Override
    public String[][] commentInformation(int messid) {
        List<Comment> list=this.commentList(messid);
        String [][]result=new String[list.size()][5];
        int i=list.size()-1;
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        for(Comment comment:list){
            System.out.print(comment.getUserByUserId().getUserId());
            result[i][0]=userdao.get(comment.getUserByUserId().getUserId()).getUserNikename();//昵称
            result[i][1]=userdao.get(comment.getUserByUserId().getUserId()).getIcon();//照片url
            result[i][2]= String.valueOf((timestamp.getTime()-comment.getCommentTime().getTime())/(1000*60));//评论时间
            result[i][3]=comment.getCommentInfo();//评论内容
            result[i][4]= String.valueOf(comment.getUserByUserId().getUserId());//userid
            i--;
        }
        return result;
    }

}
