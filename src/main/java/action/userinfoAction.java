package action;

import com.opensymphony.xwork2.ActionContext;
import pojo.User;
import pojo.Userinfo;
import service.userinfoService;

public class userinfoAction {
    userinfoService userinfoservice;
    Userinfo userinfo;

    public userinfoService getUserinfoservice() {
        return userinfoservice;
    }

    public void setUserinfoservice(userinfoService userinfoservice) {
        this.userinfoservice = userinfoservice;
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public String add(){
        User user=(User) ActionContext.getContext().getSession().get("user");
        User user1=new User();
        user1.setUserId(user.getUserId());
        userinfo.setUserByUserId(user1);
        userinfoservice.add(userinfo);
        return "success";
    }
}
