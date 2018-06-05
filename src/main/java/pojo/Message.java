package pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Message {
    private int messageId;
    private String messageType;
    private String messageInfo;
    private Timestamp messageTime;
    private Integer messageCollectnum;
    private Integer messageCommentnum;
    private Integer messageTranspondnum;
    private Integer messageAgreenum;
    private User userByUserId;
    private Picture pictureByPictureId;
    private Set<Collection> collectionSet=new HashSet<Collection>();
    private Set<Comment> commentSet=new HashSet<Comment>();
    private Set<Agree> agreeSet=new HashSet<Agree>();
    private Set<Transpond> transpondSet=new HashSet<Transpond>();
    private Set<Transpond> transpondresultSet=new HashSet<Transpond>();

    public Set<Transpond> getTranspondresultSet() {
        return transpondresultSet;
    }

    public void setTranspondresultSet(Set<Transpond> transpondresultSet) {
        this.transpondresultSet = transpondresultSet;
    }
    public Set<Agree> getAgreeSet() {
        return agreeSet;
    }

    public Set<Collection> getCollectionSet() {
        return collectionSet;
    }

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setAgreeSet(Set<Agree> agreeSet) {
        this.agreeSet = agreeSet;
    }

    public Set<Transpond> getTranspondSet() {
        return transpondSet;
    }

    public void setCollectionSet(Set<Collection> collectionSet) {
        this.collectionSet = collectionSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    public void setTranspondSet(Set<Transpond> transpondSet) {
        this.transpondSet = transpondSet;
    }

    @Id
    @Column(name = "message_id")
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "message_type")
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Basic
    @Column(name = "message_info")
    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    @Basic
    @Column(name = "message_time")
    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    @Basic
    @Column(name = "message_collectnum")
    public Integer getMessageCollectnum() {
        return messageCollectnum;
    }

    public void setMessageCollectnum(Integer messageCollectnum) {
        this.messageCollectnum = messageCollectnum;
    }

    @Basic
    @Column(name = "message_commentnum")
    public Integer getMessageCommentnum() {
        return messageCommentnum;
    }

    public void setMessageCommentnum(Integer messageCommentnum) {
        this.messageCommentnum = messageCommentnum;
    }

    @Basic
    @Column(name = "message_transpondnum")
    public Integer getMessageTranspondnum() {
        return messageTranspondnum;
    }

    public void setMessageTranspondnum(Integer messageTranspondnum) {
        this.messageTranspondnum = messageTranspondnum;
    }

    @Basic
    @Column(name = "message_agreenum")
    public Integer getMessageAgreenum() {
        return messageAgreenum;
    }

    public void setMessageAgreenum(Integer messageAgreenum) {
        this.messageAgreenum = messageAgreenum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (messageId != message.messageId) return false;
        if (messageType != null ? !messageType.equals(message.messageType) : message.messageType != null) return false;
        if (messageInfo != null ? !messageInfo.equals(message.messageInfo) : message.messageInfo != null) return false;
        if (messageTime != null ? !messageTime.equals(message.messageTime) : message.messageTime != null) return false;
        if (messageCollectnum != null ? !messageCollectnum.equals(message.messageCollectnum) : message.messageCollectnum != null)
            return false;
        if (messageCommentnum != null ? !messageCommentnum.equals(message.messageCommentnum) : message.messageCommentnum != null)
            return false;
        if (messageTranspondnum != null ? !messageTranspondnum.equals(message.messageTranspondnum) : message.messageTranspondnum != null)
            return false;
        if (messageAgreenum != null ? !messageAgreenum.equals(message.messageAgreenum) : message.messageAgreenum != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageId;
        result = 31 * result + (messageType != null ? messageType.hashCode() : 0);
        result = 31 * result + (messageInfo != null ? messageInfo.hashCode() : 0);
        result = 31 * result + (messageTime != null ? messageTime.hashCode() : 0);
        result = 31 * result + (messageCollectnum != null ? messageCollectnum.hashCode() : 0);
        result = 31 * result + (messageCommentnum != null ? messageCommentnum.hashCode() : 0);
        result = 31 * result + (messageTranspondnum != null ? messageTranspondnum.hashCode() : 0);
        result = 31 * result + (messageAgreenum != null ? messageAgreenum.hashCode() : 0);
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
    @JoinColumn(name = "picture_id", referencedColumnName = "picture_id")
    public Picture getPictureByPictureId() {
        return pictureByPictureId;
    }

    public void setPictureByPictureId(Picture pictureByPictureId) {
        this.pictureByPictureId = pictureByPictureId;
    }
}
