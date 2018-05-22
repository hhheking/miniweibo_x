package service;

import dao.messageDAO;
import pojo.Message;

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
    public void updata(Message message) {
        messagedao.updata(message);
    }

    @Override
    public Message get(int id) {
        return messagedao.get(id);
    }
}
