package bean;

import java.sql.Timestamp;
import java.util.List;

public class agreeWB {
    int id;//userid
    Timestamp timestamp;
    String image;
    String nikename;
    long time;
    String weiboInfo;
    int transpond;
    int collect;
    int comment;
    int agree;
    int messid;
    String isTransponpd;//记录该微博是转发的还是原创的
    int tranfrommessid;     //转发自哪条微博
    List<transweibo> list;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public List<transweibo> getList() {
        return list;
    }

    public void setList(List<transweibo> list) {
        this.list = list;
    }

    public String getIsTransponpd() {
        return isTransponpd;
    }

    public void setIsTransponpd(String isTransponpd) {
        this.isTransponpd = isTransponpd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getWeiboInfo() {
        return weiboInfo;
    }

    public void setWeiboInfo(String weiboInfo) {
        this.weiboInfo = weiboInfo;
    }

    public int getTranspond() {
        return transpond;
    }

    public void setTranspond(int transpond) {
        this.transpond = transpond;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getAgree() {
        return agree;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }

    public int getMessid() {
        return messid;
    }

    public void setMessid(int messid) {
        this.messid = messid;
    }

    public int getTranfrommessid() {
        return tranfrommessid;
    }

    public void setTranfrommessid(int tranfrommessid) {
        this.tranfrommessid = tranfrommessid;
    }
}
