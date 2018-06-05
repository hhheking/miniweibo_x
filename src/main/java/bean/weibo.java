package bean;

import pojo.Message;
import pojo.Transpond;

import java.util.List;

public class weibo {
    int id;//userid
    String image;
    String nikename;
    long time;
    String weiboInfo;
    int transpond;
    int collect;
    int comment;
    int agree;
    int messid;
    String collect_status;
    String agree_status;
    String isTransponpd;      //记录该微博是转发的还是原创的
    int tranfrommessid;     //转发自哪条微博
    List<transInfo> tranList;


    public List<transInfo> getTranList() {
        return tranList;
    }

    public void setTranList(List<transInfo> tranList) {
        this.tranList = tranList;
    }

    public int getTranfrommessid() {
        return tranfrommessid;
    }

    public void setTranfrommessid(int tranfrommessid) {
        this.tranfrommessid = tranfrommessid;
    }
    public String getAgree_status() {
        return agree_status;
    }

    public String getCollect_status() {
        return collect_status;
    }

    public void setAgree_status(String agree_status) {
        this.agree_status = agree_status;
    }

    public void setCollect_status(String collect_status) {
        this.collect_status = collect_status;
    }

    public int getMessid() {
        return messid;
    }

    public void setMessid(int messid) {
        this.messid = messid;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public int getCollect() {
        return collect;
    }

    public String getImage() {
        return image;
    }

    public int getTranspond() {
        return transpond;
    }

    public long getTime() {
        return time;
    }

    public String getWeiboInfo() {
        return weiboInfo;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getComment() {
        return comment;
    }

    public int getAgree() {
        return agree;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setWeiboInfo(String weiboInfo) {
        this.weiboInfo = weiboInfo;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public void setTranspond(int transpond) {
        this.transpond = transpond;
    }

    public String getIsTransponpd() {
        return isTransponpd;
    }

    public void setIsTransponpd(String isTransponpd) {
        this.isTransponpd = isTransponpd;
    }
}
