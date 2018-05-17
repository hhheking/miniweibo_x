package pojo;

import javax.persistence.*;

@Entity
public class Transpond {
    private int transpondId;
    private User userByUserId;
    private Message messageByMessageId;

    @Id
    @Column(name = "transpond_id")
    public int getTranspondId() {
        return transpondId;
    }

    public void setTranspondId(int transpondId) {
        this.transpondId = transpondId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transpond transpond = (Transpond) o;

        if (transpondId != transpond.transpondId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return transpondId;
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
    @JoinColumn(name = "message_id", referencedColumnName = "message_id", nullable = false)
    public Message getMessageByMessageId() {
        return messageByMessageId;
    }

    public void setMessageByMessageId(Message messageByMessageId) {
        this.messageByMessageId = messageByMessageId;
    }
}
