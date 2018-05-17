package pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class User {
    private int userId;
    private String userEmail;
    private String userPassword;
    private String userNikename;
    private Timestamp userTime;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_email")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "user_password")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "user_nikename")
    public String getUserNikename() {
        return userNikename;
    }

    public void setUserNikename(String userNikename) {
        this.userNikename = userNikename;
    }

    @Basic
    @Column(name = "user_time")
    public Timestamp getUserTime() {
        return userTime;
    }

    public void setUserTime(Timestamp userTime) {
        this.userTime = userTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (userEmail != null ? !userEmail.equals(user.userEmail) : user.userEmail != null) return false;
        if (userPassword != null ? !userPassword.equals(user.userPassword) : user.userPassword != null) return false;
        if (userNikename != null ? !userNikename.equals(user.userNikename) : user.userNikename != null) return false;
        if (userTime != null ? !userTime.equals(user.userTime) : user.userTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (userNikename != null ? userNikename.hashCode() : 0);
        result = 31 * result + (userTime != null ? userTime.hashCode() : 0);
        return result;
    }
}
