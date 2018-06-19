package service;

import pojo.Comment;

import java.util.List;

/**
 * Created by apple on 2018/5/29.
 */
public interface commentService {
    public List<Comment> commentList(int messid) ;
    public int addComment(int messid,String commetinfo,int userid);
    public void deleteComment(int commetid);
    public int  countCommentNum(int messid);
    //获得该条微博具体评论人的头像,昵称,评论的时间,评论的内容
    public String[][] commentInformation(int messid);
}
