package pojo;

import javax.persistence.*;

@Entity
public class Agree {
    private int agreeId;
    private Message messageByMessageId;
    private User userByUserId;

    @Id
    @Column(name = "agree_id")
    public int getAgreeId() {
        return agreeId;
    }

    public void setAgreeId(int agreeId) {
        this.agreeId = agreeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agree agree = (Agree) o;

        if (agreeId != agree.agreeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return agreeId;
    }

    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "message_id", nullable = false)
    public Message getMessageByMessageId() {
        return messageByMessageId;
    }

    public void setMessageByMessageId(Message messageByMessageId) {
        this.messageByMessageId = messageByMessageId;
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
