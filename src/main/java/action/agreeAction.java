package action;

import com.opensymphony.xwork2.ActionSupport;
import service.agreeService;
import service.remindService;

public class agreeAction extends ActionSupport {
    private int message_id;
    private int user_id;
    private agreeService agreeService;
    int add;

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setAdd(int add) {
        this.add = add;
    }

    public int getAdd() {
        return add;
    }

    public void setAgreeService(agreeService agreeServices) {
        this.agreeService = agreeServices;
    }

    public agreeService getAgreeService() { return agreeService; }

    @Override
    public String execute() throws Exception {
        agreeService.updateMessage(this.message_id,this.add);
        if(add == 1)
            agreeService.addAgree(this.user_id,this.message_id);
        else
            agreeService.deleteAgree(this.user_id,this.message_id);
        return SUCCESS;
    }
}
