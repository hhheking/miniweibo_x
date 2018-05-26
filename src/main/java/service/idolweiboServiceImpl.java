package service;

import bean.weibo;
import pojo.Message;
import pojo.Relation;
import pojo.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class idolweiboServiceImpl implements idolweiboService {
    messageService messageservice;
    relationService relationservice;
    userService userservice;

    public relationService getRelationservice() {
        return relationservice;
    }

    public void setRelationservice(relationService relationservice) {
        this.relationservice = relationservice;
    }

    public messageService getMessageservice() {
        return messageservice;
    }

    public void setMessageservice(messageService messageservice) {
        this.messageservice = messageservice;
    }

    public userService getUserservice() {
        return userservice;
    }

    public void setUserservice(userService userservice) {
        this.userservice = userservice;
    }

    @Override
    public String imageaddress() {
        return null;
    }

    //user指的是登录用户的对象
    @Override
    public List<User> idols(User user) {
        List<User> userList=new ArrayList<>();
        for(Relation relation:relationservice.list()){
            if(relation.getUserByUserId().getUserId()==user.getUserId())
                userList.add(userservice.get(relation.getUserByUserByid().getUserId()));
        }
        userList.add(user);
        return userList;
    }


    @Override
    public long timeCount(Message message) {
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        //由毫秒转化为分钟，所以除以1000*60
        //填写测试用例的时候 没有写微博发表的时间 因此这里要稍微修改
        return (timestamp.getTime()-message.getMessageTime().getTime())/(1000*60);
    }

    //此处的user为登录用户的实例
    @Override
    public List<weibo> calidolweibos(User user){
        List<weibo> weiboList=new ArrayList<>();
        for(User user1:idols(user)){
            for(Message message:messageservice.myMessage(user1)) {
                weibo wb=new weibo();
                //设置用户的头像
                //
                wb.setNikename(user1.getUserNikename());
                wb.setTime(timeCount(message));
                wb.setWeiboInfo(message.getMessageInfo());
                wb.setTranspond(message.getMessageTranspondnum());
                wb.setAgree(message.getMessageAgreenum());
                wb.setComment(message.getMessageCommentnum());
                wb.setCollect(message.getMessageCollectnum());
                wb.setId(user1.getUserId());
                weiboList.add(wb);
            }
        }
        //此处按照微博发布的时间先后顺序 进行排序
        weiboList.sort(new Comparator<weibo>() {
            @Override
            public int compare(weibo o1, weibo o2) {
                if(o1.getTime()>o2.getTime()){
                    return 1;
                }else {
                    return -1;
                }
            }
        });
        return weiboList;
    }
}
