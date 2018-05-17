package pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Collection {
    private int collectionId;
    private Timestamp collectionTime;
    private String collectionStatus;
    private User userByUserId;
    private Message messageByMessageId;

    @Id
    @Column(name = "collection_id")
    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    @Basic
    @Column(name = "collection_time")
    public Timestamp getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Timestamp collectionTime) {
        this.collectionTime = collectionTime;
    }

    @Basic
    @Column(name = "collection_status")
    public String getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(String collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Collection that = (Collection) o;

        if (collectionId != that.collectionId) return false;
        if (collectionTime != null ? !collectionTime.equals(that.collectionTime) : that.collectionTime != null)
            return false;
        if (collectionStatus != null ? !collectionStatus.equals(that.collectionStatus) : that.collectionStatus != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = collectionId;
        result = 31 * result + (collectionTime != null ? collectionTime.hashCode() : 0);
        result = 31 * result + (collectionStatus != null ? collectionStatus.hashCode() : 0);
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
    @JoinColumn(name = "message_id", referencedColumnName = "message_id", nullable = false)
    public Message getMessageByMessageId() {
        return messageByMessageId;
    }

    public void setMessageByMessageId(Message messageByMessageId) {
        this.messageByMessageId = messageByMessageId;
    }
}
