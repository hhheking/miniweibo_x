package action;

import bean.*;
import com.opensymphony.xwork2.ActionContext;
import dao.commentDAO;
import dao.transpondDAO;
import org.springframework.context.ApplicationContext;
import pojo.*;
import service.messageService;
import service.remindService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class remindAction{
    private remindService remindservice;
    private messageService messageservice;
    private int touser_id;
    private int[] result;
    List<remindcomment> comments;
    List<remindletter> letters;
    List<remindagree> agrees;
    List<remindTranspond> transponds;
    transpondDAO transponddao;
    commentDAO commentdao;

    public List<remindTranspond> getTransponds() {
        return transponds;
    }

    public List<remindcomment> getComments() {
        return comments;
    }

    public commentDAO getCommentdao() {
        return commentdao;
    }

    public void setCommentdao(commentDAO commentdao) {
        this.commentdao = commentdao;
    }

    public transpondDAO getTransponddao() {
        return transponddao;
    }

    public void setTransponddao(transpondDAO transponddao) {
        this.transponddao = transponddao;
    }

    public void setMessageservice(messageService messageservice) {
        this.messageservice = messageservice;
    }

    public messageService getMessageservice() {
        return messageservice;
    }

    public List<remindagree> getAgrees() {
        return agrees;
    }

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

    public String agree(){
        User user=(User)ActionContext.getContext().getSession().get("user");
        agrees=new ArrayList<>();
        List<Remind> list=remindservice.list(user.getUserId(),"agree");
        //list为所有点赞过“我”的remind对象
        User user1;
        for(Remind remind:list){
            if(remind.getIsnew() == false)
                remindservice.updateRemind(remind);
            remindagree ragree=new remindagree();
            ragree.setAgreetime(remind.getTime());
            user1=remindservice.getUser(remind.getUsreId());
            ragree.setPic(user1.getIcon());
            ragree.setUsername(user1.getUserNikename());
            //为ragree对象填入weibo对象
            agreeWB wb=new agreeWB();
            //得到点赞的微博的对象
            Message message=messageservice.get(remind.getMessageId().getMessageId());
            wb.setNikename(user.getUserNikename());
            wb.setWeiboInfo(message.getMessageInfo());
            wb.setTranspond(message.getMessageTranspondnum());
            wb.setAgree(message.getMessageAgreenum());
            wb.setComment(message.getMessageCommentnum());
            wb.setCollect(message.getMessageCollectnum());
            wb.setMessid(message.getMessageId());
            wb.setId(user.getUserId());
            if (message.getMessageType().equals("Transpond")) {
                wb.setIsTransponpd("true");
                Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
                int OrignId =0;
                if (transpond!=null)
                    OrignId = transpond.getMessageByMessageId().getMessageId();
                wb.setTranfrommessid(OrignId);
                ragree.setWb(wb);
                agrees.add(ragree);
            }
            else {
                wb.setIsTransponpd("false");
                ragree.setWb(wb);
                agrees.add(ragree);
            }

        }
        for (int i = 0; i < agrees.size(); i++) {
            Message message = messageservice.get(agrees.get(i).getWb().getMessid());
            List<transweibo> transweibos = new ArrayList<>();
            while (message.getMessageType().equals("Transpond")) {
                search_message message1=new search_message();
                Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
                int OrignId =0;
                if (transpond!=null)
                    OrignId = transpond.getMessageByMessageId().getMessageId();
                message = messageservice.get(OrignId);
                message1.setMessageType(message.getMessageType());
                message1.setMessageTime(message.getMessageTime());
                message1.setMessageCommentnum(message.getMessageCommentnum());
                message1.setMessageTranspondnum(message.getMessageTranspondnum());
                message1.setMessageAgreenum(message.getMessageAgreenum());
                message1.setInfo(message.getMessageInfo());
                message1.setId(message.getMessageId());
                message1.setMessageCollectnum(message.getMessageCollectnum());
                User u;
                search_user user2=new search_user();
                if(message==null){
                    message=new Message();
                    //message.setMessageInfo("转发微博被删除");
                    message1.setInfo("转发微博被删除");
                    message.setMessageType("Orign");
                    message1.setMessageType("Orign");
                    u=new User();
                }
                else {
                    u = remindservice.getUser(message.getUserByUserId().getUserId());
                    user2.setName(u.getUserNikename());
                    user2.setImageurl(u.getIcon());
                    user2.setId(u.getUserId());
                }
                agrees.get(i).getWb().setList(transweibos);
                transweibo transwb = new transweibo();
                transwb.setMessage(message1);
                transwb.setUser(user2);
                transweibos.add(transwb);
            }
        }
        return "agree";
    }

    public String comment(){
        //同一个用户对同一个微博可能会有很多评价、注意去掉重复的
        User user=(User)ActionContext.getContext().getSession().get("user");
        List<Remind> list = remindservice.list(user.getUserId(),"comment");
        //list为所有评论过“我发的微博”的remind对象
        User user1;
        //comments列表为返回的数据对象;
        List<flag> flagList=new ArrayList<>();
        boolean exit=false;
        int userid,messageid;
        comments=new ArrayList<>();
        for(Remind remind:list){
            if(remind.getIsnew() == false)
                remindservice.updateRemind(remind);
            //首先查询用户评论某条微博的全部评论
            userid=remind.getUsreId();
            messageid=remind.getMessageId().getMessageId();
            for(flag flag:flagList){
                if(flag.getA()==userid && flag.getB()==messageid){
                    exit=true;
                }
            }
            if(exit){
                exit=false;
                continue;
            }
            flag flag=new flag();
            flag.setA(userid);
            flag.setB(messageid);
            flagList.add(flag);
            List<String> commentlist=commentdao.findByUseridAndMessageid(remind.getUsreId(),remind.getMessageId().getMessageId());
            remindcomment rcomment=new remindcomment();
            rcomment.setCommenttime(remind.getTime());
            user1=remindservice.getUser(remind.getUsreId());
            rcomment.setPic(user1.getIcon());
            rcomment.setUsername(user1.getUserNikename());
            rcomment.setCommentinfos(commentlist);
            //为rcomment对象填入weibo对象
            agreeWB wb=new agreeWB();
            //得到点赞的微博的对象
            Message message=messageservice.get(remind.getMessageId().getMessageId());
            wb.setNikename(user.getUserNikename());
            wb.setWeiboInfo(message.getMessageInfo());
            wb.setTranspond(message.getMessageTranspondnum());
            wb.setAgree(message.getMessageAgreenum());
            wb.setComment(message.getMessageCommentnum());
            wb.setCollect(message.getMessageCollectnum());
            wb.setMessid(message.getMessageId());
            wb.setId(user.getUserId());
            if (message.getMessageType().equals("Transpond")) {
                wb.setIsTransponpd("true");
                Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
                int OrignId =0;
                if (transpond!=null)
                    OrignId = transpond.getMessageByMessageId().getMessageId();
                wb.setTranfrommessid(OrignId);
                rcomment.setWb(wb);
                comments.add(rcomment);
            }
            else {
                wb.setIsTransponpd("false");
                rcomment.setWb(wb);
                comments.add(rcomment);
            }

        }
        for (int i = 0; i < comments.size(); i++) {
            Message message = messageservice.get(comments.get(i).getWb().getMessid());
            List<transweibo> transweibos = new ArrayList<>();
            while (message.getMessageType().equals("Transpond")) {
                search_message message1=new search_message();
                Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
                int OrignId =0;
                if (transpond!=null)
                    OrignId = transpond.getMessageByMessageId().getMessageId();
                message = messageservice.get(OrignId);
                message1.setMessageType(message.getMessageType());
                message1.setMessageTime(message.getMessageTime());
                message1.setMessageCommentnum(message.getMessageCommentnum());
                message1.setMessageTranspondnum(message.getMessageTranspondnum());
                message1.setMessageAgreenum(message.getMessageAgreenum());
                message1.setInfo(message.getMessageInfo());
                message1.setId(message.getMessageId());
                message1.setMessageCollectnum(message.getMessageCollectnum());
                User u;
                search_user user2=new search_user();
                if(message==null){
                    message=new Message();
                    //message.setMessageInfo("转发微博被删除");
                    message1.setInfo("转发微博被删除");
                    message.setMessageType("Orign");
                    message1.setMessageType("Orign");
                    u=new User();
                }
                else {
                    u = remindservice.getUser(message.getUserByUserId().getUserId());
                    user2.setName(u.getUserNikename());
                    user2.setImageurl(u.getIcon());
                    user2.setId(u.getUserId());
                }
                comments.get(i).getWb().setList(transweibos);
                transweibo transwb = new transweibo();
                transwb.setMessage(message1);
                transwb.setUser(user2);
                transweibos.add(transwb);
            }
        }
        return "comment";
    }

    public String transPond(){
        User user=(User)ActionContext.getContext().getSession().get("user");
        List<Remind> list = remindservice.list(user.getUserId(),"transpond");
        //list为所有转发过“我发的微博”的remind对象
        User user1;
        //comments列表为返回的数据对象
        List<flag> flagList=new ArrayList<>();
        boolean exit=false;
        transponds=new ArrayList<>();
        int userid,messageid;
        for(Remind remind:list){
            if(remind.getIsnew() == false)
                remindservice.updateRemind(remind);
            userid=remind.getUsreId();
            messageid=remind.getMessageId().getMessageId();
            for(flag flag:flagList){
                if(flag.getA()==userid && flag.getB()==messageid){
                    exit=true;
                }
            }
            if(exit){
                exit=false;
                continue;
            }
            flag flag=new flag();
            flag.setA(userid);
            flag.setB(messageid);
            flagList.add(flag);
            remindTranspond rtranspond=new remindTranspond();
            user1=remindservice.getUser(remind.getUsreId());
            rtranspond.setCommenttime(remind.getTime());
            rtranspond.setPic(user1.getIcon());
            rtranspond.setUsername(user1.getUserNikename());
            List<Transpond> transpondList=transponddao.getTranspondByuseridAndmessageid(remind.getUsreId(),remind.getMessageId().getMessageId());
            List<String> strings=new ArrayList<>();
            for(Transpond transpond:transpondList){
                //得到用户转发后的微博生成的对象
                Message message=messageservice.get(transpond.getResultmessid().getMessageId());
                strings.add(message.getMessageInfo());
            }
            rtranspond.setTransinfos(strings);
            //为rtranspond对象填入weibo对象
            agreeWB wb=new agreeWB();
            //得到点赞的微博的对象
            Message message=messageservice.get(remind.getMessageId().getMessageId());
            wb.setNikename(user.getUserNikename());
            wb.setWeiboInfo(message.getMessageInfo());
            wb.setTranspond(message.getMessageTranspondnum());
            wb.setAgree(message.getMessageAgreenum());
            wb.setComment(message.getMessageCommentnum());
            wb.setCollect(message.getMessageCollectnum());
            wb.setMessid(message.getMessageId());
            wb.setId(user.getUserId());
            if (message.getMessageType().equals("Transpond")) {
                wb.setIsTransponpd("true");
                Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
                int OrignId =0;
                if (transpond!=null)
                    OrignId = transpond.getMessageByMessageId().getMessageId();
                wb.setTranfrommessid(OrignId);
                rtranspond.setWb(wb);
                transponds.add(rtranspond);
            }
            else {
                wb.setIsTransponpd("false");
                rtranspond.setWb(wb);
                transponds.add(rtranspond);
            }

        }
        for (int i = 0; i < transponds.size(); i++) {
            Message message = messageservice.get(transponds.get(i).getWb().getMessid());
            List<transweibo> transweibos = new ArrayList<>();
            while (message.getMessageType().equals("Transpond")) {
                search_message message1=new search_message();
                Transpond transpond=transponddao.findTranspondFrom(message.getMessageId());
                int OrignId =0;
                if (transpond!=null)
                    OrignId = transpond.getMessageByMessageId().getMessageId();
                message = messageservice.get(OrignId);
                message1.setMessageType(message.getMessageType());
                message1.setMessageTime(message.getMessageTime());
                message1.setMessageCommentnum(message.getMessageCommentnum());
                message1.setMessageTranspondnum(message.getMessageTranspondnum());
                message1.setMessageAgreenum(message.getMessageAgreenum());
                message1.setInfo(message.getMessageInfo());
                message1.setId(message.getMessageId());
                message1.setMessageCollectnum(message.getMessageCollectnum());
                User u;
                search_user user2=new search_user();
                if(message==null){
                    message=new Message();
                    //message.setMessageInfo("转发微博被删除");
                    message1.setInfo("转发微博被删除");
                    message.setMessageType("Orign");
                    message1.setMessageType("Orign");
                    u=new User();
                }
                else {
                    u = remindservice.getUser(message.getUserByUserId().getUserId());
                    user2.setName(u.getUserNikename());
                    user2.setImageurl(u.getIcon());
                    user2.setId(u.getUserId());
                }
                transponds.get(i).getWb().setList(transweibos);
                transweibo transwb = new transweibo();
                transwb.setMessage(message1);
                transwb.setUser(user2);
                transweibos.add(transwb);
            }
        }
        return "transPond";
    }
}
