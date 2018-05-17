package pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Privateletter {
    private int privateletterId;
    private String privateletterInfo;
    private Timestamp privateletterTime;
    private User userByUserId;
    private User userByTouserId;

    @Id
    @Column(name = "privateletter_id")
    public int getPrivateletterId() {
        return privateletterId;
    }

    public void setPrivateletterId(int privateletterId) {
        this.privateletterId = privateletterId;
    }

    @Basic
    @Column(name = "privateletter_info")
    public String getPrivateletterInfo() {
        return privateletterInfo;
    }

    public void setPrivateletterInfo(String privateletterInfo) {
        this.privateletterInfo = privateletterInfo;
    }

    @Basic
    @Column(name = "privateletter_time")
    public Timestamp getPrivateletterTime() {
        return privateletterTime;
    }

    public void setPrivateletterTime(Timestamp privateletterTime) {
        this.privateletterTime = privateletterTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Privateletter that = (Privateletter) o;

        if (privateletterId != that.privateletterId) return false;
        if (privateletterInfo != null ? !privateletterInfo.equals(that.privateletterInfo) : that.privateletterInfo != null)
            return false;
        if (privateletterTime != null ? !privateletterTime.equals(that.privateletterTime) : that.privateletterTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = privateletterId;
        result = 31 * result + (privateletterInfo != null ? privateletterInfo.hashCode() : 0);
        result = 31 * result + (privateletterTime != null ? privateletterTime.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "touser_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByTouserId() {
        return userByTouserId;
    }

    public void setUserByTouserId(User userByTouserId) {
        this.userByTouserId = userByTouserId;
    }
}
