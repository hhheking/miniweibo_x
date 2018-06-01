package service;

import org.springframework.orm.hibernate3.HibernateTemplate;
import pojo.Agree;
import pojo.Message;
import dao.agreeDAOImpl;
import pojo.User;

import java.util.List;

public class agreeServicesImpl implements agreeService {
    agreeDAOImpl agreeDAO;

    public void setAgreeDAO(agreeDAOImpl agreeDAO) {
        this.agreeDAO = agreeDAO;
    }

    public agreeDAOImpl getAgreeDAO() {
        return agreeDAO;
    }

    @Override
    public void addAgree(int user_id, int message_id) {
        Agree agree = new Agree();
        User user = new User();
        user.setUserId(user_id);
        Message message = new Message();
        message.setMessageId(message_id);
        agree.setUserByUserId(user);
        agree.setMessageByMessageId(message);
        agreeDAO.save(agree);
    }

    @Override
    public void deleteAgree(int user_id, int message_id) {
        List<Agree> agrees = agreeDAO.findAgree(message_id,user_id);
        agreeDAO.deleteAgree(agrees.get(0));
    }

    @Override
    public void updateMessage(int message_id, int add) {
        Message message = agreeDAO.getMessage(message_id);
        if(add == 1)
            message.setMessageAgreenum(message.getMessageAgreenum() + 1);
        else
            message.setMessageAgreenum(message.getMessageAgreenum() - 1);
        agreeDAO.update(message);

    }
}