import bean.chatinfo;
import bean.letter;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Privateletter;
import pojo.User;
import service.PrivateletterService;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/other_person.jsp")
public class ChatEntpoint extends ActionSupport {
    private String user;
    private String touser;
    private static final Set<ChatEntpoint> clientSet = new CopyOnWriteArraySet<ChatEntpoint>();
    private Session session;
    private String picture;
    private List<letter> list;
    private List<String> data;

    public void setUser(String user) {
        this.user = user;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public List<String> getData() {
        return data;
    }

    @OnOpen
    public void start(Session session){
        this.session = session;
        clientSet.add(this);
        this.list = new ArrayList<letter>();
    }

    @OnClose
    public void end(){
        clientSet.remove(this);
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
        PrivateletterService p = ctx.getBean("privateletterservixeImpl", PrivateletterService.class);
        Privateletter pri = new Privateletter();
        User user1 = new User();
        User user2 = new User();
        letter le = new letter();
        while(!list.isEmpty()) {
            le = list.get(0);
            user1 = p.getID(this.user).get(0);
            user2 = p.getID(this.touser).get(0);
            pri.setUserByUserId(user1);
            pri.setUserByTouserId(user2);
            pri.setPrivateletterInfo(le.content);
            pri.setPrivateletterTime(le.timestamp);
            p.add(pri);
            list.remove(0);
        }
    }

    @OnMessage
    public void incoming(String message) throws IOException {
        if(message.startsWith("$$$#")) {
            String s[] = message.split("#");
            this.user = s[1];
            this.picture = s[2];
            this.touser = s[3];
        }
        else {
            String s[] = message.split("#");
            letter le = new letter();
            le.content = s[0];
            le.timestamp = new Timestamp(System.currentTimeMillis());
            list.add(le);
            for (ChatEntpoint client : clientSet) {
                if (client.user.equals(s[1])) {
                    synchronized (client) {
                        client.session.getBasicRemote().sendText(s[0] + "#" + picture);
                    }
                    break;
                }
            }
        }

    }

    @OnError
    public void onError(Throwable t) throws Throwable{
    }

    public String chatajax(){
        List<chatinfo> chat = new ArrayList<chatinfo>();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
        PrivateletterService p = ctx.getBean("privateletterservixeImpl", PrivateletterService.class);
        int user_id = p.getID(this.user).get(0).getUserId();
        int touser_id = p.getID(this.touser).get(0).getUserId();
        List<Privateletter> priletter;
        priletter = p.list(user_id,touser_id);

        for(int i=priletter.size()-1;i>=0;i--){
            chatinfo chinfo = new chatinfo();
            chinfo.content = priletter.get(i).getPrivateletterInfo();
            chinfo.time = priletter.get(i).getPrivateletterTime();
            if(priletter.get(i).getUserByUserId().getUserId() == user_id)
                chinfo.methond = "show";
            else
                chinfo.methond = "send";
            chat.add(chinfo);
        }
        Collections.sort(chat);
        this.data = new ArrayList<String>();
        for(int i=0;i < chat.size();i++)
            this.data.add(chat.get(i).content + "#"+chat.get(i).time.toString().substring(0,chat.get(i).time.toString().length()-2) + "#"+chat.get(i).methond);
        return SUCCESS;
    }

}
