
package Listener;
import com.opensymphony.xwork2.ActionContext;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;

public class OnlineUserListener implements HttpSessionListener,
        HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent arg0) {
        System.out.println("attributeAdded.....");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent arg0) {
        System.out.println("attributeRemoved.....");

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent arg0) {
        System.out.println("attributeReplaced.....");
    }

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        System.out.println("createSession.....");
        Map<String, Object> application = ActionContext.getContext().getApplication();
        //从web配置文件中得到属性的值，如果创建一个session就加一，销毁减一
        if(application.get("onlineNumber")==null){
            application.put("onlineNumber",0);
        }
        int onlineNumber = Integer.parseInt(application.get("onlineNumber").toString()) + 1;
        application.put("onlineNumber", onlineNumber);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        System.out.println("deleteSession.....");
        Map<String, Object> application = ActionContext.getContext().getApplication();
        int onlineNumber = Integer.parseInt(application.get("onlineNumber").toString()) - 1;
        application.put("onlineNumber", onlineNumber);
    }
}