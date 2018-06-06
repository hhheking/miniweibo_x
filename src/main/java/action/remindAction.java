package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.ApplicationContext;
import pojo.Remind;
import pojo.User;
import service.remindService;

import java.util.List;

public class remindAction extends ActionSupport {
    private remindService remindservice;
    private int touser_id;
    private int[] result;
    public remindService getRemindservice() {
        return remindservice;
    }

    public void setRemindservice(remindService remindservice) {
        this.remindservice = remindservice;
    }

    public int[] getResult() {
        return result;
    }

    @Override
    public String execute() throws Exception {
        User u=(User)ActionContext.getContext().getSession().get("user");
        touser_id = u.getUserId();
        result = new int[5];
        List<Remind> list = remindservice.findnew(touser_id);
       for(Remind remind:list){
           if(remind.getType().equals("transpond"))
               result[0]++;
           else if(remind.getType().equals("comment"))
               result[1]++;
           else if(remind.getType().equals("agree"))
               result[2]++;
           else
               result[3]++;
           result[4] = result[0]+result[1]+result[2]+result[3];

       }
        return SUCCESS;
    }
}
