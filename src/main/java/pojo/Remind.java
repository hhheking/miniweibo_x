package pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Remind {
    private int id;
    private Integer usreId;
    private Integer touserId;
    private Message messageId;
    private String content;
    private Timestamp time;
    private String type;
    private Boolean isnew;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "usre_id")
    public Integer getUsreId() {
        return usreId;
    }

    public void setUsreId(Integer usreId) {
        this.usreId = usreId;
    }

    @Basic
    @Column(name = "touser_id")
    public Integer getTouserId() {
        return touserId;
    }

    public void setTouserId(Integer touserId) {
        this.touserId = touserId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "isnew")
    public Boolean getIsnew() {
        return isnew;
    }

    public void setIsnew(Boolean isnew) {
        this.isnew = isnew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Remind remind = (Remind) o;
        return id == remind.id &&
                Objects.equals(usreId, remind.usreId) &&
                Objects.equals(touserId, remind.touserId) &&
                Objects.equals(content, remind.content) &&
                Objects.equals(time, remind.time) &&
                Objects.equals(type, remind.type) &&
                Objects.equals(isnew, remind.isnew);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, usreId, touserId, messageId, content, time, type, isnew);
    }
    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "message_id", nullable = false)
    public Message getMessageId(){
        return messageId;
    }
   public void setMessageId(Message messageId){
        this.messageId = messageId;
   }
}
