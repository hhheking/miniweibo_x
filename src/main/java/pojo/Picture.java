package pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Picture {
    private int pictureId;
    private String pictureUrl;
    private Timestamp pictureTime;
    private User userByUserId;

    @Id
    @Column(name = "picture_id")
    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    @Basic
    @Column(name = "picture_url")
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Basic
    @Column(name = "picture_time")
    public Timestamp getPictureTime() {
        return pictureTime;
    }

    public void setPictureTime(Timestamp pictureTime) {
        this.pictureTime = pictureTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        if (pictureId != picture.pictureId) return false;
        if (pictureUrl != null ? !pictureUrl.equals(picture.pictureUrl) : picture.pictureUrl != null) return false;
        if (pictureTime != null ? !pictureTime.equals(picture.pictureTime) : picture.pictureTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pictureId;
        result = 31 * result + (pictureUrl != null ? pictureUrl.hashCode() : 0);
        result = 31 * result + (pictureTime != null ? pictureTime.hashCode() : 0);
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
