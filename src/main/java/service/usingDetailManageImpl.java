package service;

import bean.UsingCondition;
import com.opensymphony.xwork2.ActionContext;
import dao.messageDAO;
import dao.userDAO;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by apple on 2018/6/14.
 */
public class usingDetailManageImpl implements usingDetaiLManage{
    messageDAO messagedao;
    userDAO userdao;
    @Override
    public UsingCondition get() {
        UsingCondition usingCondition = new UsingCondition();
        Map<String, Object> application = ActionContext.getContext().getApplication();
        int onlineNumber = Integer.parseInt(application.get("onlineNumber").toString());
        usingCondition.setOnlinenum(onlineNumber);
        usingCondition.setTodaynum(onlineNumber);
        usingCondition.setUsernum(userdao.list().size());
        usingCondition.setTotalmessnum(messagedao.list().size());
        Date now=new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowdate=sdf.format(now);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(now);
        int []num=new int[7];
        //获取7天内增加的用户数
        for(int i=0;i<7;i++){
            num[6-i]=userdao.dayAddUser(nowdate);
            calendar.add(Calendar.DAY_OF_MONTH,-1);
            nowdate=sdf.format(calendar.getTime());
        }
        usingCondition.setNewuser(num);
        return usingCondition;
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
}
