package intercept;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import pojo.User;

import java.util.Map;

public class messageInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        //得到当前拦截的ActionContext实例
        ActionContext ctx=actionInvocation.getInvocationContext();
        String[] o=(String[])ctx.getParameters().get("messageID");
        //得到当前要查看的message的id
        //当前访问的是发微博
        if(o!=null && o[0].equals("0")){
            return "messagefail";
        }
        return actionInvocation.invoke();
    }
}
