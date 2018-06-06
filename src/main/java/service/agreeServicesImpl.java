package service;

import pojo.Agree;
import pojo.Message;
import dao.agreeDAO;
import pojo.Remind;
import pojo.User;
import dao.remindDAO;

import java.sql.Timestamp;
import java.util.List;

public class agreeServicesImpl implements agreeService {
    agreeDAO agreeDAO;
    remindDAO remindDAO;

    public void setAgreeDAO(agreeDAO agreeDAO) {
        this.agreeDAO = agreeDAO;
    }

    public agreeDAO getAgreeDAO() {
        return agreeDAO;
    }

    public dao.remindDAO getRemindDAO() { return remindDAO; }

    public void setRemindDAO(dao.remindDAO remindDAO) { this.remindDAO = remindDAO; }

    @Override
    public void addAgree(int user_id, int message_id) {
        Agree agree = new Agree();
        User user = new User();
        user.setUserId(user_id);
        Message message = new Message();
        message.setMessageId(message_id);
        agree.setUserByUserId(user);
        agree.setMessageByMessageId(message);
        agreeDAO.addAgree(agree);
        int touser_id = agreeDAO.getMessage(message_id).getUserByUserId().getUserId();
        if(touser_id!= user_id) {
            Remind remind = new Remind();
            message = agreeDAO.getMessage(message_id);
            remind.setIsnew(false);
            remind.setMessageId(message);
            remind.setTouserId(touser_id);
            remind.setUsreId(user_id);
            remind.setType("agree");
            remind.setTime(new Timestamp(System.currentTimeMillis()));
            this.remindDAO.addRemind(remind);
        }
    }

    @Override
    public void deleteAgree(int user_id, int message_id) {
        List<Agree> agrees = agreeDAO.findAgree(message_id,user_id);
        agreeDAO.deleteAgree(agrees.get(0));
        int touser_id = agreeDAO.getMessage(message_id).getUserByUserId().getUserId();
        if(touser_id != user_id) {
            Remind remind = remindDAO.getRemind(touser_id, user_id, message_id, "agree").get(0);
            remindDAO.deleteRemind(remind);
        }
    }

    @Override
    public void updateMessage(int message_id, int add) {
        Message message = agreeDAO.getMessage(message_id);
        if(add == 1)
            message.setMessageAgreenum(message.getMessageAgreenum() + 1);
        else
            message.setMessageAgreenum(message.getMessageAgreenum() - 1);
        agreeDAO.updateMessage(message);

    }
}
