package action;

import bean.weibo;
import com.opensymphony.xwork2.ActionSupport;
import service.idolweiboService;

import java.util.List;

public class indexAction  extends ActionSupport{
    List<weibo> weibos;
    idolweiboService idolweiboservice;

    public List<weibo> getWeibos() {
        return weibos;
    }

    public void setWeibos(List<weibo> weibos) {
        this.weibos = weibos;
    }

    public idolweiboService getIdolweiboservice() {
        return idolweiboservice;
    }

    public void setIdolweiboservice(idolweiboService idolweiboservice) {
        this.idolweiboservice = idolweiboservice;
    }

    @Override
    public String execute() throws Exception {
        weibos=idolweiboservice.index();
        return SUCCESS;
    }
}
