package pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Atuser {
    private int atuserId;
    private int userId;
    private Timestamp atuserTime;
    private User userByAtuserId;
    private Message messageByMessageId;

    @Id
    @Column(name = "atuser_id")
    public int getAtuserId() {
        return atuserId;
    }

    public void setAtuserId(int atuserId) {
        this.atuserId = atuserId;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "atuser_time")
    public Timestamp getAtuserTime() {
        return atuserTime;
    }

    public void setAtuserTime(Timestamp atuserTime) {
        this.atuserTime = atuserTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Atuser atuser = (Atuser) o;

        if (atuserId != atuser.atuserId) return false;
        if (userId != atuser.userId) return false;
        if (atuserTime != null ? !atuserTime.equals(atuser.atuserTime) : atuser.atuserTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = atuserId;
        result = 31 * result + userId;
        result = 31 * result + (atuserTime != null ? atuserTime.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "atuser_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByAtuserId() {
        return userByAtuserId;
    }

    public void setUserByAtuserId(User userByAtuserId) {
        this.userByAtuserId = userByAtuserId;
    }

    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "message_id", nullable = false)
    public Message getMessageByMessageId() {
        return messageByMessageId;
    }

    public void setMessageByMessageId(Message messageByMessageId) {
        this.messageByMessageId = messageByMessageId;
    }
}
