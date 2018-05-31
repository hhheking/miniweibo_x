package service;

import com.opensymphony.xwork2.ActionContext;
import dao.messageDAO;
import dao.userDAO;
import pojo.Message;
import pojo.Picture;
import pojo.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class messageServiceImpl implements messageService {
    messageDAO messagedao;

    public messageDAO getMessagedao() {
        return messagedao;
    }

    public void setMessagedao(messageDAO messagedao) {
        this.messagedao = messagedao;
    }

    @Override
    public List<Message> list() {
        return messagedao.list();
    }

    @Override
    public void add(Message message) {
        messagedao.add(message);
    }

    @Override
    public void delete(Message message) {
        messagedao.delete(message);
    }

    @Override
    public Message get(int id) {
        return messagedao.get(id);
    }

    @Override
    public List<Message> myMessage(User user) {
        List<Message> messageList = new ArrayList<>();
        for(Message message:messagedao.list()){
            if(message.getUserByUserId().getUserId()==user.getUserId()){
                messageList.add(message);
            }
        }
        return messageList;
    }

    @Override
    public List<Message> idols_messages(List<User> users) {
        List<Message> messageList=new ArrayList<>();
        for(Message message:messagedao.list()){
            for(User u:users)
            {
                if(message.getUserByUserId().getUserId()==u.getUserId()){
                    message.getUserByUserId().setUserNikename(u.getUserNikename());
                    messageList.add(message);
                }
            }
        }
        return messageList;
    }

    @Override
    public Message message(String info) {
        //得到session
        Map<String, Object> session = ActionContext.getContext().getSession();
        User u=(User)session.get("user");
        User user=new User();
        user.setUserId(u.getUserId());
        Picture picture=new Picture();
        picture.setPictureId(1);
        Message message=new Message();
        message.setMessageTranspondnum(0);
        message.setMessageAgreenum(0);
        message.setMessageCollectnum(0);
        message.setMessageCommentnum(0);
        message.setMessageType("Orign");
        message.setMessageTime(new Timestamp(System.currentTimeMillis()));
        message.setMessageInfo(info);
        message.setUserByUserId(user);
        message.setPictureByPictureId(picture);
        return message;
    }
}
