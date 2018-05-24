package bean;

public class weibo {
    int id;
    String image;
    String nikename;
    long time;
    String weiboInfo;
    int transpond;
    int collect;
    int comment;
    int agree;

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
}
