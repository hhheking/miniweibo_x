package bean;

public class fan {
    int userid;
    String name;
    String imageurl;
    int fans;
    int idols;
    int weibos;
    //status为互相关注或者关注
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getIdols() {
        return idols;
    }

    public void setIdols(int idols) {
        this.idols = idols;
    }

    public int getWeibos() {
        return weibos;
    }

    public void setWeibos(int weibos) {
        this.weibos = weibos;
    }
}
