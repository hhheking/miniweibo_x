package intercept;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import pojo.User;

import java.util.Map;

public class indexInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        //得到当前拦截的ActionContext实例
        ActionContext ctx=actionInvocation.getInvocationContext();
        Map session=ctx.getSession();
        User user=(User)session.get("user");
        //当前用户没有登录
        if(user!=null || ctx.getName().equals("loginUser")){
            return actionInvocation.invoke();
        }
        return "loginUser";
    }
}
