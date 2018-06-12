package action;

import com.sun.net.httpserver.Authenticator;
import org.omg.PortableInterceptor.SUCCESSFUL;

public class jumpAction {
    int param;

    public int getParam() {
        return param;
    }

    public void setParam(int param) {
        this.param = param;
    }
    public String jump(){
        return "success";
    }
}
