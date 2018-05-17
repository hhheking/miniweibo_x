package pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Relation {
    private int realtionId;
    private Timestamp relationTime;
    private String relationType;
    private User userByUserId;
    private User userByUserByid;

    @Id
    @Column(name = "realtion_id")
    public int getRealtionId() {
        return realtionId;
    }

    public void setRealtionId(int realtionId) {
        this.realtionId = realtionId;
    }

    @Basic
    @Column(name = "relation_time")
    public Timestamp getRelationTime() {
        return relationTime;
    }

    public void setRelationTime(Timestamp relationTime) {
        this.relationTime = relationTime;
    }

    @Basic
    @Column(name = "relation_type")
    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Relation relation = (Relation) o;

        if (realtionId != relation.realtionId) return false;
        if (relationTime != null ? !relationTime.equals(relation.relationTime) : relation.relationTime != null)
            return false;
        if (relationType != null ? !relationType.equals(relation.relationType) : relation.relationType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = realtionId;
        result = 31 * result + (relationTime != null ? relationTime.hashCode() : 0);
        result = 31 * result + (relationType != null ? relationType.hashCode() : 0);
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
    @JoinColumn(name = "user_byid", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserByid() {
        return userByUserByid;
    }

    public void setUserByUserByid(User userByUserByid) {
        this.userByUserByid = userByUserByid;
    }
}
