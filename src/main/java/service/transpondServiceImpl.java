package service;

import com.opensymphony.xwork2.ActionContext;
import dao.messageDAO;
import dao.transpondDAO;
import pojo.Message;
import pojo.Remind;
import pojo.Transpond;
import pojo.User;
import dao.remindDAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class transpondServiceImpl implements transpondService {
    transpondDAO transponddao;
    messageDAO messagedao;
    remindDAO reminddao;

    @Override
    //得到该用户转发的所有数据
    public List<Transpond> listMyTranspond(User user) {
        List<Transpond> list=new ArrayList<>();
        for(Transpond transpond:transponddao.list()){
            if(transpond.getUserByUserId().getUserId()==user.getUserId()){
                list.add(transpond);
            }
        }
        return list;
    }

    @Override
    //在主页删除自己转发的微博
    public void delete(Transpond transpond) {
        transponddao.delete(transpond);
    }

    @Override
    public void add(Transpond transpond) {
        transponddao.add(transpond);
    }

    @Override
    //得到某个转发的数据
    public Transpond get(int id) {
        return (Transpond)transponddao.get(id);
    }

    @Override
    public void add(int messid, int reslut_meddid) {
        Transpond t=new Transpond();
        Message m=new Message();
        m.setMessageId(messid);
        Message r=new Message();
        r.setMessageId(reslut_meddid);
        t.setMessageByMessageId(m);
        t.setResultmessid(r);
        Map<String, Object> session = ActionContext.getContext().getSession();
        User u=(User)session.get("user");
        t.setUserByUserId(u);
        add(t);
        //将转发写入数据库
        int user_id = u.getUserId();
        m = messagedao.get(messid);
        int touser_id = m.getUserByUserId().getUserId();
        if(user_id!= touser_id) {
            Remind remind = new Remind();
            remind.setIsnew(false);
            remind.setMessageId(m);
            remind.setTouserId(touser_id);
            remind.setUsreId(user_id);
            remind.setType("transpond");
            remind.setTime(new Timestamp(System.currentTimeMillis()));
            this.reminddao.addRemind(remind);
        }
    }

    public transpondDAO getTransponddao() {
        return transponddao;
    }

    public void setTransponddao(transpondDAO transponddao) {
        this.transponddao = transponddao;
    }

    public messageDAO getMessagedao() {
        return messagedao;
    }

    public void setMessagedao(messageDAO messagedao) {
        this.messagedao = messagedao;
    }

    public remindDAO getReminddao() {
        return reminddao;
    }

    public void setReminddao(remindDAO reminddao) {
        this.reminddao = reminddao;
    }
}
