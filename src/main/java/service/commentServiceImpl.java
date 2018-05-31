package service;

import dao.*;
import pojo.Comment;
import pojo.Message;
import pojo.User;

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

    @Override
    public List<Comment> commentList(int messid) {
        return commentdao.list(messid);
    }

    @Override
    public void addComment(int messid, String commetinfo, int userid) {
        Comment com=new Comment();
        com.setCommentInfo(commetinfo);
        com.setCommentTime(new Timestamp(System.currentTimeMillis()));
        User user=userdao.get(userid);
        Message message=messagedao.get(messid);
        com.setUserByUserId(user);
        com.setMessageByMessageId(message);
        commentdao.add(com);
    }

    @Override
    public void deleteComment(int commentid) {
        commentdao.delete(commentdao.get(commentid));
    }

    @Override
    public int countCommentNum(int messid) {
        return  commentdao.list(messid).size();
    }

    @Override
    public String[][] commentInformation(int messid) {
        List<Comment> list=this.commentList(messid);
        String [][]result=new String[list.size()][5];
        int i=0;
        System.out.println(list.size());
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        for(Comment comment:list){
            System.out.print(comment.getUserByUserId().getUserId());
            result[i][0]=userdao.get(comment.getUserByUserId().getUserId()).getUserNikename();//昵称
            result[i][1]="1";//照片url
            result[i][2]= String.valueOf((timestamp.getTime()-comment.getCommentTime().getTime())/(1000*60));//评论时间
            result[i][3]=comment.getCommentInfo();//评论内容
            result[i][4]= String.valueOf(comment.getUserByUserId().getUserId());//userid
            i++;
        }
        return result;
    }

}
