package action;

import bean.remindletter;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.ApplicationContext;
import pojo.Remind;
import pojo.User;
import service.remindService;
import java.util.ArrayList;
import java.util.List;

public class remindAction{
    private remindService remindservice;
    private int touser_id;
    private int[] result;
    List<remindletter> letters;

    public remindService getRemindservice() {
        return remindservice;
    }

    public void setRemindservice(remindService remindservice) {
        this.remindservice = remindservice;
    }

    public int[] getResult() {
        return result;
    }

    public List<remindletter> getLetters() {
        return letters;
    }

    public String root(){
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
        return "success";
    }

    public String letter(){
        User u=(User)ActionContext.getContext().getSession().get("user");
        touser_id = u.getUserId();
        letters = new ArrayList<>();
        List<Remind> list = remindservice.list(touser_id,"letter");
        List<Integer> userid = new ArrayList<>();
        Remind remind;
        for(int i = list.size()-1;i>=0;i--){
            remind = list.get(i);
            if(remind.getIsnew() == false)
                remindservice.updateRemind(remind);
            if(!userid.contains(remind.getUsreId())){
                remindletter rletter = new remindletter();
                rletter.setContent(remind.getContent());
                u = remindservice.getUser(remind.getUsreId());
                rletter.setName(u.getUserNikename());
                rletter.setPic(u.getIcon());
                rletter.setTime(remind.getTime().toString().substring(0,remind.getTime().toString().length()-2));
                userid.add(remind.getUsreId());
                letters.add(rletter);
            }
        }
        return "letter";
    }
}
