package pojo;

import javax.persistence.*;

@Entity
public class Userinfo {
    private int userinfoId;
    private String userinfoTruename;
    private String userinfoAddress;
    private String userinfoSex;
    private String userinfoBirthday;
    private String userinfoIntro;
    private String userinfoQqnumber;
    private User userByUserId;

    @Id
    @Column(name = "userinfo_id")
    public int getUserinfoId() {
        return userinfoId;
    }

    public void setUserinfoId(int userinfoId) {
        this.userinfoId = userinfoId;
    }

    @Basic
    @Column(name = "userinfo_truename")
    public String getUserinfoTruename() {
        return userinfoTruename;
    }

    public void setUserinfoTruename(String userinfoTruename) {
        this.userinfoTruename = userinfoTruename;
    }

    @Basic
    @Column(name = "userinfo_address")
    public String getUserinfoAddress() {
        return userinfoAddress;
    }

    public void setUserinfoAddress(String userinfoAddress) {
        this.userinfoAddress = userinfoAddress;
    }

    @Basic
    @Column(name = "userinfo_sex")
    public String getUserinfoSex() {
        return userinfoSex;
    }

    public void setUserinfoSex(String userinfoSex) {
        this.userinfoSex = userinfoSex;
    }

    @Basic
    @Column(name = "userinfo_birthday")
    public String getUserinfoBirthday() {
        return userinfoBirthday;
    }

    public void setUserinfoBirthday(String userinfoBirthday) {
        this.userinfoBirthday = userinfoBirthday;
    }

    @Basic
    @Column(name = "userinfo_intro")
    public String getUserinfoIntro() {
        return userinfoIntro;
    }

    public void setUserinfoIntro(String userinfoIntro) {
        this.userinfoIntro = userinfoIntro;
    }

    @Basic
    @Column(name = "userinfo_qqnumber")
    public String getUserinfoQqnumber() {
        return userinfoQqnumber;
    }

    public void setUserinfoQqnumber(String userinfoQqnumber) {
        this.userinfoQqnumber = userinfoQqnumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Userinfo userinfo = (Userinfo) o;

        if (userinfoId != userinfo.userinfoId) return false;
        if (userinfoTruename != null ? !userinfoTruename.equals(userinfo.userinfoTruename) : userinfo.userinfoTruename != null)
            return false;
        if (userinfoAddress != null ? !userinfoAddress.equals(userinfo.userinfoAddress) : userinfo.userinfoAddress != null)
            return false;
        if (userinfoSex != null ? !userinfoSex.equals(userinfo.userinfoSex) : userinfo.userinfoSex != null)
            return false;
        if (userinfoBirthday != null ? !userinfoBirthday.equals(userinfo.userinfoBirthday) : userinfo.userinfoBirthday != null)
            return false;
        if (userinfoIntro != null ? !userinfoIntro.equals(userinfo.userinfoIntro) : userinfo.userinfoIntro != null)
            return false;
        if (userinfoQqnumber != null ? !userinfoQqnumber.equals(userinfo.userinfoQqnumber) : userinfo.userinfoQqnumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userinfoId;
        result = 31 * result + (userinfoTruename != null ? userinfoTruename.hashCode() : 0);
        result = 31 * result + (userinfoAddress != null ? userinfoAddress.hashCode() : 0);
        result = 31 * result + (userinfoSex != null ? userinfoSex.hashCode() : 0);
        result = 31 * result + (userinfoBirthday != null ? userinfoBirthday.hashCode() : 0);
        result = 31 * result + (userinfoIntro != null ? userinfoIntro.hashCode() : 0);
        result = 31 * result + (userinfoQqnumber != null ? userinfoQqnumber.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
