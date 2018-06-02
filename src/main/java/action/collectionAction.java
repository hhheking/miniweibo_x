package action;

import com.opensymphony.xwork2.ActionSupport;
import service.collectionService;

public class collectionAction extends ActionSupport {
    private int message_id;
    private int user_id;
    private int add;
    private  collectionService collectionservice;

    public void setAdd(int add) {
        this.add = add;
    }

    public int getAdd() {
        return add;
    }

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

    public void setCollectionservice(collectionService collectionservice) {
        this.collectionservice = collectionservice;
    }

    public collectionService getCollectionservice() {
        return collectionservice;
    }

    @Override
    public String execute() throws Exception {
        this.collectionservice.updateMessage(this.message_id,this.add);
        if(this.add == 1)
            this.collectionservice.addCollection(this.user_id,this.message_id);
        else
            this.collectionservice.deleteCollection(this.user_id,this.message_id);
        return SUCCESS;
    }
}
