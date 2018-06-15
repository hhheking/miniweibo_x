package service;

import com.opensymphony.xwork2.ActionContext;
import com.sun.xml.internal.ws.api.message.MessageWritable;
import dao.messageDAO;
import dao.transpondDAO;
import dao.userDAO;
import pojo.Message;
import pojo.Picture;
import pojo.Transpond;
import pojo.User;

import javax.jws.soap.SOAPBinding;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class messageServiceImpl implements messageService {
    messageDAO messagedao;
    userDAO userdao;
    transpondDAO transponddao;

    public transpondDAO getTransponddao() {
        return transponddao;
    }

    public void setTransponddao(transpondDAO transponddao) {
        this.transponddao = transponddao;
    }

    public userDAO getUserdao() {
        return userdao;
    }

    public void setUserdao(userDAO userdao) {
        this.userdao = userdao;
    }

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
    public void delete(int messageid) {
        Message message=messagedao.get(messageid);
        Message orignmessage=message;
        if (orignmessage.getMessageType().equals("Transpond")) {
            Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
            int OrignId =0;
            if (transpond!=null)
                OrignId = transpond.getMessageByMessageId().getMessageId();
            orignmessage = messagedao.get(OrignId);
            if(orignmessage!=null){
                orignmessage.setMessageTranspondnum(orignmessage.getMessageTranspondnum()-1);
                messagedao.updata(orignmessage);
            }
        }
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

    @Override
    public Message transmessage(String resaon,int id) {
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
        message.setMessageType("Transpond");
        message.setMessageTime(new Timestamp(System.currentTimeMillis()));
        message.setMessageInfo(resaon);
        message.setUserByUserId(user);
        message.setPictureByPictureId(picture);
        //得到当前用户转发的原始微博,将转发数加1
        Message message1=messagedao.get(id);
        message1.setMessageTranspondnum(message1.getMessageTranspondnum()+1);
        messagedao.updata(message1);
        return message;
    }

    @Override
    public List<Message> myMessage(int userid) {
        User user=userdao.get(userid);
        return myMessage(user);
    }
}
