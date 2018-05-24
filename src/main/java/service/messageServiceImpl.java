package service;

import dao.messageDAO;
import dao.userDAO;
import pojo.Message;
import pojo.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
}
